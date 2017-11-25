package player;
import java.util.*;

import javax.swing.JOptionPane;

import main.Mahjong;
import tile.Tile;

public class Player {
	
	public ArrayList<Tile> hand;
	public Tile playerTile;
	public String name;
	public ArrayList<ArrayList<Tile>> chowOptions;
	
	public Player(String name) {
		this.name = name;
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
		if (!isSorted()) {
			quickSort(0, hand.size() - 1);
		}
	}
	
	private void quickSort(int low, int high) {
		int i = low;
		int j = high;
		int pivot = hand.get(j).value();
		while (i <= j) {
			while (hand.get(i).value() < pivot) {
				i++;
			}
			while (hand.get(j).value() > pivot) {
				j--;
			}
			if (i <= j) {
				switchTile(i, j);
				i++;
				j--;
			}
		}
		if (low < j) quickSort(low, j);
		if (high > i) quickSort(i, high);
	}
	
	private void switchTile(int i, int j) {
		Tile t = hand.get(i);
		hand.set(i, hand.get(j));
		hand.set(j, t);
	}
	
	private boolean isSorted() {
		int[] value = new int[hand.size()];
		for (int i = 0; i < value.length; i++) 
			value[i] = hand.get(i).value();
		for (int i = 0; i < value.length - 1; i++)
			if (value[i] > value[i + 1])
				return false;
		return true;
	}
	
	public void draw() {
		Tile t = Mahjong.tiles.tilePile.get(0);
		hand.add(t);
		Mahjong.tiles.tilePile.remove(t);
	}
	
	public Tile discard() {
		Tile[] tiles = new Tile[hand.size()];
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = hand.get(i);
		}
		Object tile = JOptionPane.showInputDialog(null, "Select a tile to discard.", this.name,
				JOptionPane.PLAIN_MESSAGE, null, tiles, tiles[0]);
		Tile discard = (Tile) tile;
		this.hand.remove(discard);
		return discard;
	}
	
}
