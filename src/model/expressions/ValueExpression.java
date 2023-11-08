package model.expressions;

import model.exceptions.ToyException;
import model.values.Value;
import utils.collections.ToyIDictionary;

public class ValueExpression implements Expression {
    Value expression;

    public ValueExpression(Value expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(ToyIDictionary<String,Value> symbolTable) throws ToyException {
        return this.expression;
    }
}
