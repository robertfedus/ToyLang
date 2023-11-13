package controller;

import model.exceptions.EmptyExecutionStackException;
import model.exceptions.ToyException;
import model.statements.IStatement;
import model.ProgramState;
import repository.IRepository;
import utils.collections.ToyIStack;

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
        ProgramState program = this.repository.getCurrentProgramState(); // repo is the controller field of type MyRepoInterface
        //here you can display the prg state
        System.out.println(program);
        while (!program.getExecutionStack().isEmpty()){
            try {
                this.oneStep(program);
            }
            catch (ToyException ex)
            {
                System.out.println(ex.getMessage());
            }
            //here you can display the prg state
        }
        System.out.println(program);
    }
}
