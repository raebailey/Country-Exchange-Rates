package cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {
	static Map<String, Object> cache = new HashMap<String, Object>();

	/**
	 * Adds item to cache
	 * 
	 * @param key  The unique identifier used to represent cached item
	 * @param item The object to be cached
	 */
	public static void addCache(String key, Object item) {
		cache.put(key, item);
	}

	/**
	 * Gets item form cache
	 * 
	 * @param key Unique identifier for object
	 * @return Object
	 */
	public static Object getCacheItem(String key) {
		return cache.get(key);
	}

}
