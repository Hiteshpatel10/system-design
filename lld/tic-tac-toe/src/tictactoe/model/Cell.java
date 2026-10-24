package tictactoe.model;

import tictactoe.player.Player;


public class Cell {

    private Player player;
    private int row;
    private int col;

    public Cell(int row, int col){
        this.row = row;
        this.col = col;
        this.player = null;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void clearCell(){
        this.player = null;
    }

    public boolean isEmpty(){
        return player == null;
    }
}