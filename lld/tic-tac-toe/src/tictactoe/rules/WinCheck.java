package tictactoe.rules;

import tictactoe.model.Board;
import tictactoe.player.Player;


public class WinCheck {

    private Board board;

    public WinCheck(Board board){
        this.board = board;
    }


    public boolean isDraw(){
        for(int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){
                if(board.getSymbol(i, j) == '-'){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isWinner(Player player){
        return checkRows(player) || checkColumns(player) || checkDiagonals(player);
    }

    boolean checkRows(Player player){
        for(int i = 0; i < board.getSize(); i++){
            boolean flag = true;
            for(int j = 0; j < board.getSize(); j++){
                if(board.getSymbol(i, j) != player.getSymbol()){
                    flag = false;
                    break;
                }
            }
            if(flag){
                return true;
            }
        }
        return false;
    }

    boolean checkColumns(Player player){
        for(int i = 0; i < board.getSize(); i++){
            boolean flag = true;
            for(int j = 0; j < board.getSize(); j++){
                if(board.getSymbol(j, i) != player.getSymbol()){
                    flag = false;
                    break;
                }
            }
            if(flag){
                return true;
            }
        }
        return false;
    }

    boolean checkDiagonals(Player player){
        int size = board.getSize();
        boolean mainDiag = true;
        boolean antiDiag = true;

        for(int i = 0; i < size; i++){
            if(board.getSymbol(i, i) != player.getSymbol()){
                mainDiag = false;
            }
            if(board.getSymbol(i, size - 1 - i) != player.getSymbol()){
                antiDiag = false;
            }
        }
        return mainDiag || antiDiag;
    }
       
   
    
}