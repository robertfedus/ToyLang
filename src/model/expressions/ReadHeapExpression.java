package model.expressions;

import model.exceptions.ToyException;
import model.types.ReferenceType;
import model.types.Type;
import model.values.ReferenceValue;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIHeap;

public class ReadHeapExpression implements Expression {
    private final Expression expression;

    public ReadHeapExpression(Expression expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(ToyIDictionary<String, Value> symbolTable, ToyIHeap<Value> heap) throws ToyException {
        Value value = this.expression.eval(symbolTable, heap);

        if (!(value instanceof ReferenceValue)) {
            throw new ToyException("Expression is not a ReferenceValue.");
        }

        ReferenceValue referenceValue = (ReferenceValue) value;

        if (!heap.contains(referenceValue.getAddress())) {
            throw new ToyException("Invalid heap address.");
        }

        return heap.get(referenceValue.getAddress());
    }

    @Override
    public Type typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException {
        Type type = expression.typecheck(typeEnvironment);
        if (type instanceof ReferenceType) {
            ReferenceType refType = (ReferenceType) type;
            return refType.getInner();
        }
        else {
            throw new ToyException("Expression is not of a reference type in " + this.toString());
        }
    }

    @Override
    public String toString() {
        return "ReadHeap(" + this.expression + ")";
    }
}
