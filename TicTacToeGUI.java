import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {
    JButton[] buttons = new JButton[9];
    char currentPlayer = 'X';
    boolean gameOver = false;

    public TicTacToeGUI() {
        setTitle("Tic Tac Toe");
        setSize(400, 400);
        setLayout(new GridLayout(3, 3));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initializeButtons();
        setVisible(true);
    }

    void initializeButtons() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton) e.getSource();

        if (!btnClicked.getText().equals("") || gameOver) return;

        btnClicked.setText(String.valueOf(currentPlayer));
        btnClicked.setForeground(currentPlayer == 'X' ? Color.BLUE : Color.RED);

        if (checkWinner()) {
            JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
            gameOver = true;
            return;
        }

        if (isDraw()) {
            JOptionPane.showMessageDialog(this, "It's a Draw!");
            gameOver = true;
            return;
        }

        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    boolean checkWinner() {
        int[][] combos = {
            {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // rows
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // cols
            {0, 4, 8}, {2, 4, 6}             // diagonals
        };

        for (int[] combo : combos) {
            if (buttons[combo[0]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[combo[1]].getText().equals(String.valueOf(currentPlayer)) &&
                buttons[combo[2]].getText().equals(String.valueOf(currentPlayer))) {
                return true;
            }
        }

        return false;
    }

    boolean isDraw() {
        for (JButton b : buttons) {
            if (b.getText().equals("")) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new TicTacToeGUI();
    }
}
