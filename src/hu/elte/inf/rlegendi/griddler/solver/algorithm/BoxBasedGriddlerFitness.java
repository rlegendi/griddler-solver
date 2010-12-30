package hu.elte.inf.rlegendi.griddler.solver.algorithm;

import hu.elte.inf.rlegendi.griddler.solver.data.Griddler;

public class BoxBasedGriddlerFitness
		extends ARowOrderedGriddlerFitness {
	private static final long serialVersionUID = 3403664082918795333L;

	public BoxBasedGriddlerFitness(final Griddler griddler) {
		super(griddler);
	}
	
	public int fitness(final int[] sequence, final int[] constraints) {
		final int sumConstraints = sum( constraints );
		final int maxDiff = N - sumConstraints; // TODO: -1 pro... 
		final int actDiff = Math.abs( sumConstraints - sum( sequence ) );
		
		if ( maxDiff < actDiff) {
			System.out.println("fail");
		}
		
		return maxDiff - actDiff;
		// CHECKME
//		int diff = Math.abs( sum( constraints ) - sum( sequence ) );
//		return N - diff;
	}
	
	public int getMaxFitnessValue() {
		int ret = 0;
		
		for (int i = 0; i < griddler.getN(); ++i) {
			ret += griddler.getRowConstraints( i ).length;
			ret += griddler.getColConstraints( i ).length;
		}
		
		return ret;
	}
	
}
