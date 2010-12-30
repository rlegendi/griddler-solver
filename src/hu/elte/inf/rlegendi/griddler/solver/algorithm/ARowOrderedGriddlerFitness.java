package hu.elte.inf.rlegendi.griddler.solver.algorithm;

import hu.elte.inf.rlegendi.griddler.solver.common.Griddler;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public abstract class ARowOrderedGriddlerFitness
		extends FitnessFunction {
	private static final long serialVersionUID = 4714298596774108737L;
	
	protected final int N; // size of a symmetric puzzle, its only an abbreviation
	protected final Griddler griddler;
	
	public ARowOrderedGriddlerFitness(final Griddler griddler) {
		super();
		
		this.griddler = griddler;
		this.N = griddler.getN();
	}
	
	@Override
	public final double evaluate(final IChromosome subject) {
		double ret = 0.0;
		
		for (int i = 0; i < N; ++i) {
			ret += fitness( getRow( subject, i ), griddler.getRowConstraints( i ) );
			ret += fitness( getCol( subject, i ), griddler.getColConstraints( i ) );
		}
		
		return ret;
	}
	
	// ======================================================================================================================
	
	protected int[] getRow(final IChromosome subject, final int row) {
		final int[] ret = new int[N];
		final int rowOffset = row * N;
		
		for (int i = 0; i < N; ++i) {
			final Boolean allele = (Boolean) subject.getGene( rowOffset + i ).getAllele();
			ret[i] = ( allele.booleanValue() ) ? 1 : 0;
		}
		
		return ret;
	}
	
	protected int[] getCol(final IChromosome subject, final int col) {
		final int[] ret = new int[N];
		
		for (int i = 0; i < N; ++i) {
			final Boolean allele = (Boolean) subject.getGene( ( i + 1 ) * col ).getAllele();
			ret[i] = ( allele.booleanValue() ) ? 1 : 0;
		}
		
		return ret;
	}
	
	// ======================================================================================================================
	
	/**
	 * @param sequence a row or column
	 * @param constraints
	 * @return
	 */
	public abstract int fitness(final int[] sequence, final int[] constraints);
	
	/**
	 * To check if the iteration can be stopped.
	 * 
	 * @return
	 */
	public abstract int getMaxFitnessValue();
	
}
