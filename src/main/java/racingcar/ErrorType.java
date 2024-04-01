package racingcar;

public enum ErrorType {
	EMPTY_CAR_NAME("자동차 이름은 공백일 수 없습니다."),
	TOO_LONG_CAR_NAME("자동차 이름은 5글자를 넘을 수 없습니다."),
	DUPLICATED_CAR_NAME("자동차 이름은 중복될 수 없습니다."),
	EMPTY_ROUND("라운드는 공백일 수 없습니다."),
	TOO_LONG_ROUND("라운드는 여섯 글자를 넘을 수 없습니다."),
	NON_NUMERICAL_ROUND("라운드는 숫자로만 이루어져야 합니다.");

	private final String message;
	ErrorType(String message) {
		this.message = message;
	}

	public String getMessage() {
		return this.message;
	}
}
