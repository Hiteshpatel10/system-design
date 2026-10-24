package tictactoe.model;

import tictactoe.player.Player;


public class Cell extends Move{

    private Player player;


    public Cell(int row, int col){
        super(row, col);
        this.player = null;
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