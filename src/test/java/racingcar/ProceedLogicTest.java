package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("전진 여부를 판단하는 클래스 테스트")
public class ProceedLogicTest {

	@Test
	@DisplayName("4이상의 값에 대해 전진 판정을 내린다.")
	void askProceedTrueTest() {
		ProceedLogic proceedLogic = new ProceedLogic(() -> 4);
		boolean result = proceedLogic.askProceed();
		Assertions.assertThat(result).isTrue();
	}

	@Test
	@DisplayName("3이하의 값에 대해 전진 불가 판정을 내린다.")
	void askProceedFalseTest() {
		ProceedLogic proceedLogic = new ProceedLogic(() -> 3);
		boolean result = proceedLogic.askProceed();
		Assertions.assertThat(result).isFalse();
	}

}
