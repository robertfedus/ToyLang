package model.expressions;

import model.exceptions.ToyException;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIHeap;

import java.util.Objects;

public class LogicalExpression implements Expression {
    Expression left;
    Expression right;
    String operator; // "and" - logical and, "or" - logical or

    public LogicalExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Value eval(ToyIDictionary<String, Value> symbolTable, ToyIHeap<Value> heap) throws ToyException {
        Value leftValue, rightValue;
        leftValue = left.eval(symbolTable, heap);

        if(leftValue.getType().equals(new BoolType())) {
            rightValue = right.eval(symbolTable, heap);

            if(rightValue.getType().equals(new BoolType())) {
                BoolValue booleanLeftValue = (BoolValue)leftValue;
                BoolValue booleanRightValue = (BoolValue)rightValue;

                boolean leftExpressionValue,rightExpressionValue;

                leftExpressionValue = booleanLeftValue.getValue();
                rightExpressionValue = booleanRightValue.getValue();

                if(Objects.equals(operator, "and")) {
                    return new BoolValue(leftExpressionValue && rightExpressionValue);
                }
                else if(Objects.equals(operator, "or")) {
                    return new BoolValue(leftExpressionValue || rightExpressionValue);
                }
                else {
                    throw new ToyException("Invalid operator.");
                }
            }
            else {
                throw new ToyException("Invalid operator.");
            }
        }
        else {
            throw new ToyException("Invalid operand.");
        }
    }

    @Override
    public String toString() {
        return this.left + " " + this.operator + " " + this.right;
    }
}