package Game.Players;

import Game.Board.Board;
import Game.Consts.type;

public class Player {
    private String name;
    private type type;
    private boolean playable = true;
    public Player(String name, type t) {
        this.name = String.valueOf(name);
        this.type = t;
    }

    public Player(Player p) {
        this.name = String.valueOf(p.getName());
        this.type = p.getType();
    }

    public void play(Board board) {
        board.setCurrentPlayer(this);
    }

    public type getType() {
        return type;
    }
    public String getName() { return name; }

    public String toString() {
        return String.format("%s (%s)", name, type);
    }

    public boolean isPlayable() {
        return playable;
    }

    protected void setPlayable(boolean playable) {
        this.playable = playable;
    }
}
