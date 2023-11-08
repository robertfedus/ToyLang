package utils.collections;

import model.exceptions.ReadFromEmptyCollectionException;

public interface ToyIDictionary<K, V> {
    boolean isDefined(K key);

    void put(K key, V value);

    V lookup(K key) throws ReadFromEmptyCollectionException;

    void update(K key, V value);
}
