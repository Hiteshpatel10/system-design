package tictactoe.player.strategy;

import tictactoe.model.Board;
import tictactoe.player.Player;
import tictactoe.model.Cell;

public class CornerStrategy implements BotStrategy{


    @Override
    public Cell makeMove(Board board, Player player) {
        int last = board.getSize() - 1;
        int[][] corners = {{0, 0}, {0, last}, {last, 0}, {last, last}};

        for(int[] rc : corners){
            Cell cell = board.getCell(rc[0], rc[1]);
            if(cell.isEmpty()){ 
                return cell;
            }
        }

        return null;
    }
}