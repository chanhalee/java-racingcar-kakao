package stringcalculator;


import java.util.List;

public class StringCalculator {
	public static int calculate(String input) {
		List<Integer> integers = StringParser.parse(input);
		int sum = 0;
		for (Integer i : integers) {
			sum += i;
		}
		return sum;
	}
}
