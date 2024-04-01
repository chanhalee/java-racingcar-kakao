package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RandomNumberGeneratorTest {

	@Test
	@DisplayName("0 ~ 9 사이의 정수를 반환한다")
	void generateTest() {
		NumberGenerator numberGenerator = new RandomNumberGenerator();
		int num = numberGenerator.generate();
		Assertions.assertThat(num).isBetween(0, 9);
	}
}
