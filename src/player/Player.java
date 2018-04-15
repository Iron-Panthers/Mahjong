package player;
import java.util.*;
import javax.swing.JOptionPane;
import tile.*;

/**
 * A class for a mahjong player.
 * A mahjong player can insert and discard tiles into or from their hand.
 * Their hand can be sorted.
 * They can play the following melds: kong, pong, chow.
 * TODO: check win
 * 
 * @author Shiloh
 *
 */
public class Player {
	
	/** The player's hand as an ArrayList of tiles */
	private ArrayList<Tile> hand;
	/** The player's role: East, South, West, or North */
	private Role role;
	/** Is the player is allowed to insert a new tile */
	private boolean canInsert;
	
	/**
	 * Makes a player with a certain role.
	 * 
	 * @param role
	 */
	public Player(Role role) {
		this.role = role;
		hand = new ArrayList<Tile>();
		canInsert = true;
	}
	
	/**
	 * Returns the player's role as a String.
	 */
	public String toString() {
		return role.toString();
	}
	
	/**
	 * Fills in the hand with 13 tiles from the wall.
	 * 
	 * @param wall
	 */
	public void initialize(TileWall wall) {
		for (int i = 0; i < 13; ++i) hand.add(wall.draw());
	}
	
	/**
	 * Pre-condition: hand is sorted.
	 * Inserts a tile into the hand if the player can insert 
	 * (player has not received a tile from a meld). 
	 * 
	 * @param tile
	 */
	public void insert(Tile tile) {
		if (!canInsert) {
			canInsert = true;
			return;
		}
		int i = 0;
		for (i = 0; i < hand.size() && tile.compareTo(hand.get(i)) >= 0; ++i);
		hand.add(i, tile);
	}
	
	/**
	 * Discards a tile from the player's hand.
	 * 
	 * @return discared tile
	 */
	public Tile discard() {
		Tile tile = (Tile) JOptionPane.showInputDialog(null, "Select a tile to discard.", toString(),
				JOptionPane.PLAIN_MESSAGE, null, 
				hand.toArray(),hand.toArray()[0]);
		hand.remove(tile);
		return tile;
	}
	
	private boolean contains(Tile tile) {
		for (int i = 0; i < hand.size(); ++i) if (hand.get(i).equals(tile)) return true;
		return false;
	}
	
	/**
	 * Counts how many tiles are in the player's hand.
	 * 
	 * @param tile
	 * @return number of that tile in the player's hand
	 */
	private int count(Tile tile) {
		int count = 0;
		for (int i = 0; i < this.hand.size(); ++i)
			if (hand.get(i).equals(tile)) ++count;
		return count;
	}
	
	/**
	 * Sees if player can kong.
	 * 
	 * @param tile
	 * @return true if player can kong, false if not
	 */
	private boolean canKong(Tile tile) {
		return count(tile) == 3; 
	}
	
	/**
	 * Sees if player can pong.
	 * 
	 * @param tile
	 * @return true if player can pong, false if not
	 */
	private boolean canPong(Tile tile) {
		return count(tile) >= 2;
	}
	
	/**
	 * Makes a tile of the same suit.
	 * Rank can be different or the same.
	 * 
	 * @param tile
	 * @param indexAwayFrom - how far it is from the rank; negative means the rank is before,
	 * 0 means the rank is the same as the time,
	 * positive means the rank comes after
	 * @return a tile of the same suit
	 */
	private Tile tile(Tile tile, int indexAwayFrom) {
		return new Tile(tile.getRank() + indexAwayFrom, tile.getSuit());
	}
	
	/**
	 * Pre-condition: Tile is ranked.
	 * Returns true if the tile can be ordered after two tiles in the hand.
	 * 
	 * @param tile
	 * @return true if the tile can be ordered after two tiles in the hand
	 */
	private boolean hasTwoBefore(Tile tile) {
		return tile.getRank() < 3 ? false : contains(tile(tile, -1)) && contains(tile(tile, -2));
	}
	
	/**
	 * Returns true if the tile can be ordered in between two tiles in the hand.
	 * 
	 * @param tile
	 * @return true if the tile can be ordered in between two tiles in the hand
	 */
	private boolean isInBetweenTwo(Tile tile) {
		return tile.getRank() != 1 && tile.getRank() != 9 ? contains(tile(tile, -1)) && contains(tile(tile, 1)) : false;
	}
	
	/**
	 * Returns true if the tile can be ordered before two tiles in the hand.
	 * 
	 * @param tile
	 * @return true if the tile can be ordered before two tiles in the hand
	 */
	private boolean hasTwoAfter(Tile tile) {
		return tile.getRank() > 7 ? false : contains(tile(tile, 1)) && contains(tile(tile, 2));
	}
	
	/**
	 * Returns true if the player can chow.
	 * Can only chow if tile is ranked.
	 * 
	 * @param tile
	 * @return whether or not player can chow
	 */
	private boolean canChow(Tile tile) {
		return tile.isRanked() ? hasTwoBefore(tile) || isInBetweenTwo(tile) || hasTwoAfter(tile) : false;
	}
	
	/**
	 * Pre-condition: Player can kong, pong, or chow the tile.
	 * Player can choose to kong, pong, chow, or pass
	 * 
	 * @param tile
	 * @return the option the player chooses
	 */
	private Object chooseKongPongChow(Tile tile) {
		ArrayList<Object> temp = new ArrayList<Object>();
		// Adds the allowable melds to the temp arraylist
		if (canKong(tile)) temp.add("Kong");
		if (canPong(tile)) temp.add("Pong");
		if (canChow(tile)) temp.add("Chow");
		temp.add("Pass"); // Gives the player the option to pass
		Object options[] = temp.toArray(); // Converts arraylist to an array for JOptionPane purposes
		// Asks the user to make a meld or to pass
		// Selects meld or pass based on user input
		return options[JOptionPane.showOptionDialog(null, "Select an option.", toString(), 
				JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0])];
	}
	
	/**
	 * Removes the first instance of this tile
	 * 
	 * @param tile
	 */
	private void remove(Tile tile) {
		for (int i = 0; i < hand.size(); ++i) {
			if (hand.get(i).equals(tile)) {
				hand.remove(i);
				return;
			}
		}
	}
	
	/**
	 * Removes all instances of the tile to be konged
	 * 
	 * @param tile
	 */
	private void kong(Tile tile) {
		for (int i = 0; i < 3; ++i) remove(tile);
	}
	
	/**
	 * Removes two instances of the tile to be ponged
	 * 
	 * @param tile
	 */
	private void pong(Tile tile) {
		for (int i = 0; i < 2; ++i) remove(tile);
	}
	
	/**
	 * Converts a Tile arr to an object
	 * 
	 * @param arr
	 * @return arr as a string object
	 */
	private Object arrToObj(Tile[] arr) {
		Object output = new String();
		for (int i = 0; i < arr.length; ++i) {
			output += arr[i].toString() + "\t";
		}
		return output;
	}
	
	/**
	 * Pre-condition: Player can and chose to chow. 
	 * Selects the desired chow from the player.
	 * If there is only one possible chow, the user does not get to choose, 
	 * and that chow combination will be returned instead.
	 * 
	 * @param tile
	 * @return the chow combination to be played
	 */
	private Object chowSelector(Tile tile) {
		List<Tile[]> temp = new ArrayList<Tile[]>();
		if (hasTwoBefore(tile)) temp.add(new Tile[] {tile(tile, -2), tile(tile, -1), tile}); // Makes the option of having this tile and the two before
		if (isInBetweenTwo(tile)) temp.add(new Tile[] {tile(tile, -1), tile, tile(tile, 1)}); // Makes the option having this tile and the ones before and after it
		if (hasTwoAfter(tile)) temp.add(new Tile[] {tile, tile(tile, 1), tile(tile, 2)}); // Makes the option of having this tile and the two after
		Object[] options = new Object[temp.size()]; // Makes an option array
		for (int i = 0; i < options.length; ++i) options[i] = arrToObj(temp.get(i)); // Converts the arrays in temp to objects that can be read as strings
		if (options.length == 1) return options[0]; // If there is only one option, it returns that option.
		return options[JOptionPane.showOptionDialog(null, "Select an option.", toString(), 
				JOptionPane.CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0])];
	}
	
	/**
	 * Pre-condition: Player can chow.
	 * Removes tiles based on the selected chow.
	 * 
	 * @param tile
	 */
	private void chow(Tile tile) {
		Object selection = chowSelector(tile);
		// Remove the the two tiles before it
		if (selection.equals(arrToObj(new Tile[] {tile(tile, -2), tile(tile, -1), tile}))) {
			remove(tile(tile, -2));
			remove(tile(tile, -1));
		}
		// Removes the two tiles it is in between
		else if (selection.equals(arrToObj(new Tile[] {tile(tile, -1), tile, tile(tile, 1)}))) {
			remove(tile(tile, -1));
			remove(tile(tile, 1));
		}
		// Removes the two tiles after it
		else {
			remove(tile(tile, 1));
			remove(tile(tile, 21));
		}
	}
	
	/**
	 * Kong, pong, chow, or pass based on selection from the player.
	 * 
	 * @param tile
	 * @return true if player konged, ponged, or chowed; false if player passed
	 */
	public boolean kongPongChow(Tile tile) {
		if (!(canKong(tile) || canPong(tile) || canChow(tile))) return false;
		Object selection = chooseKongPongChow(tile);
		if (selection.equals("Kong")) kong(tile);
		else if (selection.equals("Pong")) pong(tile);
		else if (selection.equals("Chow")) chow(tile);
		else return false;
		canInsert = false;
		return true;
	}
	
	/**
	 * Sorts the hand.
	 */
	public void sort() {
		Collections.sort(hand);
	}
}
