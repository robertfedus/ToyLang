package utils.collections;

import model.exceptions.ReadFromEmptyCollectionException;
import model.exceptions.ToyException;

import java.util.Map;

public interface ToyIDictionary<K, V> {
    public Map<K, V> getContent();
    boolean isDefined(K key);
    void put(K key, V value);
   void remove(K key) throws ToyException;
    V lookup(K key) throws ReadFromEmptyCollectionException;
    void update(K key, V value);
}
