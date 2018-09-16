import java.util.*;

interface HillClimbing<T> {

	T[] neighbors(T node);
	int evaluate(T node);

	default T solve(T node) {

		T currNode = node;
		int currScore = evaluate(currNode);

		Random r = new Random();

		for(;;) {

			int bestNeighborScore = Integer.MIN_VALUE;
			List<T> bestNeighbors = new ArrayList<>();

			for(T tmpNode : neighbors(currNode)) {

				int tmpNodeScore = evaluate(tmpNode);
				
				if(tmpNodeScore > bestNeighborScore) {
					// Reset the list of the bests, we found better one
					bestNeighbors = new ArrayList();
					bestNeighbors.add(tmpNode);
					// Update best score
					bestNeighborScore = tmpNodeScore;

				} else if(tmpNodeScore == bestNeighborScore) {
					// Put it along with the others
					bestNeighbors.add(tmpNode);
				}
			}

			if(currScore >= bestNeighborScore) {
				return currNode;
			}

			currScore = bestNeighborScore;
			// Decide one of the best neighbors as a current node
			currNode = bestNeighbors.get(r.nextInt(bestNeighbors.size()));

		}

	}

}