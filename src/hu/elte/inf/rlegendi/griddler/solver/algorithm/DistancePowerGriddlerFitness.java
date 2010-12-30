package hu.elte.inf.rlegendi.griddler.solver.algorithm;

import hu.elte.inf.rlegendi.griddler.solver.common.Griddler;
import hu.elte.inf.rlegendi.griddler.solver.common.Utilities;

public class DistancePowerGriddlerFitness
		extends ARowOrderedGriddlerFitness {
	private static final int SEQ_MAX_SCORE = 1000;
	private static final int WEIGHT_DIFF = 1;
	private static final long serialVersionUID = 3403664082918795333L;
	
	private final int maxFitness;
	
	public DistancePowerGriddlerFitness(final Griddler griddler) {
		super( griddler );
		
		maxFitness = 2 * griddler.getN() * SEQ_MAX_SCORE;
	}
	
	public int fitness(final int[] sequence, final int[] constraints) {
		int penalty = 0;
		final int[] subsums = Utilities.subsums( sequence );
		
		final int diff = Math.abs( subsums.length - constraints.length );
		
		for (int i = 0; i < Math.min( subsums.length, constraints.length ); ++i) {
			penalty += Math.pow( subsums[i] - constraints[i], 2 );
			//penalty += Math.abs( subsums[i] - constraints[i] ) * 10;
		}
		
		final int[] longer = ( subsums.length > constraints.length ) ? subsums : constraints;
		
		for (int i = Math.min( subsums.length, constraints.length ); i < Math.max( subsums.length, constraints.length ); ++i) {
			penalty += Math.pow( longer[i], 2 );
			//penalty += Math.abs( longer[i] ) * 10;
		}
		
		return SEQ_MAX_SCORE - ( penalty + WEIGHT_DIFF * diff );
	}
	
	public int getMaxFitnessValue() {
		return maxFitness;
	}
}
