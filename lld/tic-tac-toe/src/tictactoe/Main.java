package tictactoe;

import java.util.*;
import tictactoe.player.*;


public class Main{

    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);

        Player alice = new Human("Hitesh", 'X', scanner);
        Player bob = new Bot("BOT", 'O');
        Player[] players = new Player[]{alice, bob};
        Moves moves = new Moves();  
        GameController controller = new GameController(3, players, moves);

        controller.start(scanner);

    }
}