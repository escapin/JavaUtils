package tests;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import conversions.List2Map;
import conversions.Map2List;
import utilities.ToString;


public class Map2ListTest extends TestCase {
	
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	
	
	@Test
	public void test_simpleConversion() {
		List<Map.Entry<Integer,String>> list = new ArrayList<>();
		
		list.add(new AbstractMap.SimpleEntry<Integer,String>(10, "apple"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(30, "banana"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(50, "dragonfruit"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(20, "orange"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(40, "watermelon"));
		
		
		Map<Integer, String> map = List2Map.convertToMap(list);
		
		List<Map.Entry<Integer,String>> listReverted = Map2List.convertToList(map);
		
		//Map2List.con
		
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: map2List: Simple Conversion\n");
		
		System.out.print("List Original:\n\t");
		System.out.println(ToString.generateString(list));
		
		System.out.print("Map Converted from List Original:\n\t");
		System.out.println(ToString.generateString(map));
		
		System.out.print("List Reverted from Map Converted:\n\t");
		System.out.println(ToString.generateString(listReverted));
		
		//assertEquals("Conversion does not work", list, listReverted);
		
		
		
		System.out.println("\n********************************************************************************");
	}
	
	
//	@Test
//	public void test_sortedConversion() {
//		Map<Integer, String> map = new HashMap<>();
//		map.put(10, "apple");
//		map.put(20, "orange");
//		map.put(30, "banana");
//		map.put(40, "watermelon");
//		map.put(50, "dragonfruit");
//		
//		List<Map.Entry<Integer, String>> list = Map2ListConversions.convertToSortedList(map);
//		Map<Integer, String> mapSortedByKey = List2MapConversions.convertHandlingDuplicatesToMapSortedByKey(list);
//		Map<Integer, String> mapSortedByValue = List2MapConversions.convertHandlingDuplicatesToSortedMapOfValues(list);
//		
//		System.out.println("\n********************************************************************************");
//		System.out.println("\tTEST: Sorted Conversion\n");
//		
//		System.out.println("Map Original:");
//		printToScreen(map);
//		
//		System.out.println("List Sorted:");
//		printToScreen(list);
//		
//		System.out.println("Map Reverted and Sorted by Key:");
//		printToScreen(mapSortedByKey);
//		
//		System.out.println("Map Reverted and Sorted by Values:");
//		printToScreen(mapSortedByValue);
//		
//		assertEquals("Conversion does not work", map, mapSortedByKey);
//		assertEquals("Conversion does not work", map, mapSortedByValue);
//		
//		System.out.println("\n********************************************************************************");
//	}
//	
//	
//	@Test
//	public void test_duplicatesConversion() {
//		Map<Integer, String> map = new HashMap<>();
//		map.put(10, "apple");
//		map.put(20, "orange");
//		map.put(30, "banana");
//		map.put(40, "watermelon");
//		map.put(50, "dragonfruit");
//		
//		List<Map.Entry<Integer, String>> list = Map2ListConversions.convertToList(map);
//		
//		list.add(new AbstractMap.SimpleEntry<Integer,String>(10, "pineapple"));
//		
//		Map<Integer, String> mapWithNewEntry = List2MapConversions.convertHandlingDuplicatesToMap(list);
//		
//		Map<Integer, String> mapSortedByKeyWithNewEntry = List2MapConversions.convertHandlingDuplicatesToMapSortedByKey(list);
//		
//		System.out.println("\n********************************************************************************");
//		System.out.println("\tTEST: Duplicates Conversion\n");
//		
//		System.out.println("Map Original:");
//		printToScreen(map);
//		
//		System.out.println("List Converted plus Another Entry:");
//		printToScreen(list);
//		
//		System.out.println("Map Reverted with the new Entry instead of the old one:");
//		printToScreen(mapWithNewEntry);
//		
//		System.out.println("Map Reverted with the new Entry instead of the old one, sorted by Key:");
//		printToScreen(mapSortedByKeyWithNewEntry);
//		
//		
//		assertNotSame("Conversion does not work", map, mapWithNewEntry);
//		assertEquals("Conversion does not work", mapWithNewEntry, mapSortedByKeyWithNewEntry);
//		
//		System.out.println("\n********************************************************************************");
//	
//	}
	
	
	

}
