package tictactoe;

import java.util.*;
import tictactoe.player.*;
import tictactoe.player.strategy.*;
import tictactoe.rules.GridStatus;
import tictactoe.model.MoveHistory;
import tictactoe.controller.GameController;

public class Main{

    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);
        GridStatus gridStatus =  new GridStatus();

        Player alice = new Human("Hitesh", 'X', scanner);

        BotStrategy[] botStrategy = {
            new MiniMaxStrategy(alice),
            new WinStrategy(gridStatus),
            new BlockStrategy(gridStatus),
            new ForkStrategy(gridStatus),
            new BlockForkStrategy(gridStatus, alice),
            new CenterStrategy(),
            new CornerStrategy(),
            new RandomStrategy(),
        };

        Player bob = new Bot("BOT", 'O', botStrategy);
        Player[] players = new Player[]{alice, bob};
        MoveHistory moves = new MoveHistory();  
        GameController controller = new GameController(3, players, moves);

        controller.start(scanner);

    }
}