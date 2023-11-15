package utils.collections;

import model.exceptions.ReadFromEmptyCollectionException;
import utils.collections.ToyIList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ToyList<T> implements ToyIList<T> {
    private List<T> items;

    public ToyList() {
        items = new ArrayList<>();
    }

    public List<T> getItems() throws ReadFromEmptyCollectionException {
        if (this.items.isEmpty()) {
            throw new ReadFromEmptyCollectionException();
        }

        return this.items;
    }

    @Override
    public void add(T itemToAdd) {
        items.add(itemToAdd);
    }

    @Override
    public String toString() {
//        return "ToyList{" +
//                "items=" + items +
//                '}';
        StringBuilder result = new StringBuilder();

        // Iterate through the elements without modifying the list
        for (T currentElement : this.items) {
            result.append(currentElement);
            result.append("\n");
        }

        return result.toString();
    }
}
