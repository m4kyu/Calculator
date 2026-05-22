package com.m4kyu.ast;

import java.util.*;
import com.m4kyu.lexer.*;

public class AST {
  private Expresion head;
  private int index = 0;

  private static final Map<Character, Integer> WEIGHTS = Map.of(
      '+', 1,
      '-', 1,
      '*', 2,
      '/', 2);

  public void buildTree(List<Token> tokens) {
    head = buildTree(tokens, 0);
  }

  private Expresion buildTree(List<Token> tokens, int min_weight) {
    Expresion left = new Expresion(tokens.get(index));
    index++;

    while (true) {
      if (index >= tokens.size()) {
        break;
      }

      Token token = tokens.get(index);
      if (WEIGHTS.get(token.getOperator()) < min_weight) {
        break;
      }

      index++;
      Expresion right = buildTree(tokens, WEIGHTS.get(token.getOperator()) + 1);
      left = new Expresion(token, left, right);
    }

    return left;
  }

  public Expresion getTree() {
    return head;
  }
}
