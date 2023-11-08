package model.exceptions;

public class VariableNotDefinedException extends ToyException {
    public VariableNotDefinedException(String variableName) {
        super("Variable " + variableName + " is not defined.");
    }
}
