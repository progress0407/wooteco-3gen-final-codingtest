package subway.view.input;

public class FixedInputStrategy implements InputStrategy {

	String input;

	public FixedInputStrategy(String input) {
		this.input = input;
	}

	@Override
	public String getInput() {
		return input;
	}
}
