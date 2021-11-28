package subway.view;

import java.util.Scanner;

public class PlayerInputStrategy implements InputStrategy {

	private static final Scanner scanner = new Scanner(System.in);

	@Override
	public String getInput() {
		return scanner.nextLine();
	}
}
