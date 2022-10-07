import Game.Board.Board;
import Game.Consts;
import Game.Consts.type;
import Game.Game;
import Game.Players.AI;
import Game.Players.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player[] p = { new Player("Human", type.X), new AI(type.O, 9) };
        Game g = new Game(p);
        g.loop();
    }
}