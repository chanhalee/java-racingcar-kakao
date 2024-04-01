package stringcalculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringParser {
	private static final String DEFAULT_DELIMITER = ":|,";
	private static final Pattern pattern = Pattern.compile("//(.)\n(.*)");
	public static List<Integer> parse(String input) {
		String delimiter = DEFAULT_DELIMITER;
		Matcher matcher = pattern.matcher(input);
		if (matcher.find()) {
			delimiter += "|" + matcher.group(1);
			input = matcher.group(2);
		}

		List<String> numbers = Arrays.stream(input.split(delimiter)).map(String::trim).collect(Collectors.toList());
		return tokenize(numbers);
	}

	private static List<Integer> tokenize(List<String> numbers) {
		List<Integer> result = new ArrayList<>();
		for (String number : numbers) {
			number = convertBlank(number);

			int parsedInt = Integer.parseInt(number);
			checkPositive(parsedInt);

			result.add(parsedInt);
		}
		return result;
	}

	private static void checkPositive(int parsedInt) {
		if (parsedInt < 0) {
			throw new RuntimeException();
		}
	}

	private static String convertBlank(String number) {
		if (number == null || number.isEmpty()) {
			return "0";
		}
		return number;
	}

}
