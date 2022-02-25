package com.sagar.lld.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sagar.lld.bean.Ladder;
import com.sagar.lld.bean.Player;
import com.sagar.lld.bean.Snake;

public class Driver {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int noOfSnakes=sc.nextInt();
		List<Snake> snakes=new ArrayList<Snake>();
		for(int i=0;i<noOfSnakes;i++) {
			snakes.add(new Snake(sc.nextInt(), sc.nextInt()));
		}
		
		int noOfLadder=sc.nextInt();
		List<Ladder> ladders=new ArrayList<Ladder>();
		for(int i=0;i<noOfLadder;i++) {
			ladders.add(new Ladder(sc.nextInt(), sc.nextInt()));
		}
		
		int noOfPlayers=sc.nextInt();
		List<Player> players=new ArrayList<Player>();
		for(int i=0;i<noOfPlayers;i++) {
			players.add(new Player(sc.next()));
		}
		
		SnakeAndLadderService snakeAndLadderService=new SnakeAndLadderService();
		snakeAndLadderService.setSnakes(snakes);
		snakeAndLadderService.setLadders(ladders);
		snakeAndLadderService.setPlayers(players);
		snakeAndLadderService.startGame();
		
	}
}
