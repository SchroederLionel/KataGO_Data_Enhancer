package storageOfResults;
import java.util.Collections;
import java.util.List;

import MathHelper.MathHelper;

/**
 * Class which allows to store the scoreleads given by KataGo. (Is used to Store
 * after as a JsonObject).
 * 
 * @author Lionel Schroeder
 *
 */
public class Scorelead {

	private double blackScoreMax;
	private double whiteScoreMax;
	private double whiteScoreMin;
	private double blackScoreMin;
	private double blackScoreFORMULA;
	private double whiteScoreFORMULA;
	private double blackAverageScore;
	private double whiteAverageScore;

	public Scorelead(List<Double> blackScores, List<Double> whiteScores) {
		super();
		this.blackScoreMax = Collections.max(blackScores);
		this.whiteScoreMax = Collections.max(whiteScores);
		this.whiteScoreMin = Collections.min(whiteScores);
		this.blackScoreMin = Collections.min(blackScores);
		this.blackAverageScore = MathHelper.computeSimpleAverage(blackScores);
		this.whiteAverageScore = MathHelper.computeSimpleAverage(whiteScores);
		this.blackScoreFORMULA = MathHelper.computeFormulaAverage(blackScores);
		this.whiteScoreFORMULA = MathHelper.computeFormulaAverage(whiteScores);
	}

}
