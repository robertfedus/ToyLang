package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.expressions.Expression;
import model.types.StringType;
import model.types.Type;
import model.values.StringValue;
import model.values.Value;
import utils.collections.ToyIDictionary;

import java.io.*;

public class CloseFileForReadStatement implements IStatement {
    private Expression expression;

    public CloseFileForReadStatement(Expression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        Value expressionValue = this.expression.eval(symbolTable, state.getHeap());

        if (!expressionValue.getType().equals(new StringType())) {
            throw new ToyException("Expression was not evaluated to StringValue.");
        }

        ToyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();
        StringValue stringValue = (StringValue) expressionValue;

        if (!fileTable.isDefined(stringValue)) {
            throw new ToyException("File " + stringValue.getValue() + " was not opened before closing.");
        }

        BufferedReader bufferedReader = fileTable.lookup(stringValue);
        try {
            bufferedReader.close();
        } catch (IOException e) {
            throw new ToyException(e.getMessage());
        }

        fileTable.remove(stringValue);

        return null;
    }

    @Override
    public ToyIDictionary<String, Type> typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException {
        Type expType = expression.typecheck(typeEnvironment);

        if (expType.equals(new StringType())) {
            return typeEnvironment;
        }
        else {
            throw new ToyException("Expression " + this.toString() + " is not a string");
        }
    }

    @Override
    public String toString() {
        return "Open(" + this.expression + ")";
    }
}
