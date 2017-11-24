package tile;
import java.util.*;

public class Tiles {
	
	public ArrayList<Tile> tilePile;
	
	public Tiles() {
		Tile[] b = new Tile[9];
		Tile[] c = new Tile[9];
		Tile[] d = new Tile[9];
		for (int i = 0; i < 9; i++) {
			int rank = i + 1;
			b[i] = new Tile(rank, "bamboo");
			c[i] = new Tile(rank, "character");
			d[i] = new Tile(rank, "dot");
		}
		fill(b);
		fill(c);
		fill(d);
		Tile east = new Tile("east"), south = new Tile("south"), west = new Tile("west"), north = new Tile("north");
		fill(east, south, west, north);
		Tile green = new Tile("green"), red = new Tile("red"), white = new Tile("white");
		fill(green, red, white);
	}
	
	public void shuffle() {
		Collections.shuffle(tilePile);
	}
	
	public void fill(Tile... tile) {
		int i, j;
		for (i = 0; i < tile.length; i++)
			for (j = 0; j < 4; j++)
				tilePile.add(tile[i]);
	}
}
