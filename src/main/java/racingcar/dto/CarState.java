package racingcar.dto;

import java.util.Objects;

public class CarState {

    private final int position;
    private final String name;

    public CarState(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
		return name;
	}

    public int getPosition() {
		return position;
	}

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        CarState carState = (CarState)other;
        return position == carState.position && Objects.equals(name, carState.name);
    }

}
