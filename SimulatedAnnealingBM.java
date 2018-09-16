import java.util.*;

// particular interface for benchmark
// this is stupid, dont even bother ¯\_(ツ)_/¯
interface SimulatedAnnealingBM<T> extends SimulatedAnnealing<T> {

	Counter tryCount = new Counter();
	Counter stepCount = new Counter();
	Counter timer = new Counter();

	@Override
	default T solve(T node) {

		tryCount.increment();

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

			stepCount.increment();

		}

		return currNode;
	}

	default void resetTryCount() {
		tryCount.reset();
	}

	default long getTryCount() {
		return tryCount.getValue();
	}

	default void startTimer() {
		timer.setValue(System.currentTimeMillis());
	}

	default double stopTimer() {
		return (System.currentTimeMillis() - timer.getValue())/1000d;
	}

	default void resetStepCount() {
		stepCount.reset();
	}

	default long getStepCount() {
		return stepCount.getValue();
	}

}