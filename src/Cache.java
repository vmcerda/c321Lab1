import java.util.LinkedList;

public class Cache<E> {
	private LinkedList<E> cache;
	private int cacheSize;
	
	public Cache(int size){
		this.cache = new LinkedList<E>();
		cacheSize = size;

	}
	
	public E getObject(E data) {
		if(cache.contains(data)) {
			return data;
		}
		return null;
	}

	public void addObject(E data) {
		if(cache.size() == cacheSize) {
			cache.removeLast();
		}
		cache.addFirst(data);
	}
	
	public void removeObject(E data) {
		cache.remove(data);
	}
	
	public void clearCache() {
		cache.clear();
	}
	public String toString() {
	    StringBuilder result = new StringBuilder();
	    for(E item:cache) {
	        result.append(item.toString());
	        result.append(" "); //optional
	    }
	    return result.toString();
	}
	public int length() {
		return cacheSize;
	}
}
