package model.statements;

import model.exceptions.ToyException;
import model.ProgramState;
import utils.collections.ToyIStack;

public class CompoundStatement implements IStatement {
//    private String id;
    private final IStatement first;
    private final IStatement second;
//    private final Expression expression;

    public CompoundStatement(IStatement first, IStatement second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIStack<IStatement> stack = state.getExecutionStack();
        stack.push(this.second);
        stack.push(first);

        return state;
    }
//    public IStatement deepcopy() {
//        return new CompoundStatement(this.expression.deepcopy());
//    }

    public String toString() {
        return "(" + this.first.toString() + ";" + this.second.toString() + ")";
    }
}
