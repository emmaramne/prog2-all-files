package map;


public class SimpleHashMap<K,V> implements Map<K, V> {
	private Entry<K, V>[] table;
	private double loadfactor;
	private int size;

	private static class Entry<K, V> implements Map.Entry<K, V>{
		private K key;
		private V value;
		private Entry<K, V> next;
		
		public Entry(K k, V v) {
			key = k;
			value = v;
			next = null;
		}
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}
		
		@Override
		public String toString() {
			return key.toString() + "=" + value.toString();
		}

	}
	
	@SuppressWarnings("unchecked")
	public SimpleHashMap() {
		table = (Entry<K, V>[]) new Entry[16];
		loadfactor = 0.75;
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public SimpleHashMap(int capacity) {
		table = (Entry<K, V>[]) new Entry[capacity];
		loadfactor = 0.75;
		size = 0;
	}

	@Override @SuppressWarnings("unchecked")
	public V get(Object arg0) { 
		K arg1 = (K) arg0;
		int index = index(arg1);
		Entry<K,V> entry = find(index, arg1);
		if (entry != null) {
			return entry.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null) {
				return false;
			}
		}
		return true;
	}

	@Override
	public V put(K arg0, V arg1) {
		int index = index(arg0);
		Entry<K, V> entry = find(index, arg0);
		if (entry == null) {
			entry = new Entry<K, V>(arg0, arg1);
			entry.next = table[index];
			table[index] = entry;
			size++;
			if (table.length * loadfactor <= size()) {
				rehash(table.length*2);
			}
			return null;
		} else {
			V temp = entry.getValue();
			entry.setValue(arg1);
			return temp;
		}
	}
	
	private void rehash(int capacity) {
		Entry<K, V>[] old = table;
		table = (Entry<K,V>[]) new Entry[capacity];
		size = 0;
		for (int i = 0; i < old.length; i++) {
			Entry<K,V> entry = old[i];
			while(entry != null) {
				put(entry.getKey(), entry.getValue());
				entry = entry.next;
			}
		}
	}

	@Override
	public V remove(Object arg0) {
		K arg = (K) arg0;
		int index = index(arg);
		Entry<K, V> entry = table[index];
		if (entry != null) {
			if (entry.getKey().equals(arg)) {
				V temp = entry.getValue();
				table[index] = entry.next;
				size--;
				return temp;
			} else {
				Entry<K, V> former = entry;
				entry = entry.next;
				while (entry != null) {
					if (entry.getKey().equals(arg)) {
						V temp = entry.getValue();
						former.next = entry.next;
						size--;
						return temp;
					}
					former = entry;
					entry = entry.next;
				}
			}
		}
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	public String show() { //Lägg till index
		String s = "";
		for (int i = 0; i < table.length; i++) {
			s += i + ": ";
			Entry<K, V> entry = table[i];
			while (entry != null) {
				s += entry.toString() + " ";
				entry = entry.next;
			}
			s += "\n";
		}
		return s;
	}
	
	private int index(K key) { //Ändra
		int index = key.hashCode()%table.length;
		if (index < 0) {
			return -index;
		} else {
			return index;
		}
	}
	
	private Entry<K, V> find(int index, K key) {
		Entry<K, V> entry = table[index];
		while(entry != null) {
			if (entry.getKey().equals(key)) {
				return entry;
			}
			entry = entry.next;
		}
		return null;
	}

}
