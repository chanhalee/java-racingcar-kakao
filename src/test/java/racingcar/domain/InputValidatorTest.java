package racingcar.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import racingcar.RacingGameController;
import racingcar.view.RacingGameUI;

class InputValidatorTest {
    private InputValidator inputValidator;
    @BeforeEach
    void setUp() {
        RacingGameController game = new RacingGameController(
            new RacingGameUI(),
            new RandomNumberGenerator()
        );
        inputValidator = new InputValidator(game);
    }

    @ParameterizedTest
    @DisplayName("자동차 이름은 중복되어선 안된다. fail")
    @ValueSource(strings = {"a, a", "a, ab, abc, a", "a, ab, abc, a, ab", "1, 123,1"})
    void validateCarNamesDuplicatedFail(String input) {
        List<String> tokenizedInput = Arrays.stream(input.split(",")).map(String::trim).collect(Collectors.toList());
        Assertions.assertThat(inputValidator.validateCarNames(tokenizedInput)).isFalse();
    }

    @ParameterizedTest
    @DisplayName("자동차 이름은 비어있거나 6자리 이상일 수 없다. fail")
    @ValueSource(strings = {"", "asdfasd,a", "a,b,asdfas,bb"})
    void validateCarNamesLengthFail(String input) {
        List<String> tokenizedInput = Arrays.stream(input.split(",")).map(String::trim).collect(Collectors.toList());
        Assertions.assertThat(inputValidator.validateCarNames(tokenizedInput)).isFalse();
    }
    @ParameterizedTest
    @DisplayName("자동차 이름은 중복되어선 안된다. 자동차 이름은 비어있거나 6자리 이상일 수 없다. pass")
    @ValueSource(strings = {"a, c", "a, ab, abc", "a, ab, abc, abcd", "1, 123,112"})
    void validateCarNamesDuplicatedPass(String input) {
        List<String> tokenizedInput = Arrays.stream(input.split(",")).map(String::trim).collect(Collectors.toList());
        Assertions.assertThat(inputValidator.validateCarNames(tokenizedInput)).isTrue();
    }

    @ParameterizedTest
    @DisplayName("이동 횟수는 최대 5자리 정수를 입력할 수 있다. pass")
    @ValueSource(strings = {"0", "00", "1", "12", "012", "12321", "10"})
    void validateRoundPass(String input) {
        Assertions.assertThat(inputValidator.validateRounds(input)).isTrue();
    }
    @ParameterizedTest
    @DisplayName("이동 횟수는 최대 5자리 정수를 입력할 수 있다. fail")
    @ValueSource(strings = {"", "a", "1L", "-1", "!", "124561"})
    void validateRoundFail(String input) {
        Assertions.assertThat(inputValidator.validateRounds(input)).isFalse();
    }
}