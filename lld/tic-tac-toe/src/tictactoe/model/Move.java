package tictactoe.model;

import tictactoe.player.Player;

public class Move{

    private int row;
    private int col;
    private Player player;

    public static final Move UNDO = new Move(-1, -1, null);

    public Move(int row, int col, Player player){
        this.row = row;
        this.col = col;
        this.player = player;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "Move [row=" + row + ", col=" + col + "]";
    }
}