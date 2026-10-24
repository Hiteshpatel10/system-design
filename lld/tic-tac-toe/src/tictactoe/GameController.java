package tictactoe;

import tictactoe.enums.GameStatus;
import java.util.Scanner;

public class GameController{

    private Board board;
    private Player[] player;
    private WinCheck winCheck;
    private GameStatus gameStatus;
    private Moves moves;
    private int currentPlayerIdx;


    GameController(int size, Player[] player, Moves moves){
        this.board = new Board(size);
        this.player = player;
        this.moves = moves;
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

        moves.add(row, col);
        
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
            System.out.println("Enter row and column U = Undo");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if(parts[0].equals("U")){
                undo();
                continue;
            }
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            move(row, col);
            board.printBoard();
        }
    }


    public void undo(){

        if(moves.isEmpty()){
            System.out.println("No moves found");
            return;
        }

        Cell lastMove = moves.pop();
        currentPlayerIdx = (currentPlayerIdx + player.length - 1) % player.length;
        board.setCellPlayer(lastMove.getRow(), lastMove.getCol(), null);
        this.gameStatus = GameStatus.IN_PROGRESS;
        
        
    }
        
    
}