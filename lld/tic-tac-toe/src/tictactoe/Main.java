package tictactoe;

import java.util.*;
import java.io.OutputStream;
import java.io.PrintStream;
import tictactoe.enums.GameStatus;
import tictactoe.player.*;
import tictactoe.player.strategy.*;
import tictactoe.rules.GridStatus;
import tictactoe.model.MoveHistory;
import tictactoe.controller.GameController;

public class Main{

    public static void main(String[] args){


        Scanner scanner = new Scanner(System.in);
        GridStatus gridStatus =  new GridStatus();

        // strategies need the opponent, so create the arrays first and fill them once both players exist
        BotStrategy[] botStrategy = new BotStrategy[7];
        BotStrategy[] miniMaxStrategy = new BotStrategy[1];

        Player alice = new Bot("ALICE", 'X', botStrategy);
        Player bob = new Bot("BOB", 'O', miniMaxStrategy);

        botStrategy[0] = new WinStrategy(gridStatus);
        botStrategy[1] = new BlockStrategy(gridStatus);
        botStrategy[2] = new ForkStrategy(gridStatus);
        botStrategy[3] = new BlockForkStrategy(gridStatus, bob);
        botStrategy[4] = new CenterStrategy();
        botStrategy[5] = new CornerStrategy();
        botStrategy[6] = new RandomStrategy();

        miniMaxStrategy[0] = new MiniMaxStrategy(alice);


        Player[] players = new Player[]{alice, bob};

        runGames(players, 100, scanner);

        System.out.println();

        // minimax against a bot that only plays random moves
        BotStrategy[] carolMiniMax = new BotStrategy[1];

        Player randy = new Bot("RANDY", 'X', new BotStrategy[]{ new RandomStrategy() });
        Player carol = new Bot("CAROL", 'O', carolMiniMax);

        carolMiniMax[0] = new MiniMaxStrategy(randy);

        runGames(new Player[]{randy, carol}, 100, scanner);

    }

    // plays the given number of games and prints wins per player and draws
    static void runGames(Player[] players, int games, Scanner scanner){

        Map<Player, Integer> wins = new LinkedHashMap<>();
        for(Player p : players){
            wins.put(p, 0);
        }
        int draws = 0;

        // silence the per-move board printing while the games run
        PrintStream console = System.out;
        System.setOut(new PrintStream(OutputStream.nullOutputStream()));

        try{
            for(int i = 0; i < games; i++){
                // rotate the turn order so a different player starts each game
                Player[] order = new Player[players.length];
                for(int j = 0; j < players.length; j++){
                    order[j] = players[(i + j) % players.length];
                }

                GameController controller = new GameController(3, order, new MoveHistory());
                controller.start(scanner);

                if(controller.getGameStatus() == GameStatus.DRAW){
                    draws++;
                }else{
                    wins.merge(controller.getWinner(), 1, Integer::sum);
                }
            }
        }finally{
            System.setOut(console);
        }

        System.out.println("Results after " + games + " games:");
        for(Map.Entry<Player, Integer> entry : wins.entrySet()){
            System.out.println(entry.getKey().getName() + " wins: " + entry.getValue());
        }
        System.out.println("Draws: " + draws);
    }
}