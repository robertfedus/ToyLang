package model.expressions;

import model.exceptions.ToyException;
import model.values.Value;
import utils.collections.ToyIDictionary;

public interface Expression {
    Value eval(ToyIDictionary<String, Value> table) throws ToyException;
}
