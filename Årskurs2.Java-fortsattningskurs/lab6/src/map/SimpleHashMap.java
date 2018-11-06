package map;

public class SimpleHashMap<K, V> implements Map<K, V> {
	private int size;
	private Entry<K,V>[] v;
	private double loadFactor;
	
	/** Constructs an empty hashmap with the default initial capacity (16)
	and the default load factor (0.75). */
	@SuppressWarnings("unchecked")
	public SimpleHashMap() {
		v = (Entry<K,V>[]) new Entry[16];
		size = 0;
		loadFactor = 0.75;
		
	}
	
	/** Constructs an empty hashmap with the specified initial capacity
	and the default load factor (0.75). */
	@SuppressWarnings("unchecked")
	public SimpleHashMap(int capacity) {
		v = (Entry<K,V>[]) new Entry[capacity];
		loadFactor = 0.75;
	}
	
	/*För att kunna kontrollera att informationen lagras på rätt sätt ska du i klassen SimpleHashMap
	skriva en metod String show() som ger en sträng med innehållet på varje position i tabellen
	på egen rad.*/
	
	public String show() {
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < v.length; i++) {
			if(v[i] != null) {
				sb.append(i + "\t" + v[i].toString());
				
				Entry<K,V> temp = v[i].next;
				
				while (temp != null) {
					sb.append(" " + temp.toString());
					temp = temp.next;
				}
				
				sb.append("\n");
			}
			
		}
		return sb.toString();
	}
	
	/*ska returnera det index som ska användas för nyckeln key*/
	private int index(K key) {
		int hashCode = key.hashCode();
		int index = hashCode % v.length;
		return Math.abs(index);
	}
	
	/*ska returnera det Entry-par som har nyckeln key i listan som finns
	på position index i tabellen. Om det inte finns något sådant ska metoden returnera null.*/
	private Entry<K,V> find(int index, K key){
		
		//kolla första elementet
		if(v[index] == null) {
			return null;
		}

		//är det det samma returnera objektet
		if(v[index].getKey().equals(key)) {
			return v[index];
		}
		//är det inte samma kolla nästa 
		else {
			Entry<K,V> newTemp = v[index].next;
				
			while(newTemp != null) {
				if(newTemp.getKey().equals(key)) {
					return newTemp;
				}
				newTemp = newTemp.next;
			}
			return null;
		}
		
	}
	

	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key) {
		K tempKey = (K) key;
		int index = index(tempKey);
		Entry<K,V> tempEntry = find(index,tempKey);
		if(tempEntry == null) {
			return null;
		}
		else {
			return tempEntry.getValue();
		}
		
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}
	

	@SuppressWarnings("unchecked")
	private void rehash(){
		Entry<K,V>[] oldV = v;
		
		int vSize = v.length * 2;
		
		v = (Entry<K,V>[]) new Entry[vSize];
		
		size = 0;
		
		for(int i = 0; i < oldV.length; i++) {
			Entry<K,V> temp = oldV[i];
			
			if (temp != null) {
				put(temp.getKey(), temp.getValue());
				
				while(temp.next != null) {
					put(temp.next.getKey(), temp.next.getValue());
					temp = temp.next;
				}
				
			}

		}

	}

	@Override
	
	/*Om det fanns ett gammalt värde ska detta returneras. Annars returneras null. 
	 * Tänk på att fyllnadsgraden inte ska överstiga 0.75 och öka
	kapaciteten om så är fallet. Det är lämpligt att skriva en privat metod rehash för detta.*/
	public V put(K key, V value) {
		//kolla dubletter
		int index = index(key);
		Entry<K,V> temp = find(index ,key);
		
		if (temp == null){
			//Tänk på att fyllnadsgraden inte ska överstiga 0.75 och öka
			//kapaciteten om så är fallet. Det är lämpligt att skriva en privat metod rehash för detta.
			if((double) ((size +2.0)/v.length) > loadFactor) {
				rehash();
			}
			
			//ta reda på index plats för key
			int i = index(key);
			
			//kolla om v[i] == null, isf ska v[i] = new Entry(key,value);
			if(v[i] == null) {
				v[i] = new Entry<K, V>(key,value);
			}
			
			else {
				Entry<K,V> newTemp = v[i];
				
				while(newTemp.next != null) {
					newTemp = newTemp.next;
				}
				
				newTemp.next = new Entry<K,V>(key,value);
			}
			size++;
			return null;
			
		}
		V tempValue = temp.getValue();
		temp.setValue(value);
		//Om det fanns ett gammalt värde ska detta returneras.
		return tempValue;
	}
	@SuppressWarnings("unchecked")
	@Override
	public V remove(Object key) {
		//ta reda på index för key
		int index = index((K)key);
		//spara undan första entry i listan
		Entry<K,V> temp = v[index];
		
		//om första entry inte är null
		if(temp != null) {
			//kolla om key finns i första entry i listan.
			if(v[index].getKey().equals(key)) {
				//första entry blir nästa entry
				v[index] = temp.next;
				//returnera det gamla värdet
				size--;
				return temp.value;
			}
			//key finns senare i listan.
			else {
				while(temp.next != null){
					
					//om nästa entry innehåller key
					if(temp.next.getKey().equals(key)){
						//sparar undan värdet för nästa entry
						V tempV = temp.next.value;
						//temp.next länkar till nästa entry
						temp.next = temp.next.next;
						size--;
						return tempV;
					}
					temp = temp.next;
				}
			}
		}
		// key finns inte i listan.
		// Listan är null.
		return null;
	}

	@Override
	public int size() {
		return size;
	}
	
	private static class Entry<K,V> implements Map.Entry<K,V> {
		private K key;
		private V value;
		private Entry<K,V> next;
		
		
		public Entry(K key, V value) {
			super();
			this.key = key;
			this.value = value;
			next = null;
		}
		
		@Override
		public K getKey() {
			return key;
		}
		@Override
		public V getValue() {
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
			return key.toString() + " = " + value.toString();
		}
		
	
	}

}
