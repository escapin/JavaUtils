package utils;

import java.util.List;
import java.util.Map;
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


	// Generic function to convert a List of Map.Entry<K,V> with possible duplicate Keys to a LinkedHashMap<K,V> sorted by Keys 
	public static<K extends Comparable<K>,V> Map<K,V> convertHandlingDuplicatesToSortedMapOfKey(List<Map.Entry<K,V>> list) {
		if(list==null)
			return null;
	  
		return list.stream()
				.sorted(Map.Entry.comparingByKey())			// sort the entries by Keys
				.collect(
					Collectors.toMap(
						x -> x.getKey(), x -> x.getValue(),
						(oldKey, newKey) -> newKey,			// if a duplicate Key, take the latest one encountered
	    					LinkedHashMap::new));			// returns a LinkedHashMap, keep order
	}
	
	// Generic function to convert a List of Map.Entry<K,V> with possible duplicate Keys to a LinkedHashMap<K,V> sorted by Values
	public static<K,V extends Comparable<V>> Map<K,V> convertHandlingDuplicatesToSortedMapOfValues(List<Map.Entry<K,V>> list) {
		if(list==null)
			return null;
	  
		return list.stream()
				.sorted(Map.Entry.comparingByValue())			// sort the entries Values
				.collect(
					Collectors.toMap(
						x -> x.getKey(), x -> x.getValue(),
						(oldValue, newValue) -> newValue,		// if a duplicate Value, take the latest one encountered
	    					LinkedHashMap::new));				// returns a LinkedHashMap, keep order
	}
}
