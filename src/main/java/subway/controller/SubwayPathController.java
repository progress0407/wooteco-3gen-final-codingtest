package subway.controller;

import subway.view.FixedInputStrategy;
import subway.view.InputView;
import subway.view.PlayerInputStrategy;

public class SubwayPathController {

	private static final PlayerInputStrategy playerInputStrategy = new PlayerInputStrategy();
	public void run() {
		char input = InputView.getNumber(playerInputStrategy);
		// OutputView(input);
	}
}
