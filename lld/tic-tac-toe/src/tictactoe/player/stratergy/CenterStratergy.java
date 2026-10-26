package tictactoe.player.stratergy;

import tictactoe.GridStatus;
import tictactoe.Board;
import tictactoe.player.Player;
import java.util.List;
import tictactoe.model.Cell;
import tictactoe.model.LineStats;

public class CenterStratergy implements BotStrategy{

    private GridStatus gridStatus;

    public CenterStratergy(GridStatus gridStatus){
        this.gridStatus = gridStatus;
    }

    @Override
    public Cell makeMove(Board board, Player player) {
        int size = board.getSize();
        int center = size / 2;

        if(board.getCell(center, center).isEmpty()){
            return board.getCell(center, center);
        }

        List<LineStats> stats = gridStatus.getAllLineStats(board);


        
        for(LineStats stat: stats){
            List<Cell> cells = stat.getEmptyCells();

            for(Cell cell: cells){
                if(cell.getRow() == center && cell.getCol() == center){
                    return cell;
                }

                if(cell.getRow() == 0 || cell.getCol() == 0){
                return cell;
            }

            if(cell.getRow() == size - 1 || cell.getCol() == size - 1){
                return cell;
            }

            if(cell.getRow() == 0 || cell.getCol() == size -1 ){
                return cell;
            }

            if(cell.getRow() ==  size - 1 || cell.getCol() == 0){
                return cell;
            }
            }
            
            
                
        }

        return null;
    }
}