package tile;

public class Tile {
	
	private int rank;
	private Suit suit;
	private Suit[] suits = {Suit.CHARACTER, Suit.BAMBOO, Suit.DOT,
			Suit.EAST, Suit.SOUTH, Suit.WEST, Suit.NORTH,
			Suit.RED_DRAGON, Suit.GREEN_DRAGON, Suit.WHITE_DRAGON};
	
	public Tile(int rank, Suit suit) {
		if (rank >= 1 && rank <= 9) this.rank = rank; 
		else throw new IllegalArgumentException("Invalid rank value");
		boolean isValid = false;
		for (int i = 0; i < 3; i++) { // Runs the loop for the ranked suits
			if (suits[i].equals(suit)) {
				isValid = true;
				break;
			}
		}
		if (isValid) this.suit = suit;
		else throw new IllegalArgumentException("Invalid suit");
	}
	
	public Tile(Suit tile) {
		boolean isValid = false;
		for (int i = 3; i < suits.length; i++) { // Runs the loop for the nonranked suits
			if (suits[i].equals(tile)) {
				isValid = true;
				break;
			}
		}
		if (isValid) this.suit = tile; // It's actually not the suit, but for simplicity's sake, I'm calling it the suit because I can
		else throw new IllegalArgumentException("Invalid tile");
	}
	
	public int value() {
		int value = 0;
		for (int i = 0; i < suits.length; i++) {
			if (suit.equals(suits[i])) {
				if (i < 3) {
					value = i * 10 + rank;
					break;
				}
				value = 30 + (i - 3);
				break;
			}
		}
		return value;
	}
	
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
}
