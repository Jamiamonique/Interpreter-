import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

class Interpreter{
  LexicalAnalyzer scanner;
  SyntaxAnalyzer parser;
  String filename;
  Token t1;
  public Interpreter(String filename){
    scanner = new LexicalAnalyzer();
    parser=new SyntaxAnalyzer(filename);
    this.filename=filename;
  }
  private void printFile(){
    try{
      BufferedReader read = new BufferedReader(new FileReader (filename));
      String text = read.readLine();
      while(text!=null){
        System.out.println(text);
        text= read.readLine();
      }
    }catch (IOException e){
      e.printStackTrace();
    }
    System.out.println();
  
  }
  private void scanFile(){
    scanner.scan(filename);
  }
  private void printScan(){
    List<Token> tokens = scanner.getTokens();
    for(Token type: tokens){
      System.out.println(type);
    }
  }
  private void parseFile(){
    parser.beginParse();
  }
  private void printParse(){
    parser.parseTree();
  }
  public void beginInterpreter(){
    interpret();
  }
  private void interpret(){
    printFile();
    System.out.println("File is scanning...");
    scanFile();
    System.out.println("Printing file:\n");
    printScan();
    System.out.println("\nFile is parsing...");
    System.out.println("Printing file:\n");
    parseFile();
    System.out.println("\nInterpreter complete!!!");
  }
}
