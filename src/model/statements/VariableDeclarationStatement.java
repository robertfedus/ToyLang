package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.types.BoolType;
import model.types.StringType;
import model.types.Type;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;
import utils.collections.ToyIDictionary;

public class VariableDeclarationStatement implements IStatement {
    private final String name;
    private final Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        symbolTable.put(this.name, this.type.defaultValue());

        return state;
    }

    @Override
    public String toString() {
        return "Declare " + this.name;
    }
}
