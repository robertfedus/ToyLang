package model.statements;

import model.exceptions.ToyException;
import model.expressions.Expression;
import model.ProgramState;
import model.values.Value;
import model.types.Type;
import utils.collections.ToyIDictionary;

public class AssignStatement implements IStatement {
    private final String variableName;
    private final Expression expression;

    public AssignStatement(String name, Expression expression) {
        this.variableName = name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();

        if (symbolTable.isDefined(this.variableName)) {
            Value value = this.expression.eval(symbolTable, state.getHeap());

                Type typeId = symbolTable.lookup(this.variableName).getType();

            if (value.getType().equals(typeId)) {
                symbolTable.update(this.variableName, value);
            } else {
                throw new ToyException("Declared type of variable " + variableName + " and type of the assigned expression do not match.");
            }
        } else {
            throw new ToyException("The used variable " + variableName + " was not declared before.");
        }

        return state;
    }

    @Override
    public String toString() {
        return variableName +" = "+ this.expression.toString();
    }
}
