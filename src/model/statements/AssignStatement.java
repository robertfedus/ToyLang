package model.statements;

import model.exceptions.ReadFromEmptyCollectionException;
import model.exceptions.ToyException;
import model.expressions.Expression;
import model.ProgramState;
import model.values.Value;
import model.types.Type;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIStack;

public class AssignStatement implements IStatement {
    private final String name;
    private final Expression expression;

    public AssignStatement(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();

        if (symbolTable.isDefined(this.name)) {
            Value value = this.expression.eval(symbolTable);

                Type typeId = symbolTable.lookup(this.name).getType();

            if (value.getType().equals(typeId)) {
                symbolTable.update(this.name, value);
            } else {
                throw new ToyException("Declared type of variable " + name + " and type of the assigned expression do not match.");
            }
        } else {
            throw new ToyException("The used variable " + name + " was not declared before.");
        }

        return state;
    }

    @Override
    public String toString() {
        return name +" = "+ this.expression.toString();
    }
}
