package model.expressions;

import model.exceptions.DivisionByZeroException;
import model.types.Type;
import model.values.Value;
import utils.collections.ToyIDictionary;
import model.exceptions.ToyException;
import model.types.IntType;
import model.values.IntValue;
import utils.collections.ToyIHeap;

public class ArithmeticExpression implements Expression {
    Expression left;
    Expression right;
    String operator; // "+" - addition, "-" - subtraction, "*" - multiplication, "/" - division

    public ArithmeticExpression(Expression left, Expression right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public Value eval(ToyIDictionary<String,Value> symbolTable, ToyIHeap<Value> heap) throws ToyException {
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
                    case "+": {
                        return new IntValue(numberLeft + numberRight);
                    }
                    case "-": {
                        return new IntValue(numberLeft - numberRight);
                    }
                    case "*": {
                        return new IntValue(numberLeft * numberRight);
                    }
                    case "/": {
                        if(numberRight == 0) {
                            throw new DivisionByZeroException();
                        }
                        else {
                            return new IntValue(numberLeft / numberRight);
                        }
                    }
                }
            } else {
                throw new ToyException("Second operand is not an integer.");
            }
        } else {
            throw new ToyException("First operand is not an integer.");
        }

        return new IntValue(-1);
    }

    @Override
    public Type typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException {
        Type type1 = left.typecheck(typeEnvironment);
        Type type2 = right.typecheck(typeEnvironment);

        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new IntType();
            }
            else {
                throw new ToyException("Types of operands do not match in " + this.toString());
            }
        }
        else {
            throw new ToyException("Types of operands do not match in " + this.toString());
        }
    }

    @Override
    public String toString() {
        return this.left + " " + this.operator + " " + this.right;
    }
}
