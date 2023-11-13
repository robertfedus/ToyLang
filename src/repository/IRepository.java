package repository;

import model.ProgramState;
import model.exceptions.ToyException;

public interface IRepository {
    ProgramState getCurrentProgramState();
    void add(ProgramState programState);

    void logProgramState() throws ToyException;
}
