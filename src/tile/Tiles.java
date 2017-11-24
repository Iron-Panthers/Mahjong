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
	}
	
	public void shuffle() {
		Collections.shuffle(tilePile);
	}
	
	public void fill(Tile tile) {
		for (int i = 1; i <= 4; i++) {
			tilePile.add(tile);
		}
	}
}
