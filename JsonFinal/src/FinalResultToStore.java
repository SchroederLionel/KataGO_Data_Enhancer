import java.util.HashMap;
import java.util.List;
import java.util.Map;

import storageOfResults.Result;

/**
 * Class which stores the final result.
 * 
 * @author Lionel Schroeder
 *
 */
public class FinalResultToStore {

	Map<String, List<Result>> results = new HashMap<String, List<Result>>();

	String fileName;
	double whiteLeadeAverage = 0.0;
	double blackLeadAverage = 0.0;
	double blackLeadMax = 0.0;
	double whiteLeadMax = 0.0;
	double whiteLeadMin = 0.0;
	double blackLeadMin = 0.0;

	public FinalResultToStore(List<Result> whiteResult, List<Result> blackResult, String fileName) {
		super();
		results.put("white", whiteResult);
		results.put("black", blackResult);
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Map<String, List<Result>> getResults() {
		return results;
	}

	public void setResults(Map<String, List<Result>> results) {
		this.results = results;
	}

	public double getWhiteLeadeAverage() {
		return whiteLeadeAverage;
	}

	public void setWhiteLeadeAverage(double whiteLeadeAverage) {
		this.whiteLeadeAverage = whiteLeadeAverage;
	}

	public double getBlackLeadAverage() {
		return blackLeadAverage;
	}

	public void setBlackLeadAverage(double blackLeadAverage) {
		this.blackLeadAverage = blackLeadAverage;
	}

	public double getBlackLeadMax() {
		return blackLeadMax;
	}

	public void setBlackLeadMax(double blackLeadMax) {
		this.blackLeadMax = blackLeadMax;
	}

	public double getWhiteLeadMax() {
		return whiteLeadMax;
	}

	public void setWhiteLeadMax(double whiteLeadMax) {
		this.whiteLeadMax = whiteLeadMax;
	}

	public double getWhiteLeadMin() {
		return whiteLeadMin;
	}

	public void setWhiteLeadMin(double whiteLeadMin) {
		this.whiteLeadMin = whiteLeadMin;
	}

	public double getBlackLeadMin() {
		return blackLeadMin;
	}

	public void setBlackLeadMin(double blackLeadMin) {
		this.blackLeadMin = blackLeadMin;
	}

}
