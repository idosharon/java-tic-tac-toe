package Game.Players;

import Game.Board.Board;
import Game.Board.BoardInterface;
import Game.Consts;

public class AI extends Player implements BoardInterface, Consts {
    private int depth = 5;
    public AI(Consts.type t, int depth) {
        super("AI", t);
        setPlayable(false);
        this.depth = depth;
    }

    public AI(Consts.type t) {
        super("AI", t);
        setPlayable(false);
    }

    public void move(type[][] b, type t, int i, int j) {
        b[i][j] = t;
    }

    private int score(type[][] b, type current, int depth) {
        if (Board.is_win(b) == current)
            return 10;
        if (Board.is_win(b) == flip(current))
            return -10;
        if (Board.is_draw(b))
            return 0;
        // here you put static eval.
        return 0;
    }

    private static type flip(type t) {
        return (t == type.X ? type.O : type.X);
    }

    private Move negamax(type[][] b, int depth, type current) {
        if (depth == 0 || Board.is_draw(b) || Board.is_win(b) != type.None)
            return new Move(score(b, current, depth), -1, -1);

        int i, j;
        Move best = new Move(Integer.MIN_VALUE, -1, -1);
        for (i=0; i < SIZE; i++) {
            for (j=0; j < SIZE; j++) {
                if (b[i][j] != type.None)
                    continue;
                move(b, current, i, j);
                int eval = -negamax(b, depth-1, flip(current)).getScore();
                if (best.getScore() < eval)
                    best = new Move(eval, i, j);
                move(b, type.None, i, j);
            }
        }
        return best;
    }
    @Override
    public void play(Board board) {
        board.setCurrentPlayer(this);
        Move best_move = negamax(board.getBoard(), depth, this.getType());
        board.place(best_move.getI(), best_move.getJ());
    }

    @Override
    public String toString() {
        return String.format("%s (%s, %d)", getName(), getType(), depth);
    }
    static class Move {
        int score = 0;
        int i, j;

        public Move(int score, int i, int j) {
            this.score = score;
            this.i = i;
            this.j = j;
        }

        public int getScore() {
            return score;
        }

        public int getI() {
            return i;
        }

        public int getJ() {
            return j;
        }

        @Override
        public String toString() {
            return "Move{" +
                    "score=" + score +
                    ", i=" + i +
                    ", j=" + j +
                    '}';
        }
    }
}
