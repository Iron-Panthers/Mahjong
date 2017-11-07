import java.util.*;
import javax.swing.*;
public class Mahjong {
	
	public static Tiles tiles = new Tiles();
	public static Player east = new Player(PlayerName.EAST);
	public static Player south = new Player(PlayerName.SOUTH);
	public static Player west = new Player(PlayerName.WEST);
	public static Player north = new Player(PlayerName.NORTH);
	public static ArrayList<Tile> discard = new ArrayList<Tile>();
	public static Player[] player = {east, south, west, north};
	
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
			playingOrder += (Mahjong.player[i].name() + ": " + player.get(Mahjong.player[i]) + "\n");
		}
		print(playingOrder, "Player Assignment");
	}

	public static void play() {
		tiles.shuffle();
		for (int i = 0; i % 4 < 4; i++) {
			//does stuff
		}
	}
	
	public static void showDiscard() {
		System.out.println(discard.get(0));
	}
	
	public static void print(String message, String title) {
		JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	
	
}
