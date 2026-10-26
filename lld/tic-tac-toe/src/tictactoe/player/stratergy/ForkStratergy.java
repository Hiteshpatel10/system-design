package tictactoe.player.stratergy;

import tictactoe.Board;
import tictactoe.player.Player;
import tictactoe.model.Cell;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tictactoe.model.LineStats;
import tictactoe.GridStatus;

// Blocks the opponent's fork: a cell where one move would create two threats at once
public class ForkStratergy implements BotStrategy{

    private GridStatus gridStatus;

    public ForkStratergy(GridStatus gridStatus){
        this.gridStatus = gridStatus;
    }

    @Override
    public Cell makeMove(Board board, Player player) {

        int size = board.getSize();
        char me = player.getSymbol();

        List<LineStats> stats = gridStatus.getAllLineStats(board);
        Map<Cell, Integer> threatCount = new HashMap<>();

        for(LineStats stat : stats){

            for(char opp: stat.getCounts().keySet()){
                if(opp == me) continue;

                // Opponent is one piece short of a threat here: both empty cells get a vote
                if(stat.count(opp) == size - 2 && stat.getEmptyCells().size() == 2){
                    for(Cell cell : stat.getEmptyCells()){
                        threatCount.merge(cell, 1, Integer::sum);
                    }
                }
            }
        }

        // 2+ votes = playing here would give the opponent two threats at once
        for(Map.Entry<Cell, Integer> entry : threatCount.entrySet()){
            if(entry.getValue() >= 2){
                return entry.getKey();
            }
        }

        return null;
    }
}
