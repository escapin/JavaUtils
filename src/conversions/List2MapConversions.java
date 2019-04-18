package conversions;

import java.util.List;
import java.util.Map;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class List2MapConversions {
	
	// Generic function to convert a List of Map.Entry<K,V> to a Map<K,V>
	public static<K,V> Map<K,V> convertToMap(List<Map.Entry<K,V>> list) {
		if(list==null)
			return null;
		
		return list.stream().collect(
			Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
	}


	// Generic function to convert a List of Map.Entry<K,V> with possible duplicate Keys to a Map<K,V> 
	public static<K,V> Map<K,V> convertHandlingDuplicatesToMap(List<Map.Entry<K,V>> list) {
		if(list==null)
			return null;
		
		return list.stream().collect(
			Collectors.toMap(
				x -> x.getKey(), x -> x.getValue(),
				(oldKey, newKey) -> newKey));				// if a duplicate Key, take the latest one encountered
	}


	// Generic function to convert a List of Map.Entry<K,V> with possible duplicate Keys to a LinkedHashMap<K,V> sorted according the Comparator 
	public static<K,V> Map<K,V> convertHandlingDuplicatesToSortedMap(List<Map.Entry<K,V>> list,
																		Comparator<? super Map.Entry<K,V>> comparator) {
		if(list==null)
			return null;
	  
		return list.stream()
				.sorted(comparator)							// sort the entries according to the comparator
				.collect(
					Collectors.toMap(
						x -> x.getKey(), x -> x.getValue(),
						(oldKey, newKey) -> newKey,			// if a duplicate Key, take the latest one encountered
	    					LinkedHashMap::new));			// returns a LinkedHashMap, keep order
	}
}
