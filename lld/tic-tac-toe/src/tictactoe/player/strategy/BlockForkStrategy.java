package tictactoe.player.strategy;

import tictactoe.model.Board;
import tictactoe.player.Player;
import tictactoe.model.Cell;
import tictactoe.rules.GridStatus;
import java.util.Set;
import tictactoe.model.LineStats;
// Blocks the opponent's fork: a cell where one move would create two threats at once
public class BlockForkStrategy implements BotStrategy{

    private GridStatus gridStatus;
    private Player opponent;

    public BlockForkStrategy(GridStatus gridStatus, Player opponent){
        this.gridStatus = gridStatus;
        this.opponent = opponent;
    }

    @Override
    public Cell makeMove(Board board, Player player) {

        Set<Cell> forks = gridStatus.findAllForkCells(board, opponent.getSymbol());

        if(forks.isEmpty()){
            return null;
        }

        
        if(forks.size() == 1){
            return forks.iterator().next();
        }

        int size = board.getSize();
        char me = player.getSymbol();


        for(LineStats stat : gridStatus.getAllLineStats(board)){
            if(stat.count(me) == size - 2 && stat.getEmptyCells().size() == 2){
                Cell a = stat.getEmptyCells().get(0);
                Cell b = stat.getEmptyCells().get(1);
                if(!forks.contains(b)) return a;   // I play a → they must block b (safe)  
                if(!forks.contains(a)) return b;   // I play b → they must block a (safe)  
            }                                                                                                                                                             
        }


        return null;  
    }
}
