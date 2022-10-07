package Game.Board;

import Game.Players.AI;
import Game.Players.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class Board extends JPanel implements BoardInterface, Game.Consts {
    private type[][] board = EMPTY_BOARD;
    private final ImageIO[] res_arr = new ImageIO[type.values().length];
    private Player current_player = NONE_PLAYER;
    private int player_index = 0;
    private final JButton[][] buttons = new JButton[SIZE][SIZE];


    public Board() {
        this(EMPTY_BOARD);
    };

    public Board(type[][] arr) {
        super();

        // setting jpanel grid
        this.setSize(BLOCK_SIZE * SIZE, BLOCK_SIZE * SIZE);
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
                int finalI = i;
                int finalJ = j;
                button.addActionListener(e -> {
                    if (current_player.isPlayable()) {
                        place(finalI, finalJ);
                    }
                });
                buttons[i][j] = button;
                place(i, j);
                this.add(buttons[i][j]);
            }
        }
    }


    public void place(int i, int j) {
        if (board[i][j] == type.None) {
            board[i][j] = current_player.getType();
            if(current_player.getType() != type.None)
                setPlayerIndex((player_index+1) % number_of_players);
            updateButton(i, j);
        }
    }

    public type is_win() {
        return is_win(board);
    }

    static public type is_win(type[][] board) {
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

    static public boolean is_full(type[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == type.None)
                    return false;
            }
        }
        return true;
    }

    public boolean is_draw() {
        return is_draw(board);
    }
    static public boolean is_draw(type[][] board) {
        if (is_win(board) == type.None && is_full(board))
            return true;
        return false;
    }
    public type[][] getBoard() {
        type[][] temp = new type[SIZE][SIZE];
        for (int i = 0; i < board.length; i++)
            temp[i] = board[i].clone();
        return temp;
    }

    public void setCurrentPlayer(Player player) {
        current_player = player;
    }

    public Player getCurrentPlayer() {
        return current_player;
    }

    public int getPlayerIndex() {
        return player_index;
    }

    public void setPlayerIndex(int player_index) {
        this.player_index = player_index;
    }

    public void restart() {
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++) {
                board[i][j] = type.None;
                updateButton(i,j);
            }
        setPlayerIndex(0);
    }
}
