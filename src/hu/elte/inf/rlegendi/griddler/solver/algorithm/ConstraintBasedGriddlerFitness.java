package hu.elte.inf.rlegendi.griddler.solver.algorithm;

import hu.elte.inf.rlegendi.griddler.solver.data.Griddler;

public class ConstraintBasedGriddlerFitness
		extends ARowOrderedGriddlerFitness {
	private static final long serialVersionUID = 3403664082918795333L;

	public ConstraintBasedGriddlerFitness(final Griddler griddler) {
		super(griddler);
	}
	
	public int fitness(final int[] sequence, final int[] constraints) {
		int ret = 0;
		final int[] subsums = subsums( sequence );
		
		if ( subsums.length != constraints.length ) {
			return 0;
		}
		
		for (int i = 0; i < Math.min( subsums.length, constraints.length ); ++i) {
			if ( subsums[i] == constraints[i] ) {
				ret++;
			}
		}
		
		return ret;
	}
	
	public int getMaxValue() {
		int ret = 0;
		
		for (int i = 0; i < griddler.getN(); ++i) {
			ret += griddler.getRowConstraints( i ).length;
			ret += griddler.getColConstraints( i ).length;
		}
		
		return ret;
	}

}
