package tictactoe;

import tictactoe.enums.GameStatus;
import java.util.Scanner;
import tictactoe.player.Player;
import tictactoe.model.*;
import tictactoe.enums.PlayerType;

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

        moves.add(row, col, currentPlayer);
        
        if(winCheck.isWinner(currentPlayer)){
            System.out.println("Winner: " + currentPlayer.getName());
            this.gameStatus = GameStatus.WINNER;
            return;
        }

        if(winCheck.isDraw()){
            System.out.println("Game Draw!");
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
        System.out.println("U = Undo");
        while(gameStatus == GameStatus.IN_PROGRESS){
            System.out.println("Current Player: " + getCurrentPlayer().getName());
            Move move = getCurrentPlayer().getMove(board);

         


            if(move == Move.UNDO){
                undo();
                while(!moves.isEmpty() && getCurrentPlayer().getPlayerType() == PlayerType.BOT){
                    undo();
                }

                board.printBoard();
                continue;
            }

            move(move.getRow(), move.getCol());
            board.printBoard();
        }
    }


    public void undo(){

        if(moves.isEmpty()){
            System.out.println("No moves found");
            return;
        }

        Move lastMove = moves.pop();
        currentPlayerIdx = indexOf(lastMove.getPlayer());
        board.setCellPlayer(lastMove.getRow(), lastMove.getCol(), null);
        this.gameStatus = GameStatus.IN_PROGRESS;
        
        
    }

    private int indexOf(Player p){
    for(int i = 0; i < player.length; i++){
        if(player[i] == p){
            return i;
        }
    }
    throw new IllegalStateException("Player not in this game: " + p.getName());
}
        
    
}