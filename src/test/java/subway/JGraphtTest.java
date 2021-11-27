package subway;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.WeightedMultigraph;
import org.junit.jupiter.api.Test;

public class JGraphtTest {
	@Test
	public void getDijkstraShortestPath() {
		WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph(DefaultWeightedEdge.class);
		graph.addVertex("v1");
		graph.addVertex("v2");
		graph.addVertex("v3");
		graph.setEdgeWeight(graph.addEdge("v1", "v2"), 2);
		graph.setEdgeWeight(graph.addEdge("v2", "v3"), 2);
		graph.setEdgeWeight(graph.addEdge("v1", "v3"), 100);

		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
		List<String> shortestPath = dijkstraShortestPath.getPath("v3", "v1").getVertexList();

		assertThat(shortestPath.size()).isEqualTo(3);
	}

	@Test
	void getDijkstraShortestPathOtherTest() {
		WeightedMultigraph<String, DefaultWeightedEdge> graph = new WeightedMultigraph<>(DefaultWeightedEdge.class);
		graph.addVertex("a1");
		graph.addVertex("a2");
		graph.addVertex("a3");
		graph.setEdgeWeight(graph.addEdge("a1", "a2"), 3);
		graph.setEdgeWeight(graph.addEdge("a2", "a3"), 3);
		graph.setEdgeWeight(graph.addEdge("a3", "a1"), 10);

		DijkstraShortestPath dijkstraShortestPath = new DijkstraShortestPath(graph);
		List shortestPath = dijkstraShortestPath.getPath("a1", "a3").getVertexList();
		System.out.println("shortestPath = " + shortestPath);

		assertThat(shortestPath.size()).isEqualTo(3);
	}
}
