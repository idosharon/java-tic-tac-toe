package Game.Board;

public interface BoardInterface {
    int SIZE = 3;
    String[] RES = {"o.png", "empty.png", "x.png"};
    int BLOCK_SIZE = 250;
    enum type {
        O, None, X;

        public int getValue() {
            return ordinal() - 1;
        }
    };

    type[][] EMPTY_BOARD = { {type.None, type.None, type.None},
                             {type.None, type.None, type.None},
                             {type.None, type.None, type.None}};

}
