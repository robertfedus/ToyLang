package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.types.BoolType;
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

        if (type.equals(new IntType())) {
            symbolTable.put(name, new IntValue(0));
        } else if (type.equals(new BoolType())) {
            symbolTable.put(name, new BoolValue(false));
        }

        return state;
    }
}
