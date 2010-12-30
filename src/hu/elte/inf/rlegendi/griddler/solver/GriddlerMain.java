package hu.elte.inf.rlegendi.griddler.solver;

import hu.elte.inf.rlegendi.griddler.solver.algorithm.ARowOrderedGriddlerFitness;
import hu.elte.inf.rlegendi.griddler.solver.algorithm.DistancePowerGriddlerFitness;
import hu.elte.inf.rlegendi.griddler.solver.data.Griddler;

import java.io.IOException;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.BooleanGene;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.MutationOperator;

public class GriddlerMain {
	public static void main(final String[] args)
			throws IOException, InvalidConfigurationException {
		//final Griddler griddler = Griddler.load( "bit_2010_12.gr" );
		final Griddler griddler = Griddler.load( "nlogo_sample.gr" );
		
		final Configuration config = new DefaultConfiguration();
		//final ARowOrderedGriddlerFitness fitness = new ConstraintBasedGriddlerFitness( griddler );
		final ARowOrderedGriddlerFitness fitness = new DistancePowerGriddlerFitness( griddler );
		config.setFitnessFunction( fitness );
		
		//config.addGeneticOperator( new MutationOperator( config ) );
		
		final int geneSize = griddler.getN() * griddler.getN(); // row ordered representation
		
		// Note: Boolean gene.
		// Since this Gene implementation only supports two different values (true and false), there's only a 50% chance
		// that invocation of the setToRandomValue() method will actually change the value of this Gene (if it has a value).
		// As a result, it may be desirable to use a higher overall mutation rate when this Gene implementation is in use.
		
		final BooleanGene[] gene = new BooleanGene[geneSize];
		for (int i = 0; i < geneSize; ++i) {
			gene[i] = new BooleanGene( config );
		}
		
		final Chromosome chromosome = new Chromosome( config, gene );
		config.setSampleChromosome( chromosome );
		
		config.setPopulationSize( 100 );
		
		final Genotype population = Genotype.randomInitialGenotype( config );
		
		final int maxFitness = fitness.getMaxValue();
		long time = - System.currentTimeMillis();
		
		while ( true ) {
			for (int i = 0; i < 1000; ++i) {
				population.evolve();
			}
			
			final IChromosome fittest = population.getFittestChromosome();
			final GriddlerSolution solution = new GriddlerSolution( griddler, fittest );
			System.out.println( solution );
			final double value = fitness.evaluate( fittest );
			System.out.println( "Fitness: " + value + " / " + maxFitness );
			
			if ( maxFitness == value ) {
				break;
			}
		}
		
		time += System.currentTimeMillis();
		System.out.println( "Total evolution time: " + time + " ms" );
		
		final IChromosome fittest = population.getFittestChromosome();
		final GriddlerSolution solution = new GriddlerSolution( griddler, fittest );
		System.out.println( solution );
	}
}
