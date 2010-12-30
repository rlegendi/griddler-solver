package hu.elte.inf.rlegendi.griddler.solver.common;

public final class Constants {
	
	public static final int INIT_POPULATION_SIZE = 100;
	
	public static final int REPORTING_INTERVAL = 1000;
	
	/** Desired rate of mutation, expressed as the denominator of the 1 / X fraction. */
	public static final int PROBABILITY_MUTATION = 100;
	
	public static final String EOL = System.getProperty( "line.separator" );

	// ======================================================================================================================

	private Constants() {
		;
	}
}
