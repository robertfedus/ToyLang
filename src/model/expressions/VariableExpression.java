package model.expressions;

import model.exceptions.ToyException;
import model.exceptions.VariableNotDefinedException;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIHeap;

public class VariableExpression implements Expression {
   private final String name;

    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public Value eval(ToyIDictionary<String,Value> symbolTable, ToyIHeap<Value> heap) throws ToyException {
        if (!symbolTable.isDefined(this.name)) {
            throw new VariableNotDefinedException(this.name);
        }
        return symbolTable.lookup(this.name);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
