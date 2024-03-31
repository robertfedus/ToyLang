package model.statements;

import model.exceptions.ToyException;
import model.ProgramState;
import model.types.Type;
import utils.collections.ToyIDictionary;
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

        return null;
    }
//    public IStatement deepcopy() {
//        return new CompoundStatement(this.expression.deepcopy());
//    }

    @Override
    public ToyIDictionary<String, Type> typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException {
        return second.typecheck(first.typecheck(typeEnvironment));
    }

    public String toString() {
        return this.first.toString() + "\n" + this.second.toString();
    }
}
