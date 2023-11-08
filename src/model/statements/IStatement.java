package model.statements;

import model.exceptions.ToyException;
import model.ProgramState;

public interface IStatement {
    public ProgramState execute(ProgramState state) throws ToyException;
}
