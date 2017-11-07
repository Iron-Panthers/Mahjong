import java.util.*;

import javax.swing.JOptionPane;

public class Player {
	
	public ArrayList<Tile> hand;
	public Tile playerTile;
	public PlayerName player;
	
	public Player(PlayerName player) {
		this.player = player;
		hand = new ArrayList<Tile>();		
	}
	
	public void initialize() {
		for (int i = 0; i < 13; i++) {
			playerTile = Mahjong.tiles.tiles.get(i);
			hand.add(playerTile);
			Mahjong.tiles.tiles.remove(playerTile);		
		}
	}
	
	public void draw() {
		Tile t = Mahjong.tiles.tiles.get(0);
		hand.add(t);
		Mahjong.tiles.tiles.remove(t);
	}
	
	public void discard() {
		Tile[] tiles = new Tile[hand.size()];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = hand.get(i);
		}
		int tile = JOptionPane.showOptionDialog(null, "Select a tile to discard.", this.name(), 
				JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, tiles, tiles[tiles.length - 1]);
		
	}
	
	public void sort() {
		Collections.sort(hand);
	}
	
	public String name() {
		String playerName = player.toString();
		playerName = playerName.substring(0, 1) + playerName.substring(1).toLowerCase();
		return playerName;
	}

	public void showHand() {
		Object[] tiles = new Object[hand.size()];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = hand.get(i);
		}
	}
}
