import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @param <V>   {@inheritDoc}
 * @param <Key> {@inheritDoc}
 */
public class BinaryMinHeapImpl<Key extends Comparable<Key>, V> implements BinaryMinHeap<Key, V> {
    
    ArrayList<Entry<Key, V>> heap = new ArrayList<>();
    HashMap<V, Integer> values = new HashMap<V, Integer>();
    
    ArrayList<Entry<Key, V>> getArray() {
        return this.heap;
    }
    
    HashMap<V, Integer> getValues() {
        return this.values;
    }
    
    static int getRightChildIndex(int index) {
        return 2 * index + 2;
    }
    
    static int getLeftChildIndex(int index) {
        return 2 * index + 1;
    }
    
    static int getParentIndex(int index) {
        if (index == 0) {
            return 0;
        }
        if (index % 2 == 1) {
            return index / 2;
        } else {
            return (index - 1) / 2;
        }
        
    }
    
    void swapEntries(int index, int parentIndex) {
        
        Entry<Key, V> temp = heap.get(parentIndex);
        Entry<Key, V> k = heap.get(index);
        
        values.replace(temp.value, index);
        values.replace(k.value, parentIndex);
        
        heap.set(parentIndex, k);
        heap.set(index, temp);
        
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        if (heap.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) {
        if (values.containsKey(value)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Key key, V value) {
        if (key == null) {
            throw new IllegalArgumentException();
        } else if (this.containsValue(value)) {
            throw new IllegalArgumentException();
        } else {
            
            insert(key, value);
            
        }
    }
    
    // Helper method to insert a new entry into the heap
    void insert(Key k, V v) {
        
        heap.add(new Entry<>(k, v));
        int index = heap.size() - 1;
        int parentIndex = getParentIndex(index);
        
        swimUp(index, parentIndex, v);
        
    }
    
    // Helper method to swim up the entry after it is added to the end of the heap
    void swimUp(int index, int parentIndex, V value) {
        
        while (parentIndex != index && (heap.get(index).key.compareTo(heap.get(parentIndex).key) < 0
                )) {
            
            swapEntries(index, parentIndex);
            
            index = parentIndex;
            parentIndex = getParentIndex(index);
            
        }
        
        values.put(value, index);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void decreaseKey(V value, Key newKey) {
        if (!containsValue(value)) {
            throw new NoSuchElementException();
        } else if (newKey == null) {
            throw new IllegalArgumentException();
        } else {
            int oldKeyIndex = values.get(value);
            Entry<Key, V> oldKey = heap.get(oldKeyIndex);
            
            if (newKey.compareTo(oldKey.key) > 0) {
                throw new IllegalArgumentException();
            } else {
                
                Entry<Key, V> newEntry = new Entry<>(newKey, value);
                heap.set(oldKeyIndex, newEntry);
                
                int parentIndex = getParentIndex(oldKeyIndex);
                swimUp(oldKeyIndex, parentIndex, value);
                
            }
        }
        
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else {
            Entry<Key, V> minEntry = heap.get(0);
            
            return minEntry; 
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Entry<Key, V> extractMin() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        } else if (heap.size() == 1) {
            
            return heap.remove(0);
            
        } else {
            
            Entry<Key, V> lastEntry = heap.remove(heap.size() -  1);
            
            Entry<Key, V> minEntry = heap.remove(0);
            values.remove(minEntry.value, 0);
            heap.add(0, lastEntry);
            values.replace(lastEntry.value, 0);
            
            minHeapify(0);
            
            return minEntry;
            
        }
        
    }
    
    // Helper method to maintain the heap variants after an entry is removed
    void minHeapify(int parentIndex) {
        
        Entry<Key, V> currentEntry = heap.get(parentIndex);
        
        int lci = getLeftChildIndex(parentIndex);
        int rci = getRightChildIndex(parentIndex);
        
        int smallest = parentIndex;
        
        if (lci < heap.size()) {
            Entry<Key, V> left = heap.get(lci);
            
            if (left.key.compareTo(currentEntry.key) < 0) {
                smallest = lci;
            } else {
                smallest = parentIndex;
            }
        }
        
        if (rci < heap.size()) {
            Entry<Key, V> right = heap.get(rci);
            Entry<Key, V> smallestSoFar = heap.get(smallest);
            
            if (right.key.compareTo(smallestSoFar.key) < 0) {
                smallest = rci;
            }
        }
        
        if (smallest != parentIndex) {
            
            swapEntries(parentIndex, smallest);
            minHeapify(smallest);
            
        }
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Set<V> values() {
        return values.keySet();
    }
}