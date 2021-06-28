package dictionaries;

import java.util.Arrays;
public class Dictionary<K, V> implements DictionaryInterface<K, V>{
    /*TUPLE IMPLEMENTATION*/
    private Pair[] pairs;
    private int size;
    private static final int defaultCapacity = 20;
    private final boolean dynamicCapacity = true; // set to true if implementation supports resizing, false otherwise. 
    public Dictionary(int capacity){
        if(capacity < 0 || capacity > Integer.MAX_VALUE){
			pairs = new Pair[defaultCapacity];
        }else{
			pairs = new Pair[capacity];
        }
        /*TUPLE IMPLEMENTATION:*/
        size = 0;
    }
    public Dictionary(){
        this(defaultCapacity);
    }
    public int getSize(){ //checked
        return size;
    }
    public boolean isEmpty(){ //checked
        return size == 0;
    }
    public void add(K newKey, V newCon) throws NullPointerException, FullDictionaryException{ // how can we throw a FullLookupException??
        //test the full lookup exception by commenting out resize
        if(newKey == null) throw new NullPointerException();
        if(!uniqueKey(newKey)) throw new DuplicateKeyException();
        if(size == pairs.length && !dynamicCapacity) throw new FullDictionaryException("This Lookup is at capacity and does not support resizing.");
        ensureCapacity();
        /*TUPLE IMPLEMENTATION*/
        pairs[size++] = new Pair(newKey, newCon);
    }
    public V replace(K id, V newCon) throws KeyNotFoundException{
        int index = getIndexOf(id);
        if(index < 0) throw new KeyNotFoundException("Identifier not found");
        /*TUPLE IMPLEMENTATION*/
        V result = (V)pairs[index].getValue();
        pairs[index].setValue(newCon);
        return result;
    }
    public V getValue(K id) throws KeyNotFoundException{
        int index = getIndexOf(id);
        if(index < 0) throw new KeyNotFoundException();
        return (V)pairs[index].getValue();
    }
    public void remove(K id) throws NullPointerException, KeyNotFoundException{
        if(id == null) throw new NullPointerException();
        int index = getIndexOf(id);
        if(index < 0) throw new KeyNotFoundException();
        pairs[index] = pairs[size-1];
        pairs[--size] = null;
    }
    public Object[] remove(){
        if(isEmpty()) return null;
        Object[] result = new Object[2];
        result[0] = (K)pairs[size - 1].getKey();
        result[1] = (V)pairs[--size].getValue();
        pairs[size] = null;
        return result;
    }
    public void clear(){
        // while(!isEmpty()){
        //     remove();
        // }
        if(isEmpty()) return;
         pairs = new Pair[defaultCapacity];
    }
    public boolean contains(K id) throws NullPointerException{
        if(id == null) throw new NullPointerException();
        return getIndexOf(id) >= 0; 
    }
    public Object[] getAllKeys(){
        Object[] result = new Object[size];
        if(isEmpty()) return result;
        for(int i = 0; i < size; i++){
            result[i] = (K)pairs[i].getKey();
        }
        return result;
    }
	public boolean containsValue(V value){
		for(int i = 0; i < size; i++){
			if(value.equals(pairs[i].getValue())) return true;
		}
		return false;
	}
    private boolean uniqueKey(K id){
        for(int i = 0; i < size; i++){
            if(id.equals((K)pairs[i].getKey())) return false;
        }
        return true;
    }
    private int getIndexOf(K id){
        for(int i = 0; i < size; i++){
            if(id.equals((K)pairs[i].getKey())) return i; ////////////// I doesn't show error
        }
        return -1;
    }
    private void ensureCapacity(){
        if(size == pairs.length) upsizeArray();
    }
    private void upsizeArray(){
        pairs = Arrays.copyOf(pairs, pairs.length * 2);
    }

    private class Pair<K, V>{
        private K id;
        private V value;
        public Pair(K id, V value){
            this.id = id;
            this.value = value;
        }
        public V getValue(){
            return value;
        }
        public K getKey(){
            return id;
        }
        public void setValue(V newValue){
            this.value = newValue;
        }
    }
}

