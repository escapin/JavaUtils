package conversions;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Map2ListConversions {
	
	// Generic function to convert Map<K,V> to a List of <Map.Entry<K,V>
	public static<K,V> List<Map.Entry<K,V>> convertToList(Map<K,V> map) {
		if(map==null)
			return null;
	        
		return map.entrySet()
			.stream()
			.collect(Collectors.toList());
	}

	
	// Generic function to convert Map<K,V> to a List of <Map.Entry<K,V> sorted by Key K
	public static<K,V> List<Map.Entry<K,V>> convertToSortedList(Map<K,V> map, Comparator<Map.Entry<K,V>> comparator) {
		if(map==null)
			return null;
		
		return map.entrySet()
			.stream()
			.sorted(comparator)
			.collect(Collectors.toList());
	}

	
	// Generic function to convert Map<K,V> to a List<K>
	public static<K,V> List<K> convertToListOfKeys(Map<K,V> map) {
		if(map==null)
			return null;
	 	
		return map.keySet()
	    		.stream()
			.collect(Collectors.toList());
	}

	
	// Generic function to convert Map<K,V> to a List<K> sorted by K
	public static<K extends Comparable<K>,V> List<K> convertToListOfSortedKeys(Map<K,V> map) {
		if(map==null)
			return null;
		 	
		return map.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByKey())
		    	.map(x->x.getKey())
		    	.collect(Collectors.toList());
	}


	// Generic function to convert Map<K,V> to a List<V>
	public static<K,V> List<V> convertToListOfValues(Map<K,V> map) {
		if(map==null)
			return null;

		return map.values()
	    		.stream()
			.collect(Collectors.toList());
	}

	
	// Generic function to convert Map<K,V> to a List<V> sorted by V
	public static<K,V extends Comparable<V>> List<V> convertToListOfSortedValues(Map<K,V> map) {
		if(map==null)
			return null;

		return map.entrySet()
			.stream()
			.sorted(Map.Entry.comparingByValue())
			.map(x->x.getValue())
			.collect(Collectors.toList());
	}

}
