package tests;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import junit.framework.TestCase;
import utils.List2MapConversions;
import utils.Map2ListConversions;


public class ListMapConversionsTest extends TestCase {
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	private static<K,V> void printToScreen(List<Map.Entry<K, V>> list) {
		list.forEach(item -> System.out.print("[" + item + "] "));
		System.out.println();
	}
	
	private static<K,V> void printToScreen(Map<K, V> map) {
		map.forEach((k,v) -> System.out.print("[" + k + "=" + v + "] "));
		System.out.println();
	}
	
	@Test
	public void test_simpleConversion() {
		Map<Integer, String> map = new HashMap<>();
		map.put(10, "apple");
		map.put(20, "orange");
		map.put(30, "banana");
		map.put(40, "watermelon");
		map.put(50, "dragonfruit");
		
		List<Map.Entry<Integer, String>> list = Map2ListConversions.convertToList(map);
		
		Map<Integer, String> mapReverted = List2MapConversions.convertToMap(list);
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: Simple Conversion\n");
		
		System.out.println("Map Original:");
		printToScreen(map);
		
		System.out.println("List Converted:");
		printToScreen(list);
		
		System.out.println("Map Reverted:");
		printToScreen(mapReverted);
		
		assertEquals("Conversion does not work", map, mapReverted);
		
		System.out.println("\n********************************************************************************");
	}
	
	
	@Test
	public void test_sortedConversion() {
		Map<Integer, String> map = new HashMap<>();
		map.put(10, "apple");
		map.put(20, "orange");
		map.put(30, "banana");
		map.put(40, "watermelon");
		map.put(50, "dragonfruit");
		
		List<Map.Entry<Integer, String>> list = Map2ListConversions.convertToSortedList(map);
		Map<Integer, String> mapSortedByKey = List2MapConversions.convertHandlingDuplicatesToMapSortedByKey(list);
		Map<Integer, String> mapSortedByValue = List2MapConversions.convertHandlingDuplicatesToSortedMapOfValues(list);
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: Sorted Conversion\n");
		
		System.out.println("Map Original:");
		printToScreen(map);
		
		System.out.println("List Sorted:");
		printToScreen(list);
		
		System.out.println("Map Reverted and Sorted by Key:");
		printToScreen(mapSortedByKey);
		
		System.out.println("Map Reverted and Sorted by Values:");
		printToScreen(mapSortedByValue);
		
		assertEquals("Conversion does not work", map, mapSortedByKey);
		assertEquals("Conversion does not work", map, mapSortedByValue);
		
		System.out.println("\n********************************************************************************");
	}
	
	
	@Test
	public void test_duplicatesConversion() {
		Map<Integer, String> map = new HashMap<>();
		map.put(10, "apple");
		map.put(20, "orange");
		map.put(30, "banana");
		map.put(40, "watermelon");
		map.put(50, "dragonfruit");
		
		List<Map.Entry<Integer, String>> list = Map2ListConversions.convertToList(map);
		
		list.add(new AbstractMap.SimpleEntry<Integer,String>(10, "pineapple"));
		
		Map<Integer, String> mapWithNewEntry = List2MapConversions.convertHandlingDuplicatesToMap(list);
		
		Map<Integer, String> mapSortedByKeyWithNewEntry = List2MapConversions.convertHandlingDuplicatesToMapSortedByKey(list);
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: Duplicates Conversion\n");
		
		System.out.println("Map Original:");
		printToScreen(map);
		
		System.out.println("List Converted plus Another Entry:");
		printToScreen(list);
		
		System.out.println("Map Reverted with the new Entry instead of the old one:");
		printToScreen(mapWithNewEntry);
		
		System.out.println("Map Reverted with the new Entry instead of the old one, sorted by Key:");
		printToScreen(mapSortedByKeyWithNewEntry);
		
		
		assertNotSame("Conversion does not work", map, mapWithNewEntry);
		assertEquals("Conversion does not work", mapWithNewEntry, mapSortedByKeyWithNewEntry);
		
		System.out.println("\n********************************************************************************");
	
	}
	
	
	

}
