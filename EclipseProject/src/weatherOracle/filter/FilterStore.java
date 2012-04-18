package weatherOracle.filter;

import java.util.List;

import android.util.Pair;

public abstract class FilterStore {
	private static int versionNumber=0;
	public synchronized static void setFilters(List<Filter> filters){
		// TODO
		versionNumber++;
	}
	public synchronized static Pair<List<Filter>,Integer> getFilters(){
		// TODO
		return null;
	}
}
