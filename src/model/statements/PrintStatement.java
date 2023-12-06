package model.statements;

import model.exceptions.ToyException;
import model.expressions.Expression;
import model.ProgramState;
import model.values.Value;
import utils.collections.ToyIList;

public class PrintStatement implements IStatement {
    private final Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIList<Value> output = state.getOutput();

        output.add(this.expression.eval(state.getSymbolTable(), state.getHeap()));

        return state;
    }

    @Override
    public String toString() {
        return "print(" + this.expression + ");";
    }
}
