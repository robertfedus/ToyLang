package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.expressions.Expression;
import model.types.StringType;
import model.types.Type;
import model.values.StringValue;
import model.values.Value;
import utils.collections.ToyIDictionary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class OpenFileForReadStatement implements IStatement {
    private final Expression expression;

    public OpenFileForReadStatement(Expression expression) {
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

        if (fileTable.isDefined(stringValue)) {
            throw new ToyException("File " + stringValue.getValue() + " was already opened.");
        }

        try {
            Reader reader = new FileReader(stringValue.getValue());
            BufferedReader bufferedReader = new BufferedReader(reader);
            fileTable.update(stringValue, bufferedReader);
        }
        catch (FileNotFoundException e) {
            throw new ToyException(e.getMessage());
        }


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
