package tictactoe.player.strategy;

import tictactoe.model.Board;
import tictactoe.model.Move;
import tictactoe.rules.GridStatus;
import tictactoe.player.Player;
import tictactoe.model.Cell;
import tictactoe.model.LineStats;
import java.util.List;

public class BlockStrategy implements BotStrategy{

    private GridStatus gridStatus;


    public BlockStrategy(GridStatus gridStatus){
        this.gridStatus = gridStatus;
    }

    @Override
    public Cell makeMove(Board board, Player player) {


        List<LineStats> stats = gridStatus.getAllLineStats(board);


        for(LineStats stat : stats){

            if(stat.count(player.getSymbol()) == 0 && stat.getEmptyCells().size() == 1){
                return stat.getEmptyCells().get(0);
            }
        }


        return null;
    }   

   


}