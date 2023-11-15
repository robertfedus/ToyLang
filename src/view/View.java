//package view;
//
//import controller.Controller;
//import model.exceptions.ToyException;
//import model.expressions.ArithmeticExpression;
//import model.expressions.ValueExpression;
//import model.expressions.VariableExpression;
//import model.statements.*;
//import model.ProgramState;
//import model.types.BoolType;
//import model.types.IntType;
//import model.types.StringType;
//import model.values.BoolValue;
//import model.values.IntValue;
//import model.values.StringValue;
//import model.values.Value;
//import repository.IRepository;
//import repository.Repository;
//import utils.collections.*;
//
//import java.io.BufferedReader;
//import java.util.Scanner;
//
//public class View {
//
//
//    // int a; int b; a = 2 + 3 * 5; b = a + 1; Print(b);
//    private final IStatement ex2 =
//
//
//    // bool a; int v; a = true; (If a Then v = 2 Else v = 3); Print(v)
//    private final IStatement ex3 =
//        new CompoundStatement(
//                new VariableDeclarationStatement("a", new BoolType()),
//                new CompoundStatement(
//                        new VariableDeclarationStatement("v", new IntType()),
//                        new CompoundStatement(
//                                new AssignStatement("a", new ValueExpression(new BoolValue(true))),
//                                new CompoundStatement(
//                                        new IfStatement(
//                                                new VariableExpression("a"),
//                                                new AssignStatement("v",
//                                                        new ValueExpression(
//                                                                new IntValue(2)
//                                                        )
//                                                ), new AssignStatement("v", new ValueExpression(new IntValue(3)))
//                                        ), new PrintStatement(new VariableExpression("v"))
//                                )
//                        )
//                )
//        );
//
//    //    string filePath;
//    //    filePath="test.in";
//    //    openFileForRead(filePath);
//    //    int n;
//    //    readFile(filePath, n);
//    //    print(n);
//    //    readFile(filePath, n);
//    //    print(n);
//    //    closeFileForRead(filePath);
//    private final IStatement ex4 =
//            new CompoundStatement(
//                    new VariableDeclarationStatement("filePath", new StringType()),
//                    new CompoundStatement(
//                            new AssignStatement("filePath", new ValueExpression(new StringValue("test.in"))),
//                            new CompoundStatement(
//                                    new OpenFileForReadStatement(new VariableExpression("filePath")),
//                                    new CompoundStatement(
//                                            new VariableDeclarationStatement("n", new IntType()),
//                                            new CompoundStatement(
//                                                    new ReadFileStatement(new VariableExpression("filePath"), new VariableExpression("n")),
//                                                    new CompoundStatement(
//                                                            new PrintStatement(new VariableExpression("n")),
//                                                            new CompoundStatement(
//                                                                    new ReadFileStatement(new VariableExpression("filePath"), new VariableExpression("n")),
//                                                                    new CompoundStatement(
//                                                                            new PrintStatement(new VariableExpression("n")),
//                                                                            new CloseFileForReadStatement(new VariableExpression("filePath"))
//                                                                    )
//                                                            )
//                                                    )
//                                            )
//                                    )
//                            )
//
//                    )
//            );
//
//
//    private void execute(IStatement ex){
//        ToyIStack<IStatement> stack = new ToyStack<IStatement>();
//        ToyIDictionary<String, Value> symbolTable = new ToyDictionary<>();
//        ToyIDictionary<StringValue, BufferedReader> fileTable = new ToyDictionary<>();
//        ToyIList<Value> output = new ToyList<>();
//
//        ProgramState currentProgramState = new ProgramState(stack, symbolTable, fileTable, output, ex);
//
//        String logFilePath = this.logFilePrompt();
//
//        IRepository repository = new Repository(logFilePath);
//        repository.add(currentProgramState);
//
//        Controller controller = new Controller(repository);
//
//        try {
//            controller.allSteps();
//
//            for(Value currentOutput : output.getItems()){
//                System.out.println(currentOutput);
//            }
//        }
//        catch (RuntimeException e){
//            System.out.println(e.getMessage());
//        } catch (ToyException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private String logFilePrompt() {
//        System.out.println("Enter log file path:");
//
//        Scanner scanner = new Scanner(System.in);
//
//        return scanner.nextLine();
//    }
//
//    private void printMenu(){
//        System.out.println("0. Exit;");
//        System.out.println("1. Run ex. 1;");
//        System.out.println("2. Run ex. 2;");
//        System.out.println("3. Run ex. 3.");
//        System.out.println("4. Run ex. 4.");
//    }
//
//    public void runMenu() {
//        while (true) {
//            this.printMenu();
//
//            Scanner scanner = new Scanner(System.in);
//            switch (scanner.nextInt()){
//                case 0: {
//                    return;
//                }
//                case 1: {
//                    this.execute(ex1);
//                    break;
//                }
//                case 2: {
//                    this.execute(ex2);
//                    break;
//                }
//                case 3: {
//                    this.execute(ex3);
//                    break;
//                }
//                case 4: {
//                    this.execute(ex4);
//                    break;
//                }
//                default: {
//                    System.out.println("Invalid menu option.");
//                }
//            }
//
//        }
//    }
//
//    public void run() {
//        this.runMenu();
//    }
//}
