package storageOfResults;
import java.util.Collections;
import java.util.List;

import MathHelper.MathHelper;

/**
 * Class which allows to store the Euclidean Distances of a Go Game.
 * 
 * @author Lionel Schroeder
 *
 */
public class EucliResult {
	public List<Double> distances;
	public int sizeOfDistanceArray;
	public double averageDistance;
	public double averageDistanceForumla;
	public double minDistance;
	public double maxDistance;

	public EucliResult(List<Double> distances) {
		this.distances = distances;
		this.sizeOfDistanceArray = distances.size();
		this.minDistance = Collections.min(distances);
		this.maxDistance = Collections.max(distances);
		this.averageDistance = MathHelper.computeSimpleAverage(distances);
		this.averageDistanceForumla = MathHelper.computeFormulaAverage(distances);
	}

}
