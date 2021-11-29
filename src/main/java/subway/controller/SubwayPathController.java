package subway.controller;

import static subway.constant.ViewConstant.*;

import subway.view.input.MainInputView;
import subway.view.output.OutputView;
import subway.view.input.PlayerInputStrategy;

public class SubwayPathController {
	private static final PlayerInputStrategy playerInputStrategy = new PlayerInputStrategy();
	private static final MainInputView MAIN_INPUT_VIEW = new MainInputView();

	public void run() {
		boolean isContinuous;
		do {
			isContinuous = play();
		} while (isContinuous);
	}

	private boolean play() {
		char input = MAIN_INPUT_VIEW.getNumber(playerInputStrategy);
		if (input == EXIT_SIGNAL) {
			return false;
		}
		OutputView.printResult(input);
		return true;
	}

}
