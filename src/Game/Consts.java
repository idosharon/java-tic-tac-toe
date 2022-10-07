package Game;

import Game.Players.Player;

public interface Consts {

    String[] RES = {"o.png", "empty.png", "x.png"};

    enum type {
        O, None, X;

        public int getValue() {
            return ordinal() - 1;
        }
    };

    int number_of_players = 2;

    type[][] EMPTY_BOARD = { {type.None, type.None, type.None},
                             {type.None, type.None, type.None},
                             {type.None, type.None, type.None}};

    Player NONE_PLAYER = new Player("None", type.None);

}
