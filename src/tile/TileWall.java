package tile;
import java.util.*;

public class TileWall {
	
	private ArrayList<Tile> wall;
	
	/**
	 * Constructs the wall
	 * The wall is filled with each type of tile
	 * There are 4 of each tile in the wall
	 */
	public TileWall() {
		wall = new ArrayList<Tile>();
		Tile[] c = new Tile[9];
		Tile[] b = new Tile[9];
		Tile[] d = new Tile[9];
		for (int i = 1; i <= 9; ++i) {
			c[i - 1] = new Tile(i, Suit.CHARACTER);
			b[i - 1] = new Tile(i, Suit.BAMBOO);
			d[i - 1] = new Tile(i, Suit.DOT);
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
	 * Fills the wall with tiles
	 * Adds 4 of each tile to the wall
	 * 
	 * @param tiles
	 */
	private void fill(Tile... tiles) {
		for (int i = 0; i < tiles.length; i++)
			for (int j = 0; j < 4; j++)
				wall.add(tiles[i]);
	}
	
	/**
	 * Shuffles the wall
	 */
	public void shuffle() {
		Collections.shuffle(wall);
	}
	
	/**
	 * Draws the first tile and removes it from the wall
	 * 
	 * @return the first tile in {@link TileWall#wall}
	 */
	public Tile draw() {
		Tile tile = wall.get(0);
		wall.remove(0);
		return tile;
	}
	
	/**
	 * Returns whether the wall is empty or not
	 * 
	 * @return true if the wall is empty, false if it contains tiles
	 */
	public boolean isEmpty() {
		return wall.size() == 0;
	}
}
