package tictactoe.player.stratergy;

import tictactoe.Board;
import tictactoe.model.Move;
import tictactoe.player.Player;
import tictactoe.model.Cell;

public class BlockStratergy implements BotStrategy{

    @Override
    public Cell makeMove(Board board, Player player) {

        Cell block = canBlock(board, player);
        
        if(block != null){
            return block;
        }

        return null;
    }   

    private Cell canBlock(Board board, Player player){
        
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
                if(board.getSymbol(i, j) != player.getSymbol() && board.getSymbol(i, j) != '-'){
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
                if(board.getSymbol(i, j) != player.getSymbol() && board.getSymbol(i, j) != '-'){
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
        int count = 0;
        for(int i = 0; i < size; i++){
            if(board.getSymbol(i, i) != player.getSymbol() && board.getSymbol(i, i) != '-'){
                count++;
            }
        }
        if(count == size - 1){
            for(int i = 0; i < size; i++){
                if(board.getSymbol(i, i) == '-'){
                    return new Cell(i, i);
                }
            }
        }

        count = 0;
        for(int i = 0; i < size; i++){
            if(board.getSymbol(i, size - 1 - i) != player.getSymbol() && board.getSymbol(i, size - 1 - i) != '-'){
                count++;
            }
        }
        
        if(count == size - 1){
            for(int i = 0; i < size; i++){
                if(board.getSymbol(i, size - 1 - i) == '-'){
                    return new Cell(i, size - 1 - i);
                }
            }
        }
        return null;
    }


}