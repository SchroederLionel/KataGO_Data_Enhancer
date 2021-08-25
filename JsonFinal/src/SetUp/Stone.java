package SetUp;

/**
 * Class contains The player color, position, and can compute the euclidien
 * distance from this stone to the next stone.
 * 
 * @author Lionel Schroeder
 *
 */
public class Stone {
	private String player;
	private int x;
	private int y;

	private double euclidienDistance;

	public Stone(String token) {
		this.player = getPayerFromToken(token);
		setX(token);
		setY(token);
	}

	public Stone(String player, int x, int y) {
		this.player = player;
		this.x = x;
		this.y = y;
	}

	public String getPlayer() {
		return this.player;
	}

	public void setEuclidienDistance(Stone from, Stone to) {
		this.euclidienDistance = computeEuclidienDistance(from, to);
	}

	/**
	 * Function which allows to compute the euclidean distance between two stones.
	 * 
	 * @param from the Stone from where to start the euclidean distance computation.
	 * @param to   the Stone from where to compute the distance to the next stone.
	 * @return the euclidean distance between the two stones.
	 */
	private double computeEuclidienDistance(Stone from, Stone to) {
		double sustraction1 = Math.pow(from.getX() - to.getX(), 2);
		double sustraction2 = Math.pow(from.getY() - to.getY(), 2);
		double add = sustraction1 + sustraction2;
		return Math.sqrt(add);
	}

	public double getEuclidienDistance() {
		return this.euclidienDistance;
	}

	private String getPayerFromToken(String token) {
		return String.valueOf(token.charAt(0)).toUpperCase();
	}

	/**
	 * Function which allows to set the X position of the Stone.
	 * 
	 * @param token is a string which contains the Playercolor and the respective
	 *              position x and y. e.g.(B[XY]).
	 */
	public void setX(String token) {
		this.x = (int) token.charAt(2) - 97;
	}

	/**
	 * Function which allows to set the Y position of the stone.
	 * 
	 * @param token is a string which contains the Playercolor and the respective
	 *              position x and y. e.g.(B[XY]).
	 */
	public void setY(String token) {
		this.y = (int) (token.charAt(3) - 97);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Player: " + getPlayer() + " ,X: " + x + " , Y:" + y;
	}

}
