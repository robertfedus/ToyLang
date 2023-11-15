
package view;

import java.util.HashMap;
import java.util.Scanner;

public class TextMenu {
    private HashMap<String, Command> commands;

    public TextMenu() {
        this.commands = new HashMap<String, Command>();
    }

    public void addCommand(Command command) {
        commands.put(command.getKey(), command);
    }

    private void printMenu() {
        for (Command command: commands.values()) {
            String line = String.format("Option %s:\n\n%s\n", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);

        this.printMenu();

        while (true) {
            System.out.print("Type a number corresponding to the program you want to run: ");

            String key = scanner.nextLine();

            Command command = this.commands.get(key);

            if (command == null) {
                System.out.println("Invalid option.");
            }
            else {
                command.execute();
            }
        }
    }
}