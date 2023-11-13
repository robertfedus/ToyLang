package repository;

import model.ProgramState;

import java.util.*;

public class Repository implements IRepository{
    List<ProgramState> programStateList;

    public Repository() {
        this.programStateList = new ArrayList<>();
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
    public void logProgramState() {
        
    }

    @Override
    public String toString() {
        return "Repository{" +
                "programStateList=" + programStateList +
                '}';
    }
}
