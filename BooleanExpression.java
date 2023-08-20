class BooleanExpression{
  String operator;
  Node node;
  ArithmeticExpression a1;
  ArithmeticExpression a2;
  public BooleanExpression(String operator,ArithmeticExpression a1, ArithmeticExpression a2){
    this.operator=operator;
    this.a1=a1;
    this.a2=a2;
  }
  public Boolean getBool(){
    if(operator =="==")
      return a1.go()== a2.go();
    else if(operator=="~=")
      return a1.go() != a2.go();
    else if(operator=="<=")
      return a1.go() <= a2.go();
    else if(operator==">=")
      return a1.go() >= a2.go();
    else if(operator=="<")
      return a1.go() < a2.go();
    else if(operator==">")
      return a1.go() > a2.go();
    else{
      throw new IllegalArgumentException("Relative Operator not found");
    }
    
  }
}
