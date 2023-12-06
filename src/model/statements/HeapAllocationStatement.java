package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.expressions.Expression;
import model.types.ReferenceType;
import model.values.ReferenceValue;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIHeap;
import utils.collections.ToyIStack;

public class HeapAllocationStatement implements IStatement {
    String variableName;
    Expression expression;

    public HeapAllocationStatement(String variableName, Expression expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIStack<IStatement> stack = state.getExecutionStack();
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        ToyIHeap<Value> heap = state.getHeap();

        if(!symbolTable.isDefined(this.variableName)) {
            throw new ToyException("Variable not declared.");
        }

        if(!(symbolTable.lookup(this.variableName).getType() instanceof ReferenceType)) {
            throw new ToyException("Type of variable is not a reference.");
        }

        Value value = this.expression.eval(symbolTable, heap);
        Value tableValue = symbolTable.lookup(this.variableName);

        if(!(value.getType().equals(((ReferenceType)(tableValue.getType())).getInner()))) {
            throw new ToyException("Incorrect value type.");
        }

        int address = heap.allocate(value);
        symbolTable.update(this.variableName, new ReferenceValue(address, value.getType()));

        state.setSymbolTable(symbolTable);
        state.setHeap(heap);
        state.setExecutionStack(stack);

        return state;
    }

    @Override
    public String toString(){
        return "new(" + this.variableName + ", " + this.expression + ")";
    }
}
