package tictactoe.player.strategy;

import tictactoe.model.Board;
import tictactoe.player.Player;
import tictactoe.model.Cell;
import tictactoe.model.LineStats;
import java.util.List;
import tictactoe.rules.GridStatus;

public class WinStrategy implements BotStrategy{

    private GridStatus gridStatus;

    public WinStrategy(GridStatus gridStatus){
        this.gridStatus = gridStatus;
    }

    @Override
    public Cell makeMove(Board board, Player player) {

        List<LineStats> stats = gridStatus.getAllLineStats(board);

        for(LineStats stat : stats){
            Cell cell = stat.getCompletingCell(player.getSymbol());
            if (cell != null) return cell;
        }

        return null;
    }
}
