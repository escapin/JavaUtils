package tests;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import conversions.List2Map;
import conversions.Map2List;
import junit.framework.TestCase;


public class ListMapTests extends TestCase {
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void test_map2list() {
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
			= List2Map.convertToMap(listSortedDescendingByValue);
		
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: Map 2 List\n");
		
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
	public void test_map2listWithDuplicates() {
		Map<Integer, String> mapOriginal = new HashMap<>();
		mapOriginal.put(10, "apple");
		mapOriginal.put(20, "orange");
		mapOriginal.put(30, "banana");
		mapOriginal.put(40, "watermelon");
		mapOriginal.put(50, "dragonfruit");
		
		
		List<Map.Entry<Integer, String>> list = Map2List.convertToList(mapOriginal);
		
		list.add(0, new AbstractMap.SimpleEntry<Integer,String>(10, "pineapple"));
		
		
		Map<Integer, String> mapDifferenSortedByKey = 
				List2Map.convertHandlingDuplicatesToSortedMap(list, Map.Entry.comparingByKey());
		Map<Integer, String> mapDifferentSortedByValue = 
				List2Map.convertHandlingDuplicatesToSortedMap(list, Map.Entry.comparingByValue());
		
		
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: Map 2 List: Duplicate Conversions\n");
		
		System.out.print("Map Original:\n\t");
		System.out.println(mapOriginal);
		
		/****************************************************************/
		System.out.println("\nList Converted FROM Map Original WITH the Entry \"" + list.get(0) + "\" as First record:");
		/****************************************************************/
		
		System.out.print("\t");
		System.out.println(list);
		
		/****************************************************************/
		System.out.println("\nMap Reverted FROM List Converted WITH the replaced entry:");
		/****************************************************************/
		
		System.out.print("- SORTED BY Key:\n\t");
		System.out.println(mapDifferenSortedByKey);
		System.out.println(" (the first encountered between \"10=pinapple\" and \"10=apple\" is the former)");
		
		
		System.out.print("- SORTED BY Values:\n\t");
		System.out.println(mapDifferentSortedByValue);
		System.out.println(" (as the sorting by value is done before the duplicates handling, \"10=apple\" is before \"10=pinapple\")");
		
//		// List<Map.Entry<Integer, String>> 
//		Map<Integer, String > sortedByValue=list.stream().sorted(Map.Entry.comparingByValue(Comparator.naturalOrder()))
//				.collect(Collectors.toMap(
//				          Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//		//		.collect(Collectors.toList());
//		System.out.println("\t" + sortedByValue); //FIXME: non funziona
		
		System.out.println("\n********************************************************************************");
		
		
		assertFalse("Conversion does not work", mapOriginal.equals(mapDifferenSortedByKey));
		assertTrue("Conversion does not work", mapOriginal.equals(mapDifferentSortedByValue));
	}
	
	
	@Test
	public void test_list2map() {
		List<Map.Entry<Integer,String>> list = new ArrayList<>();
		
		list.add(new AbstractMap.SimpleEntry<Integer,String>(10, "apple"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(30, "banana"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(50, "dragonfruit"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(20, "orange"));
		list.add(new AbstractMap.SimpleEntry<Integer,String>(40, "watermelon"));
		
		
		Map<Integer, String> map = List2Map.convertToMap(list);
		
		List<Map.Entry<Integer,String>> listReverted = Map2List.convertToList(map);
		
		
		
		
		System.out.println("\n********************************************************************************");
		System.out.println("\tTEST: List 2 Map\n");
		
		System.out.print("List Original:\n\t");
		System.out.println(list);
		
		System.out.print("Map Converted from List Original:\n\t");
		System.out.println(map);
		
		System.out.print("List Reverted from Map Converted:\n\t");
		System.out.println(listReverted);
		
		//assertEquals("Conversion does not work", list, listReverted);
		
		
		
		System.out.println("\n********************************************************************************");
	}
	
	
	

}
