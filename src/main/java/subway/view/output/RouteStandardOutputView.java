package subway.view.output;

import static subway.constant.CommonConstant.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import subway.domain.Relation;
import subway.domain.RelationRepository;
import subway.domain.ViewModel;

public class RouteStandardOutputView {

	private static final String SEARCH_RESULT = "## 조회 결과";
	private static final Character INPUT_SHORTEST_DISTANCE = '1';
	private static final Character INPUT_SHORTEST_TIME = '2';
	private static final String INFO = "[INFO] ";
	private static final String INFO_LINE = INFO + "---";
	private static final String INFO_TOTAL_DISTANCE = INFO + "총 거리: ";
	private static final String INFO_TOTAL_TIME = INFO + "총 소요 시간: ";
	private static Map<Character, Consumer<ViewModel>> views = new HashMap<>();
	private static final String ERROR_DUPLICATES_STATIONS = "[ERROR] 출발역과 도착역이 동일합니다.";;

	static {
		views.put(INPUT_SHORTEST_DISTANCE, RouteStandardOutputView::printDistance);
		views.put(INPUT_SHORTEST_TIME, RouteStandardOutputView::printTime);
	}

	public void printResult(ViewModel viewModel) {
		views.get(viewModel.getInput()).accept(viewModel);
	}

	private static void printDistance(ViewModel viewModel) {
		String sourceStation = viewModel.getSourceStation();
		String destinationStation = viewModel.getDestinationStation();
		validateStations(sourceStation, destinationStation);
		Relation relation = RelationRepository.getShortestTime(sourceStation, destinationStation);
		System.out.println(SEARCH_RESULT);
		System.out.println(INFO_LINE);
		System.out.println(INFO_TOTAL_DISTANCE + relation.getDistanceWeight());
		System.out.println(INFO_TOTAL_TIME + relation.getTimeWeight());
		System.out.println(INFO_LINE);
		List<String> stations = RelationRepository.getStationsByDistance(sourceStation, destinationStation);
		stations.forEach(station -> System.out.println(INFO + station));
		System.out.println();
	}

	private static void validateStations(String sourceStation, String destinationStation) {
		if (sourceStation.equals(destinationStation)) {
			throw new IllegalArgumentException(ERROR_DUPLICATES_STATIONS);
		}
	}

	private static void printTime(ViewModel viewModel) {
		String sourceStation = viewModel.getSourceStation();
		String destinationStation = viewModel.getDestinationStation();
		validateStations(sourceStation, destinationStation);
		Relation relation =
			RelationRepository.getShortestDistance(sourceStation, destinationStation);
		System.out.println(SEARCH_RESULT);
		System.out.println(INFO_LINE);
		System.out.println(INFO_TOTAL_DISTANCE + relation.getDistanceWeight());
		System.out.println(INFO_TOTAL_TIME + relation.getTimeWeight());
		System.out.println(INFO_LINE);
		List<String> stations = RelationRepository.getStationsByTime(sourceStation, destinationStation);
		stations.forEach(station -> System.out.println(INFO + station));
		System.out.println();
	}
}
