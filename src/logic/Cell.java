package logic;

/**
 * All cells have some things in common:
 * 	
 * - They have a Position 
 * - They have a specific Score
 * 	
 * @author UO225811@uniovi.es
 *
 */
public abstract class Cell {
	//value-object class type
	private int position;
	private int amount;
	private boolean isRed;
	
	public Cell(int position, int amount) {
		this.position = position;
		this.amount = amount;
	}

	public Cell(int position, int score, boolean isEven) {
		super();
		this.position = position;
		this.amount = score;
	}

	public Cell(int position, int score, boolean isEven, boolean isRed) {
		super();
		this.position = position;
		this.amount = score;
		this.isRed = isRed;
	}
	
	public boolean isRed() {
		return isRed;
	}
	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}
	
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
