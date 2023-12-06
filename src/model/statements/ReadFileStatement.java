package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.exceptions.VariableNotDefinedException;
import model.expressions.Expression;
import model.expressions.VariableExpression;
import model.types.IntType;
import model.types.StringType;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
import utils.collections.ToyIDictionary;

import java.io.*;

public class ReadFileStatement implements IStatement {
    private final Expression filePathExpression;
    private final Expression variableNameExpression;

    public ReadFileStatement(Expression expression, Expression variableName) {
        this.filePathExpression = expression;
        this.variableNameExpression = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        ToyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        VariableExpression variableNameVariableExpression = (VariableExpression) this.variableNameExpression;
        String variableNameString = variableNameVariableExpression.getName();

        if (!symbolTable.isDefined(variableNameString)) {
            throw new VariableNotDefinedException(variableNameString);
        }

        if (!symbolTable.lookup(variableNameString).getType().equals(new IntType())) {
            throw new ToyException("Variable " + variableNameString + " is not of type int.");
        }

        Value filePathExpressionValue = this.filePathExpression.eval(symbolTable, state.getHeap());

        if (!filePathExpressionValue.getType().equals(new StringType())) {
            throw new ToyException("File name is not StringValue.");
        }

        StringValue filePathStringValue = (StringValue) filePathExpressionValue;

        if (!fileTable.isDefined(filePathStringValue)) {
            throw new ToyException("File " + filePathStringValue.getValue() + " was not opened before reading.");
        }

        BufferedReader bufferedReader = fileTable.lookup(filePathStringValue);

        try {
            String line = bufferedReader.readLine();
            Value intValue;
            IntType type = new IntType();
            if (line == null) {
                intValue = type.defaultValue();
            } else {
                intValue = new IntValue(Integer.parseInt(line));
            }
            symbolTable.update(variableNameString, intValue);
        } catch (IOException e) {
            throw new ToyException(e.getMessage());
        }

        return state;
    }

    @Override
    public String toString() {
        return "Open(" + this.filePathExpression + ")";
    }
}
