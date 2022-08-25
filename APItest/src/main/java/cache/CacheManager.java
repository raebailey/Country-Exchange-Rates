package cache;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		try {
			key  = gethash(key);
			cache.put(key, item);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
				
		
	}

	/**
	 * Gets item form cache
	 * 
	 * @param key Unique identifier for object
	 * @return Object
	 */
	public static Object getCacheItem(String key) {
		try {
			key  = gethash(key);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cache.get(key);
	}
	
	public static String gethash(final String input) throws Exception{
	  
		   String hashtext = null;
		      MessageDigest md = MessageDigest.getInstance("MD5");

		      // Compute message digest of the input
		      byte[] messageDigest = md.digest(input.getBytes());

		      hashtext = convertToHex(messageDigest);
	   

	      return hashtext;
	   }

	   private static String convertToHex(final byte[] messageDigest) {
	      BigInteger bigint = new BigInteger(1, messageDigest);
	      String hexText = bigint.toString(16);
	      while (hexText.length() < 32) {
	         hexText = "0".concat(hexText);
	      }
	      return hexText;
	   }

}
