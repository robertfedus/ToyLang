package view;

import controller.Controller;
import model.exceptions.ToyException;
import model.values.Value;

import java.io.IOException;

public class RunExample extends Command {
    private final Controller controller;

    public RunExample(String key, String description, Controller controller) {
        super(key, description);
        this.controller = controller;
    }

    @Override
    public void execute() {
        try {
            controller.allSteps();
        }
        catch (ToyException e) {
            System.out.println(e.getMessage());
        }
    }
}