package utils.collections;

import model.exceptions.ReadFromEmptyCollectionException;
import model.exceptions.ToyException;

public interface ToyIDictionary<K, V> {
    boolean isDefined(K key);

    void put(K key, V value);

   void remove(K key) throws ToyException;

    V lookup(K key) throws ReadFromEmptyCollectionException;

    void update(K key, V value);
}
