package hu.elte.inf.rlegendi.griddler.solver;

import hu.elte.inf.rlegendi.griddler.solver.data.Griddler;

import java.util.ArrayList;

import org.jgap.FitnessFunction;
import org.jgap.IChromosome;

public class GriddlerFitness
		extends FitnessFunction {
	private static final long serialVersionUID = 4714298596774108737L;
	
	private final int N; // length of a shortcut, its only an abbreviation
	private final Griddler griddler;
	
	public GriddlerFitness(final Griddler griddler) {
		super();
		
		this.griddler = griddler;
		this.N = griddler.getN();
	}
	
	@Override
	protected double evaluate(final IChromosome subject) {
		double ret = 0.0;
//		System.out.println( new GriddlerSolution( griddler, subject ) );
		
		for (int i = 0; i < N; ++i) {
			ret += fitness( getRow( subject, i ), griddler.getRowConstraints( i ) );
			ret += fitness( getCol( subject, i ), griddler.getColConstraints( i ) );
		}
		
		return ret;
	}
	
	/**
	 * @param sequence a row or column
	 * @param constraints
	 * @return
	 */
	private int fitness(final int[] sequence, final int[] constraints) {
//		int sumConstraints = sum( constraints );
//		final int maxDiff = N - sumConstraints; // TODO: -1 pro... 
//		final int actDiff = Math.abs( sumConstraints - sum( sequence ) );
//		
//		if ( maxDiff < actDiff) {
//			System.out.println("fail");
//		}
//		
//		return maxDiff - actDiff;
		
//		int diff = Math.abs( sum( constraints ) - sum( sequence ) );
//		return N - diff;
		
		int ret = 0;
		final int[] subsums = subsums( sequence );
		for (int i = 0; i < Math.min( subsums.length, constraints.length ); ++i) {
			if ( subsums[i] == constraints[i] ) {
				ret++;
			}
		}
		return ret;
	}
	
	private int[] getRow(final IChromosome subject, final int row) {
		final int[] ret = new int[N];
		final int rowOffset = row * N;
		
		for (int i = 0; i < N; ++i) {
			final Integer allele = (Integer) subject.getGene( rowOffset + i ).getAllele();
			ret[i] = allele.intValue();
		}
		
		return ret;
	}
	
	private int[] getCol(final IChromosome subject, final int col) {
		final int[] ret = new int[N];
		
		for (int i = 0; i < N; ++i) {
			final Integer allele = (Integer) subject.getGene( ( i + 1 ) * col ).getAllele();
			ret[i] = allele.intValue();
		}
		
		return ret;
	}
	
	private int sum(final int[] arr) {
		int ret = 0;
		for (final int act : arr) {
			ret += act;
		}
		return ret;
	}
	
	private int[] subsums(final int[] arr) {
		final ArrayList<Integer> list = new ArrayList<Integer>();
		int sum = 0;
		for (int i = 0; i < arr.length; ++i) {
			if ( 0 == arr[i] ) {
				if ( sum != 0 ) {
					list.add( sum );
				}
				sum = 0;
			} else {
				sum++;
			}
		}
		
		if ( sum != 0 ) {
			list.add( sum );
		}
		
		final int[] ret = new int[list.size()];
		for (int i = 0; i < ret.length; ++i) {
			ret[i] = list.get( i );
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
