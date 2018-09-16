import java.util.*;

public class EightQueensHC implements HillClimbing<EightQueens> {

	@Override
	public EightQueens[] neighbors(EightQueens eq) {
		List<EightQueens> neighbors = new ArrayList<>();
		// For each column
		for(int i=0; i < 8; i++) {
			int x = eq.getXPos(i);
			// All x values excluding the current one
			for(int j=0; j < 8; j++) {
				if(j == x) {
					continue;
				}
				EightQueens move = new EightQueens(eq);
				move.setPos(j, i);
				neighbors.add(move);
			}
		}
		return neighbors.toArray(new EightQueens[neighbors.size()]);
	}

	@Override
	public int evaluate(EightQueens eq) {
		return Integer.MAX_VALUE - eq.collisionCount();
	}

}