package tictactoe.player.strategy;

import java.util.Random;
import tictactoe.model.Board;
import tictactoe.model.Move;
import tictactoe.player.Player;
import tictactoe.model.Cell;

public class RandomStrategy implements BotStrategy{
    
    private Random random = new Random();
    @Override
    public Cell makeMove(Board board, Player player) {
        while(true){
            int row = random.nextInt(board.getSize());
            int col = random.nextInt(board.getSize());
            if(board.getSymbol(row, col) == '-'){
                return new Cell(row, col);
            }
        }
    }
}