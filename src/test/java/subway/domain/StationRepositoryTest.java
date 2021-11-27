package subway.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StationRepositoryTest {

	List<Station> stations;

	@BeforeEach
	void setUp() {
		stations = StationRepository.stations();
	}

	@AfterEach
	void tearDown() {
		StationRepository.initData();
	}

	@Test
	void 역_조회하기() {
		assertThat(stations).contains(
			new Station("교대역"),
			new Station("강남역"),
			new Station("역삼역"),
			new Station("남부터미널역"),
			new Station("양재역"),
			new Station("양재시민의숲역"),
			new Station("매봉역")
		);
	}

	@Test
	void 역_등록하기() {
		StationRepository.addStation(new Station("영등포역"));
		assertThat(stations).contains(new Station("영등포역"));
	}

	@Test
	void 역_삭제하기() {
		StationRepository.deleteStation("매봉역");
		assertThat(stations).doesNotContain(new Station("매봉역"));
	}
}