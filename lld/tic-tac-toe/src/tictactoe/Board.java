package tictactoe;

import tictactoe.model.Cell;
import tictactoe.player.Player;

public class Board{

    private int size;
    private Cell[][] cells;

    Board() {
        this(3); 
    }

    Board(int size) {
        this.size = size;
        this.cells = loadBoard(size);
    }

    private Cell[][] loadBoard(int size){

        Cell[][] temp = new Cell[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                temp[i][j] = new Cell(i, j);
            }
        }
        return temp;
    }

    private boolean isValidMove(int row, int col){
        
        if(row < 0 || row >= size || col < 0 || col >= size || !cells[row][col].isEmpty()){
            return false;
        }

        return true;
    }

    public void move(int row, int col, Player player){
        if(!isValidMove(row, col)){
            throw new IllegalArgumentException("Invalid move");
        }
        cells[row][col].setPlayer(player);
    }

    public int getSize(){
        return size;
    }

    public Cell getCell(int row, int col){
        return cells[row][col];
    }
    public char getSymbol(int row, int col){
        Player p = cells[row][col].getPlayer();
        return p == null ? '-' : p.getSymbol();
    }

    public void setCellPlayer(int row, int col, Player player){
        cells[row][col].setPlayer(player);
    }
        
    public void printBoard(){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                System.out.print(cells[i][j].getPlayer() == null ? " - " : cells[i][j].getPlayer().getSymbol() + " ");
            }
            System.out.println();
        }
    }

}