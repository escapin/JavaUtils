package tests;

import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import conversions.List2MapConversions;
import conversions.Map2ListConversions;
import junit.framework.TestCase;
import utilities.ToString;


public class List2MapConversionsTest extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void test_simpleConversion() {
		Map<Integer, String> mapOriginal = new HashMap<>();
		mapOriginal.put(10, "apple");
		mapOriginal.put(20, "orange");
		mapOriginal.put(30, "banana");
		mapOriginal.put(40, "watermelon");
		mapOriginal.put(50, "dragonfruit");
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: list2map: Simple Conversions\n");
		
		System.out.print("Map Original:\n\t");
		System.out.println(ToString.generateString(mapOriginal));
		
		List<Map.Entry<Integer, String>> list 
			= Map2ListConversions.convertToList(mapOriginal);
		System.out.print("List Converted FROM Map Original:\n\t");
		System.out.println(ToString.generateString(list));
		
		Map<Integer, String> mapReverted 
			= List2MapConversions.convertToMap(list);
		System.out.print("Map Reverted from List Converted:\n\t");
		System.out.println(ToString.generateString(mapReverted));
		
		assertEquals("Conversion does not work", mapOriginal, mapReverted);
		
		
		List<Map.Entry<Integer, String>> listSortedByKey 
			= Map2ListConversions.convertToSortedList(mapOriginal, Map.Entry.comparingByKey());
		System.out.print("List Converted AND Sorted by Key FROM Map Original:\n\t");
		System.out.println(ToString.generateString(listSortedByKey));
		
		List<Map.Entry<Integer, String>> listSortedDescendingByValue 
			= Map2ListConversions.convertToSortedList(mapOriginal, Collections.reverseOrder(Map.Entry.comparingByValue()));
		System.out.print("List Converted AND Sorted by Value in Descending Order FROM Map Original:\n\t");
		System.out.println(ToString.generateString(listSortedDescendingByValue));

		
		
		
		System.out.println("\n********************************************************************************");
	}
	
	
	@Test
	public void test_sortedConversion() {
		Map<Integer, String> mapOriginal = new HashMap<>();
		mapOriginal.put(10, "apple");
		mapOriginal.put(20, "orange");
		mapOriginal.put(30, "banana");
		mapOriginal.put(40, "watermelon");
		mapOriginal.put(50, "dragonfruit");
		
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: Sorted Conversions\n");
		
		System.out.print("Map Original:\n\t");
		System.out.println(ToString.generateString(mapOriginal));
		
		List<Map.Entry<Integer, String>> list = Map2ListConversions.convertToList(mapOriginal);
		System.out.print("List Converted FROM Map Original:\n\t");
		System.out.println(ToString.generateString(list));
		
		Map<Integer, String> mapSortedByKey = List2MapConversions.convertHandlingDuplicatesToSortedMap(list, Map.Entry.comparingByKey());
		System.out.print("Map Reverted AND Sorted by Key FROM List Converted:\n\t");
		System.out.println(ToString.generateString(mapSortedByKey));
		
		Map<Integer, String> mapSortedByValue = List2MapConversions.convertHandlingDuplicatesToSortedMap(list, Map.Entry.comparingByValue());
		System.out.print("Map Reverted AND Sorted by Values FROM List Converted:\n\t");
		System.out.println(ToString.generateString(mapSortedByValue));
		
		assertEquals("Conversion does not work", mapOriginal, mapSortedByKey);
		assertEquals("Conversion does not work", mapOriginal, mapSortedByValue);
		
		System.out.println("\n********************************************************************************");
	}
	
	
	@Test
	public void test_duplicatesConversion() {
		Map<Integer, String> mapOriginal = new HashMap<>();
		mapOriginal.put(10, "apple");
		mapOriginal.put(20, "orange");
		mapOriginal.put(30, "banana");
		mapOriginal.put(40, "watermelon");
		mapOriginal.put(50, "dragonfruit");
		
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: Duplicates Conversion\n");
		
		System.out.print("Map Original:\n\t");
		System.out.println(ToString.generateString(mapOriginal));
		
		List<Map.Entry<Integer, String>> list = Map2ListConversions.convertToList(mapOriginal);
		list.add(new AbstractMap.SimpleEntry<Integer,String>(10, "pineapple"));
		
		System.out.print("List Converted FROM Map Original WITH a new Entry:\n\t");
		System.out.println(ToString.generateString(list));
		
		Map<Integer, String> mapWithNewEntry = List2MapConversions.convertHandlingDuplicatesToMap(list);
		System.out.print("Map Reverted FROM List Converted WITH the new Entry INSTEAD OF the old one:\n\t");
		System.out.println(ToString.generateString(mapWithNewEntry));
		
		Map<Integer, String> mapSortedByKeyWithNewEntry = List2MapConversions.convertHandlingDuplicatesToSortedMap(list, Map.Entry.comparingByKey());
		System.out.print("Map Reverted AND Sorted by Key FROM List Converted WITH the new Entry INSTEAD OF the old one:\n\t");
		System.out.println(ToString.generateString(mapSortedByKeyWithNewEntry));
		
		
		assertNotSame("Conversion does not work", mapOriginal, mapWithNewEntry);
		assertEquals("Conversion does not work", mapWithNewEntry, mapSortedByKeyWithNewEntry);
		
		System.out.println("\n********************************************************************************");
	
	}
	
	
	

}
