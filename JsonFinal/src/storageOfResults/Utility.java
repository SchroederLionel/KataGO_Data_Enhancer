package storageOfResults;
import java.util.Collections;
import java.util.List;

import MathHelper.MathHelper;

/**
 * Class which allows to store the different utility values. (Used for Json
 * Storage).
 * 
 * @author Lionel Schroeder
 *
 */
public class Utility {
	private double blackUtilityMin;
	private double whiteUtilityMin;
	private double blackUtilityMax;
	private double whiteUtilityMax;
	private double blackUtilityAverage;
	private double whiteUtilityAverage;
	private double blackUtilityFORMULA;
	private double whiteUtilityFORMULA;

	public Utility(List<Double> whiteUtilities, List<Double> blackUtilities) {
		super();
		this.blackUtilityMin = Collections.min(blackUtilities);
		this.whiteUtilityMin = Collections.min(whiteUtilities);
		this.blackUtilityMax = Collections.max(blackUtilities);
		this.whiteUtilityMax = Collections.max(whiteUtilities);
		this.blackUtilityAverage = MathHelper.computeSimpleAverage(blackUtilities);
		this.whiteUtilityAverage = MathHelper.computeSimpleAverage(whiteUtilities);
		this.blackUtilityFORMULA = MathHelper.computeFormulaAverage(blackUtilities);
		this.whiteUtilityFORMULA = MathHelper.computeFormulaAverage(whiteUtilities);
	}

}
