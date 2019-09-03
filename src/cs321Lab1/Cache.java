package cs321Lab1;

import java.util.LinkedList;

public class Cache {
	private LinkedList<Object> cache;
	private int cacheSize;

	
	
	
	public Cache(){
		this.cache = new LinkedList<Object>();

	}
	public void setCachSize(int i) {
		cacheSize = i;
	}
	
	public Object getObject(Object data) {
		if(cache.contains(data)) {
			return data;
		}
		return null;

	}

	public void addObject(Object data) {
		if(cache.size() == cacheSize) {
			cache.removeLast();
			cache.addFirst(data);
		}else {
			cache.addFirst(data);
		}
			
	}
	
	public void removeObject(Object data) {
		cache.remove(data);
	}
	
	public void clearCache() {
		
	}
	public String toString() {
	    StringBuilder result = new StringBuilder();
	    for(Object item:cache) {
	        result.append(item.toString());
	        result.append(" "); //optional
	    }
	    return result.toString();
	}
	public int length() {
		return cacheSize;
	}
}
