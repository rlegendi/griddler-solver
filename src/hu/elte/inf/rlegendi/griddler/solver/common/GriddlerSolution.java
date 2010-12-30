package hu.elte.inf.rlegendi.griddler.solver.common;


import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.impl.BooleanGene;

public class GriddlerSolution {
	private static final String EMPTY_BOX = " ";
	private static final String FULL_BOX = "#";
	
	@SuppressWarnings("unused")
	private final IChromosome fittest;
	private final String solution;
	
	public GriddlerSolution(final Griddler griddler, final IChromosome fittest) {
		super();
		
		this.fittest = fittest;
		
		// Constructing String representation
		final Gene[] genes = fittest.getGenes();
		final StringBuilder sb = new StringBuilder();
		for (int i = 0; i < griddler.getN(); ++i) {
			for (int j = 0; j < griddler.getN(); ++j) {
				BooleanGene gene = (BooleanGene) genes[i * griddler.getN() + j];
				sb.append( ( gene.booleanValue() ) ? FULL_BOX : EMPTY_BOX );
			}
			
			sb.append( Constants.EOL );
		}
		
		solution = sb.toString();
	}
	
	@Override
	public String toString() {
		return solution;
	}
	
}
