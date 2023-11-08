package model.exceptions;

public class ReadFromEmptyCollectionException extends ToyException {
    public ReadFromEmptyCollectionException() {
        super("The collection is empty.");
    }
}
