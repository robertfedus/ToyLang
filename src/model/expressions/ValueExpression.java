package model.expressions;

import model.exceptions.ToyException;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIHeap;

public class ValueExpression implements Expression {
    Value expression;
    public ValueExpression(Value expression) {
        this.expression = expression;
    }
    @Override
    public Value eval(ToyIDictionary<String,Value> symbolTable, ToyIHeap<Value> heap) throws ToyException {
        return this.expression;
    }

    @Override
    public String toString() {
        return this.expression.toString();
    }
}
