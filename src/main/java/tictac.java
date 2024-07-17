import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class tictac {
  int boardwidth = 600;
  int boardheight = 650;

  JFrame frame = new JFrame("TIC TAC TOE");
  JLabel textlabel = new JLabel();
  JPanel textpanel = new JPanel();
  JPanel boardpanel = new JPanel();
  JButton[][] board = new JButton[3][3];
  String playerx = "x";
  String playero = "o";
  String currentplayer = playerx;

  boolean gameover = false;
  int turns = 0;

  tictac() {
    frame.setVisible(true);
    frame.setSize(boardwidth, boardheight);
    frame.setLocationRelativeTo(null);
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());

    textlabel.setBackground(Color.darkGray);
    textlabel.setForeground(Color.white);
    textlabel.setFont(new Font("Arial", Font.BOLD, 50));
    textlabel.setHorizontalAlignment(JLabel.CENTER);
    textlabel.setText("tic tac toe");
    textlabel.setOpaque(true);

    textpanel.setLayout(new BorderLayout());
    textpanel.add(textlabel);
    frame.add(textpanel, BorderLayout.NORTH);

    boardpanel.setLayout(new GridLayout(3, 3));
    boardpanel.setBackground(Color.darkGray);
    frame.add(boardpanel);
    for (int i = 0; i < 3; i++) {
      for (int c = 0; c < 3; c++) {
        JButton tile = new JButton();

        board[i][c] = tile;
        boardpanel.add(tile);
        tile.setBackground(Color.pink);
        tile.setForeground(Color.white);
        tile.setFont(new Font("Arial", Font.BOLD, 120));
        tile.setFocusable(false);
        // tile.setText(currentplayer);

        tile.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            if (gameover)
              return;
            JButton tile = (JButton) e.getSource();

            if (tile.getText() == "") {
              tile.setText(currentplayer);
              turns++;
              checkwinner();
              if (!gameover) {
                currentplayer = currentplayer == playerx ? playero : playerx;
                textlabel.setText(currentplayer + "'s turn ");
              }

            }

          }
        });

      }
    }
  }

  void checkwinner() {
    // horizontal
    for (int r = 0; r < 3; r++) {
      if (board[r][0].getText() == "")
        continue;

      if (board[r][0].getText() == board[r][1].getText() && board[r][1].getText() == board[r][2].getText()) {
        for (int i = 0; i < 3; i++) {
          setwinner(board[r][i]);
        }
        gameover = true;
        return;
      }
    }
    // vertical
    for (int c = 0; c < 3; c++) {
      if (board[0][c].getText() == "")
        continue;

      if (board[0][c].getText() == board[1][c].getText() && board[1][c].getText() == board[2][c].getText()) {
        for (int i = 0; i < 3; i++) {
          setwinner(board[i][c]);
        }
        gameover = true;
        return;
      }
    }
    // left diagonal
    if (board[0][0].getText() == board[1][1].getText() && board[1][1].getText() == board[2][2].getText()
        && board[0][0].getText() != "") {
      for (int i = 0; i < 3; i++) {
        setwinner(board[i][i]);
      }
      gameover = true;
      return;
    }
    // right diagonal
    if (board[0][2].getText() == board[1][1].getText() && board[1][1].getText() == board[2][0].getText()
        && board[0][2].getText() != "") {

      setwinner(board[0][2]);
      setwinner(board[1][1]);
      setwinner(board[2][0]);

      gameover = true;
      return;
    }
    // draw
    if (turns == 9) {
      for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
          settie(board[r][c]);
        }
      }
      gameover = true;
    }
  }

  void setwinner(JButton tile) {
    tile.setForeground(Color.cyan);
    tile.setBackground(Color.yellow);
    textlabel.setText(currentplayer + " is the winner ");
  }

  void settie(JButton tile) {
    tile.setForeground(Color.white);
    tile.setBackground(Color.gray);
    textlabel.setText("tie !");
  }
}
