package model.exceptions;

public class DivisionByZeroException extends ToyException {
    public DivisionByZeroException() {
        super("Division by zero.");
    }
}
