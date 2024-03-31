package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.expressions.Expression;
import model.types.ReferenceType;
import model.types.Type;
import model.values.ReferenceValue;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIHeap;

public class WriteHeapStatement implements IStatement {
    private final String variableName;
    private final Expression expression;

    public WriteHeapStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        ToyIHeap<Value> heap = state.getHeap();

        if (!symbolTable.isDefined(this.variableName)) {
            throw new ToyException(this.variableName + " is not defined.");
        }

        if (!(symbolTable.lookup(this.variableName).getType() instanceof ReferenceType)) {
            throw new ToyException(this.variableName + " is not a reference variable.");
        }

        ReferenceValue referenceValue = (ReferenceValue) symbolTable.lookup(this.variableName);

        if (!heap.contains(referenceValue.getAddress())) {
            throw new ToyException("Invalid heap address.");
        }

        Value value = expression.eval(symbolTable, heap);

        if (!(symbolTable.lookup(variableName).getType().equals(new ReferenceType(value.getType())))) {
            throw new ToyException("Expression type is different from pointer type.");
        }

        int address = referenceValue.getAddress();
        heap.update(address, value);

        return null;
    }

    @Override
    public ToyIDictionary<String, Type> typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException {
        if (typeEnvironment.isDefined(variableName)) {
            Type variableType = typeEnvironment.lookup(variableName);
            Type expType = expression.typecheck(typeEnvironment);

            if (!(variableType instanceof ReferenceType)) {
                throw new ToyException("File name in " + this.toString() + " is not a string");
            }
            if (!variableType.equals(new ReferenceType(expType))) {
                throw new ToyException("Types of operands do not match in " + this.toString());
            }
            return typeEnvironment;
        }
        else {
            throw new ToyException("Variable " + variableName + " is undefined");
        }
    }

    @Override
    public String toString() {
        return "WriteHeap(" + variableName + ", " + expression + ")";
    }
}
