package hu.elte.inf.rlegendi.griddler.solver;

import hu.elte.inf.rlegendi.griddler.solver.data.Griddler;

import java.io.IOException;

import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;

public class GriddlerMain {
	public static void main(final String[] args)
			throws IOException, InvalidConfigurationException {
		//final Griddler griddler = Griddler.load( "bit_2010_12.gr" );
		final Griddler griddler = Griddler.load( "nlogo_sample.gr" );
		
		final Configuration config = new DefaultConfiguration();
		final GriddlerFitness fitness = new GriddlerFitness( griddler );
		config.setFitnessFunction( fitness );
		
		final int geneSize = griddler.getN() * griddler.getN();
		final IntegerGene[] gene = new IntegerGene[geneSize];
		for (int i = 0; i < geneSize; ++i) {
			gene[i] = new IntegerGene( config, 0, 1 );
		}
		
		final Chromosome chromosome = new Chromosome( config, gene );
		config.setSampleChromosome( chromosome );
		
		config.setPopulationSize( 10 );
		
		final Genotype population = Genotype.randomInitialGenotype( config );
		
		final long startTime = System.currentTimeMillis();
		while ( true ) {
			for (int i = 0; i < 1000; ++i) {
				population.evolve();
			}
			
			final IChromosome fittest = population.getFittestChromosome();
			final GriddlerSolution solution = new GriddlerSolution( griddler, fittest );
			System.out.println( solution );
			double value = fitness.evaluate( fittest );
			System.out.println( "Fitness: " + value );
			
			if ( fitness.getMaxValue() == value ) {
				break;
			}
		}
		
		final long endTime = System.currentTimeMillis();
		System.out.println( "Total evolution time: " + ( endTime - startTime ) + " ms" );
		
		final IChromosome fittest = population.getFittestChromosome();
		final GriddlerSolution solution = new GriddlerSolution( griddler, fittest );
		System.out.println( solution );
	}
}
