package tile;

public class Tile {
	
	private int rank;
	private String suit;
	private String[] suits = {"character", "bamboo", "dot", "east", "south", "west", "north", "red", "green", "white"};
	
	public Tile(int rank, String suit) {
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
	
	public Tile(String tile) {
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
		case "character":
			tile += "萬";
			break;
		case "bamboo":
			tile += "素";
			break;
		case "dot":
			tile += "筒";
			break;
		case "east":
			tile += "東";
			break;
		case "south":
			tile += "南";
			break;
		case "west":
			tile += "西";
			break;
		case "north":
			tile += "北";
			break;
		case "red":
			tile += "紅中";
			break;
		case "green":
			tile += "青發";
			break;
		case "white":
			tile += "白板";
			break;
		default:
			break;
		}
		return tile;
	}
}
