package subway.view.output;

import static subway.constant.CommonConstant.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import subway.domain.Relation;
import subway.domain.RelationRepository;
import subway.domain.ViewModel;

public class RouteStandardOutputView {

	private static final String SEARCH_RESULT = "## 조회 결과";
	private static final Character INPUT_SHORTEST_DISTANCE = '1';
	private static final Character INPUT_SHORTEST_TIME = '2';
	private static final String INFO_LINE = "[INFO] ---";
	private static final String INFO_TOTAL_DISTANCE = "[INFO] 총 거리: ";
	private static final String INFO_TOTAL_TIME = "[INFO] 총 소요 시간: ";
	private static Map<Character, Consumer<ViewModel>> views = new HashMap<>();

	static {
		views.put(INPUT_SHORTEST_DISTANCE, RouteStandardOutputView::printDistance);
		views.put(INPUT_SHORTEST_TIME, RouteStandardOutputView::printTime);
	}

	public void printResult(ViewModel viewModel) {
		views.get(viewModel.getInput()).accept(viewModel);
	}

	private static void printDistance(ViewModel viewModel) {
		Relation relation =
			RelationRepository.getShortestTime(viewModel.getSourceStation(), viewModel.getDestinationStation());
		System.out.println(SEARCH_RESULT);
		System.out.println(INFO_LINE);
		System.out.println(INFO_TOTAL_DISTANCE + relation.getDistanceWeight());
		System.out.println(INFO_TOTAL_TIME + relation.getTimeWeight());
		System.out.println(INFO_LINE + LINE_SEPARATOR);
	}

	private static void printTime(ViewModel viewModel) {
		Relation relation =
			RelationRepository.getShortestDistance(viewModel.getSourceStation(), viewModel.getDestinationStation());
		System.out.println(SEARCH_RESULT);
		System.out.println(INFO_LINE);
		System.out.println(INFO_TOTAL_DISTANCE + relation.getDistanceWeight());
		System.out.println(INFO_TOTAL_TIME + relation.getTimeWeight());
		System.out.println(INFO_LINE + LINE_SEPARATOR);
	}
}
