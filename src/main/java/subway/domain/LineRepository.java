package subway.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LineRepository {
	private static final List<Line> lines = new ArrayList<>();

	/*
	교대역, 강남역, 역삼역, 남부터미널역, 양재역, 양재시민의숲역, 매봉역
	 */
	static {
		addLine(new Line("교대역"));
		addLine(new Line("강남역"));
		addLine(new Line("역삼역"));
		addLine(new Line("남부터미널역"));
		addLine(new Line("양재역"));
		addLine(new Line("양재시민의숲역"));
		addLine(new Line("매봉역"));
	}

	public static List<Line> lines() {
		return Collections.unmodifiableList(lines);
	}

	public static void addLine(Line line) {
		lines.add(line);
	}

	public static boolean deleteLineByName(String name) {
		return lines.removeIf(line -> Objects.equals(line.getName(), name));
	}

	public static void deleteAll() {
		lines.clear();
	}
}
