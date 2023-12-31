package utils.collections;

import model.exceptions.ReadFromEmptyCollectionException;
import utils.collections.ToyIStack;

import java.util.*;

public class ToyStack<T> implements ToyIStack<T> {
    Stack<T> stack;

    public ToyStack() {
        stack = new Stack<T>();
    }
    @Override
    public void push(T elem) {
        stack.push(elem);
    }

    @Override
    public T pop() throws ReadFromEmptyCollectionException {
        if (this.stack.isEmpty()) {
            throw new ReadFromEmptyCollectionException();
        }

        return stack.pop();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public List<T> getReverse() {
        List<T> auxList = Arrays.asList((T[])stack.toArray());
        Collections.reverse(auxList);
        return auxList;
    }

    @Override
    public String toString() {
//        return "stack " + getReverse();
        StringBuilder result = new StringBuilder();

        for (T currentElement : this.stack) {
            result.append(currentElement);
            result.append("\n");
        }

        return result.toString();
    }
}
