package utilities;

import java.util.List;
import java.util.Map;

public class ToString {

	
	public static<K,V> String generateString(List<Map.Entry<K, V>> list) {
		StringBuffer  buffer= new StringBuffer("{");
		list.forEach(item -> buffer.append(item + ", "));
		buffer.delete(buffer.length()-2, buffer.length());
		buffer.append("}");
		return buffer.toString();
	}
	
	public static<K,V> String generateString(Map<K, V> map) {
		StringBuffer  buffer= new StringBuffer("{");
		map.forEach((k,v) -> buffer.append(k + "=" + v + ", "));
		buffer.delete(buffer.length()-2, buffer.length());
		buffer.append("}");
		return buffer.toString();
	}
}
