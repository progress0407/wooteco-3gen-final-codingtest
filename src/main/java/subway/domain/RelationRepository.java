package subway.domain;

import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;

public class RelationRepository {

	private static WeightedMultigraph<String, DefaultWeightedEdge> distanceRelation = new WeightedMultigraph<>(
		DefaultWeightedEdge.class);
	private static WeightedMultigraph<String, DefaultWeightedEdge> timeRelation = new WeightedMultigraph<>(
		DefaultWeightedEdge.class);

	private static DijkstraShortestPath dijkstraShortestDistance;
	private static DijkstraShortestPath dijkstraShortestTime;

	static {
		initData();
	}

	private static void initData() {
		addVertex();
		setDistanceEdgeWeight();
		setTimeEdgeWeight();
	}

	private static void setDistanceEdgeWeight() {
		// 2호선
		addEdgeWeight("교대역", "강남역", 2);
		addEdgeWeight("강남역", "역삼역", 3);
		// 3호선
		addEdgeWeight("교대역", "남부터미널역", 3);
		addEdgeWeight("남부터미널역", "양재역", 6);
		addEdgeWeight("양재역", "매봉역", 1);
		// 신분당선
		addEdgeWeight("강남역", "양재역", 2);
		addEdgeWeight("양재역", "양재시민의숲역", 10);
		dijkstraShortestDistance = new DijkstraShortestPath(distanceRelation);
	}

	private static void setTimeEdgeWeight() {
		// 2호선
		addTimeEdgeWeight("교대역", "강남역", 3);
		addTimeEdgeWeight("강남역", "역삼역", 3);
		// 3호선
		addTimeEdgeWeight("교대역", "남부터미널역", 2);
		addTimeEdgeWeight("남부터미널역", "양재역", 5);
		addTimeEdgeWeight("양재역", "매봉역", 1);
		// 신분당선
		addTimeEdgeWeight("강남역", "양재역", 8);
		addTimeEdgeWeight("양재역", "양재시민의숲역", 3);
		dijkstraShortestTime = new DijkstraShortestPath(timeRelation);
	}

	private static void addTimeEdgeWeight(String sourceStation, String destinationStation, int timeWeight) {
		timeRelation.setEdgeWeight(timeRelation.addEdge(sourceStation, destinationStation), timeWeight);
	}

	private static void addEdgeWeight(String sourceStation, String destinationStation, int distanceWeight) {
		distanceRelation.setEdgeWeight(distanceRelation.addEdge(sourceStation, destinationStation), distanceWeight);
	}

	private static void addVertex() {
		List<Station> stations = StationRepository.stations();
		stations.forEach(station -> {
			distanceRelation.addVertex(station.getName());
			timeRelation.addVertex(station.getName());
		});
	}

	public static int getShortestDistance(String source, String destination) {
		double weight = dijkstraShortestDistance.getPath(source, destination).getWeight();
		return (int) weight;
	}

	public static int getShortestTime(String source, String destination) {
		double weight = dijkstraShortestTime.getPath(source, destination).getWeight();
		return (int) weight;
	}
}
