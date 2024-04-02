package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import racingcar.domain.Car;
import racingcar.domain.ErrorType;
import racingcar.domain.InputValidator;
import racingcar.domain.NumberGenerator;
import racingcar.domain.ProceedLogic;
import racingcar.dto.CarState;
import racingcar.view.RacingGameUI;

public class RacingGameController {

    private int rounds;

    private final List<Car> cars;
    private final RacingGameUI ui;
    private final ProceedLogic logic;
    private final InputValidator validator;

    public RacingGameController(RacingGameUI ui, NumberGenerator numberGenerator) {
        this(new ArrayList<>(), ui, new ProceedLogic(numberGenerator));
    }

    public RacingGameController(List<Car> cars, RacingGameUI ui, ProceedLogic logic) {
        this.cars = cars;
        this.ui = ui;
        this.logic = logic;
        this.validator = new InputValidator(this);
    }

    public void play() {
        initialize();

        ui.printResultHeader();
        for (int i = 0; i < rounds; i++) {
            playRound();
            ui.printCarStates(cars.stream().map(Car::getState).collect(Collectors.toList()));
        }

        ui.printWinners(this.findWinner());
    }
    private void initialize() {
        initCars();
        initRounds();
    }

    private void initRounds() {
        boolean validationPass = false;
        String input = "";
        while(!validationPass){
            input = ui.getRounds();
            validationPass = validator.validateRounds(input);
        }
        rounds = Integer.parseInt(input);
    }

    private void initCars() {
        boolean validationPass = false;
        List<String> carNames = null;
        while (!validationPass) {
            String carNameString = ui.getCarNameString();
            carNames = Arrays.stream(carNameString.split(",")).map(String::trim).collect(Collectors.toList());
            validationPass = validator.validateCarNames(carNames);
        }

        for (String name : carNames) {
            cars.add(new Car(name));
        }
    }
    private void playRound() {
        cars.stream()
            .filter(car -> logic.askProceed())
            .forEach(Car::proceed);
    }

    public List<CarState> findWinner() {
        Collections.sort(cars);
        Car winner = cars.get(0);
        return cars.stream()
            .filter(car -> winner.compareTo(car) == 0)
            .map(Car::getState)
            .collect(Collectors.toList());
    }

    public void printError(ErrorType errorType) {
		ui.printError(errorType);
	}
}
