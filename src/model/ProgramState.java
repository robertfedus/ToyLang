package model;

import model.statements.IStatement;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIList;
import utils.collections.ToyIStack;

public class ProgramState {
    ToyIStack<IStatement> executionStack;
    ToyIDictionary<String, Value> symbolTable;
    ToyIList<Value> output;
    IStatement originalProgram; //optional field, but good to have

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

    public ProgramState(ToyIStack<IStatement> stk, ToyIDictionary<String,Value> symtbl, ToyIList<Value> ot, IStatement prg){
        this.executionStack = stk;
        this.symbolTable = symtbl;
        this.output = ot;
        //originalProgram=deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
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