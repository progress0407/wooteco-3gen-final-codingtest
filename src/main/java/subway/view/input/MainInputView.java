package subway.view.input;

import static subway.constant.CommonConstant.*;
import static subway.constant.ViewConstant.*;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import subway.domain.ViewModel;

public class MainInputView implements InputView {

	private static final String MAIN_MENU =
		"## 메인 화면" + LINE_SEPARATOR
			+ "1. 경로 조회" + LINE_SEPARATOR
			+ "Q. 종료" + LINE_SEPARATOR;

	private static final Map<Character, Supplier<InputView>> views = new HashMap<>();

	static {
		views.put(ROUTE_STANDARD, RouteStandardInputView::new);
		views.put(EXIT_SIGNAL, () -> null);
	}

	public ViewModel getNumber(InputStrategy inputStrategy) {
		ViewModel viewModel;
		do {
			viewModel = displayMainMenu(inputStrategy);
			if (viewModel.getInput() == EXIT_SIGNAL) {
				return new ViewModel(EXIT_SIGNAL);
			}
		} while (viewModel.getInput() == BACK_SIGNAL); // 돌아가기의 경우 계속 메인 메뉴로 돌아간다

		return viewModel;
	}

	private ViewModel displayMainMenu(InputStrategy inputStrategy) {
		System.out.println(MAIN_MENU);
		System.out.println(INPUT_FUNCTION);

		String input = inputStrategy.getInput();
		validateInput(input);

		char inputMenu = input.toUpperCase().charAt(0);

		if (inputMenu == EXIT_SIGNAL) {
			return new ViewModel(EXIT_SIGNAL);
		}

		InputView inputView = views.get(inputMenu).get();

		return inputView.getNumber(inputStrategy);
	}

	private static void validateInput(String input) {
		String regex = "[1qQ]";
		boolean matches = Pattern.matches(regex, input);
		if (!matches) {
			throw new IllegalArgumentException(ERROR_WRONG_INPUT);
		}
	}
}
