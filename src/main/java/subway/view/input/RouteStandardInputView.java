package subway.view.input;

import static subway.constant.CommonConstant.*;
import static subway.constant.ViewConstant.*;

import java.util.regex.Pattern;

public class RouteStandardInputView implements InputView {

	private static final String ROUTE_STANDARD =
		"## 경로 기준" + LINE_SEPERATOR +
			"1. 최단 거리" + LINE_SEPERATOR +
			"2. 최소 시간" + LINE_SEPERATOR +
			"B. 돌아가기" + LINE_SEPERATOR;

	public char getNumber(InputStrategy inputStrategy) {
		System.out.println(ROUTE_STANDARD);
		System.out.println(INPUT_FUNCTION);

		String input = inputStrategy.getInput();
		validateInput(input);
		return input.toUpperCase().charAt(0);
	}

	private void validateInput(String input) {
		String regex = "[1-2Bb]";
		boolean matches = Pattern.matches(regex, input);
		if (!matches) {
			throw new IllegalArgumentException(ERROR_WRONG_INPUT);
		}
	}
}
