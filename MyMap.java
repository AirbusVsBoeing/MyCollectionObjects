package myDoublyLinkedList.Construction1;;

import java.util.Collection;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;



/**
 * @author Elijah Einstein
 *
 * @param <K> is they generic key 
 * @param <V> is the is the generic value
 */
public class MyMap<K,V> implements Map<K,V> {
	
	private class Entry<K,V>{
		K key;
		V value;
		Entry(K key, V value){
			this.key=key;
			this.value=value;
		}
	}

	private MyDoublyLinkedList<Entry<K,V>> backingStore = new MyDoublyLinkedList<Entry<K,V>>();
	private int size,modCount;
	
	
	@Override
	public void clear() {
		backingStore.clear();
		size=0;
		++modCount;
	}

	@Override
	public boolean containsKey(Object key) {
		for(Entry e : backingStore){
			if(key==null){
				if(e.key==null)
					return true;
			}
			else if(e.key.equals(key))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(Object value) {
		for(Entry e : backingStore){
			if(value==null){
				if(e.value==null)
					return true;
			}
			else if(e.value.equals(value))
				return true;
		}
		return false;
	}

	@Override
	public Set<java.util.Map.Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V get(Object key) {

		for(Entry e: backingStore){
			if(key==null){
				if(e.key==null)
					return (V) e.value;
			}
			else if(key.equals(e.key))
				return (V) e.value;
		}
		throw new NoSuchElementException();
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V put(K key, V value) {
	
			backingStore.add(new Entry(key,value));
			++size;
			++modCount;
		
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public V remove(Object key) {
		for(Entry e: backingStore){
			if(key==null){
				if(e==null){
					V toReturn = (V) e.value;
					backingStore.remove(e);
					return toReturn;
				}
			}
			else if (e.equals(key)){
				V toReturn = (V) e.value;
				backingStore.remove(e);
				return toReturn;
			}
				
		}
		throw new NoSuchElementException();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
