package tile;
import java.util.*;

public class Tiles {
	
	private ArrayList<Tile> tilePile;
	
	/**
	 * Constructs Tiles
	 * Each Tiles object is filled with a pile of tiles
	 * There are 4 of each tile in the pile
	 */
	public Tiles() {
		tilePile = new ArrayList<Tile>();
		Tile[] c = new Tile[9];
		Tile[] b = new Tile[9];
		Tile[] d = new Tile[9];
		for (int i = 1; i <= 9; ++i) {
			c[i] = new Tile(i, Suit.CHARACTER);
			b[i] = new Tile(i, Suit.BAMBOO);
			d[i] = new Tile(i, Suit.DOT);
		}
		fill(c);
		fill(b);
		fill(d);
		Tile east = new Tile(Suit.EAST), south = new Tile(Suit.EAST), west = new Tile(Suit.WEST), north = new Tile(Suit.NORTH);
		fill(east, south, west, north);
		Tile red = new Tile(Suit.RED_DRAGON), green = new Tile(Suit.GREEN_DRAGON), white = new Tile(Suit.WHITE_DRAGON);
		fill(red, green, white);
	}
	
	/**
	 * Fills the pile with tiles
	 * Adds 4 of each tile to the tile pile
	 * 
	 * @param tiles
	 */
	private void fill(Tile... tiles) {
		for (int i = 0; i < tiles.length; i++)
			for (int j = 0; j < 4; j++)
				tilePile.add(tiles[i]);
	}
	
	/**
	 * Shuffles the tile pile
	 */
	public void shuffle() {
		Collections.shuffle(tilePile);
	}
	
	/**
	 * Returns the pile of tiles
	 * 
	 * @return {@link Tiles#tilePile}
	 */
	public ArrayList<Tile> getPile() {
		return tilePile;
	}
}
