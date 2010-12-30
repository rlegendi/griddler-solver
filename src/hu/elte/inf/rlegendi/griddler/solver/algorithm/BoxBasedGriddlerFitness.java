package hu.elte.inf.rlegendi.griddler.solver.algorithm;

import hu.elte.inf.rlegendi.griddler.solver.common.Griddler;
import hu.elte.inf.rlegendi.griddler.solver.common.Utilities;

/**
 * Fitness based only on the difference between the number of filled and empty boxes.
 * 
 * @author rlegendi
 */
public class BoxBasedGriddlerFitness
		extends ARowOrderedGriddlerFitness {
	private static final long serialVersionUID = 3403664082918795333L;
	
	public BoxBasedGriddlerFitness(final Griddler griddler) {
		super( griddler );
	}
	
	public int fitness(final int[] sequence, final int[] constraints) {
		final int diff = Math.abs( Utilities.sum( constraints ) - Utilities.sum( sequence ) );
		return N * N - diff * diff;
	}
	
	public int getMaxFitnessValue() {
		return 2 * N * N * N;
	}
	
}
