package tictactoe.rules;

import java.util.ArrayList;
import java.util.List;
import tictactoe.model.Board;
import tictactoe.model.Cell;
import tictactoe.model.LineStats;

public class GridStatus{

    // Every winnable line: rows, columns, main diagonal, anti-diagonal
    public List<List<Cell>> getAllLines(Board board){
        int size = board.getSize();
        List<List<Cell>> lines = new ArrayList<>();

        for(int i = 0; i < size; i++){
            List<Cell> row = new ArrayList<>();
            List<Cell> col = new ArrayList<>();
            for(int j = 0; j < size; j++){
                row.add(board.getCell(i, j));
                col.add(board.getCell(j, i));
            }
            lines.add(row);
            lines.add(col);
        }

        List<Cell> diag = new ArrayList<>();
        List<Cell> antiDiag = new ArrayList<>();
        for(int i = 0; i < size; i++){
            diag.add(board.getCell(i, i));
            antiDiag.add(board.getCell(i, size - 1 - i));
        }
        lines.add(diag);
        lines.add(antiDiag);

        return lines;
    }

    public List<LineStats> getAllLineStats(Board board){
        List<LineStats> stats = new ArrayList<>();
        for(List<Cell> line : getAllLines(board)){
            stats.add(new LineStats(line));
        }
        return stats;
    }
}
