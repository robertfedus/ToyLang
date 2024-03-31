package model.statements;

import model.exceptions.ToyException;
import model.ProgramState;
import model.types.Type;
import utils.collections.ToyIDictionary;

public interface IStatement {
    public ProgramState execute(ProgramState state) throws ToyException;

    ToyIDictionary<String, Type> typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException;
}
