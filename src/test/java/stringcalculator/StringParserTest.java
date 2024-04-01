package stringcalculator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringParserTest {


	@ParameterizedTest
	@CsvSource(value={"10,2,3|10,2,3", "1:2:3|1,2,3", "1:2,3|1,2,3", "1,2:3,10|1,2,3,10", "1,,3|1,3", "1, ,3|1,3"}, delimiter = '|')
	@DisplayName("default 구분자 테스트")
	public void parseDefaultDelimiterTest(String input, String expected) {
		List<Integer> result = StringParser.parse(input);
		List<Integer> expectedResult = Arrays.stream(expected.split(",")).map(Integer::parseInt).collect(Collectors.toList());
		Assertions.assertThat(result).containsAll(expectedResult);
	}



	// @ParameterizedTest
	// @CsvSource(value={"//;\n10;2;3#10,2,3", "//;\n1,2,3#1,2,3", "//;\n1:2:3#1,2,3", "//;\n1;2:3,4#1,2,3,4"}, delimiter = '#')
	// @DisplayName("custom 구분자 테스트")
	// public void parseCustomDelimiterTest(String input, String expected) {
	// 	List<Integer> result = StringParser.parse(input);
	// 	List<Integer> expectedResult = Arrays.stream(expected.split(",")).map(Integer::parseInt).collect(Collectors.toList());
	// 	Assertions.assertThat(result).containsAll(expectedResult);
	// }

	@Test
	@DisplayName("custom 구분자 테스트")
	public void parseCustomDelimiterTest() {
		String input = "//;\n1;2;3";
		List<Integer> result = StringParser.parse(input);
		Assertions.assertThat(result).containsExactly(1, 2, 3);
		input = "//;\n1,2,3";
		result = StringParser.parse(input);
		Assertions.assertThat(result).containsExactly(1, 2, 3);
		input = "//;\n1:2:3";
		result = StringParser.parse(input);
		Assertions.assertThat(result).containsExactly(1, 2, 3);
		input = "//;\n1;2:3,4";
		result = StringParser.parse(input);
		Assertions.assertThat(result).containsExactly(1, 2, 3, 4);

	}

	@ParameterizedTest
	@ValueSource(strings = {"//n1;2;3", "//,;\n1,2,3", "1 1:2:3", "//\n1;2:3,4", "1,a,3", "1,-2,3"})
	@DisplayName("Error input 테스트")
	public void parseErrorInputTest(String input) {
		Assertions.assertThatThrownBy(() -> StringParser.parse(input))
			.isInstanceOf(RuntimeException.class);
	}
}
