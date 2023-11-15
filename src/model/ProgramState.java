package model;

import model.statements.IStatement;
import model.values.StringValue;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIList;
import utils.collections.ToyIStack;

import java.io.BufferedReader;
import java.nio.Buffer;

public class ProgramState {
    ToyIStack<IStatement> executionStack;
    ToyIDictionary<String, Value> symbolTable;
    ToyIDictionary<StringValue, BufferedReader> fileTable;
    ToyIList<Value> output;
    IStatement originalProgram; //optional field, but good to have

    public ProgramState(
            ToyIStack<IStatement> executionStack,
            ToyIDictionary<String,Value> symbolTable,
            ToyIDictionary<StringValue, BufferedReader> fileTable,
            ToyIList<Value> output,
            IStatement program) {
        this.executionStack = executionStack;
        this.symbolTable = symbolTable;
        this.fileTable = fileTable;
        this.output = output;
        //originalProgram=deepCopy(prg);//recreate the entire original prg
        executionStack.push(program);
    }

    public ToyIStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public void setExecutionStack(ToyIStack<IStatement> executionStack) {
        this.executionStack = executionStack;
    }

    public ToyIDictionary<String, Value> getSymbolTable() {
        return symbolTable;
    }

    public void setSymbolTable(ToyIDictionary<String, Value> symbolTable) {
        this.symbolTable = symbolTable;
    }

    public ToyIDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public void setFileTable(ToyIDictionary<StringValue, BufferedReader> fileTable) {
        this.fileTable = fileTable;
    }

    public ToyIList<Value> getOutput() {
        return output;
    }

    public void setOutput(ToyIList<Value> output) {
        this.output = output;
    }

    public IStatement getOriginalProgram() {
        return originalProgram;
    }

    public void setOriginalProgram(IStatement originalProgram) {
        this.originalProgram = originalProgram;
    }

    @Override
    public String toString() {
        return "ProgramState{" +
                "exeStack=" + this.executionStack +
                ", symTable=" + this.symbolTable +
                ", out=" + this.output +
                '}';
    }
}