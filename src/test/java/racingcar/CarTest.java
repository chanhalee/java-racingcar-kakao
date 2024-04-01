package racingcar;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import racingcar.domain.Car;

public class CarTest {

	@Test
	@DisplayName("자동차가 전진하면 위치가 1 증가한다.")
	void proceedTest() {
		Car car1 = new Car("car1");
		car1.proceed();
		Car car2 = new Car("car1", 1);
		Assertions.assertThat(car1).isEqualTo(car2);
	}

	@ParameterizedTest
	@DisplayName("자동차 이름은 비어있거나 6자리 이상일 수 없다. fail")
	@ValueSource(strings = {"asbsmd", "asdsada", "asdsddd", "123456789", ""})
	public void carCreateFailTestFail(String input) {
		Assertions.assertThatThrownBy(() -> new Car(input))
			.isInstanceOf(RuntimeException.class);
	}

	@ParameterizedTest
	@DisplayName("자동차 이름은 비어있거나 6자리 이상일 수 없다. success")
	@ValueSource(strings = {"asbsm", "a", "ad", "1234", "12345"})
	public void carCreateFailTestSuccess(String input) {
		Assertions.assertThat(new Car(input)).isInstanceOf(Car.class);
	}

}
