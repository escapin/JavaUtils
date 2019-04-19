package conversions;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Map2List {
	
	// Generic function to convert Map<K,V> to a List of <Map.Entry<K,V>
	public static<K,V> List<Map.Entry<K,V>> convertToList(Map<K,V> map) {
		if(map==null)
			return null;
	        
		return map.entrySet()
				.stream()
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
	

	// Generic function to convert Map<K,V> to a List<V>
	public static<K,V> List<V> convertToListOfValues(Map<K,V> map) {
		if(map==null)
			return null;

		return map.values()
	    		.stream()
	    		.collect(Collectors.toList());
	}


	
	
	/************************ 
	 * SORTING 
	 ************************/
	

	// Generic function to convert Map<K,V> to a List of <Map.Entry<K,V> sorted according to the comparator
	public static<K extends Comparable<? super K>,V> List<Map.Entry<K,V>> convertToSortedList(Map<K, V> map) {
		if(map==null)
			return null;
		
		return map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())		// // sort the entries according to the comparator
				.collect(Collectors.toList());
	}


	// Generic function to convert Map<K,V> to a List of <Map.Entry<K,V> sorted according to the comparator
	public static<K extends Comparable<? super K>,V> List<Map.Entry<K,V>> convertToSortedList(Map<K,V> map,
			Comparator<? super Map.Entry<K,V>> comparator) {
		if(map==null)
			return null;
		if(comparator==null)
			return convertToSortedList(map); 
		
		return map.entrySet()
				.stream()
				.sorted(comparator)		// // sort the entries according to the comparator
				.collect(Collectors.toList());
	}

	
	// Generic function to convert Map<K,V> to a List<K> sorted in natural order on Key
	public static<K extends Comparable<? super K>,V> List<K> convertToListOfSortedKeys(Map<K,V> map) {
		if(map==null)
			return null;
		 	
		return map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey())
		    	.map(x->x.getKey())
		    	.collect(Collectors.toList());
	}

	
	// Generic function to convert Map<K,V> to a List<K> sorted according to the Comparator on Key
	public static<K extends Comparable<? super K>,V> List<K> convertToListOfSortedKeys(Map<K,V> map,
			Comparator<? super K> comparator) {
		if(map==null)
			return null;
		if(comparator==null)
			return convertToListOfSortedKeys(map);
		 	
		return map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByKey(comparator))
		    	.map(x->x.getKey())
		    	.collect(Collectors.toList());
	}

	
	// Generic function to convert Map<K,V> to a List<V> sorted in natural order on Value
	public static<K,V extends Comparable<? super V>> List<V> convertToListOfSortedValues(Map<K,V> map) {
		if(map==null)
			return null;

		return map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue())
				.map(x->x.getValue())
				.collect(Collectors.toList());
	}

	
	// Generic function to convert Map<K,V> to a List<V> sorted according to the Comparator on Value
	public static<K,V extends Comparable<? super V>> List<V> convertToListOfSortedValues(Map<K,V> map,
			Comparator<? super V> comparator) {
		if(map==null)
			return null;
		if(comparator==null)
			return convertToListOfSortedValues(map);

		return map.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(comparator))
				.map(x->x.getValue())
				.collect(Collectors.toList());
	}
}
