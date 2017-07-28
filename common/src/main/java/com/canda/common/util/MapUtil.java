package com.canda.common.util;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.collections4.MapUtils;

/**
 * Map工具类
 * @author kun.wang
 * @createDate 2016年12月15日 下午2:22:50
 */
public class MapUtil {
	
	public static void main(String[] args) {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("2", "2");
		map.put("4", "4");
		map.put("1", "1");
		map.put("6", "6");
		map = sortByKey(map, Integer.class, 1);
		System.out.println(map.keySet());
	}

	/**
	 * Map按key排序
	 * @createDate 2016年12月15日 下午2:42:53
	 * @author kun.wang
	 * @param map
	 * @param keyClass key的实际类型
	 * @param direction 升序： 1， 降序： -1， 不排序：0
	 * @return
	 * @return Map<K,V>
	 */
	public static <K, V> Map<K, V> sortByKey(Map<K, V> map, final Class<?> keyClass, final Integer direction) {
		if (MapUtils.isEmpty(map)) {
			return map;
		}
		Map<K, V> sortedMap = new TreeMap<K, V>(new Comparator<K>() {
			@Override
			public int compare(K key1, K key2) {
				if (keyClass.isAssignableFrom(BigDecimal.class)
						|| keyClass.isAssignableFrom(Integer.class)
						|| keyClass.isAssignableFrom(Long.class)
						|| keyClass.isAssignableFrom(Double.class)
						|| keyClass.isAssignableFrom(Float.class)) {
					BigDecimal bigDecimal1 = new BigDecimal(key1.toString());
					BigDecimal bigDecimal2 = new BigDecimal(key2.toString());
					return bigDecimal1.compareTo(bigDecimal2) * direction;
				}else {
					return key1.toString().compareTo(key2.toString()) * direction;
				}
			}
		});
		sortedMap.putAll(map);
		return sortedMap;
	}
}
