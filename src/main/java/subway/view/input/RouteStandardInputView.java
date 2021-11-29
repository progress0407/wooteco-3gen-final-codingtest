package subway.view.input;

import static subway.constant.CommonConstant.*;
import static subway.constant.ViewConstant.*;

import java.util.regex.Pattern;

import subway.domain.ViewModel;

public class RouteStandardInputView implements InputView {

	private static final String ROUTE_STANDARD =
		"## 경로 기준" + LINE_SEPARATOR +
			"1. 최단 거리" + LINE_SEPARATOR +
			"2. 최소 시간" + LINE_SEPARATOR +
			"B. 돌아가기" + LINE_SEPARATOR;

	private static final String INPUT_START_STATION = "## 출발역을 입력하세요.";
	private static final String INPUT_LEAVE_STATION = "## 도착역을 입력하세요.";

	public ViewModel getNumber(InputStrategy inputStrategy) {
		System.out.println(ROUTE_STANDARD);
		System.out.println(INPUT_FUNCTION);

		String inputString = inputStrategy.getInput();
		validateInput(inputString);

		char intputChar = inputString.toUpperCase().charAt(0);

		if (intputChar == BACK_SIGNAL) {
			return new ViewModel(intputChar);
		}

		System.out.println(INPUT_START_STATION);
		String sourceStation = inputStrategy.getInput();

		System.out.println(INPUT_LEAVE_STATION);
		String destinationStation = inputStrategy.getInput();

		return new ViewModel(intputChar, sourceStation, destinationStation);
	}

	private void validateInput(String input) {
		String regex = "[1-2Bb]";
		boolean matches = Pattern.matches(regex, input);
		if (!matches) {
			throw new IllegalArgumentException(ERROR_WRONG_INPUT);
		}
	}
}
