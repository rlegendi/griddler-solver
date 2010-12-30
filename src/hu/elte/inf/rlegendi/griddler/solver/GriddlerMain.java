package hu.elte.inf.rlegendi.griddler.solver;

import hu.elte.inf.rlegendi.griddler.solver.algorithm.ARowOrderedGriddlerFitness;
import hu.elte.inf.rlegendi.griddler.solver.algorithm.DistancePowerGriddlerFitness;
import hu.elte.inf.rlegendi.griddler.solver.common.Constants;
import hu.elte.inf.rlegendi.griddler.solver.common.Griddler;
import hu.elte.inf.rlegendi.griddler.solver.common.GriddlerSolution;

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
	private static Configuration setupConfiguration(final Griddler griddler, final ARowOrderedGriddlerFitness fitness)
			throws InvalidConfigurationException {
		final Configuration config = new DefaultConfiguration();
		config.setFitnessFunction( fitness );
		
		config.addGeneticOperator( new MutationOperator( config, Constants.PROBABILITY_MUTATION ) ); // P_m = 0.01
		final int geneSize = griddler.getN() * griddler.getN(); // Row ordered representation of the puzzle
		
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
		
		config.setPopulationSize( Constants.INIT_POPULATION_SIZE );
		return config;
	}
	
	private static void evolve(final Griddler griddler, final ARowOrderedGriddlerFitness fitness, final Genotype population) {
		final int maxFitness = fitness.getMaxFitnessValue();
		
		while ( true ) {
			for (int i = 0; i < Constants.REPORTING_INTERVAL; ++i) {
				population.evolve();
			}
			
			// Show a report of the current state
			final IChromosome fittest = population.getFittestChromosome();
			final double value = fitness.evaluate( fittest );
			final GriddlerSolution solution = new GriddlerSolution( griddler, fittest );
			
			System.out.println( solution );
			System.out.println( "Fitness: " + value + " / " + maxFitness );
			
			if ( maxFitness <= value ) {
				break;
			}
		}
	}
	
	public static void main(final String[] args)
			throws IOException, InvalidConfigurationException {
		
		// Load puzzle
		final String puzzleToLoad = ( args.length > 0 ) ? args[0] : "nlogo_sample.gr";
		final Griddler griddler = Griddler.load( puzzleToLoad );
		
		// ----------------------------------------------------------------------------------------------------------------------
		// Modify this to play with different fitness functions
		// See classes of package hu.elte.inf.rlegendi.griddler.solver.algorithm for alternative implemented fitness functinos.
		//final ARowOrderedGriddlerFitness fitness = new BoxBasedGriddlerFitness( griddler );
		//final ARowOrderedGriddlerFitness fitness = new ConstraintBasedGriddlerFitness( griddler );
		final ARowOrderedGriddlerFitness fitness = new DistancePowerGriddlerFitness( griddler );
		// ----------------------------------------------------------------------------------------------------------------------
		
		// Create used JGAP configuration
		final Configuration configuration = setupConfiguration( griddler, fitness );
		final Genotype population = Genotype.randomInitialGenotype( configuration );
		
		long time = - System.currentTimeMillis();
		
		// Evolution takes place here
		evolve( griddler, fitness, population );
		
		time += System.currentTimeMillis();
		System.out.println( "Total evolution time: " + time + " ms" );
		
		// Show the solution
		final IChromosome fittest = population.getFittestChromosome();
		final GriddlerSolution solution = new GriddlerSolution( griddler, fittest );
		System.out.println( solution );
	}
	
}
