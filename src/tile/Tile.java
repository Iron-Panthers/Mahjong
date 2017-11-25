package tile;

public class Tile {
	
	private int rank;
	private String suit;
	
	public Tile(int rank, String suit) {
		this.rank = rank; 
		this.suit = suit;
	}
	
	public Tile(String suit) {
		this.suit = suit;
		//it's actually not the suit, but for simplicity's sake, I'm calling it the suit because I can
	}
	
	public int value() {
		int value = 0;
		String[] suits = {"character", "bamboo", "dot", "east", "south", "west", "north", "red", "green", "white"};
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
