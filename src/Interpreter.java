import controller.Controller;
import model.ProgramState;
import model.exceptions.ToyException;
import model.expressions.*;
import model.statements.*;
import model.types.*;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.StringValue;
import model.values.Value;
import repository.IRepository;
import repository.Repository;
import utils.collections.*;
import view.ExitCommand;
import view.TextMenu;
import view.RunExample;

import java.io.BufferedReader;

public class Interpreter {
    public static void main(String[] args) throws ToyException {
        // int v; v = 2; Print(v);
        IStatement example1 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(2))),
                                new PrintStatement(new VariableExpression("v"))
                        )
                );
        example1.typecheck(new ToyDictionary<String, Type>());

        ProgramState program1 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
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
        example2.typecheck(new ToyDictionary<String, Type>());

        ProgramState program2 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
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
        example3.typecheck(new ToyDictionary<String, Type>());

        ProgramState program3 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example3
        );

        IRepository repository3 = new Repository("log3.txt");
        repository3.add(program3);
        Controller controller3 = new Controller(repository3);

        IStatement example4 =
                new CompoundStatement(
                        new VariableDeclarationStatement("filePath", new StringType()),
                        new CompoundStatement(
                                new AssignStatement("filePath", new ValueExpression(new StringValue("test.in"))),
                                new CompoundStatement(
                                        new OpenFileForReadStatement(new VariableExpression("filePath")),
                                        new CompoundStatement(
                                                new VariableDeclarationStatement("n", new IntType()),
                                                new CompoundStatement(
                                                        new ReadFileStatement(new VariableExpression("filePath"), new VariableExpression("n")),
                                                        new CompoundStatement(
                                                                new PrintStatement(new VariableExpression("n")),
                                                                new CompoundStatement(
                                                                        new ReadFileStatement(new VariableExpression("filePath"), new VariableExpression("n")),
                                                                        new CompoundStatement(
                                                                                new PrintStatement(new VariableExpression("n")),
                                                                                new CloseFileForReadStatement(new VariableExpression("filePath"))
                                                                        )
                                                                )
                                                        )
                                                )
                                        )
                                )

                        )
                );
        example4.typecheck(new ToyDictionary<String, Type>());

        ProgramState program4 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example4
        );

        IRepository repository4 = new Repository("log4.txt");
        repository4.add(program4);
        Controller controller4 = new Controller(repository4);

//      Example: Ref int v;new(v,20);Ref Ref int a; new(a,v);print(v);print(a)
//      At the end of execution: Heap={1->20, 2->(1,int)}, SymTable={v->(1,int), a->(2,Ref int)} and
//      Out={(1,int),(2,Ref int)}
        IStatement example5 =
            new CompoundStatement(
                new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                new CompoundStatement(
                    new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                    new CompoundStatement(
                        new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                        new CompoundStatement(
                            new HeapAllocationStatement("a", new VariableExpression("v")),
                            new CompoundStatement(
                                new PrintStatement(new VariableExpression("v")),
                                new PrintStatement(new VariableExpression("a"))
                            )
                        )
                    )
                )
            );
        example5.typecheck(new ToyDictionary<String, Type>());

        ProgramState program5 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example5
        );

        IRepository repository5 = new Repository("log5.txt");
        repository5.add(program5);
        Controller controller5 = new Controller(repository5);

//        Example: Ref int v;new(v,20);Ref Ref int a; new(a,v);print(rH(v));print(rH(rH(a))+5)
//        At the end of execution: Heap={1->20, 2->(1,int)}, SymTable={v->(1,int), a->(2,Ref int)} and
//        Out={20, 25}
        IStatement example6 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("a", new VariableExpression("v")),
                                                new CompoundStatement(
                                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                                        new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))), new ValueExpression(new IntValue(5)), "+"))
                                                )
                                        )
                                )
                        )
                );
        example6.typecheck(new ToyDictionary<String, Type>());

        ProgramState program6 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example6
        );

        IRepository repository6 = new Repository("log6.txt");
        repository6.add(program6);
        Controller controller6 = new Controller(repository6);

//        Example: Ref int v;new(v,20);print(rH(v)); wH(v,30);print(rH(v)+5);
//        At the end of execution: Heap={1->30}, SymTable={v->(1,int)} and
//        Out={20, 35}
        IStatement example7 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new PrintStatement(new ReadHeapExpression(new VariableExpression("v"))),
                                        new CompoundStatement(
                                                new WriteHeapStatement("v", new ValueExpression(new IntValue(30))),
                                                new PrintStatement(new ArithmeticExpression(new ReadHeapExpression(new VariableExpression("v")), new ValueExpression(new IntValue(5)), "+"))
                                        )
                                )
                        )
                );
        example7.typecheck(new ToyDictionary<String, Type>());

        ProgramState program7 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example7
        );

        IRepository repository7 = new Repository("log7.txt");
        repository7.add(program7);
        Controller controller7 = new Controller(repository7);

//        Ref int v;new(v,20);Ref Ref int a; new(a,v); new(v,30);print(rH(rH(a)))
        IStatement example8 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new ReferenceType(new IntType())),
                        new CompoundStatement(
                                new HeapAllocationStatement("v", new ValueExpression(new IntValue(20))),
                                new CompoundStatement(
                                        new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntType()))),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("a", new VariableExpression("v")),
                                                new CompoundStatement(
                                                    new HeapAllocationStatement("v", new ValueExpression(new IntValue(30))),
                                                    new PrintStatement(new ReadHeapExpression(new ReadHeapExpression(new VariableExpression("a"))))
                                                )
                                        )
                                )
                        )
                );
        example8.typecheck(new ToyDictionary<String, Type>());

        ProgramState program8 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example8
        );

        IRepository repository8 = new Repository("log8.txt");
        repository8.add(program8);
        Controller controller8 = new Controller(repository8);

//      int v; v=4; (while (v>0) print(v);v=v-1);print(v)
        IStatement example9 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v", new IntType()),
                        new CompoundStatement(
                                new AssignStatement("v", new ValueExpression(new IntValue(4))),
                                new CompoundStatement(
                                    new WhileStatement(
                                        new RelationalExpression(new VariableExpression("v"), new ValueExpression(new IntValue(0)), ">"),
                                        new CompoundStatement(
                                                new PrintStatement(new VariableExpression("v")),
                                                new AssignStatement("v", new ArithmeticExpression(new VariableExpression("v"), new ValueExpression(new IntValue(1)), "-"))
                                        )

                                    ),
                                    new PrintStatement(new VariableExpression("v"))
                                )
                        )
                );
        example9.typecheck(new ToyDictionary<String, Type>());

        ProgramState program9 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example9
        );

        IRepository repository9 = new Repository("log9.txt");
        repository9.add(program9);
        Controller controller9 = new Controller(repository9);

        IStatement example10 =
                new CompoundStatement(
                        new VariableDeclarationStatement("v",new IntType()),
                        new CompoundStatement(
                                new VariableDeclarationStatement("a",new ReferenceType(new IntType())),
                                new CompoundStatement(
                                        new AssignStatement("v",new ValueExpression(new IntValue(10))),
                                        new CompoundStatement(
                                                new HeapAllocationStatement("a",new ValueExpression(new IntValue(22))),
                                                new CompoundStatement
                                                        (new ForkStatement(
                                                                new CompoundStatement(
                                                                        new WriteHeapStatement("a", new ValueExpression(new IntValue(30))),
                                                                        new CompoundStatement(
                                                                                new AssignStatement("v",new ValueExpression(new IntValue(32))),
                                                                                new CompoundStatement(
                                                                                        new PrintStatement(new VariableExpression("v")),new PrintStatement(new ReadHeapExpression(new VariableExpression("a")))
                                                                        )
                                                                )
                                                        )),
                                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")),new PrintStatement(new ReadHeapExpression(new VariableExpression("a"))))
                                                )
                                        )
                                )
                        )
                );
        example10.typecheck(new ToyDictionary<String, Type>());

        ProgramState program10 = new ProgramState(
                new ToyStack<>(),
                new ToyDictionary<String, Value>(),
                new ToyHeap<Value>(),
                new ToyDictionary<StringValue, BufferedReader>(),
                new ToyList<Value>(),
                example10
        );

        IRepository repository10 = new Repository("log10.txt");
        repository10.add(program10);
        Controller controller10 = new Controller(repository10);

        TextMenu menu = new TextMenu();

        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", example1.toString(), controller1));
        menu.addCommand(new RunExample("2", example2.toString(), controller2));
        menu.addCommand(new RunExample("3", example3.toString(), controller3));
        menu.addCommand(new RunExample("4", example4.toString(), controller4));
        menu.addCommand(new RunExample("5", example5.toString(), controller5));
        menu.addCommand(new RunExample("6", example6.toString(), controller6));
        menu.addCommand(new RunExample("7", example7.toString(), controller7));
        menu.addCommand(new RunExample("8", example8.toString(), controller8));
        menu.addCommand(new RunExample("9", example9.toString(), controller9));
        menu.addCommand(new RunExample("10", example10.toString(), controller10));

        menu.show();
    }
}