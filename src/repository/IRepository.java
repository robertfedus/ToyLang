package repository;

import model.ProgramState;
import model.exceptions.ToyException;

import java.util.List;

public interface IRepository {
//    ProgramState getCurrentProgramState();
    List<ProgramState> getProgramStateList();
    void setProgramStateList(List<ProgramState> newProgramStateList);
    void add(ProgramState programState);
    void logProgramState(ProgramState state) throws ToyException;
}
