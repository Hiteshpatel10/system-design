package tictactoe.player;

import java.util.Scanner;
import tictactoe.model.Move;

public class Human extends Player{
    public Human(String name, char symbol){
        super(name, symbol);
    }


    @Override
    public Move getMove(Scanner scanner) {
        System.out.println("Enter row and column");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        return new Move(row, col);
    }
}
