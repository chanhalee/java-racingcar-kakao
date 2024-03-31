package racingcar;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RacingGameTest {
    RacingGame racingGame;
    List<Car> cars;
    @BeforeEach
    void setUp() {
        cars = new ArrayList<>();
        cars.add(new Car("car1", 0));
        cars.add(new Car("car2", 0));
        cars.add(new Car("car3", 0));
    }

    @Test
    @DisplayName("자동차들 중 가장 많이 이동한 자동차가 우승자가 된다.")
    public void findWinnerTest(){
        Car winner = new Car("win", 3);
        cars.add(winner);
        racingGame = new RacingGame(cars, null, null);
        Assertions.assertThat(racingGame.findWinner()).contains(winner.getState());
    }

    @Test
    @DisplayName("복수의 우승자가 발생할 수 있다.")
    public void findWinnersTest() {
        Car winner1 = new Car("win1", 3);
        cars.add(winner1);
        Car winner2 = new Car("win2", 3);
        cars.add(winner2);
        racingGame = new RacingGame(cars, null, null);
        Assertions.assertThat(racingGame.findWinner()).contains(winner1.getState(), winner2.getState());
    }

}