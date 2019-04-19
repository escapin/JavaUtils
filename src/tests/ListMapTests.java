package tests;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import conversions.List2Map;
import conversions.Map2List;
import junit.framework.TestCase;
import utilities.ToString;


public class ListMapTests extends TestCase {
	
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
		
		List<Map.Entry<Integer, String>> list
							= Map2List.convertToList(mapOriginal);
		
		List<Map.Entry<Integer, String>> listSortedByKey 
			= Map2List.convertToSortedList(mapOriginal, Map.Entry.comparingByKey());
		
		List<Map.Entry<Integer, String>> listSortedDescendingByValue 
			= Map2List.convertToSortedList(mapOriginal, 
					Map.Entry.comparingByValue(Comparator.reverseOrder()));
		
		Map<Integer, String> mapRevertedFromList 
			= List2Map.convertToMap(list);

		Map<Integer, String> mapRevertedFromListSortedByKey 
			= List2Map.convertToMap(listSortedByKey);
		
		Map<Integer, String> mapRevertedFromListSortedDescendingByValue 
			= List2Map.convertToMap(listSortedByKey);
		
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: Simple Conversions\n");
		
		System.out.print("Map Original:\n\t");
		System.out.println(mapOriginal);
		
		/****************************************************************/
		System.out.println("\nList Converted from Map Original:");
		/****************************************************************/
	
		System.out.print("\t");
		System.out.println(list);
		
		
		System.out.print("- SORTED BY Key\n\t");
		System.out.println(listSortedByKey);
		
		System.out.print("- SORTED BY Value in Descending Order\n\t");
		System.out.println(listSortedDescendingByValue);
		
		/****************************************************************/
		System.out.println("\nMap Reverted:");
		/****************************************************************/
		
		System.out.print("- FROM List Converted:\n\t");
		System.out.println(mapRevertedFromList);
		
		System.out.print("- FROM List Converted SORTED BY Key:\n\t");
		System.out.println(mapRevertedFromListSortedByKey);
		
		System.out.print("- FROM List Converted SORTED BY Value in Descending Order:\n\t");
		System.out.println(mapRevertedFromListSortedDescendingByValue);
		
		System.out.println("\n********************************************************************************");
		
		
		assertEquals("Conversion does not work", mapOriginal, mapRevertedFromList);
		assertEquals("Conversion does not work", mapOriginal, mapRevertedFromListSortedByKey);
		assertEquals("Conversion does not work", mapOriginal, mapRevertedFromListSortedDescendingByValue);
	}
	
	
	@Test
	public void test_sortedConversion() {
		Map<Integer, String> mapOriginal = new HashMap<>();
		mapOriginal.put(10, "apple");
		mapOriginal.put(20, "orange");
		mapOriginal.put(30, "banana");
		mapOriginal.put(40, "watermelon");
		mapOriginal.put(50, "dragonfruit");
		
		
		List<Map.Entry<Integer, String>> list = Map2List.convertToList(mapOriginal);
		
		list.add(new AbstractMap.SimpleEntry<Integer,String>(10, "pineapple"));
		
		Map<Integer, String> mapDifferenSortedByKey = 
				List2Map.convertHandlingDuplicatesToSortedMap(list, Map.Entry.comparingByKey());
		Map<Integer, String> mapDifferentSortedByValue = 
				List2Map.convertHandlingDuplicatesToSortedMap(list, Map.Entry.comparingByValue());
		
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: Duplicate Conversions\n");
		
		System.out.print("Map Original:\n\t");
		System.out.println(mapOriginal);
		
		/****************************************************************/
		System.out.println("\nList Converted FROM Map Original WITH another Entry:");
		/****************************************************************/
		
		System.out.print("\t");
		System.out.println(list);
		
		/****************************************************************/
		System.out.println("\nMap Reverted FROM List Converted WITH a replaced entry:");
		/****************************************************************/
		
		System.out.print("- SORTED BY Key:\n\t");
		System.out.println(mapDifferenSortedByKey);
		
		
		System.out.print("- SORTED BY Values:\n\t");
		System.out.println(mapDifferentSortedByValue);
		
		
		System.out.println("\n********************************************************************************");
		
		
		assertFalse("Conversion does not work", mapOriginal.equals(mapDifferenSortedByKey));
		assertFalse("Conversion does not work", mapOriginal.equals(mapDifferentSortedByValue));
	}
	
	
//	@Test
//	public void test_duplicatesConversion() {
//		Map<Integer, String> mapOriginal = new HashMap<>();
//		mapOriginal.put(10, "apple");
//		mapOriginal.put(20, "orange");
//		mapOriginal.put(30, "banana");
//		mapOriginal.put(40, "watermelon");
//		mapOriginal.put(50, "dragonfruit");
//		
//		
//		System.out.println("\n********************************************************************************");
//		System.out.println("\tTEST: Duplicates Conversion\n");
//		
//		System.out.print("Map Original:\n\t");
//		System.out.println(ToString.generateString(mapOriginal));
//		
//		List<Map.Entry<Integer, String>> list = Map2List.convertToList(mapOriginal);
//		
//		
//		System.out.print("List Converted FROM Map Original WITH a new Entry:\n\t");
//		System.out.println(ToString.generateString(list));
//		
//		Map<Integer, String> mapWithNewEntry = List2Map.convertHandlingDuplicatesToMap(list);
//		System.out.print("Map Reverted FROM List Converted WITH the new Entry INSTEAD OF the old one:\n\t");
//		System.out.println(ToString.generateString(mapWithNewEntry));
//		
//		Map<Integer, String> mapSortedByKeyWithNewEntry = List2Map.convertHandlingDuplicatesToSortedMap(list, Map.Entry.comparingByKey());
//		System.out.print("Map Reverted AND Sorted by Key FROM List Converted WITH the new Entry INSTEAD OF the old one:\n\t");
//		System.out.println(ToString.generateString(mapSortedByKeyWithNewEntry));
//		
//		
//		assertNotSame("Conversion does not work", mapOriginal, mapWithNewEntry);
//		assertEquals("Conversion does not work", mapWithNewEntry, mapSortedByKeyWithNewEntry);
//		
//		System.out.println("\n********************************************************************************");
//	
//	}
	
	
	

}
