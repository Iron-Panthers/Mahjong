package player;
import java.util.*;
import javax.swing.JOptionPane;
import tile.*;

public class Player {
	
	/** The player's hand as an ArrayList of tiles */
	private ArrayList<Tile> hand;
	/** The player's role: East, South, West, or North */
	private Role role;
	
	/**
	 * Makes a player with a certain role.
	 * 
	 * @param role
	 */
	public Player(Role role) {
		this.role = role;
		hand = new ArrayList<Tile>();		
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
	 * Inserts a tile into the hand.
	 * 
	 * @param tile
	 */
	public void insert(Tile tile) {
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
	 * Sees if player can pung.
	 * 
	 * @param tile
	 * @return true if player can pung, false if not
	 */
	private boolean canPung(Tile tile) {
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
	 * Checks if player can kong, pung, or chow the tile.
	 * 
	 * @param tile
	 * @return true if player can kong, pung, or chow the tile
	 */
	private boolean canKongPungChow(Tile tile) {
		return canKong(tile) || canPung(tile) || canChow(tile);
	}
	
	/**
	 * Pre-condition: Player can kong, pung, or chow the tile.
	 * Player can choose to kong, pung, chow, or pass
	 * 
	 * @param tile
	 * @return the option the player chooses
	 */
	private Object chooseKongPungChow(Tile tile) {
		ArrayList<Object> temp = new ArrayList<Object>();
		if (canKong(tile)) temp.add("Kong");
		if (canPung(tile)) temp.add("Pung");
		if (canChow(tile)) temp.add("Chow");
		temp.add("Pass");
		Object options[] = temp.toArray();
		return options[JOptionPane.showOptionDialog(null, "Select an option.", null, 
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
	 * Removes two instances of the tile to be punged
	 * 
	 * @param tile
	 */
	private void pung(Tile tile) {
		for (int i = 0; i < 2; ++i) remove(tile);
	}
	
	/**
	 * Converts a Tile arr to an object
	 * 
	 * @param arr
	 * @return
	 */
	private Object arrToObj(Tile[] arr) {
		Object output = new Object();
		for (int i = 0; i < arr.length; ++i) {
			output += arr[i].toString() + "\t";
		}
		return output;
	}
	
	/**
	 * Selects the desired chow from the player.
	 * If there is only one possible chow, the user does not get to choose, 
	 * and that chow combination will be returned instead.
	 * 
	 * @param tile
	 * @return the chow combination to be played
	 */
	private Object chowSelector(Tile tile) {
		List<Tile[]> temp = new ArrayList<Tile[]>();
		if (hasTwoBefore(tile)) temp.add(new Tile[] {tile(tile, -2), tile(tile, -1), tile});
		if (isInBetweenTwo(tile)) temp.add(new Tile[] {tile(tile, -1), tile, tile(tile, 1)});
		if (hasTwoAfter(tile)) temp.add(new Tile[] {tile, tile(tile, 1), tile(tile, 2)});
		Object[] options = new Object[temp.size()];
		for (int i = 0; i < options.length; ++i) options[i] = arrToObj(temp.get(i));
		if (options.length == 0) return options[0];
		return options[JOptionPane.showOptionDialog(null, "Select an option.", null, 
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
		System.out.println(selection.toString());
		if (selection.equals(arrToObj(new Tile[] {tile(tile, -2), tile(tile, -1), tile}))) {
			remove(tile(tile, -2));
			remove(tile(tile, -1));
		}
		else if (selection.equals(arrToObj(new Tile[] {tile(tile, -1), tile, tile(tile, 1)}))) {
			remove(tile(tile, -1));
			remove(tile(tile, 1));
		}
		else {
			remove(tile(tile, 1));
			remove(tile(tile, 21));
		}
	}
	
	/**
	 * Kong, pung, chow, or pass based on selection from the player.
	 * 
	 * @param tile
	 * @return true if player konged, punged, or chowed; false if player passed
	 */
	public boolean kongPungChow(Tile tile) {
		if (!canKongPungChow(tile)) return false;
		Object selection = chooseKongPungChow(tile);
		if (selection.equals("Kong")) kong(tile);
		else if (selection.equals("Pung")) pung(tile);
		else if (selection.equals("Chow")) chow(tile);
		else return false;
		return true;
	}
	
	/**
	 * Sorts the hand.
	 */
	public void sort() {
		Collections.sort(hand);
	}
}
