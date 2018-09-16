import java.util.*;

public class EightQueens extends EQBoard {

	public EightQueens() {}

	// Deep copy
	public EightQueens(EightQueens eq) {
		for(int i=0; i < 8; i++) {
			_points[i] = eq._points[i];
		}
	}
 
	public void random() {
		Random r = new Random();
		// For each column randomize an x value
		for(int i=0; i < 8; i++) {
			int x = r.nextInt(8);
			setPos(x, i);
		}
	}

	public boolean isSolved() {
		return collisionCount() == 0;
	}

	public int collisionCount() {
		int collisionCount = 0;
		for(int i=0; i < 7; i++) {
			for(int j=i+1; j < 8; j++) {

				int x1 = getXPos(i);
				int x2 = getXPos(j);				

				if(x1 == x2) {
					collisionCount++;
					continue;
				}

				double slope = ((double)(i-j)) / (x1-x2);
				if(Math.abs(slope) == 1d) {
					collisionCount++;
				}

			}
		} 
		return collisionCount;
	}

}