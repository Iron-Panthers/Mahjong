package main;
import java.util.*;
import javax.swing.*;

import player.Player;
import tile.Tile;
import tile.Tiles;
public class Mahjong {
	
	private static Tiles tiles = new Tiles();
	private static Player east = new Player("East");
	private static Player south = new Player("South");
	private static Player west = new Player("West");
	private static Player north = new Player("North");
	private static ArrayList<Tile> discard = new ArrayList<Tile>();
	private static Player[] player = {east, south, west, north};
	
	public static void run() {
		assign();
		play();
	}
	
	public static void assign() { 
		String[] name = new String[4]; 
		ArrayList<Integer> num = new ArrayList<Integer>();
		for (int i = 0; i < player.length; i++) num.add(i);
		Collections.shuffle(num);
		HashMap<Player, String> player = new HashMap<Player, String>();
		for (int i = 0; i < Mahjong.player.length; i++) {
			name[i] = JOptionPane.showInputDialog(null, "Enter the name for player " + (i + 1) + ".");
			player.put(Mahjong.player[num.get(i)], name[i]);
		}
		String playingOrder = "You'll play in this order:\n";
		for (int i = 0; i < Mahjong.player.length; i++) {
			playingOrder += (Mahjong.player[i].getName() + ": " + player.get(Mahjong.player[i]) + "\n");
		}
		print(playingOrder, "Player Assignment");
	}

	public static void play() {
		tiles.shuffle();
		int decision = JOptionPane.showConfirmDialog(null, "Would you like to play again?");
		if (decision == JOptionPane.YES_OPTION) play();
		else System.exit(0);
	}
	
	
	public static void showDiscard() {
		if (discard.size() > 0) System.out.print(discard.get(0).toString() + "\t");
	}
	
	public static void print(String message, String title) {
		JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	
	public static int question(String message) {
		return JOptionPane.showOptionDialog(null, message, message, JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
}
