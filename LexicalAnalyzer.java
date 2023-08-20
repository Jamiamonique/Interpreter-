import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class LexicalAnalyzer{
  private ArrayList <Token> allTokens;
  public LexicalAnalyzer(){
    this.allTokens = new ArrayList<>();
  }
  ArrayList<Token> getTokens(){
    return allTokens;
  }
  public void makeToken(String data){
    String comments ="//.*?$";
    Pattern p = Pattern.compile(comments, Pattern.MULTILINE);
    String noComment= p.matcher(data).replaceAll(" ");
    String pattern = "(function)|(print)|(end)|(if)|(else)|(while)|(then)|(do)|(=).|(<=)|(<)|(>=)|(=)|(>)|(~=)|(\\+)|(-)|(\\*)|(/)|([(])|([)])|([\\d.])|([\\w])";
    Pattern regex = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
    Matcher match = regex.matcher(noComment);
    
    while(match.find()){
      if(match.group(1)!=null)
        allTokens.add(new Token(makeToken.FUNCTION,match.group(1)));
      else if(match.group(2)!=null)
        allTokens.add(new Token(makeToken.PRINT,match.group(2)));
      else if(match.group(3)!=null)
        allTokens.add(new Token(makeToken.END,match.group(3)));
      else if(match.group(4)!=null)
        allTokens.add(new Token(makeToken.IF,match.group(4)));
      else if(match.group(5)!=null)
        allTokens.add(new Token(makeToken.ELSE,match.group(5)));
      else if(match.group(6)!=null)
        allTokens.add(new Token(makeToken.WHILE,match.group(6)));
      else if(match.group(7)!=null)
        allTokens.add(new Token(makeToken.THEN,match.group(7)));
      else if(match.group(8)!=null)
        allTokens.add(new Token(makeToken.DO,match.group(8)));
      else if(match.group(9)!=null)
        allTokens.add(new Token(makeToken.ASSIGNMENT_OPERATOR,match.group(9)));
      else if(match.group(10)!=null)
        allTokens.add(new Token(makeToken.LE_OPERATOR,match.group(10)));
      else if(match.group(11)!=null)
        allTokens.add(new Token(makeToken.LT_OPERATOR,match.group(11)));
      else if(match.group(12)!=null)
        allTokens.add(new Token(makeToken.GE_OPERATOR,match.group(12)));
      else if(match.group(13)!=null)
        allTokens.add(new Token(makeToken.EQ_OPERATOR,match.group(13)));
      else if(match.group(14)!=null)
        allTokens.add(new Token(makeToken.GT_OPERATOR,match.group(14)));
      else if(match.group(15)!=null)
        allTokens.add(new Token(makeToken.NE_OPERATOR,match.group(15)));
      else if(match.group(16)!=null)
        allTokens.add(new Token(makeToken.ADD_OPERATOR,match.group(16)));
      else if(match.group(17)!=null)
        allTokens.add(new Token(makeToken.SUB_OPERATOR,match.group(17)));
      else if(match.group(18)!=null)
        allTokens.add(new Token(makeToken.MUL_OPERATOR,match.group(18)));
      else if(match.group(19)!=null)
        allTokens.add(new Token(makeToken.DIV_OPERATOR,match.group(19)));
      else if(match.group(20)!=null)
        allTokens.add(new Token(makeToken.OPEN_PARENTHESIS,match.group(20)));
      else if(match.group(21)!=null)
        allTokens.add(new Token(makeToken.CLOSE_PARENTHESIS,match.group(21)));
      else if(match.group(22)!=null)
        allTokens.add(new Token(makeToken.INTEGER,match.group(22)));
      else if(match.group(23)!=null)
        allTokens.add(new Token(makeToken.IDENTIFIER,match.group(23)));
        
    }
  }
  public void scan(String file){
    try(BufferedReader myScan = new BufferedReader(new FileReader(file))){
      String data;
      while((data=myScan.readLine())!=null){
        makeToken(data); 
      }
      allTokens.add(new Token(makeToken.END, "end"));
      allTokens.add(new Token(makeToken.SUCCESS, "Success!! :)"));
      
    }catch(IOException e){
      e.printStackTrace();
      allTokens.add(new Token(makeToken.UNSUCCESSFUL, "Unsuccessful :("));
      
    }
  }
}
