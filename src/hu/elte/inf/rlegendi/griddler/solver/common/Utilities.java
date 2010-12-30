package hu.elte.inf.rlegendi.griddler.solver.common;

import java.util.ArrayList;

public final class Utilities {
	
	public static int sum(final int[] arr) {
		int ret = 0;
		
		for (final int act : arr) {
			ret += act;
		}
		
		return ret;
	}
	
	public static int[] subsums(final int[] arr) {
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
	
	private Utilities() {
		;
	}
}
