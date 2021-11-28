package subway.view;

import static subway.constant.Constant.*;

import java.util.regex.Pattern;

public class InputView {

	private static final String MAIN_MENU =
			"## 메인 화면" + LINE_SEPERATOR
			+ "1. 경로 조회" + LINE_SEPERATOR
			+ "Q. 종료" + LINE_SEPERATOR;

	private static final String INPUT_FUNCTION = "## 원하는 기능을 선택하세요.";
	private static final String ERROR_WRONG_INPUT = "[ERROR] 입력값이 올바르지 않습니다.";

	public static Character getNumber(InputStrategy inputStrategy) {
		System.out.println(MAIN_MENU);
		System.out.println(INPUT_FUNCTION);

		String input = inputStrategy.getInput();
		validateInput(input);

		return input.charAt(0);
	}

	private static void validateInput(String input) {
		String regex = "[1qQ]";
		boolean matches = Pattern.matches(regex, input);
		if (!matches) {
			throw new IllegalArgumentException(ERROR_WRONG_INPUT);
		}
	}
}
