package SetUp;

import java.util.List;

/**
 * Class which allows to store the different players stones. It also
 * automatically computes the euclidean distance.
 * 
 * @author Lionel Schroeder
 *
 */
public class PlayerStone {
	List<Stone> whitePlayerStones;
	List<Stone> blackPlayerStones;

	public PlayerStone(List<Stone> blackPlayerStones, List<Stone> whitePlayerStone) {
		setUpEuclidienDistances(whitePlayerStone);
		setUpEuclidienDistances(blackPlayerStones);
		this.whitePlayerStones = whitePlayerStone;
		this.blackPlayerStones = blackPlayerStones;

	}

	/**
	 * Function which allows to set the euclidean distance of stones.
	 * 
	 * @param stonesForSpecificPlayers List of stones of a specific player.
	 */
	public void setUpEuclidienDistances(List<Stone> stonesForSpecificPlayers) {
		for (int i = 0; i < stonesForSpecificPlayers.size() - 1; i++) {
			Stone from = stonesForSpecificPlayers.get(i);

			Stone to = stonesForSpecificPlayers.get(i + 1);

			from.setEuclidienDistance(from, to);
		}

	}

	public List<Stone> getWhitePlayerStones() {
		return whitePlayerStones;
	}

	public void setWhitePlayerStones(List<Stone> whitePlayerStones) {
		this.whitePlayerStones = whitePlayerStones;
	}

	public List<Stone> getBlackPlayerStones() {
		return blackPlayerStones;
	}

	public void setBlackPlayerStones(List<Stone> blackPlayerStones) {
		this.blackPlayerStones = blackPlayerStones;
	}

}
