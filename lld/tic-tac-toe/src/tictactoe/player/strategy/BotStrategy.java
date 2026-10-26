package tictactoe.player.strategy;

import tictactoe.model.Board;
import tictactoe.model.Move;
import tictactoe.player.Player;
import tictactoe.model.Cell;

public interface BotStrategy {
    Cell makeMove(Board board, Player player);
}