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
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller {
    IRepository repository;
    private ExecutorService executor;

    public Controller(IRepository repository) {
        this.repository = repository;
    }

//    public void allSteps() throws ToyException {
//        ProgramState program = this.repository.getCurrentProgramState();
//        // Here you can display the prg state
////        System.out.println(program);
//        this.repository.logProgramState(program);
//        while (!program.getExecutionStack().isEmpty()){
//            try {
//                this.oneStep(program);
//            }
//            catch (ToyException ex)
//            {
//                System.out.println(ex.getMessage());
//            }
//            // Here you can display the prg state
////            System.out.println(program);
//            this.repository.logProgramState(program);
//
//            program.getHeap().setContent(garbageCollector(
//                    getAddressesFromTable(program.getSymbolTable().getContent().values()),
//                    getAddressesFromTable(program.getHeap().getContent().values()),
//                    program.getHeap().getContent()));
//
//            this.repository.logProgramState(program);
//        }
//    }

    public void allSteps() throws  ToyException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<ProgramState> programsList = removeCompletedPrograms(this.repository.getProgramStateList());

        while(!programsList.isEmpty()){
            conservativeGarbageCollector(programsList);

            programsList = removeCompletedPrograms(this.repository.getProgramStateList());
            this.oneStepForAllPrograms(programsList);
        }

        executor.shutdownNow();

        this.repository.setProgramStateList(programsList);
    }

    Map<Integer, Value> unsafeGarbageCollector(List<Integer> addresses, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(element -> addresses.contains(element.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

//    public Map<Integer, Value> garbageCollector(List<Integer> symbolTableAddresses, List<Integer> heapAddresses, Map<Integer,Value> heap)
//    {
//        return heap
//                .entrySet()
//                .stream()
//                .filter(e -> (symbolTableAddresses.contains(e.getKey()) || heapAddresses.contains(e.getKey())))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }

    void conservativeGarbageCollector(List<ProgramState> prgList) {
        List<Integer> adresses = Objects.requireNonNull(prgList.stream()
                .map(p -> getAddressesFromSymbolTable(
                        p.getSymbolTable().getContent().values(),
                        p.getHeap().getContent().values()))
                .map(Collection::stream)
                .reduce(Stream::concat).orElse(null)).collect(Collectors.toList());
        prgList.forEach(programState -> {
            programState.getHeap().setContent(
                    unsafeGarbageCollector(
                            adresses,
                            prgList.get(0).getHeap().getContent()
                    ));
        });
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

    List<Integer> getAddressesFromSymbolTable(Collection<Value> symTableValues, Collection<Value> heap) {
        return Stream.concat(
                        heap.stream()
                                .filter(v -> v instanceof ReferenceValue)
                                .map(v -> {
                                    ReferenceValue v1 = (ReferenceValue) v;
                                    return v1.getAddress();
                                })
                        ,symTableValues.stream()
                                .filter(v -> v instanceof ReferenceValue)
                                .map(v -> {
                                    ReferenceValue v1 = (ReferenceValue) v;
                                    return v1.getAddress();
                                }))

                .collect(Collectors.toList());
    }

    public List<ProgramState> removeCompletedPrograms(List<ProgramState> programList){
        return programList.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
    }

    void oneStepForAllPrograms(List<ProgramState> pogramsList) throws ToyException {
        pogramsList.forEach(program-> {
            try {
                repository.logProgramState(program);
            } catch (ToyException e) {
                System.out.println(e.getMessage());
            }
        });
        List <Callable<ProgramState>> callList = pogramsList.stream()
                .map((ProgramState p)->(Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());
        try {
            List<ProgramState> newProgramsList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (ExecutionException | InterruptedException e) {
                            System.out.println(e.getMessage());
                        }

                        return null;
                    })
                    .filter(Objects::nonNull)
                    .toList();

            pogramsList.addAll(newProgramsList);
        }
        catch(InterruptedException e) {
            throw new ToyException(e.getMessage());
        }

        pogramsList.forEach(prg -> {
            try {
                repository.logProgramState(prg);
            } catch (ToyException e) {
                System.out.println(e.getMessage());
            }
        });

        repository.setProgramStateList(pogramsList);
    }
}
