package subway.view;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import subway.view.input.FixedInputStrategy;
import subway.view.input.MainInputView;

class InputViewTest {

	MainInputView mainInputView;

	@BeforeEach
	void setUp() {
		mainInputView = new MainInputView();
	}

	@Test
	void 입력값_예외처리() {
		// 정상
		assertThat(mainInputView.getNumber(new FixedInputStrategy("1"))).isEqualTo('1');
		assertThat(mainInputView.getNumber(new FixedInputStrategy("Q"))).isEqualTo('Q');
		assertThat(mainInputView.getNumber(new FixedInputStrategy("q"))).isEqualTo('q');
		// 예외
		assertThatThrownBy(() -> mainInputView.getNumber(new FixedInputStrategy("11")));
		assertThatThrownBy(() -> mainInputView.getNumber(new FixedInputStrategy("2")));
		assertThatThrownBy(() -> mainInputView.getNumber(new FixedInputStrategy("A")));
	}
}