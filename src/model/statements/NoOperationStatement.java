package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.types.Type;
import utils.collections.ToyIDictionary;

public class NoOperationStatement implements IStatement {
    public NoOperationStatement() {
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        return null;
    }

    @Override
    public ToyIDictionary<String, Type> typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException {
        return typeEnvironment;
    }
}
