import java.util.*;

public class BenchmarkTest {

	public static void main(String[] args) {
		
		if(args.length < 1) {
			System.out.println("Invalid usage, at least one operand: <bruteforce> <hillclimbing> <simulatedannealing>");
			return;
		}

		EightQueens eq = new EightQueens();

		List argList = Arrays.asList(args);
		if(argList.contains("bruteforce")) {

			System.out.println("Benchmarking brute force...");

			long totalTryCount = 0;
			long time = System.currentTimeMillis();

			do {
				eq.random();
				totalTryCount++;
			} while(!eq.isSolved());

			System.out.println("Total time elapsed: " + (System.currentTimeMillis() - time)/1000d);
			System.out.println("Total try(restart) count: " + totalTryCount);
			System.out.println("Solution: \n" + eq);
		}

		if(argList.contains("hillclimbing")) {
			
			System.out.println("Benchmarking hill climbing...");

			EightQueensHCBM eqHCBM = new EightQueensHCBM();
			eqHCBM.startTimer();

			do {

				eq.random();
				eq = eqHCBM.solve(eq);

			} while(!eq.isSolved());

			System.out.println("Total time elapsed: " + eqHCBM.stopTimer());
			System.out.println("Total step count: " + eqHCBM.getStepCount());
			System.out.println("Total try(restart) count: " + eqHCBM.getTryCount());
			System.out.println("Solution: \n" + eq);
			
		}

		if(argList.contains("simulatedannealing")) {

			System.out.println("Benchmarking simulated annealing...");

			EightQueensSABM eqSABM = new EightQueensSABM();
			eqSABM.startTimer();

			do {

				eq.random();
				eq = eqSABM.solve(eq);

			} while(!eq.isSolved());

			System.out.println("Total time elapsed: " + eqSABM.stopTimer());
			System.out.println("Total step count: " + eqSABM.getStepCount());
			System.out.println("Total try(restart) count: " + eqSABM.getTryCount());
			System.out.println("Solution: \n" + eq);

		}

	}

}