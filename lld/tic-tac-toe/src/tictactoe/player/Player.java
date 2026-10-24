package tictactoe.player;

import java.util.Scanner; 
import tictactoe.model.Move;

public abstract class Player{

    private String name;
    private char symbol;

    protected Player(String name, char symbol){
        this.name = name;
        this.symbol = symbol;
    }

    public String getName(){
        return name;
    }

    public char getSymbol(){
        return symbol;
    }

    public abstract Move getMove(Scanner scanner);
}