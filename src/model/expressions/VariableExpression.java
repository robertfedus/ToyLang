package model.expressions;

import model.exceptions.ToyException;
import model.exceptions.VariableNotDefinedException;
import model.values.Value;
import utils.collections.ToyIDictionary;

public class VariableExpression implements Expression {
   private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Value eval(ToyIDictionary<String,Value> symbolTable) throws ToyException {
        if (!symbolTable.isDefined(this.name)) {
            throw new VariableNotDefinedException(this.name);
        }
        return symbolTable.lookup(this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
