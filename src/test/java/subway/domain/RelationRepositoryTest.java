package subway.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RelationRepositoryTest {

	@Test
	void 최단경로_조회() {
		String source = "교대역";
		String destination = "양재역";
		Relation relation = RelationRepository.getShortestDistance(source, destination);
		int distanceWeight = relation.getDistanceWeight();
		int timeWeight = relation.getTimeWeight();
		assertThat(distanceWeight).isEqualTo(4);
		assertThat(timeWeight).isEqualTo(11);
	}

/*
	@Test
	void 최단시간_조회() {
		String source = "교대역";
		String destination = "양재역";
		int shortestTime = RelationRepository.getShortestTime(source, destination);
		assertThat(shortestTime).isEqualTo(11);
	}
*/
}