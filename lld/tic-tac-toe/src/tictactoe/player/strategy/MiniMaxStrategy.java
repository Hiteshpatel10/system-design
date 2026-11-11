package tictactoe.player.strategy;


import tictactoe.model.Board;
import tictactoe.model.Cell;
import tictactoe.player.Player;
import tictactoe.rules.WinCheck;


public class MiniMaxStrategy implements BotStrategy {


    private Player human;

    public MiniMaxStrategy(Player human) {
        this.human = human;
    }

    @Override
    public Cell makeMove(Board board, Player bot) {

        // built here so it checks the same board the game is played on
        WinCheck winCheck = new WinCheck(board);

        int score = Integer.MIN_VALUE;
        Cell bestMove = null;


        for(int i=0; i<board.getSize(); i++){
            for(int j=0;j<board.getSize();j++){

                if(!board.getCell(i, j).isEmpty()) continue;

                // bot just moved here, so it's the human's turn next
                Cell cell = board.getCell(i, j);
                cell.setPlayer(bot);
                int current = minimax(board, 0, false, bot, winCheck);
                cell.clearCell();

                if(score < current){
                    score = current;
                    bestMove = cell;
                }
            }
        }



        return bestMove;
    }

    int minimax(Board board, int depth, boolean isBotTurn, Player bot, WinCheck winCheck){

        if(winCheck.isWinner(bot)){
           return 10 - depth;
        }

        if(winCheck.isWinner(human)){
           return depth - 10;
        }

        if(winCheck.isDraw()){
            return 0;
        }


        if(isBotTurn){

            int maxValue = Integer.MIN_VALUE;
            for(int i=0; i<board.getSize(); i++){
                for(int j=0;j<board.getSize();j++){

                    if(!board.getCell(i, j).isEmpty()) continue;

                    Cell cell = board.getCell(i, j);
                    cell.setPlayer(bot);
                    maxValue = Math.max(maxValue, minimax(board, depth + 1, false, bot, winCheck));
                    cell.clearCell();
                }
            }
            return maxValue;

        }else{
            int minValue = Integer.MAX_VALUE;
            for(int i=0; i<board.getSize(); i++){
                for(int j=0;j<board.getSize();j++){

                    if(!board.getCell(i, j).isEmpty()) continue;

                    Cell cell = board.getCell(i, j);
                    cell.setPlayer(human);
                    minValue = Math.min(minValue, minimax(board, depth+1, true, bot, winCheck));
                    cell.clearCell();
                }
            }
            return minValue;
        }

    }

}
