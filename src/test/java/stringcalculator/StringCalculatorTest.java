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
	@DisplayName("문자열을 입력받아 연산 결과를 반환")
	@CsvSource(value = {"1,2,3|6","1:2:3|6","1:2,3|6","1,2:3,10|16", "1,,3|4","1, ,3|4"}, delimiter = '|')
	public void calculateTest(String input, int expected) {
		Assertions.assertThat(StringCalculator.calculate(input)).isEqualTo(expected);
	}
}
