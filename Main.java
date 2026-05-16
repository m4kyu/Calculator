import java.util.List;
import java.awt.*;
import javax.swing.*;

public class Main {
  private JFrame frame;
  private JPanel buttonsPanel;
  private JPanel inputPanel;

  private JTextField inputField;

  private JButton[] buttons;

  private static final String[] BUTTONS_LAYOUT = {
      "7", "8", "9", "/",
      "4", "5", "6", "*",
      "1", "2", "3", "-",
      "CE", "0", "=", "+"
  };

  public void main(String[] args) {
    buttons = new JButton[BUTTONS_LAYOUT.length];

    for (int i = 0; i < BUTTONS_LAYOUT.length; i++) {
      int index = i;
      buttons[i] = createButton(BUTTONS_LAYOUT[index]);
      buttons[i].addActionListener(_ -> handleButton(BUTTONS_LAYOUT[index]));
    }

    initUI();
  }

  private void initUI() {
    frame = new JFrame();
    frame.setLayout(new BorderLayout(10, 10));

    inputPanel = new JPanel();
    inputPanel.setPreferredSize(new Dimension(100, 80));

    inputField = new JTextField();
    inputField.setEditable(false);
    inputField.setFont(new Font("Arial", Font.BOLD, 20));
    inputField.setHorizontalAlignment(JTextField.RIGHT);
    inputField.setFocusable(false);
    inputField.setPreferredSize(new Dimension(380, 70));
    inputField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

    inputPanel.add(inputField);

    buttonsPanel = new JPanel();
    buttonsPanel.setLayout(new GridLayout(4, 4, 10, 10));
    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10));
    for (JButton button : buttons) {
      buttonsPanel.add(button);
    }

    frame.add(inputPanel, BorderLayout.NORTH);
    frame.add(buttonsPanel);

    frame.setSize(400, 600);
    frame.setResizable(false);
    frame.setTitle("Calculator");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  private JButton createButton(String text) {
    JButton button = new JButton(text);
    button.setFont(new Font("Arial", Font.BOLD, 30));
    button.setBackground(new Color(224, 224, 224));
    button.setFocusable(false);

    return button;
  }

  private void clear() {
    inputField.setText("");
  }

  private void handleButton(String text) {
    switch (text) {
      case "CE":
        clear();
        break;
      case "/":
      case "*":
      case "-":
      case "+":
        handleOperator(text);
        break;
      case "=":
        calculate();
        break;
      default:
        handleNum(Integer.parseInt(text));
    }
  }

  private void handleNum(int num) {
    String text = inputField.getText();
    if (num == 0) {
      if (text.isEmpty() || lastSpec(text)) {
        return;
      }
    }

    inputField.setText(text + num);
  }

  private void handleOperator(String operator) {
    String text = inputField.getText();
    if (text.isEmpty()) {
      return;
    }

    if (lastSpec(text)) {
      return;
    }

    inputField.setText(text + operator);
  }

  private boolean lastSpec(String text) {
    char last = text.charAt(text.length() - 1);
    if (last == '/' || last == '*' || last == '-' || last == '+') {
      return true;
    }

    return false;
  }

  private void calculate() {
    if (inputField.getText().isEmpty()) {
      return;
    }

    Lexer lexer = new Lexer();
    lexer.parse(inputField.getText());

    List<Token> tokens = lexer.getTokens();

    for (Token token : tokens) {
      System.out.println(token);
    }
  }
}
