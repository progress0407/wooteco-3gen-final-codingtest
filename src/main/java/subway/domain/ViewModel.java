package subway.domain;

public class ViewModel {
	private final char input;
	private final String sourceStation;
	private final String destinationStation;

	public ViewModel(char input) {
		this.input = input;
		this.sourceStation = null;
		this.destinationStation = null;
	}

	public ViewModel(char input, String sourceStation, String destinationStation) {
		this.input = input;
		this.sourceStation = sourceStation;
		this.destinationStation = destinationStation;
	}

	public char getInput() {
		return input;
	}

	public String getSourceStation() {
		return sourceStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}
}
