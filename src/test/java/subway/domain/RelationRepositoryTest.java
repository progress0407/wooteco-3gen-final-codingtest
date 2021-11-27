package subway.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RelationRepositoryTest {

	@Test
	void 최단경로_조회() {
		String source = "교대역";
		String destination = "양재역";
		int shortestPath = RelationRepository.getShortestPath(source, destination);
		assertThat(shortestPath).isEqualTo(4);
	}
}