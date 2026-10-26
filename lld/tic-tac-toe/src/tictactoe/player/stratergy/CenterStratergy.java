package tictactoe.player.stratergy;

import tictactoe.Board;
import tictactoe.player.Player;
import tictactoe.model.Cell;

public class CenterStratergy implements BotStrategy{


    @Override
    public Cell makeMove(Board board, Player player) {
        int size = board.getSize();
        int center = size / 2;

        if(board.getCell(center, center).isEmpty()){
            return board.getCell(center, center);
        }



        return null;
    }
}