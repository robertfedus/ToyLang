package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.types.Type;
import model.values.Value;
import utils.collections.ToyIDictionary;

public class VariableDeclarationStatement implements IStatement {
    private final String variableName;
    private final Type type;

    public VariableDeclarationStatement(String name, Type type) {
        this.variableName = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        symbolTable.put(this.variableName, this.type.defaultValue());

        return null;
    }

    @Override
    public ToyIDictionary<String, Type> typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException {
        if (typeEnvironment.isDefined(variableName)) {
            throw new ToyException("Variable " + variableName + " already defined");
        }

        typeEnvironment.update(variableName, type);

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "Declare " + this.variableName;
    }
}
