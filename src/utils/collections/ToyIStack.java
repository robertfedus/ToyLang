package utils.collections;

import model.exceptions.ReadFromEmptyCollectionException;

import java.util.List;

public interface ToyIStack<T> {
    void push(T elem);
    T pop() throws ReadFromEmptyCollectionException;
    boolean isEmpty();
    List<T> getReverse();
}
