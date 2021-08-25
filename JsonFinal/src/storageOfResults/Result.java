package storageOfResults;
import java.util.HashMap;
import java.util.List;


/**
 * Class which allows to store the euclidean distance between two Stones.
 * 
 * @author Lionel Schroeder
 *
 */
public class Result {
	String stoneFromPosition;
	String stoneToPosition;
	double euclidiennDistance;

	HashMap<String, List<Value>> values = new HashMap<String,List<Value>>();

	public Result(String stoneFromPosition, String stoneToPosition, double euclidiennDistance, List<Value> values) {
		super();

		this.stoneFromPosition = stoneFromPosition;
		this.stoneToPosition = stoneToPosition;
		this.euclidiennDistance = euclidiennDistance;
		this.values.put("values", values);
	}

	public String getStoneFromPosition() {
		return stoneFromPosition;
	}

	public String getStoneToPosition() {
		return stoneToPosition;
	}

	public double getEuclidiennDistance() {
		return euclidiennDistance;
	}

}
