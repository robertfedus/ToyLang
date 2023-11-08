package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;

public class NoOperationStatement implements IStatement {
    public NoOperationStatement() {
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        return state;
    }
}
