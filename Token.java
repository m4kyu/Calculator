
public class Token {
  private char operator;
  private int num;
  private TokenType type;

  Token(char operator, TokenType type) {
    this.operator = operator;
    this.type = type;
  }

  Token(int num, TokenType type) {
    this.num = num;
    this.type = type;
  }

  public int getNum() {
    return num;
  }

  public char getOperator() {
    return operator;
  }

  public TokenType getType() {
    return type;
  }
}
