package tictactoe;

import java.util.*;
import tictactoe.player.*;
import tictactoe.player.stratergy.*;

public class Main{

    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);

        BotStrategy[] botStrategy = {
            new WinStratergy(),
            new BlockStratergy(),
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