package racingcar;

public class ProceedLogic {
	private final NumberGenerator numberGenerator;
	public ProceedLogic(NumberGenerator numberGenerator) {
		this.numberGenerator = numberGenerator;
	}

	public boolean askProceed() {
		return numberGenerator.generate() > 3;
	}
}
