package racingcar.domain;

import java.util.Random;

import racingcar.domain.NumberGenerator;

public class RandomNumberGenerator implements NumberGenerator {

    private final Random random = new Random();
    @Override
    public int generate() {
		return random.nextInt(10);
	}
}
