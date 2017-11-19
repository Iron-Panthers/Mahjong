package player;
import java.util.*;
import main.Mahjong;
import tile.Tile;

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
			playerTile = Mahjong.tiles.tilePile.get(i);
			hand.add(playerTile);
			Mahjong.tiles.tilePile.remove(playerTile);		
		}
	}
	
	public boolean canKong(Tile tile) {
		int count = 0;
		for (int i = 0; i < this.hand.size(); i++) {
			if (this.hand.contains(tile)) count++;
		}
		if (count == 3) return true;
		return false;
	}
	
	public boolean canPung(Tile tile) {
		int count = 0;
		for (int i = 0; i < this.hand.size(); i++) {
			if (this.hand.contains(tile)) count++;
		}
		if (count == 2) return true;
		return false;
	}
	
	public boolean canChow(Tile tile) {
		return false;
	}
	
	public void sort() {
		Collections.sort(hand);
	}
	
	public void draw() {
		Tile t = Mahjong.tiles.tilePile.get(0);
		hand.add(t);
		Mahjong.tiles.tilePile.remove(t);
	}
	
	public String name() {
		String playerName = player.toString();
		playerName = playerName.substring(0, 1) + playerName.substring(1).toLowerCase();
		return playerName;
	}
}
