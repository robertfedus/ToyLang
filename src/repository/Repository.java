package repository;

import model.ProgramState;
import model.exceptions.ToyException;
import model.statements.IStatement;
import model.values.Value;
import utils.collections.ToyIDictionary;
import utils.collections.ToyIList;
import utils.collections.ToyIStack;
import utils.collections.ToyStack;
import utils.collections.ToyList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Repository implements IRepository{
    List<ProgramState> programStateList;
    private String logFilePath;

    public Repository(String logFilePath) {
        this.logFilePath = logFilePath;
        this.programStateList = new ArrayList<>();
        // Clearing the log file
//        try {
//            PrintWriter writer = new PrintWriter(logFilePath);
//            writer.print("");
//            writer.close();
//        } catch(IOException e) {
//            System.err.print("Error clearing the log file.");
//        }
    }

    @Override
    public ProgramState getCurrentProgramState() {
        try {
            return this.programStateList.get(0);
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            return null;
        }
    }

    @Override
    public void add(ProgramState programState) {
        programStateList.add(programState);
    }

    @Override
    public void logProgramState(ProgramState state) throws ToyException {
        try {
            FileWriter fileWriter = new FileWriter(this.logFilePath, true);

            PrintWriter printWriter = new PrintWriter(fileWriter);

            ToyIStack<IStatement> executionStack = state.getExecutionStack();
            ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();
            ToyIList<Value> output = state.getOutput();

            printWriter.println("ExecutionStack");
            printWriter.println(executionStack);

            printWriter.println("SymbolTable");
            printWriter.println(symbolTable);

            printWriter.println("Output");
            printWriter.println(output);

            printWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing to the file.");
        }
    }

    @Override
    public String toString() {
        return "Repository{" +
                "programStateList=" + programStateList +
                '}';
    }
}
