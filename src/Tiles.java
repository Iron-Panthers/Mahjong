import java.util.*;
public class Tiles {
	
	public ArrayList<Tile> tiles;
	public EnumSet<Tile> all;
	public HashMap<String, Integer> chiNum;
	
	public Tiles() {
		tiles = new ArrayList<Tile>();
		all = EnumSet.allOf(Tile.class);
		for (Tile t : all) {
			fill(t);
		}
		
		chiNum = new HashMap<String, Integer>();
		chiNum.put("一", 1);
		chiNum.put("二", 2);
		chiNum.put("三", 3);
		chiNum.put("四", 4);
		chiNum.put("五", 5);
		chiNum.put("六", 6);
		chiNum.put("七", 7);
		chiNum.put("八", 8);
		chiNum.put("九", 9);
	}
	
	public void shuffle() {
		Collections.shuffle(tiles);
	}
	
	public void fill(Tile tile) {
		for (int i = 1; i <= 4; i++) {
			tiles.add(tile);
		}
	}
}
