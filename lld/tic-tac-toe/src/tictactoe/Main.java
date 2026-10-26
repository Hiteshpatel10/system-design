package tictactoe;

import java.util.*;
import tictactoe.player.*;
import tictactoe.player.stratergy.*;
import tictactoe.GridStatus;

public class Main{

    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);
        GridStatus gridStatus =  new GridStatus();
        BotStrategy[] botStrategy = {
            new WinStratergy(gridStatus),
            new BlockStratergy(gridStatus),
            new ForkStratergy(gridStatus),
            new CenterStratergy(),
            new CornerStratergy(),
            new RandomStratergy(),
        };

        Player alice = new Human("Hitesh", 'X', scanner);
        Player bob = new Bot("BOT", 'O', botStrategy);
        Player[] players = new Player[]{alice, bob};
        Moves moves = new Moves();  
        GameController controller = new GameController(3, players, moves);

        controller.start(scanner);

    }
}