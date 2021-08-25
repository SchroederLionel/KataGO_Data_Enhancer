package nextMoves;
import java.util.List;

/**
 * Class which stores the proposed moves by KataGo. White and black moves are
 * seperated.
 * 
 * @author Lionel Schroeder
 *
 */
public class BlackWhiteNextMove {
	List<ProposedAsNextMove> white;
	List<ProposedAsNextMove> black;

	public BlackWhiteNextMove(List<ProposedAsNextMove> white, List<ProposedAsNextMove> black) {
		this.white = white;
		this.black = black;
	}

}
