import java.util.*;

public class Lexer {
  private List<Token> tokens;

  public void parse(String text) {
    tokens = new ArrayList<>();

    int start = 0;
    boolean isNum = false;
    for (int i = 0; i < text.length(); i++) {
      if (Character.isDigit(text.charAt(i))) {
        if (!isNum) {
          isNum = true;
          start = i;
        }

        continue;
      }

      if (isNum) {
        isNum = false;
        String num = text.substring(start, i);
        tokens.add(new Token(Integer.parseInt(num), TokenType.OPERAND));
      }

      tokens.add(new Token(text.charAt(i), TokenType.OPERATOR));
    }

    if (isNum) {
      String num = text.substring(start, text.length());
      tokens.add(new Token(Integer.parseInt(num), TokenType.OPERAND));
    }
  }

  public List<Token> getTokens() {
    return tokens;
  }
}
