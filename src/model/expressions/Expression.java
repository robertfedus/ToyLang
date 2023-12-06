package model.expressions;

import model.exceptions.ToyException;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIHeap;

public interface Expression {
    Value eval(ToyIDictionary<String, Value> table, ToyIHeap<Value> heap) throws ToyException;
}
