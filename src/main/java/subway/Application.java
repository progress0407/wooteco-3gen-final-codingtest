package subway;

import java.util.Scanner;

import subway.controller.SubwayPathController;

public class Application {
	// final Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SubwayPathController subwayPathController = new SubwayPathController();
		subwayPathController.run();
	}
}
