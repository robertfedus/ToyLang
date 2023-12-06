package utils.collections;

import model.exceptions.ToyException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ToyHeap<V> implements ToyIHeap<V> {
    private Map<Integer, V> map;
    private AtomicInteger freeLocation;

    public ToyHeap() {
        this.map = new ConcurrentHashMap<Integer, V>();
        this.freeLocation = new AtomicInteger(0);
    }

    @Override
    public int allocate(V value) {
        int newLocation = freeLocation.incrementAndGet();

        map.put(newLocation, value);

        return newLocation;
    }

    @Override
    public void update(int address, V value) {
        map.put(address, value);
    }

    @Override
    public V get(int address) {
        return map.get(address);
    }

    @Override
    public void deallocate(int address) {
        map.remove(address);
    }

    @Override
    public boolean contains(int address) {
        return map.containsKey(address);
    }

    @Override
    public Map<Integer, V> getContent() {
        return map;
    }

    @Override
    public void setContent(Map<Integer, V> content) {
        map = content;
    }

    @Override
    public void add(Integer id, V value) throws ToyException {
        if (map.containsKey(id)) {
            throw new ToyException("Element already exists in heap.");
        }

        map.put(id, value);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<Integer, V> currentEntry : this.map.entrySet()) {
            result.append(currentEntry.getKey());
            result.append(" -> ");
            result.append(currentEntry.getValue());
            result.append("\n");
        }

        return result.toString();
    }
}