package tictactoe.player;

import tictactoe.model.Move;
import tictactoe.player.Player;
import java.util.Scanner;
import tictactoe.Board;
import tictactoe.enums.PlayerType;
import tictactoe.player.stratergy.BotStrategy;
import tictactoe.model.Cell;

public class Bot extends Player{

    BotStrategy[] botStrategy;

    public Bot(String name, char symbol, BotStrategy[] botStrategy){
        super(name, symbol, PlayerType.BOT);
        this.botStrategy = botStrategy;
    }

    @Override
    public Move getMove(Board board) {

        for(BotStrategy botStrategy : botStrategy){
            Cell cell = botStrategy.makeMove(board, this);
            if(cell != null){
                System.out.println(getName() + " is playing " + cell.getRow() + " " + cell.getCol());
                return new Move(cell.getRow(), cell.getCol(), this);
            }
        }

        return null;
    }
}