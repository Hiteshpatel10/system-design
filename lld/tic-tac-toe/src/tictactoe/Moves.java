package tictactoe;

import java.util.Deque;
import java.util.ArrayDeque;
import tictactoe.Cell;

public class Moves{

    private Deque<Cell> moves;

    Moves(){
        this.moves = new ArrayDeque<>();
    }

    public void add(int row, int col){
        moves.push(new Cell(row, col));
    }

    public Cell top(){
        return moves.peek();
    }

    public Cell pop(){
        return moves.pop();
    }
    
    public boolean isEmpty(){
        return moves.isEmpty();
    }
}