package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.expressions.Expression;
import model.types.BoolType;
import model.values.BoolValue;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIStack;

public class WhileStatement implements IStatement {
    private Expression exp;
    private IStatement statement;

    public WhileStatement(Expression exp, IStatement statement) {
        this.exp = exp;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIStack<IStatement> stack = state.getExecutionStack();
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();

        Value value = exp.eval(symbolTable, state.getHeap());

        if (!value.getType().equals(new BoolType())) {
            throw new ToyException("While loop expects boolean expression.");
        }

        BoolValue boolValue = (BoolValue) value;
        if (boolValue.getValue()) {
            stack.push(this);
            stack.push(statement);
        }

        return state;
    }

    @Override
    public String toString() {
        return "(while (" + exp + ") " + statement + ")";
    }
}
