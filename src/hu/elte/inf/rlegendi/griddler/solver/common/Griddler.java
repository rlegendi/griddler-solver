package hu.elte.inf.rlegendi.griddler.solver.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Griddler {
	/** Length of a row/column, N*N is the length of a chromosome. */
	private final int N;
	
	private final int[][] rowConstraints; // row -> constraints
	private final int[][] colConstraints; // col -> constraints
	
	public Griddler(final int N, final int[][] rowConstraints, final int[][] colConstraints) {
		super();
		
		this.N = N;
		this.rowConstraints = rowConstraints;
		this.colConstraints = colConstraints;
	}
	
	public int getN() {
		return N;
	}
	
	public int[] getRowConstraints(final int row) {
		return rowConstraints[row];
	}
	
	public int[] getColConstraints(final int col) {
		return colConstraints[col];
	}
	
	// ======================================================================================================================
	
	public static Griddler load(final String path)
			throws IOException {
		final BufferedReader br = new BufferedReader( new FileReader( path ) );
		final int N = Integer.parseInt( br.readLine() );
		
		final int[][] rows = new int[N][];
		fillConstraints( br, N, rows );
		
		final int[][] cols = new int[N][];
		fillConstraints( br, N, cols );
		
		return new Griddler( N, rows, cols );
	}
	
	private static void fillConstraints(final BufferedReader br, final int N, final int[][] constraints)
			throws IOException {
		for (int i = 0; i < N; ++i) {
			final String[] nums = br.readLine().split( "\\s" );
			constraints[i] = new int[nums.length];
			for (int j = 0; j < nums.length; ++j) {
				constraints[i][j] = Integer.parseInt( nums[j] );
			}
		}
	}
	
}
