import Game.Board.Board;
import Game.Board.BoardInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BoardInterface.type[][] BOARD = { {BoardInterface.type.X, BoardInterface.type.None, BoardInterface.type.O},
                                          {BoardInterface.type.None, BoardInterface.type.None, BoardInterface.type.O},
                                          {BoardInterface.type.X, BoardInterface.type.None, BoardInterface.type.O}};

        Board b = new Board(BOARD);
        Scanner sc = new Scanner(System.in);
        while (true) {
            int i = sc.nextInt();
            int j = sc.nextInt();

            b.place(BoardInterface.type.O, i, j);
            System.out.println(b.is_win());
        }
    }
}