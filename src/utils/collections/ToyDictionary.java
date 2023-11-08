package utils.collections;

import model.exceptions.ReadFromEmptyCollectionException;

import java.util.HashMap;
import java.util.Map;

public class ToyDictionary<K,V> implements ToyIDictionary<K,V> {

    Map<K,V> dictionary;
    public ToyDictionary() {
        dictionary = new HashMap<>();
    }
    @Override
    public boolean isDefined(K key) {
        return dictionary.containsKey(key);
    }

    @Override
    public void put(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public V lookup(K key) throws ReadFromEmptyCollectionException {
        if (dictionary.isEmpty()) {
            throw new ReadFromEmptyCollectionException();
        }

        return dictionary.get(key);
    }
    @Override
    public void update(K key, V value) {
        dictionary.put(key, value);
    }

    @Override
    public String toString() {
        return "ToyDictionary{" +
                "dictionary=" + dictionary +
                '}';
    }
}
