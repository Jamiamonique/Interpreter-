import java.util.Scanner;
class Main {
  public static void main(String[] args) {
    Scanner myScan = new Scanner(System.in);
    System.out.print("Enter filename (INCLUDE .txt): ");
    String filename = myScan.nextLine();
    Interpreter interpret = new Interpreter(filename);
    interpret.beginInterpreter();
  }
}
