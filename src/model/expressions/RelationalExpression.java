package model.expressions;

import model.exceptions.ToyException;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIHeap;

public class RelationalExpression implements Expression {
    Expression left;
    Expression right;
    String operator; // <, <=, ==, !=, >, >=

    public RelationalExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Value eval(ToyIDictionary<String, Value> symbolTable, ToyIHeap<Value> heap) throws ToyException {
        Value leftValue, rightValue;

        leftValue = this.left.eval(symbolTable, heap);

        if (leftValue.getType().equals(new IntType())) {
            rightValue = this.right.eval(symbolTable, heap);

            if (rightValue.getType().equals(new IntType())) {
                IntValue intLeft = (IntValue)leftValue;
                IntValue intRight = (IntValue)rightValue;

                int numberLeft, numberRight;

                numberLeft = intLeft.getValue();
                numberRight = intRight.getValue();

                switch (this.operator) {
                    case "<" :
                        return new BoolValue(numberLeft < numberRight);
                    case "<=" :
                        return new BoolValue (numberLeft <= numberRight);
                    case "==" :
                        return new BoolValue(intLeft.equals(intRight));
                    case "!=" :
                        return new BoolValue(!intLeft.equals(intRight));
                    case ">" :
                        return new BoolValue(numberLeft > numberRight);
                    case ">=" :
                        return new BoolValue(numberLeft >= numberRight);
                }
            } else {
                throw new ToyException("Second operand is not an integer.");
            }
        } else {
            throw new ToyException("First operand is not an integer.");
        }

        return new BoolValue(false);
    }

    @Override
    public String toString() {
        return this.left + " " + this.operator + " " + this.right;
    }
}
