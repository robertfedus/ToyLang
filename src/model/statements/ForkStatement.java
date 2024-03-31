package model.statements;

import model.ProgramState;
import model.exceptions.ToyException;
import model.types.Type;
import model.values.StringValue;
import model.values.Value;
import utils.collections.*;

import java.io.BufferedReader;
import java.util.Map;

public class ForkStatement implements IStatement {
    private IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    public ProgramState execute(ProgramState state) throws ToyException {
        ToyIDictionary<String, Value> symbolTable = state.getSymbolTable();
        ToyIHeap<Value> heap = state.getHeap();
        ToyIList<Value> output = state.getOutput();
        ToyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        ToyIStack<IStatement> newStack = new ToyStack<IStatement>();
        ToyIDictionary<String, Value> newSymbolTable = new ToyDictionary<String, Value>();

        for (Map.Entry<String, Value> entry: symbolTable.getContent().entrySet()) {
            newSymbolTable.update(entry.getKey(), entry.getValue().deepCopy());
        }

        return new ProgramState(newStack, newSymbolTable, heap, fileTable, output, this.statement);
    }

    @Override
    public ToyIDictionary<String, Type> typecheck(ToyIDictionary<String, Type> typeEnvironment) throws ToyException {
        statement.typecheck(typeEnvironment.deepCopy());

        return typeEnvironment;
    }

    @Override
    public String toString() {
        return "fork(" + this.statement + ")";
    }
}
