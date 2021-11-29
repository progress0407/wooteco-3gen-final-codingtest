package subway.controller;

import static subway.constant.ViewConstant.*;

import subway.domain.ViewModel;
import subway.view.input.MainInputView;
import subway.view.input.PlayerInputStrategy;
import subway.view.output.RouteStandardOutputView;

public class SubwayPathController {
	private static final PlayerInputStrategy playerInputStrategy = new PlayerInputStrategy();
	private final MainInputView mainInputView = new MainInputView();
	private final RouteStandardOutputView routeStandardOutput = new RouteStandardOutputView();

	public void run() {
		boolean isContinuous;
		do {
			isContinuous = play();
		} while (isContinuous);
	}

	private boolean play() {
		ViewModel viewModel = mainInputView.getNumber(playerInputStrategy);
		if (viewModel.getInput() == EXIT_SIGNAL) {
			return false;
		}
		routeStandardOutput.printResult(viewModel);
		return true;
	}
}