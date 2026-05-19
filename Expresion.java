public class Expresion {
  private Token token;
  private Expresion left;
  private Expresion right;

  Expresion(Token token, Expresion left, Expresion right) {
    this.token = token;
    this.left = left;
    this.right = right;
  }

  Expresion(Token token) {
    this.token = token;
  }

  public Token getToken() {
    return token;
  }

  public Expresion getLeft() {
    return left;
  }

  public Expresion getRight() {
    return right;
  }
}
