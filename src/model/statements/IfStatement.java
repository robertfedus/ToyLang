package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.expressions.Expression;
import model.values.BoolValue;
import utils.collections.ToyIStack;

public class IfStatement implements IStatement {
    private final Expression expression;
    private final IStatement thenStatement;
    private final IStatement elseStatement;

    public IfStatement(Expression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIStack<IStatement> stack = state.getExecutionStack();
        BoolValue expressionValue = (BoolValue)this.expression.eval(state.getSymbolTable(), state.getHeap());

        if (expressionValue.getValue()) {
            stack.push(thenStatement);
        }
        else {
            stack.push(elseStatement);
        }

        return state;
    }

    @Override
    public String toString() {
        return "(IF(" + this.expression.toString() + ") THEN(" + this.thenStatement.toString() + ")ELSE(" + this.elseStatement.toString() + "))";
    }
}
