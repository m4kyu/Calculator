package com.m4kyu.evaluator;

import com.m4kyu.ast.*;
import com.m4kyu.lexer.*;

public class Evaluator {
  public static int evaluate(Expresion head) {
    if (head == null) {
      return 0;
    }

    Token token = head.getToken();
    if (token.getType() == TokenType.OPERAND) {
      return token.getNum();
    }

    switch (token.getOperator()) {
      case '+':
        return evaluate(head.getLeft()) + evaluate(head.getRight());
      case '-':
        return evaluate(head.getLeft()) - evaluate(head.getRight());
      case '*':
        return evaluate(head.getLeft()) * evaluate(head.getRight());
      case '/':
        return evaluate(head.getLeft()) / evaluate(head.getRight());
      default:
        return 0;
    }
  }

}
