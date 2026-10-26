package tictactoe.player.stratergy;

import tictactoe.Board;
import tictactoe.player.Player;
import tictactoe.model.Cell;

public class WinStratergy implements BotStrategy{

    @Override
    public Cell makeMove(Board board, Player player) {

        Cell row = checkRow(board, player);
        Cell col = checkCol(board, player);
        Cell diagonal = checkDiagonal(board, player);

        if(row != null){
            return row;
        }
        if(col != null){
            return col;
        }
        if(diagonal != null){
            return diagonal;
        }

        return null;
    }

    private Cell checkRow(Board board, Player player){

        int size = board.getSize();

        for(int i = 0; i < size; i++){
            int count = 0;
            for(int j = 0; j < size; j++){
                if(board.getSymbol(i, j) == player.getSymbol()){
                    count++;
                }
            }
            if(count == size - 1){
                for(int j = 0; j < size; j++){
                    if(board.getSymbol(i, j) == '-'){
                        return new Cell(i, j);
                    }
                }
            }
        }   
        return null;
    }

    private Cell checkCol(Board board, Player player){

        int size = board.getSize();

        for(int j = 0; j < size; j++){
            int count = 0;
            for(int i = 0; i < size; i++){
                if(board.getSymbol(i, j) == player.getSymbol()){
                    count++;
                }
            }
            if(count == size - 1){
                for(int i = 0; i < size; i++){
                    if(board.getSymbol(i, j) == '-'){
                        return new Cell(i, j);
                    }
                }
            }
        }

        return null;
    }
    
    private Cell checkDiagonal(Board board, Player player){

        int size = board.getSize();

        for(int i = 0; i < size; i++){
            int count = 0;
            for(int j = 0; j < size; j++){
                if(board.getSymbol(i, j) == player.getSymbol()){
                    count++;
                }
            }
            if(count == size - 1){
                for(int j = 0; j < size; j++){
                    if(board.getSymbol(i, j) == '-'){
                        return new Cell(i, j);
                    }
                }
            }
        }   

        for(int i = size; i >= 0; i--){
            int count = 0;
            for(int j = size; j >= 0; j--){
                if(board.getSymbol(i, j) == player.getSymbol()){
                    count++;
                }
            }
            if(count == size - 1){
                for(int j = size; j >= 0; j--){
                    if(board.getSymbol(i, j) == '-'){
                        return new Cell(i, j);
                    }
                }
            }
        }
        return null;
    }
}
