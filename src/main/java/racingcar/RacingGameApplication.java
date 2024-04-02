package racingcar;

import racingcar.domain.RandomNumberGenerator;
import racingcar.view.RacingGameUI;

public class RacingGameApplication {

    public static void main(String[] args) {
        RacingGameController game = new RacingGameController(
            new RacingGameUI(),
            new RandomNumberGenerator()
        );

        game.play();
    }
}
