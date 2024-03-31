package racingcar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import racingcar.dto.CarState;

public class RacingGame {

	private int rounds;

	private final List<Car> cars;
	private final RacingGameUI ui;
	private final ProceedLogic logic;

	public RacingGame(RacingGameUI ui, NumberGenerator numberGenerator) {
		this(new ArrayList<>(), ui, new ProceedLogic(numberGenerator));
	}

	public RacingGame(List<Car> cars, RacingGameUI ui, ProceedLogic logic) {
		this.cars = cars;
		this.ui = ui;
		this.logic = logic;
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
			validationPass = validateRounds(input);
		}
		rounds = Integer.parseInt(input);
	}

	private void initCars() {
		boolean validationPass = false;
		List<String> carNames = null;
		while (!validationPass) {
			String carNameString = ui.getCarNameString();
			carNames = Arrays.stream(carNameString.split(",")).map(String::trim).collect(Collectors.toList());
			validationPass = validateCarNames(carNames);
		}

		for (String name : carNames) {
			cars.add(new Car(name));
		}
	}

	private boolean validateRounds(String input) {
		String round = input.trim();

		return notEmptyRound(round) && notTooLongRound(round) && isNumericalRound(round);
	}

	private boolean validateCarNames(List<String> carNameList) {
		return notEmptyCarName(carNameList) && notTooLongCarName(carNameList) && isUniqueCarName(carNameList);
	}

	private boolean notEmptyRound(String round) {
		if (round.isEmpty()) {
			ui.printError(ErrorType.EMPTY_ROUND);
			return false;
		}
		return true;
	}

	private boolean notTooLongRound(String round) {
		if (round.length() > 5) {
			ui.printError(ErrorType.TOO_LONG_ROUND);
			return false;
		}
		return true;
	}

	private boolean isNumericalRound(String round) {
		if (Arrays.stream(round.split("")).anyMatch(s -> !Character.isDigit(s.charAt(0)))){
			ui.printError(ErrorType.NON_NUMERICAL_ROUND);
			return false;
		}
		return true;
	}

	private boolean notEmptyCarName(List<String> carNameList) {
		if (carNameList.isEmpty() || carNameList.stream().anyMatch(String::isEmpty)) {
			ui.printError(ErrorType.EMPTY_CAR_NAME);
			return false;
		}
		return true;
	}

	private boolean notTooLongCarName(List<String> carNameList) {
		if (carNameList.stream().anyMatch(name -> name.length() > 5)) {
			ui.printError(ErrorType.TOO_LONG_CAR_NAME);
			return false;
		}
		return true;
	}

	private boolean isUniqueCarName(List<String> carNameList) {
		Set<String> uniqueName = new HashSet<>(carNameList);
		if (uniqueName.size() != carNameList.size()) {
			ui.printError(ErrorType.DUPLICATED_CAR_NAME);
			return false;
		}
		return true;
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
}
