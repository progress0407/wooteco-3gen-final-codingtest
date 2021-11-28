package subway.view;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

class InputViewTest {

	@Test
	void 입력값_예외처리() {
		// 정상
		assertThat(InputView.getNumber(new FixedInputStrategy("1"))).isEqualTo('1');
		assertThat(InputView.getNumber(new FixedInputStrategy("Q"))).isEqualTo('Q');
		assertThat(InputView.getNumber(new FixedInputStrategy("q"))).isEqualTo('q');
		// 예외
		assertThatThrownBy(() -> InputView.getNumber(new FixedInputStrategy("11")));
		assertThatThrownBy(() -> InputView.getNumber(new FixedInputStrategy("2")));
		assertThatThrownBy(() -> InputView.getNumber(new FixedInputStrategy("A")));
	}
}