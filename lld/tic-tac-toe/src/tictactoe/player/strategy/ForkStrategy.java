package tictactoe.player.strategy;

import tictactoe.model.Board;
import tictactoe.player.Player;
import tictactoe.model.Cell;
import tictactoe.rules.GridStatus;

// Blocks the opponent's fork: a cell where one move would create two threats at once
public class ForkStrategy implements BotStrategy{

    private GridStatus gridStatus;

    public ForkStrategy(GridStatus gridStatus){
        this.gridStatus = gridStatus;
    }

    @Override
    public Cell makeMove(Board board, Player player) {

        return gridStatus.findForkCell(board, player.getSymbol());

    }
}
