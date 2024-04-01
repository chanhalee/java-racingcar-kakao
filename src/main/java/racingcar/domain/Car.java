package racingcar.domain;

import java.util.Objects;
import racingcar.dto.CarState;

public class Car implements Comparable<Car>{

	private int position;

	private final String name;

	public Car(String name) {
		this(name, 0);
	}

	public Car(String name, int position) {
		this.name = name;
		if (name.length() >= 6) {
			throw new RuntimeException("자동차 이름의 길이가 너무 긺니다.");
		}
		if (name.isEmpty()) {
			throw new RuntimeException("자동차 이름은 공백이거나 빈 문자열 일 수 없습니다.");
		}
		this.position = position;
	}

	public CarState getState() {
		return new CarState(this.name, this.position);
	}

	public void proceed() {
		this.position += 1;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Car car = (Car)o;
		return position == car.position && Objects.equals(name, car.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, position);
	}

	@Override
	public int compareTo(Car other) {
		return other.position - this.position;
	}
}
