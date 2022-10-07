package Game;

import Game.Board.Board;
import Game.Players.Player;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

import static javax.swing.JOptionPane.showMessageDialog;

public class Game implements Consts {
    private Board board;
    private Player[] players;
    private JLabel[] labels;
    private int[] scores;
    private JFrame frame = new JFrame("Tic Tac Toe");

    private void update_scores() {
        for (int i = 0; i < labels.length; i++) {
            labels[i].setText(players[i] + " - " + scores[i]);
        }
    }

    public Game(Player[] p) {
        board = new Board();
        players = p.clone();

        scores = new int[players.length];
        Arrays.fill(scores, 0);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(board, c);

        c.fill = GridBagConstraints.CENTER;

        c.gridx = 0;
        c.gridy = 1;

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        labels = new JLabel[players.length];
        for ( int i = 0; i < players.length; i++ ){
            JLabel label = new JLabel(players[i] + " - " + scores[i]);
            label.setFont(new Font("Calibri", Font.BOLD, 20));
            labels[i] = label;
            panel.add(label);
        }
        JButton restart_game_btn = new JButton("Restart Game");
        JButton restart_all_btn = new JButton("Restart All");
        restart_game_btn.addActionListener(e -> { board.restart(); });
        restart_all_btn.addActionListener(e -> { board.restart(); Arrays.fill(scores, 0); update_scores(); });
        panel.add(restart_game_btn);
        panel.add(restart_all_btn);

        frame.add(panel, c);

        frame.pack();
        frame.setVisible(true);
    }
    public void loop() {
        while (true) {
            type winner;
            int index;

            do {
                index = board.getPlayerIndex();

                Player current_player = players[index];
                current_player.play(board);

                winner = board.is_win();
            } while (winner == type.None && !board.is_draw());

            if (winner != type.None) {
                scores[index]++;
                labels[index].setText(players[index] + " - " + scores[index]);
                showMessageDialog(null, "Winner is " + winner);
            } else showMessageDialog(null, "Draw!");


            board.restart();
        }

    }


}
