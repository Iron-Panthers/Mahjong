package tile;

public class Tile {
	
	public int rank;
	public String suit;
	
	public Tile(int rank, String suit) {
		this.rank = rank; 
		this.suit = suit;
	}
	public Tile(String suit) {
		this.rank = 0;
		this.suit = suit;
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
		case "bamboo":
			tile += "素";
			break;
		case "dot":
			tile += "筒";
			break;
		case "character":
			tile += "萬";
			break;
		case "green":
			tile += "青發";
			break;
		case "red":
			tile += "紅中";
			break;
		case "white":
			tile += "白板";
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
		default:
			tile = null;
			break;
		}
		return tile;
	}
}
