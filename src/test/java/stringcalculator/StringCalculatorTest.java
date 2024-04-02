package stringcalculator;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringCalculatorTest {

	@ParameterizedTest
	@DisplayName("구분자를 컴마(,) 이외에 콜론(:)을 사용할 수 있다.")
	@CsvSource(value = {"1,2,3|6","1:2:3|6","1:2,3|6","1,2:3,10|16"}, delimiter = '|')
	public void calculateTest(String input, int expected) {
		Assertions.assertThat(StringCalculator.calculate(input)).isEqualTo(expected);
	}
	@Test
	@DisplayName("빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.")
	public void calculateEmptyTest() {
		Assertions.assertThat(StringCalculator.calculate("")).isEqualTo(0);
	}
	@Test
	@DisplayName("빈 문자열 또는 null 값을 입력할 경우 0을 반환해야 한다.")
	public void calculateNullTest() {
		Assertions.assertThat(StringCalculator.calculate(null)).isEqualTo(0);
	}
	@ParameterizedTest
	@DisplayName("음수를 전달할 경우 RuntimeException 예외가 발생해야 한다.")
	@ValueSource(strings = {"1,2,-3","1:2:-3","1:2,-4","1,2:3,-10"} )
	public void negativeNumberTest(String input) {
		Assertions.assertThatThrownBy(() ->StringCalculator.calculate(input)).isInstanceOf(RuntimeException.class);
	}
}
