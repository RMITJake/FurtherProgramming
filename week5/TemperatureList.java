import java.util.ArrayList;
import java.util.Collections;

public class TemperatureList {

	private final static int MIN_TEMP = -80;
	private final static int MAX_TEMP = 55;

	private ArrayList<Integer> temps;

	public TemperatureList() {
		this.temps = new ArrayList<Integer>();
	}

	public void setTemps(ArrayList<Integer> temps) {
		this.temps = temps;
	}

	public ArrayList<Integer> getTemps() {
		return temps;
	}

	public void addTemp(int temp) throws IllegalArgumentException {
		if (temp < MIN_TEMP || temp > MAX_TEMP) {
			throw new IllegalArgumentException("Invalid temperature");
		} else {
			temps.add(temp);
		}
	}

	public int getMinTemp() {
		if (temps.isEmpty()) {
			throw new IllegalArgumentException("Empty temperature list");
		}
		return Collections.min(temps);
	}
}
