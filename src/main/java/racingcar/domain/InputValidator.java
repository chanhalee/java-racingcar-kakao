package racingcar.domain;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import racingcar.RacingGameController;

public class InputValidator {

    private final RacingGameController controller;

    public InputValidator(RacingGameController racingGameController) {
        this.controller = racingGameController;
    }

    public boolean validateRounds(String input) {
        String round = input.trim();

        return notEmptyRound(round) && notTooLongRound(round) && isNumericalRound(round);
    }

    public boolean validateCarNames(List<String> carNameList) {
        return notEmptyCarName(carNameList) && notTooLongCarName(carNameList) && isUniqueCarName(carNameList);
    }

    private boolean notEmptyRound(String round) {
        if (round.isEmpty()) {
            controller.printError(ErrorType.EMPTY_ROUND);
            return false;
        }
        return true;
    }

    private boolean notTooLongRound(String round) {
        if (round.length() > 5) {
            controller.printError(ErrorType.TOO_LONG_ROUND);
            return false;
        }
        return true;
    }

    private boolean isNumericalRound(String round) {
        if (Arrays.stream(round.split("")).anyMatch(s -> !Character.isDigit(s.charAt(0)))){
            controller.printError(ErrorType.NON_NUMERICAL_ROUND);
            return false;
        }
        return true;
    }

    private boolean notEmptyCarName(List<String> carNameList) {
        if (carNameList.isEmpty() || carNameList.stream().anyMatch(String::isEmpty)) {
            controller.printError(ErrorType.EMPTY_CAR_NAME);
            return false;
        }
        return true;
    }

    private boolean notTooLongCarName(List<String> carNameList) {
        if (carNameList.stream().anyMatch(name -> name.length() > 5)) {
            controller.printError(ErrorType.TOO_LONG_CAR_NAME);
            return false;
        }
        return true;
    }

    private boolean isUniqueCarName(List<String> carNameList) {
        Set<String> uniqueName = new HashSet<>(carNameList);
        if (uniqueName.size() != carNameList.size()) {
            controller.printError(ErrorType.DUPLICATED_CAR_NAME);
            return false;
        }
        return true;
    }

}
