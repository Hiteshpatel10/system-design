package tictactoe.player;

import java.util.Scanner;
import tictactoe.model.Move;
import tictactoe.model.Board;
import tictactoe.enums.PlayerType;

public class Human extends Player{

    private Scanner scanner;

    public Human(String name, char symbol, Scanner scanner){
        super(name, symbol, PlayerType.HUMAN);
        this.scanner = scanner;
    }


    @Override
    public Move getMove(Board board) {
        System.out.println("Enter row and column ");
        String input = scanner.nextLine();
        String[] parts = input.split(" ");
        if(parts[0].equals("U")){
            return Move.UNDO;
        }
        int row = Integer.parseInt(parts[0]);
        int col = Integer.parseInt(parts[1]);
        return new Move(row, col, this);
    }
}
