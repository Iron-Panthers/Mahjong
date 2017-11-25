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
			c[i] = new Tile(rank, "character");
			b[i] = new Tile(rank, "bamboo");
			d[i] = new Tile(rank, "dot");
		}
		fill(c);
		fill(b);
		fill(d);
		Tile east = new Tile("east"), south = new Tile("south"), west = new Tile("west"), north = new Tile("north");
		fill(east, south, west, north);
		Tile red = new Tile("red"), green = new Tile("green"), white = new Tile("white");
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
