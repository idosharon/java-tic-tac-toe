package Game;

import Game.Board.Board;
import Game.Players.Player;

public class Game {
    private static final int number_of_players = 2;
    private Board board;
    private Player[] players;

    public Game(Player[] p) {
        board = new Board();
        players = p.clone();
    }

    public void init() {
        
    }

}
