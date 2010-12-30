package hu.elte.inf.rlegendi.griddler.solver.algorithm;

import hu.elte.inf.rlegendi.griddler.solver.common.Griddler;
import hu.elte.inf.rlegendi.griddler.solver.common.Utilities;

/**
 * Fitness that requires exact match of isles to constraints.
 * 
 * @author rlegendi
 */
public class ConstraintBasedGriddlerFitness
		extends ARowOrderedGriddlerFitness {
	private static final long serialVersionUID = 3403664082918795333L;
	private final int maxFitnesssValue;
	
	public ConstraintBasedGriddlerFitness(final Griddler griddler) {
		super( griddler );
		
		int maxFitnesssValueInit = 0;
		
		for (int i = 0; i < griddler.getN(); ++i) {
			maxFitnesssValueInit += 100 * griddler.getRowConstraints( i ).length;
			maxFitnesssValueInit += 100 * griddler.getColConstraints( i ).length;
		}
		
		maxFitnesssValue = maxFitnesssValueInit;
	}
	
	public int fitness(final int[] sequence, final int[] constraints) {
		int ret = 0;
		final int[] subsums = Utilities.subsums( sequence );
		
		if ( subsums.length != constraints.length ) {
			return 0;
		}
		
		for (int i = 0; i < Math.min( subsums.length, constraints.length ); ++i) {
			if ( subsums[i] == constraints[i] ) {
				ret += 100;
			}
		}
		
		return ret;
	}
	
	public int getMaxFitnessValue() {
		return maxFitnesssValue;
	}
	
}
