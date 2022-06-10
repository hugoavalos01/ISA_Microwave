package microwave;

public class Heating {

	private boolean heating = false;
	private int power = 0;

	void heatingOn() {
		heating = true;
	}

	void heatingOff() {
		heating = false;
	}

	void setPower(int p) {
		if (p >= 0) {
			power = p;
		}
	}

	int getPower() {
		return power;
	}

	public boolean isHeating() {
		return heating;
	}
}
