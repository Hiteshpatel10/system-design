package tictactoe.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LineStats {

    private int length;
    private Map<Character, Integer> counts;
    private List<Cell> emptyCells;

    public LineStats(List<Cell> line){
        this.length = line.size();
        this.counts = new HashMap<>();
        this.emptyCells = new ArrayList<>();

        for(Cell cell : line){
            if(cell.isEmpty()){
                emptyCells.add(cell);
            }else{
                counts.merge(cell.getPlayer().getSymbol(), 1, Integer::sum);
            }
        }
    }

    public int count(char symbol){
        return counts.getOrDefault(symbol, 0);
    }

    public Map<Character, Integer> getCounts(){
        return counts;
    }

    public List<Cell> getEmptyCells(){
        return emptyCells;
    }

    // Whole line belongs to this symbol
    public boolean isFilledBy(char symbol){
        return count(symbol) == length;
    }

    // Returns the cell that would complete the line for this symbol, or null
    public Cell getCompletingCell(char symbol){
        if(count(symbol) == length - 1 && emptyCells.size() == 1){
            return emptyCells.get(0);
        }
        return null;
    }

}
