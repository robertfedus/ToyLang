import controller.Controller;
import model.ProgramState;
import model.expressions.ArithmeticExpression;
import model.expressions.ValueExpression;
import model.expressions.VariableExpression;
import model.statements.*;
import model.types.BoolType;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
import repository.IRepository;
import repository.Repository;
import utils.collections.ToyDictionary;
import utils.collections.ToyStack;
import utils.collections.ToyList;
import view.ExitCommand;
import view.TextMenu;
import view.RunExample;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) {
        // int v; v = 2; Print(v);
        IStatement example1 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                new PrintStatement(new VariableExpression("v"))
                        )
                );

        ProgramState program1 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example1
        );

        IRepository repository1 = new Repository("log1.txt");
        repository1.add(program1);
        Controller controller1 = new Controller(repository1);

        // int a; int b; a = 2 + 3 * 5; b = a + 1; Print(b);
        IStatement example2 =
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("b", new IntType()),
                                new CompoundStatement(
                                        new AssignStatement(
                                                "a", new ArithmeticExpression(
                                                new ValueExpression(new IntValue(2)),
                                                new ArithmeticExpression(
                                                        new ValueExpression(new IntValue(3)),
                                                        new ValueExpression(new IntValue(5)),"*"
                                                ),"+"
                                        )
                                        ),
                                        new CompoundStatement(
                                                new AssignStatement(
                                                        "b", new ArithmeticExpression(
                                                        new VariableExpression("a"),
                                                        new ValueExpression(new IntValue(1)),"+"
                                                )
                                                ),
                                                new PrintStatement(new VariableExpression("b"))
                                        )
                                )
                        )
                );

        ProgramState program2 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example2
        );

        IRepository repository2 = new Repository("log2.txt");
        repository2.add(program2);
        Controller controller2 = new Controller(repository2);

        // bool a; int v; a = true; (If a Then v = 2 Else v = 3); Print(v)
        IStatement example3 =
                new CompoundStatement(
                        new VariableDeclarationStatement("a", new BoolType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("v", new IntType()),
                                new CompoundStatement(
                                        new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                        new CompoundStatement(
                                                new IfStatement(
                                                        new VariableExpression("a"),
                                                        new AssignStatement("v",
                                                                new ValueExpression(
                                                                        new IntValue(2)
                                                                )
                                                        ), new AssignStatement("v", new ValueExpression(new IntValue(3)))
                                                ), new PrintStatement(new VariableExpression("v"))
                                        )
                                )
                        )
                );

        ProgramState program3 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example3
        );

        IRepository repository3 = new Repository("log3.txt");
        repository3.add(program3);
        Controller controller3 = new Controller(repository3);

        // int v; v = 2; Print(v);
        IStatement example4 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                new PrintStatement(new VariableExpression("v"))
                        )
                );

        ProgramState program4 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example4
        );

        IRepository repository4 = new Repository("log4.txt");
        repository4.add(program4);
        Controller controller4 = new Controller(repository4);

        TextMenu menu = new TextMenu();

        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", example1.toString(), controller1));
        menu.addCommand(new RunExample("2", example2.toString(), controller2));
        menu.addCommand(new RunExample("3", example3.toString(), controller3));
        menu.addCommand(new RunExample("4", example4.toString(), controller4));
        menu.show();
    }
}