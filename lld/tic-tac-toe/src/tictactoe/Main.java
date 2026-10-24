package tictactoe;

import java.util.*;


public class Main{

    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);

        Player alice = new Player("Alice", 'X');
        Player bob = new Player("Bob", 'O');
        Player[] players = new Player[]{alice, bob};
        GameController controller = new GameController(3, players);

        controller.start(scanner);

    }
}