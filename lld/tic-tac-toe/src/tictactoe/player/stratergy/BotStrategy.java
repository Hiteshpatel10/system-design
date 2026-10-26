package tictactoe.player.stratergy;

import tictactoe.Board;
import tictactoe.model.Move;
import tictactoe.player.Player;
import tictactoe.model.Cell;

public interface BotStrategy {
    Cell makeMove(Board board, Player player);
}