package tictactoe;


public class Cell{

    private int row;
    private int col;
    private Player player;


    public Cell(int row, int col){
        this.row = row;
        this.col = col;
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



    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}