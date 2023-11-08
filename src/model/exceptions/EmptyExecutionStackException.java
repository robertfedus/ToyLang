package model.exceptions;

public class EmptyExecutionStackException extends ToyException {
    public EmptyExecutionStackException() {
        super("There is nothing to execute. The execution stack is empty.");
    }
}
