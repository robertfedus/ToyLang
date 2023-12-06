package utils.collections;

import model.exceptions.ReadFromEmptyCollectionException;
import model.exceptions.ToyException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ToyDictionary<K,V> implements ToyIDictionary<K,V> {
    private Map<K,V> dictionary;
    public ToyDictionary() {
        this.dictionary = new HashMap<>();
    }
    @Override
    public Map<K, V> getContent() {
        return this.dictionary;
    }
    @Override
    public boolean isDefined(K key) {
        return this.dictionary.containsKey(key);
    }

    @Override
    public void put(K key, V value) {
        this.dictionary.put(key, value);
    }

    @Override
    public void remove(K key) throws ToyException {
        if (!this.dictionary.containsKey(key)) {
            throw new ToyException("Key does not exist in the dictionary.");
        }

        this.dictionary.remove(key);
    }

    @Override
    public V lookup(K key) throws ReadFromEmptyCollectionException {
        if (this.dictionary.isEmpty()) {
            throw new ReadFromEmptyCollectionException();
        }

        return this.dictionary.get(key);
    }
    @Override
    public void update(K key, V value) {
        this.dictionary.put(key, value);
    }

    @Override
    public String toString() {
//        return "ToyDictionary{" +
//                "dictionary=" + dictionary +
//                '}';
        StringBuilder result = new StringBuilder();

        for (Map.Entry<K, V> currentEntry : this.dictionary.entrySet()) {
            result.append(currentEntry.getKey());
            result.append(" -> ");
            result.append(currentEntry.getValue());
            result.append("\n");
        }

        return result.toString();
    }
}
