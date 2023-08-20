public abstract class Statement{
  public abstract void check();
}
class ifStatement extends Statement{
  BooleanExpression boolExpression;
  Block b1;
  Block b2;
  public ifStatement(BooleanExpression boolExpression, Block b1, Block b2){
    this.boolExpression=boolExpression;
    this.b1=b1;
    this.b2=b2;
  }
  public void check(){
    if(boolExpression.getBool())
      this.b1.go();
    else
      this.b2.go();
  }
}
class assignmentStatement extends Statement{
  ArithmeticExpression expression;
  ID variable;
  public assignmentStatement (ID variable, ArithmeticExpression expression){
    this.variable=variable;
    this.expression=expression;
  }
  public void check(){
    Find.store(variable.getID(),expression.go());
  }
    
}
class whileStatement extends Statement{
  BooleanExpression boolExpression;
  Block b1;
  public whileStatement(BooleanExpression boolExpression, Block b1){
    this.boolExpression=boolExpression;
    this.b1=b1;
  }
  public void check(){
    while(boolExpression.getBool())
      this.b1.go();
  }
}
class printStatement extends Statement{
  ArithmeticExpression arithmeticExpression;
  public printStatement(ArithmeticExpression arithmeticExpression){
    this.arithmeticExpression=arithmeticExpression;
  }
  public void check(){
    System.out.println(arithmeticExpression.go());
  }
}
class repeatStatement extends Statement{
  BooleanExpression boolExpression;
  Block b1;
  public repeatStatement(BooleanExpression boolExpression, Block b1){
    this.boolExpression=boolExpression;
    this.b1=b1;
  }
  public void check(){
    while(boolExpression.getBool())
      this.b1.go();
  }
}
