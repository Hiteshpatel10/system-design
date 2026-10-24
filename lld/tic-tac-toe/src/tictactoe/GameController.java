package tictactoe;

import tictactoe.enums.GameStatus;
import java.util.Scanner;

public class GameController{

    private Board board;
    private Player[] player;
    private WinCheck winCheck;
    private GameStatus gameStatus;

    private int currentPlayerIdx;


    GameController(int size, Player[] player){
        this.board = new Board(size);
        this.player = player;
        this.winCheck = new WinCheck(this.board);
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.currentPlayerIdx = 0;
    }


    public void move(int row, int col){

        if(gameStatus != GameStatus.IN_PROGRESS){
            System.out.println("Game is not in progress");
            return;
        }

        Player currentPlayer = getCurrentPlayer();

        try{
            board.move(row, col, currentPlayer);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return;
        }
        
        if(winCheck.isWinner(currentPlayer)){
            System.out.println("Winner: " + currentPlayer.getName());
            this.gameStatus = GameStatus.WINNER;
            return;
        }

        if(winCheck.isDraw()){
            this.gameStatus = GameStatus.DRAW;
            return;
        }

        nextPlayer();


    }

    private void nextPlayer(){
        currentPlayerIdx = (currentPlayerIdx + 1) % player.length;
    }

    private Player getCurrentPlayer(){
        return player[currentPlayerIdx];
    }

    public void start(Scanner scanner){

        while(gameStatus == GameStatus.IN_PROGRESS){
            System.out.println("Current Player: " + getCurrentPlayer().getName());
            System.out.println("Enter row and column");
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            move(row, col);
            board.printBoard();
        }
    }
        
    
}