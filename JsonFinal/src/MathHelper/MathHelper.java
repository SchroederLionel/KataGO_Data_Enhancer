package MathHelper;
import java.util.List;

/**
 * Class which is used to to simple computations such as the average and a more
 * custom designed average.
 * 
 * @author Lionel Schroeder
 *
 */
public class MathHelper {

	/**
	 * Function which allows to compute the simple average of a list of doubles.
	 * 
	 * @param scores double list which is required to compute the average.
	 * @return the average of the list.
	 */
	public static double computeSimpleAverage(List<Double> scores) {
		double value = 0;
		for (Double val : scores)
			value += val;
		return value / scores.size();
	}

	/**
	 * Function which allows to compute a more specific average.
	 * 
	 * @param scores List of doubles which is required for the computation.
	 * @return more complicated average computation.
	 */
	public static double computeFormulaAverage(List<Double> scores) {
		double valueMultiplier = Double.valueOf(2.0 / scores.size());
		double scoreLead = 0.0;

		for (int i = 0; i < scores.size(); i++) {
			double previousValue = 0;
			double currentValue = scores.get(i);
			int j = i - 1;
			if (j < 0) {
				previousValue = 0;
			} else {
				previousValue = scores.get(j);
			}
			scoreLead += currentValue - previousValue;
		}

		return valueMultiplier * scoreLead;
	}
}
