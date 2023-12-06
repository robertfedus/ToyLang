package controller;

import model.exceptions.EmptyExecutionStackException;
import model.exceptions.ToyException;
import model.statements.IStatement;
import model.ProgramState;
import model.values.ReferenceValue;
import model.values.Value;
import repository.IRepository;
import utils.collections.ToyIStack;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Controller {
    IRepository repository;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    private ProgramState oneStep(ProgramState state) throws ToyException {
        ToyIStack<IStatement> stack = state.getExecutionStack();
        if (stack.isEmpty()) throw new EmptyExecutionStackException();

        IStatement currentStatement = stack.pop();

        return currentStatement.execute(state);
    }
    public void allSteps() throws ToyException {
        ProgramState program = this.repository.getCurrentProgramState();
        // Here you can display the prg state
//        System.out.println(program);
        this.repository.logProgramState(program);
        while (!program.getExecutionStack().isEmpty()){
            try {
                this.oneStep(program);
            }
            catch (ToyException ex)
            {
                System.out.println(ex.getMessage());
            }
            // Here you can display the prg state
//            System.out.println(program);
            this.repository.logProgramState(program);

            program.getHeap().setContent(garbageCollector(
                    getAddressesFromTable(program.getSymbolTable().getContent().values()),
                    getAddressesFromTable(program.getHeap().getContent().values()),
                    program.getHeap().getContent()));

            this.repository.logProgramState(program);
        }
    }

    Map<Integer, Value> unsafeGarbageCollector(List<Integer> addresses, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(element -> addresses.contains(element.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public Map<Integer, Value> garbageCollector(List<Integer> symbolTableAddresses, List<Integer> heapAddresses, Map<Integer,Value> heap)
    {
        return heap
                .entrySet()
                .stream()
                .filter(e -> (symbolTableAddresses.contains(e.getKey()) || heapAddresses.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Method used both for symbol table and heap
    List<Integer> getAddressesFromTable(Collection<Value> table) {
        return table
                .stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> {
                    ReferenceValue v1 = (ReferenceValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }
}
