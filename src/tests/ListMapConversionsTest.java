package tests;

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
		
		assertEquals("Conversion does not work", map, mapReverted);
		
	}

}
