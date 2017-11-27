package tile;
import java.util.*;

public class Tiles {
	
	public ArrayList<Tile> tilePile;
	
	public Tiles() {
		tilePile = new ArrayList<Tile>();
		Tile[] c = new Tile[9];
		Tile[] b = new Tile[9];
		Tile[] d = new Tile[9];
		for (int i = 0; i < 9; i++) {
			int rank = i + 1;
			c[i] = new Tile(rank, Suit.CHARACTER);
			b[i] = new Tile(rank, Suit.BAMBOO);
			d[i] = new Tile(rank, Suit.DOT);
		}
		fill(c);
		fill(b);
		fill(d);
		Tile east = new Tile(Suit.EAST), south = new Tile(Suit.EAST), west = new Tile(Suit.WEST), north = new Tile(Suit.NORTH);
		fill(east, south, west, north);
		Tile red = new Tile(Suit.RED_DRAGON), green = new Tile(Suit.GREEN_DRAGON), white = new Tile(Suit.WHITE_DRAGON);
		fill(red, green, white);
	}
	
	public void shuffle() {
		Collections.shuffle(tilePile);
	}
	
	public void fill(Tile... tile) {
		for (int i = 0; i < tile.length; i++)
			for (int j = 0; j < 4; j++)
				tilePile.add(tile[i]);
	}
}
