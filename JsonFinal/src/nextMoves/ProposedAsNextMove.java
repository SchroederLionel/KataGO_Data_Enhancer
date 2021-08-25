package nextMoves;
/**
 * Class which allows to store if a the next move played by a player was
 * suggested by KataGo.
 * 
 * @author Lionel Schroeder
 *
 */
public class ProposedAsNextMove {
	private boolean wasProposed;
	private int indexOfTheProposedInList;
	private int listLengthOfProposedElements;
	private int inSuggestionNumber;

	public ProposedAsNextMove(boolean wasProposed, int indexOfTheProposedInList, int listLengthOfProposedElements,
			int inSuggestionNumber) {
		super();
		this.wasProposed = wasProposed;
		this.indexOfTheProposedInList = indexOfTheProposedInList;
		this.listLengthOfProposedElements = listLengthOfProposedElements;
		this.inSuggestionNumber = inSuggestionNumber;

	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Was Proposed: " + wasProposed + " , indexOfTheProposedInList: " + indexOfTheProposedInList
				+ " , List LengthOfPrposedEleemnts: " + listLengthOfProposedElements + " , inSuggestionNumber: "
				+ inSuggestionNumber;
	}

}
