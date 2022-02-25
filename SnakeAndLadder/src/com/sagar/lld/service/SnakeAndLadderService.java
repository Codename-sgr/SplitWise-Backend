package com.sagar.lld.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import com.sagar.lld.bean.Ladder;
import com.sagar.lld.bean.Player;
import com.sagar.lld.bean.Snake;
import com.sagar.lld.bean.SnakeAndLadderBoard;

public class SnakeAndLadderService {
	private SnakeAndLadderBoard snakeAndLadderBoard;
	private int initialNumberOfPlayers;
	private Queue<Player> players;

	private static final int DEFAULT_BOARD_SIZE = 100;
	private static final int DEFAULT_NO_OF_DICES = 1;

	public SnakeAndLadderService() {
		this.snakeAndLadderBoard = new SnakeAndLadderBoard(DEFAULT_BOARD_SIZE);
		this.players = new LinkedList<Player>();
	}

	// SETTING UP GAME

	public void setPlayers(List<Player> players) {
		this.players = new LinkedList<Player>();
		this.initialNumberOfPlayers = players.size();

		Map<String, Integer> playerPieces = new HashMap<>();
		for (Player p : players) {
			this.players.add(p);
			playerPieces.put(p.getName(), 0);
		}

		snakeAndLadderBoard.setPlayerPieces(playerPieces);
	}

	public void setSnakes(List<Snake> snakes) {
		snakeAndLadderBoard.setSnakes(snakes);
	}

	public void setLadders(List<Ladder> ladders) {
		snakeAndLadderBoard.setLadders(ladders);
	}

	// LOGIC OF THE GAME

	public void startGame() {
		while (!isGameCompleted()) {
			int diceValueAfterRoll = getDiceValueAfterDiceRolls();
			Player currentPlayer = players.poll();
			movePlayer(currentPlayer, diceValueAfterRoll);
			if (hasPlayerWon(currentPlayer)) {
				System.out.println(currentPlayer.getName() + " wins the game");
				snakeAndLadderBoard.getPlayerPieces().remove(currentPlayer.getName());
			} else {
				players.add(currentPlayer);
			}
		}
	}

	private void movePlayer(Player currentPlayer, int diceValue) {
		// TODO Auto-generated method stub
		int oldPosition = snakeAndLadderBoard.getPlayerPieces().get(currentPlayer.getName());
		int newPosition = oldPosition + diceValue;

		int boardSize = snakeAndLadderBoard.getSize();

		if (newPosition > boardSize) {
			newPosition = oldPosition;
		} else {
			newPosition = getNewPositionAfterSnakeAndLadder(newPosition);
		}

		snakeAndLadderBoard.getPlayerPieces().put(currentPlayer.getName(), newPosition);
		System.out.println(currentPlayer.getName() + " rolled a " + diceValue + " and moved from " + oldPosition
				+ " to " + newPosition);

	}

	private int getNewPositionAfterSnakeAndLadder(int newPosition) {
		// TODO Auto-generated method stub
		int prevPosition;
		do {
			prevPosition = newPosition;
			for (Snake snake : snakeAndLadderBoard.getSnakes()) {
				if (snake.getStart() == newPosition) {
					newPosition = snake.getEnd();
				}
			}

			for (Ladder ladder : snakeAndLadderBoard.getLadders()) {
				if (ladder.getStart() == newPosition) {
					newPosition = ladder.getEnd();
				}
			}
		} while (newPosition != prevPosition);

		return newPosition;
	}

	private boolean isGameCompleted() {
		// TODO Auto-generated method stub
		return players.size() < initialNumberOfPlayers;
	}

	private boolean hasPlayerWon(Player currentPlayer) {
		// TODO Auto-generated method stub
		int playerPosition = snakeAndLadderBoard.getPlayerPieces().get(currentPlayer.getName());
		return playerPosition == snakeAndLadderBoard.getSize();
	}

	private int getDiceValueAfterDiceRolls() {
		// TODO Auto-generated method stub
		return DiceService.roll();

	}

}
