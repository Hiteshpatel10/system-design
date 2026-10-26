package tictactoe.player.stratergy;

import tictactoe.Board;
import tictactoe.player.Player;
import tictactoe.model.Cell;
import tictactoe.model.LineStats;
import java.util.List;
import tictactoe.GridStatus;

public class WinStratergy implements BotStrategy{

    private GridStatus gridStatus;

    public WinStratergy(GridStatus gridStatus){
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
