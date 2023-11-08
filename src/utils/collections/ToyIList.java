package utils.collections;

import model.exceptions.ReadFromEmptyCollectionException;

import java.util.List;

public interface ToyIList<T> {
    void add(T itemToAdd);
    List<T> getItems() throws ReadFromEmptyCollectionException;
}
