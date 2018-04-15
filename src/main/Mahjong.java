package main;
import java.util.*;
import javax.swing.JOptionPane;
import player.*;
import tile.*;

/**
 * A game of Mahjong
 * 
 * @author Shiloh
 *
 */
public class Mahjong {
	
	/** The wall from which players draw a tile */
	private static TileWall wall = new TileWall();
	/** The East player. Goes first */
	private static Player east = new Player(Role.East);
	/** The South player */
	private static Player south = new Player(Role.South);
	/** The West player */
	private static Player west = new Player(Role.West);
	/** The North player */
	private static Player north = new Player(Role.North);
	/** The pile of discarded tiles */
	private static ArrayList<Tile> discard = new ArrayList<Tile>();
	/** All the players in the game */
	private static Player[] players = {east, south, west, north};
	/** Boolean value indicating whether or not a tile has been discarded */
	private static boolean hasDiscarded = true;
	
	/**
	 * Runs the mahjong game
	 */
	public static void run() {
		assign(); // Assign roles
		try {
			play(); // Plays the game
		} catch (Exception e) {
			System.exit(0); // Exits the game because I don't want to deal with exceptions.
		}
	}
	
	/**
	 * Randomly assigns the player a role.
	 * Roles: East, South, West, North.
	 */
	private static void assign() { 
		String[] names = new String[4]; // Name of the players
		ArrayList<Integer> num = new ArrayList<Integer>(); // List of numbers 
		for (int i = 0; i < players.length; i++) num.add(i); // Adds all the player indices to the list
		Collections.shuffle(num); // Randomizes the order of numbers
		HashMap<Player, String> playerNameMap = new HashMap<Player, String>(); // Player to name map/key
		for (int i = 0; i < players.length; i++) {
			names[i] = JOptionPane.showInputDialog(null, "Enter the name for player " + (i + 1) + "."); // Add a name to names
			playerNameMap.put(players[num.get(i)], names[i]); // Assigns a player to a name
		}
		String playingOrder = "You'll play in this order:\n"; // Message to be displayed
		for (int i = 0; i < players.length; i++) playingOrder += (players[i] + ": " + playerNameMap.get(players[i]) + "\n");
		print(playingOrder, "Player Assignment"); // Displays the assignment
	}

	/**
	 * Plays the whole game.
	 * Can play multiple rounds in one game.
	 */
	private static void play() {
		wall.shuffle();
		for (Player player : players){
			player.initialize(wall); // Fills hand with tiles from the wall
			player.sort(); // Sorts their hand
		}
		round();
		int decision = JOptionPane.showConfirmDialog(null, "Would you like to play again?"); // Ask if players want to play again
		if (decision == JOptionPane.YES_OPTION) play(); // Play again if the decision is yes
		System.exit(0); // Exit game if people don't want to play
	}
	
	/**
	 * Each round to be played.
	 */
	private static void round() {
		int i = 0;
		while (true) {
			showDiscard(); // Prints out the most recent discarded tile
			if (wall.isEmpty()) {
				print("No win.", null);
				return; // Game over if no one wins and wall runs out of tiles
			}
			players[i].insert(wall.draw()); // Player draws a tile if applicable 
			i = setNextPlayer(i, players[i].discard()); // Sets i to the next player
		}
	}
	
	/**
	 * Gets the position of the next player
	 * 
	 * @param currentPlayer - index of the current player
	 * @return index of the next player
	 */
	private static int getNextPlayer(int currentPlayer) {
		return (currentPlayer + 1) % players.length;
	}
	
	/**
	 * Sets the next player.
	 * Returns the index of the player after the current player if no one makes a meld.
	 * Returns the player's index if they make a meld.
	 * If a move is made, {@link Mahjong#hasDiscarded} becomes false.
	 * Otherwise, it becomes true.
	 * 
	 * @param currentPlayer
	 * @param tile
	 * @return the player who plays next
	 */
	private static int setNextPlayer(int currentPlayer, Tile tile) {
		for (int i = currentPlayer + 1; i != currentPlayer; i = getNextPlayer(i)) {
			if (players[i].kongPongChow(tile)) {
				hasDiscarded = false;
				return i;
			}
		}
		discard.add(tile); // Adds tile to the discard pile if no one made a move.
		hasDiscarded = true;
		return getNextPlayer(currentPlayer);
	}
	
	/**
	 * Shows the tile in the discard pile.
	 */
	private static void showDiscard() {
		if (!discard.isEmpty() && hasDiscarded) System.out.print(discard.get(0).toString() + "\t");
	}
	
	/**
	 * Prints a message.
	 * 
	 * @param message
	 * @param title
	 */
	private static void print(String message, String title) {
		JOptionPane.showOptionDialog(null, message, title, JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
	}
	
}
