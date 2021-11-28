package subway.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class RelationRepository {

	private static final List<Relation> relations = new ArrayList<>();

	private static WeightedMultigraph<String, DefaultWeightedEdge> distanceRelation =
		new WeightedMultigraph<>(DefaultWeightedEdge.class);

	private static WeightedMultigraph<String, DefaultWeightedEdge> timeRelation =
		new WeightedMultigraph<>(DefaultWeightedEdge.class);

	private static DijkstraShortestPath dijkstraShortestDistance;
	private static DijkstraShortestPath dijkstraShortestTime;

	static {
		initData();
	}

	private static void initData() {
		deleteAll();
		addRelations();
		addVertex();
		addDistanceEdgeWeight();
		addTimeEdgeWeight();
	}

	private static void addRelations() {
		relations.addAll(Arrays.asList(
			new Relation("2호선", "교대역", "강남역", 2, 3),
			new Relation("2호선", "강남역", "역삼역", 2, 3),
			new Relation("3호선", "교대역", "남부터미널역", 3, 2),
			new Relation("3호선", "남부터미널역", "양재역", 6, 5),
			new Relation("3호선", "양재역", "매봉역", 1, 1),
			new Relation("신분당선", "강남역", "양재역", 2, 8),
			new Relation("신분당선", "양재역", "양재시민의숲역", 10, 3)
		));
	}

	private static void addVertex() {
		List<Station> stations = StationRepository.stations();
		stations.forEach(station -> {
			distanceRelation.addVertex(station.getName());
			timeRelation.addVertex(station.getName());
		});
	}

	private static void addDistanceEdgeWeight() {
		for (Relation relation : relations) {
			distanceRelation.setEdgeWeight(
				distanceRelation.addEdge(relation.getStationName(), relation.getOpponentStationName()),
				relation.getDistanceWeight()
			);
		}
		dijkstraShortestDistance = new DijkstraShortestPath(distanceRelation);
	}

	private static void addTimeEdgeWeight() {
		for (Relation relation : relations) {
			timeRelation.setEdgeWeight(
				timeRelation.addEdge(relation.getStationName(), relation.getOpponentStationName()),
				relation.getTimeWeight()
			);
		}
		dijkstraShortestTime = new DijkstraShortestPath(timeRelation);
	}

	public static Relation getShortestDistance(String source, String destination) {

		GraphPath graphPath = dijkstraShortestDistance.getPath(source, destination);
		double distanceShortest = graphPath.getWeight();
		List<DefaultWeightedEdge> edgeList = graphPath.getEdgeList();

		double timeTotal = 0;
		for (DefaultWeightedEdge edge : edgeList) {
			int timeWeight = getTimeWeightByEdge(edge);
			timeTotal += timeWeight;
		}
		return new Relation(distanceShortest, timeTotal);
	}

	public static Relation getShortestTime(String source, String destination) {
		GraphPath graphPath = dijkstraShortestTime.getPath(source, destination);
		double shortestTime = graphPath.getWeight();
		List<DefaultWeightedEdge> edgeList = graphPath.getEdgeList();

		double distanceTotal = 0;
		for (DefaultWeightedEdge edge : edgeList) {
			int distanceWeight = getDistanceWeightByEdge(edge);
			distanceTotal += distanceWeight;
		}
		return new Relation(distanceTotal, shortestTime);
	}

	private static int getTimeWeightByEdge(DefaultWeightedEdge edge) {
		Relation relation = getStationNamesByEdge(edge);
		return relations.stream()
			.filter(e ->
				e.getStationName().equals(relation.getStationName())
					&& e.getOpponentStationName().equals(relation.getOpponentStationName()))
			.map(Relation::getTimeWeight)
			.findAny()
			.get();
	}

	private static int getDistanceWeightByEdge(DefaultWeightedEdge edge) {
		Relation relation = getStationNamesByEdge(edge);
		return relations.stream()
			.filter(e ->
				e.getStationName().equals(e.getStationName())
					&& e.getOpponentStationName().equals(relation.getOpponentStationName()))
			.map(Relation::getDistanceWeight)
			.findAny()
			.get();
	}

	private static Relation getStationNamesByEdge(DefaultWeightedEdge edge) {
		String[] split = edge.toString().replaceAll("[()]", "").split(" : ");
		String stationName = split[0];
		String opponentStationName = split[1];
		return new Relation(stationName, opponentStationName);
	}

	private static void deleteAll() {
		relations.clear();
	}
}
