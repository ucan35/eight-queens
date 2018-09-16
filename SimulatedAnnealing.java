import java.util.*;

interface SimulatedAnnealing<T> extends HillClimbing<T> {
		
	@Override
	default T solve(T node) {

		double currTemp = 10000;
		double coolingRate = 0.03;

		T currNode = node;
		int currScore = evaluate(currNode);

		Random r = new Random();

		while(currTemp > 1) {

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

			if(bestNeighborScore > currScore || 
				Math.exp((currScore - bestNeighborScore)/currTemp) > r.nextDouble()) {

				currNode = bestNeighbors.get(r.nextInt(bestNeighbors.size()));
				currScore = bestNeighborScore;
			}

			currTemp *= 1 - coolingRate;

		}

		return currNode;
	}

}