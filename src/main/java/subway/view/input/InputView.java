package subway.view.input;

import subway.domain.ViewModel;

public interface InputView {
	String INPUT_FUNCTION = "## 원하는 기능을 선택하세요.";
	ViewModel getNumber(InputStrategy inputStrategy);
}