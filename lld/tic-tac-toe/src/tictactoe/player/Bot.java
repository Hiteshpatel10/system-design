package tictactoe.player;

import tictactoe.model.Move;
import tictactoe.player.Player;
import java.util.Random;
import java.util.Scanner;
import tictactoe.Board;
import tictactoe.enums.PlayerType;

public class Bot extends Player{
    public Bot(String name, char symbol){
        super(name, symbol, PlayerType.BOT);
    }

    @Override
    public Move getMove(Board board) {

        while(true){
            Random random = new Random();
            int row = random.nextInt(board.getSize());
            int col = random.nextInt(board.getSize());
            if(board.getSymbol(row, col) == '-'){
                System.out.println(getName() + " is playing " + row + " " + col);
                return new Move(row, col, this);
            }
        }
    }
}