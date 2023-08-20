import java.util.ArrayList;
import java.util.List;
class SyntaxAnalyzer{
  private Node parent;
  LexicalAnalyzer scanner;
  ArrayList<Token> tokens;
  Token t1;
  Token previous;
  public SyntaxAnalyzer(String filename){
    scanner= new LexicalAnalyzer();
    scanner.scan(filename);
    tokens= scanner.getTokens();
    t1= tokens.get(0);
  }
  public void parseTree(){
    TreeNode t1 = new TreeNode(parent);
    t1.display();
  }
  private void nextToken(){
    this.previous=this.t1;
    if(!tokens.isEmpty()){
      this.tokens.remove(t1);
      this.t1=tokens.get(0);
    }
  }
  private makeToken look(){
    if(tokens.size()>=2)
      return tokens.get(1).getSymbol();
    return makeToken.END;
  }
  public Boolean end(){
    return t1.getSymbol()==makeToken.END;
  }
  public void beginParse(){
    this.parent= new Node("<program> -> function id() <block> end");
    parse(parent);
  }
  private Program parse(Node node){
  
    if(this.t1.getSymbol()!=makeToken.FUNCTION){
      throw new IllegalArgumentException("Function expected");
    }
    
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.IDENTIFIER){
      throw new IllegalArgumentException("ID expected");
    }
    ID id = new ID(this.t1.getLexeme().charAt(0));
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.OPEN_PARENTHESIS){
      throw new IllegalArgumentException("( expected");
    }
    
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.CLOSE_PARENTHESIS){
      throw new IllegalArgumentException(") expected");
    }
    
    this.nextToken();
    Block b1 = this.block(node);
    b1.go();
    this.nextToken();
    Node n2 = new Node("end");
    node.addNode(n2);
    if(this.t1.getSymbol()!=makeToken.END){
      throw new IllegalArgumentException("END expected");
    }
    this.nextToken();
    Program p1 = new Program(b1);
    return p1;
  }
  private boolean statement(makeToken token){
    if(token==makeToken.IF||token==makeToken.IDENTIFIER||token==makeToken.WHILE||token==makeToken.PRINT||token==makeToken.REPEAT){
      return true;
    }
    else{
      return false;
    }
  }
  private ArithmeticExpression arithmetic_expression(Node node){
    Node n1 = new Node("<arithmetic_expression> -> <id>|<literal_integer>|<arithmetic_op>|<arithmetic_expression><arithmetic_expression>");
    node.addNode(n1);
    if(this.t1.getSymbol()!=makeToken.IDENTIFIER&&this.t1.getSymbol()!=makeToken.INTEGER&&this.t1.getSymbol()!=makeToken.ADD_OPERATOR&&this.t1.getSymbol()!=makeToken.SUB_OPERATOR&&this.t1.getSymbol()!=makeToken.MUL_OPERATOR&&this.t1.getSymbol()!=makeToken.DIV_OPERATOR)
      throw new IllegalArgumentException("Expected identifier, integer, or arithmetic operator not found");
else if(this.t1.getSymbol()!=makeToken.ADD_OPERATOR&&this.t1.getSymbol()!=makeToken.SUB_OPERATOR&&this.t1.getSymbol()!=makeToken.MUL_OPERATOR&&this.t1.getSymbol()!=makeToken.DIV_OPERATOR){
  Node n2= new Node("<arithmetic_op> -> <add_operator>|<sub_operator>|<mul_operator>|<div_operator>");
    node.addNode(n2);
}
    if(this.t1.getSymbol()!=makeToken.INTEGER&&this.t1.getSymbol()!=makeToken.IDENTIFIER){
      String operator = this.t1.getLexeme();
      this.nextToken();
      ArithmeticExpression a1 = this.arithmetic_expression(node);
      this.nextToken();
      ArithmeticExpression a2= this.arithmetic_expression(node);
      return new ArithmeticExpression(operator,a1,a2);
    }
    else if(this.t1.getSymbol()==makeToken.INTEGER){
      int digit = Integer.valueOf(this.t1.getLexeme());
      return new ArithmeticExpression(digit);
    }
    else{
      ID id = new ID(this.t1.getLexeme().charAt(0));
      return new ArithmeticExpression(id);
    }
  }
  private BooleanExpression boolean_expression(Node node){
    Node n1= new Node("<boolean_expression> -> <relative_op>|<arithmetic_expression><arithmetic_expresion>");
    node.addNode(n1);
    if(this.t1.getSymbol()!=makeToken.GE_OPERATOR||this.t1.getSymbol()!=makeToken.GT_OPERATOR||this.t1.getSymbol()!=makeToken.LE_OPERATOR||this.t1.getSymbol()!=makeToken.LT_OPERATOR||this.t1.getSymbol()!=makeToken.EQ_OPERATOR||this.t1.getSymbol()!=makeToken.NE_OPERATOR){
      TreeNode t1 = new TreeNode(parent);
      t1.display();
      throw new IllegalArgumentException("Relative Operator expected");
      
    }else{
      Node n2= new Node("<relative_op> -> <le_operator>|<lt_operator>|<ge_operator>|<gt_operator>|<eq_operator>|<ne_operator>");
    node.addNode(n2);
    }
      
    String operator=this.t1.getLexeme();
    
    this.nextToken();
    ArithmeticExpression a1 = this.arithmetic_expression(node);
    this.nextToken();
    ArithmeticExpression a2 = this.arithmetic_expression(node);
    BooleanExpression boolExpression= new BooleanExpression(operator,a1,a2);
    return boolExpression;
  }
  private Block block(Node node){
    Node n1 = new Node("<block> -> <statement>|<statement><block>");
    node.addNode(n1);
    if(!this.statement(t1.getSymbol()))
      throw new IllegalArgumentException("Expected statement");
    Statement s1= this.statement(node);
    
    Block b1= new Block(s1);
    makeToken t2 = look();
    while(this.statement(t2)){
      this.nextToken();
      Statement s2 = this.statement(node);
      t2 = look();
    }
    return b1;
  }
  private Statement statement(Node node){
    Node n1 = new Node("<statement> -> <if_statement>|<assignment_statement>|<while_statement>|<print_statement>|<repeat_statement>");
    node.addNode(n1);
    if(this.t1.getSymbol()==makeToken.IF){
      this.nextToken();
      Statement if_statement = this.ifStatement(node);
      return if_statement;
    }
    else if(this.t1.getSymbol()==makeToken.IDENTIFIER){
    this.nextToken() ;
      Statement assignment_statement= this.assignment_statement(node);
      return assignment_statement;
    }
    else if(this.t1.getSymbol()==makeToken.WHILE){
      this.nextToken();
      Statement whileStatement = this.whileStatement(node);
      return whileStatement;
    }
    else if(this.t1.getSymbol()==makeToken.PRINT){
      this.nextToken();
      Statement printStatement = this.print_statement(node);
      return printStatement;
    }
    else if(this.t1.getSymbol()==makeToken.REPEAT){
      this.nextToken();
      Statement repeatStatement = this.repeat_statement(node);
      return repeatStatement;
    }
    return null;
  }
  private Statement ifStatement(Node node){
    Node n1 = new Node("<if_statement> -> if <boolean_expression> then <block> else <block> end");
    node.addNode(n1);
    BooleanExpression bool = this.boolean_expression(node);
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.THEN)
      throw new IllegalArgumentException("THEN expected");
    this.nextToken();
    Block b1 = this.block(node);
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.ELSE)
      throw new IllegalArgumentException("ELSE expected");
    this.nextToken();
    Block b2 = this.block(node);
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.END)
      throw new IllegalArgumentException("END expected");
    Statement statement = new ifStatement(bool,b1,b2);
    return statement;  
  }
  private Statement whileStatement(Node node){
    Node n1 = new Node("<while_statement> -> while <boolean_expression> do <block> end");
    node.addNode(n1);
    BooleanExpression bool = this.boolean_expression(node);
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.DO)
      throw new IllegalArgumentException("DO expected");
    this.nextToken();
    Block b1= this.block(node);
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.END)
      throw new IllegalArgumentException("END expected");
    Statement s1 = new whileStatement(bool,b1); 
    return s1;
  }
  private assignmentStatement assignment_statement(Node node){
    Node n1 = new Node("<assignment_statement> -> id <assignment_operator><arithmetic_expression>");
    node.addNode(n1);
    if(this.t1.getSymbol()!=makeToken.ASSIGNMENT_OPERATOR)
      throw new IllegalArgumentException("Assignment operator expected");
    ID id = new
      ID(this.previous.getLexeme().charAt(0));
    this.nextToken();
    ArithmeticExpression expression = this.arithmetic_expression(node);
    return new assignmentStatement(id,expression); 
  }
  private printStatement print_statement(Node node){
    Node n1= new Node("<print_statement> -> print (<arithmetic_expression>)");
    node.addNode(n1);
    if(this.t1.getSymbol()!=makeToken.OPEN_PARENTHESIS)
      throw new IllegalArgumentException("Open parenthesis expected");
    this.nextToken();
    ArithmeticExpression a1 =this.arithmetic_expression(node);
    printStatement p1= new printStatement(a1);
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.CLOSE_PARENTHESIS)
      throw new IllegalArgumentException("Close parenthesis expected");
    return p1;
  }
  private Statement repeat_statement(Node node){
    Node n1 = new Node("<repeat_statement> -> repeat <block> until <boolean_expression>");
    node.addNode(n1);
    Block b1 = this.block(node);
    this.nextToken();
    if(this.t1.getSymbol()!=makeToken.UNTIL)
      throw new IllegalArgumentException("Until expected");
    this.nextToken();
    BooleanExpression bool= this.boolean_expression(node);
    return new repeatStatement(bool,b1);
  }
}
