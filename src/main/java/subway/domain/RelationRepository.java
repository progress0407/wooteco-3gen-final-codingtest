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

	private static DijkstraShortestPath dijkstraShortestPath;

	static {
		initData();
	}

	private static void initData() {
		addVertex();
		setDistanceEdgeWeight();
		dijkstraShortestPath = new DijkstraShortestPath(distanceRelation);
	}

	private static void setDistanceEdgeWeight() {
		// 2호선
		distanceRelation.setEdgeWeight(distanceRelation.addEdge("교대역", "강남역"), 2);
		distanceRelation.setEdgeWeight(distanceRelation.addEdge("강남역", "역삼역"), 3);
		// 3호선
		distanceRelation.setEdgeWeight(distanceRelation.addEdge("교대역", "남부터미널역"), 3);
		distanceRelation.setEdgeWeight(distanceRelation.addEdge("남부터미널역", "양재역"), 6);
		distanceRelation.setEdgeWeight(distanceRelation.addEdge("양재역", "매봉역"), 1);
		// 신분당선
		distanceRelation.setEdgeWeight(distanceRelation.addEdge("강남역", "양재역"), 2);
		distanceRelation.setEdgeWeight(distanceRelation.addEdge("양재역", "양재시민의숲역"), 10);
	}

	private static void addVertex() {
		List<Station> stations = StationRepository.stations();
		stations.forEach(station -> {
			distanceRelation.addVertex(station.getName());
			timeRelation.addVertex(station.getName());
		});
	}

	public static int getShortestPath(String source, String destination) {
		double weight = dijkstraShortestPath.getPath(source, destination).getWeight();
		return (int) weight;
	}
}
