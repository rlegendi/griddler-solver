package hu.elte.inf.rlegendi.griddler.solver;

import hu.elte.inf.rlegendi.griddler.solver.data.Griddler;

import org.jgap.Gene;
import org.jgap.IChromosome;

public class GriddlerSolution {
	@SuppressWarnings("unused")
	private final IChromosome fittest;
	private final String solution;
	
	private static final String EOL = System.getProperty( "line.separator" );
	
	public GriddlerSolution(final Griddler griddler, final IChromosome fittest) {
		super();
		
		this.fittest = fittest;
		
		final Gene[] genes = fittest.getGenes(); // TODO: IntegerGene[] cast?
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < griddler.getN(); ++i) {
			for (int j = 0; j < griddler.getN(); ++j) {
				sb.append( ( (Boolean) genes[i*griddler.getN() + j].getAllele() ) ? "#" : " " );
			}
			
			sb.append( EOL );
		}
		
		solution = sb.toString();
	}
	
	@Override
	public String toString() {
		return solution;
	}
	
}
