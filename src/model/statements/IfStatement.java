package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.expressions.Expression;
import model.types.BoolType;
import model.types.Type;
import model.values.BoolValue;
import utils.collections.ToyIDictionary;
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

        return null;
    }

    @Override
    public ToyIDictionary<String, Type> typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException {
        Type expressionType = expression.typecheck(typeEnvironment);

        if (expressionType.equals(new BoolType())) {
            thenStatement.typecheck(typeEnvironment.deepCopy());
            elseStatement.typecheck(typeEnvironment.deepCopy());
            return typeEnvironment;
        }
        else {
            throw new ToyException("Statement " + this.toString() + " is not a boolean");
        }
    }

    @Override
    public String toString() {
        return "(IF(" + this.expression.toString() + ") THEN(" + this.thenStatement.toString() + ")ELSE(" + this.elseStatement.toString() + "))";
    }
}
