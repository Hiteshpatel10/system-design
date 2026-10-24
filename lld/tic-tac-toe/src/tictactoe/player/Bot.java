package tictactoe.player;

import tictactoe.model.Move;
import tictactoe.player.Player;
import java.util.Random;
import java.util.Scanner;

public class Bot extends Player{
    public Bot(String name, char symbol){
        super(name, symbol);
    }

    @Override
    public Move getMove(Scanner scanner) {
        Random random = new Random();
        int row = random.nextInt(3);
        int col = random.nextInt(3);
        System.out.println(getName() + " is playing " + row + " " + col);
        return new Move(row, col);
    }
}