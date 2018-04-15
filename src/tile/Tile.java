package tile;

/**
 * A class for the tiles in a game of mahjong.
 * 
 * @author Shiloh
 *
 */
public class Tile implements Comparable<Tile> {
	
	/** Rank of the tile */
	private int rank;
	/** Suit of the tile */
	private Suit suit;
	/** All possible suits in order */
	private Suit[] suits = {Suit.CHARACTER, Suit.BAMBOO, Suit.DOT,
			Suit.EAST, Suit.SOUTH, Suit.WEST, Suit.NORTH,
			Suit.RED_DRAGON, Suit.GREEN_DRAGON, Suit.WHITE_DRAGON}; // First three are ranked suits
	
	/**
	 * Construct a tile that has a rank.
	 * 
	 * @param rank 1 to 9
	 * @param suit CHARACTER, BAMBOO, or DOT
	 */
	public Tile(int rank, Suit suit) {
		if (rank >= 1 && rank <= 9) this.rank = rank; 
		else throw new IllegalArgumentException("Invalid rank value");
		boolean isValid = false;
		for (int i = 0; i < 3 && !isValid; i++) if (suits[i].equals(suit)) isValid = true; // Runs loop for ranked suits
		if (isValid) this.suit = suit;
		else throw new IllegalArgumentException("Invalid suit");
	}
	
	/**
	 * Constructs a nonranked tile.
	 * 
	 * @param suit EAST, SOUTH, WEST, NORTH, RED_DRAGON, GREEN_DRAGON, WHITE_DRAGON
	 */
	public Tile(Suit suit) {
		boolean isValid = false;
		for (int i = 3; i < suits.length && !isValid; i++) if (suits[i].equals(suit)) isValid = true; // Runs the loop for the nonranked suits
		if (isValid) {
			this.suit = suit;
			this.rank = 0;
		}
		else throw new IllegalArgumentException("Invalid tile");
	}
	
	
	/**
	 * Returns the tile as a String in Chinese.
	 * 
	 * @return tile as a String
	 */
	public String toString() {
		String tile = new String();
		switch(rank) {
		case 1:
			tile += "一";
			break;
		case 2:
			tile += "二";
			break;
		case 3:
			tile += "三";
			break;
		case 4:
			tile += "四";
			break;
		case 5:
			tile += "五";
			break;
		case 6:
			tile += "六";
			break;
		case 7:
			tile += "七";
			break;
		case 8:
			tile += "八";
			break;
		case 9:
			tile += "九";
			break;
		default:
			break;
		}
		switch (suit) {
		case CHARACTER:
			tile += "萬";
			break;
		case BAMBOO:
			tile += "素";
			break;
		case DOT:
			tile += "筒";
			break;
		case EAST:
			tile += "東";
			break;
		case SOUTH:
			tile += "南";
			break;
		case WEST:
			tile += "西";
			break;
		case NORTH:
			tile += "北";
			break;
		case RED_DRAGON:
			tile += "紅中";
			break;
		case GREEN_DRAGON:
			tile += "青發";
			break;
		case WHITE_DRAGON:
			tile += "白板";
			break;
		default:
			break;
		}
		return tile;
	}

	/**
	 * Finds the index of a suit in the suits array.
	 * 
	 * @param suit
	 * @return index of a suit in {@link Tile#suits}
	 */
	private int getIndexOf(Suit suit) {
		for (int i = 1; i < suits.length; ++i) if (suit == suits[i]) return i;
		return -1;
	}
	
	/**
	 * Compares the suit of two tiles.
	 * 
	 * @param tile
	 * @return negative if this suit comes before tile's suit, 
	 * 0 if they are the same suit, 
	 * positive if this rank comes after the tile's suit
	 */
	private int compareSuit(Tile tile) {
		return getIndexOf(this.getSuit()) - getIndexOf(tile.getSuit());
	}
	
	/**
	 * Pre-condition: Tiles share the same suit.
	 * Compares the rank of two tiles.
	 * 
	 * @param tile
	 * @return negative if this rank comes before tile's rank, 
	 * 0 if they are the same rank, 
	 * positive if this rank comes after the tile's rank
	 */
	private int compareRank(Tile tile) {
		return this.getRank() - tile.getRank();
	}
	
	/**
	 * Compares two tiles.
	 * 
	 * @param tile
	 * @return negative if this tile comes before the other tile, 
	 * 0 if they are the same tile, 
	 * positive if this tile comes after the other tile
	 */
	public int compareTo(Tile tile) {
		return compareSuit(tile) == 0 ? compareRank(tile) : compareSuit(tile);
	}
	
	/**
	 * Checks if two tiles are equal.
	 * 
	 * @param tile
	 * @return true if their suit and rank hole the same value, false if otherwise
	 */
	public boolean equals(Tile tile) {
		return compareTo(tile) == 0;
	}
	
	/**
	 * Returns whether or not the tile has a rank.
	 * 
	 * @return true if rank is nonzero, false if it is 0
	 */
	public boolean isRanked() {
		return getRank() != 0;
	}
	
	/**
	 * Returns the rank of the tile.
	 * 
	 * @return {@link Tile#rank}
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * Returns the suit of the tile.
	 * 
	 * @return {@link Tile#suit}
	 */
	public Suit getSuit() {
		return suit;
	}
}
