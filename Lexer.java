import java.util.*;

public class Lexer {
  private List<Token> tokens;

  public void parse(String text) {
    tokens = new ArrayList<>();

    for (int i = 0; i < text.length(); i++) {
      if (Character.isDigit(text.charAt(i))) {
        StringBuilder sb = new StringBuilder();

        while (i < text.length() && Character.isDigit(text.charAt(i))) {
          sb.append(text.charAt(i));
          i++;
        }

        tokens.add(new Token(Integer.parseInt(sb.toString()), TokenType.OPERAND));
        i--;
        continue;
      }

      tokens.add(new Token(text.charAt(i), TokenType.OPERATOR));
    }
  }

  public List<Token> getTokens() {
    return tokens;
  }
}
