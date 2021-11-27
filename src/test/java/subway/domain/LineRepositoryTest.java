package subway.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LineRepositoryTest {
	List<Line> lines;

	@BeforeEach
	void setUp() {
		lines = LineRepository.lines();
	}

	@AfterEach
	void tearDown() {
		LineRepository.deleteAll();
	}

	@Test
	void 노선_조회하기() {
		assertThat(lines).contains(
			new Line("2호선"),
			new Line("3호선"),
			new Line("신분당선")
		);
	}
}
