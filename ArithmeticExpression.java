class ArithmeticExpression{
  ID id;
  Node node;
  ArithmeticExpression a1;
  ArithmeticExpression a2;
  String operator;
  int value;
  public ArithmeticExpression(ID id){
    this.id=id;
    operator="";
  }
  public ArithmeticExpression(int value){
    this.value=value;
    operator="";
  }
  public ArithmeticExpression(String operator, ArithmeticExpression a1, ArithmeticExpression a2){
    this.operator=operator;
    this.a1=a1;
    this.a2=a2;
  }
  public int go(){
    if((id==null)&&(operator==""))
      return this.value;
    else 
      return this.calculate();
    
  }
  private int calculate(){
    if(operator =="/")
      return a1.go() / a2.go();
    else if(operator =="*")
      return a1.go() * a2.go();
    else if(operator =="+")
      return a1.go() + a2.go();
    else if(operator =="-")
      return a1.go() - a2.go();
    else{
      throw new IllegalArgumentException("Arithmetic operator not found");
    }
  }
}
