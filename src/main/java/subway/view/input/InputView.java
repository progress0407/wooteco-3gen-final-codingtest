package subway.view.input;

public interface InputView {
	String INPUT_FUNCTION = "## 원하는 기능을 선택하세요.";
	char getNumber(InputStrategy inputStrategy);
}