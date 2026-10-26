package tictactoe.model;

import java.util.Deque;
import java.util.ArrayDeque;
import tictactoe.player.Player;

public class MoveHistory{

    private Deque<Move> moves;

    public MoveHistory(){
        this.moves = new ArrayDeque<>();
    }

    public void add(int row, int col, Player player){
        moves.push(new Move(row, col, player));
    }

    public Move top(){
        return moves.peek();
    }

    public Move pop(){
        return moves.pop();
    }
    
    public boolean isEmpty(){
        return moves.isEmpty();
    }
}