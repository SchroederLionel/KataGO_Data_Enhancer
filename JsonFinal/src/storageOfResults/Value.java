package storageOfResults;
/**
 * Class which allows to store the Winrate scorelead and Utility of a specific move played by a placer.
 * @author Lionel Schroeder
 *
 */
public class Value {
	private String winrate;
	private String scroeLead;
	private String utility;
	private boolean isItValueTo;
	

	
	public Value(String winrate, String scroeLead, String utility,boolean isItValueTo) {
		super();
		this.winrate = winrate;
		this.scroeLead = scroeLead;
		this.utility = utility;
		this.isItValueTo = isItValueTo;
	}

	public String getWinrate() {
		return winrate;
	}

	public String getScroeLead() {
		return scroeLead;
	}

	public String getUtility() {
		return utility;
	}

	public boolean isItValueTo() {
		return isItValueTo;
	}
	
	


}
