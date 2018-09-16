import java.util.*;

// EightQueens Board
public class EQBoard {

	// One queen for each column.
	// Ex. point {3, 4} = _points[4] = 3
	protected int[] _points;

	public EQBoard() {
		_points = new int[8];
	}

	protected void setPos(int x, int y) {
		_points[y] = x;
	}

	protected int getXPos(int y) {
		return _points[y];
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i < 8; i++) {
			for(int j=0; j < 8; j++) {
				sb.append("| ");
				sb.append(getXPos(j) == i ? "X" : " ");
				sb.append(" ");
			}
			sb.append("|\n");
			for(int j=0; j < 8; j++) {
				sb.append("----");
			}
			sb.append("-\n");
		}
		return sb.toString();
	}

}