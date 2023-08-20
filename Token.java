enum makeToken{
  FUNCTION, IDENTIFIER, OPEN_PARENTHESIS, CLOSE_PARENTHESIS, ASSIGNMENT_OPERATOR, INTEGER, IF, NE_OPERATOR, THEN, ELSE, END, LE_OPERATOR, LT_OPERATOR, GE_OPERATOR, GT_OPERATOR, EQ_OPERATOR, ADD_OPERATOR, SUB_OPERATOR, MUL_OPERATOR, DIV_OPERATOR, PRINT, WHILE, DO, SUCCESS, UNSUCCESSFUL, REPEAT,UNTIL
}
class Token{
  private makeToken symbol;
  private String lexeme;

  public Token(makeToken symbol, String lexeme){
    this.symbol=symbol;
    this.lexeme=lexeme;
  }

  public makeToken getSymbol(){
    return symbol;
  }
  public String getLexeme(){
    return lexeme;
  }
  @Override public String toString(){
    return "Lexeme: "+lexeme+ "\tSymbol: "+symbol;
  }
}
