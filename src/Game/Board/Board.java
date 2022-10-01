package Game.Board;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Board extends JFrame implements BoardInterface {
    private type[][] board = EMPTY_BOARD;
    private ImageIO[] res_arr = new ImageIO[type.values().length];
    private JButton[][] buttons = new JButton[SIZE][SIZE];

    public Board() {
        this(EMPTY_BOARD);
    };

    public Board(type[][] arr) {
        super();

        // setting jframe grid
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(BLOCK_SIZE * SIZE, BLOCK_SIZE * SIZE);
        this.setResizable(false);
        this.setLayout(new GridLayout(SIZE, SIZE, 1, 1));
        init(arr);
        this.setVisible(true);
    };

    private void updateButton(int i, int j) {
        String res = "res/" + RES[board[i][j].ordinal()];
        buttons[i][j].setIcon(new ImageIcon(
                new ImageIcon(res).getImage().getScaledInstance(BLOCK_SIZE, BLOCK_SIZE, Image.SCALE_DEFAULT)));
    }
    private void init(type[][] arr) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                JButton button = new JButton();
                buttons[i][j] = button;
                place(arr[i][j], i, j);
                this.add(buttons[i][j]);
            }
        }
    }

    public void place(type t, int i, int j) {
        if (board[i][j] == type.None) {
            board[i][j] = t;
            updateButton(i, j);
        }
    }

    public type is_win() {
        int main_diagonal_sum = 0, side_diagonal_sum = 0;

        for (int i = 0; i < board.length; i++) {
            int row_sum = 0, col_sum = 0;
            for (int j = 0; j < board[i].length; j++) {
                row_sum += board[i][j].getValue();
                col_sum += board[j][i].getValue();
            }

            if (row_sum == SIZE | col_sum == SIZE)
                return type.X;
            else if (row_sum == -SIZE | col_sum == -SIZE)
                return type.O;

            main_diagonal_sum += board[i][i].getValue();
            side_diagonal_sum += board[i][SIZE - i - 1].getValue();
        }

        if (main_diagonal_sum == SIZE | side_diagonal_sum == SIZE)
            return type.X;
        else if (main_diagonal_sum == -SIZE | side_diagonal_sum == -SIZE)
            return type.O;
        return type.None;
    }

    public type[][] getBoard() {
        type[][] temp = new type[SIZE][SIZE];
        for (int i = 0; i < board.length; i++)
            temp[i] = board[i].clone();
        return temp;
    }
}
