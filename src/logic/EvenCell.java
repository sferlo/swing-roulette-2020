package logic;

public class EvenCell extends Cell{

	private boolean isEven;
	
	public EvenCell(int position, int amount) {
		super(position, amount);
		this.isEven = isEven(amount);
	}
	
	public boolean isEven(int value) {
		if (value % 2 == 0)
			isEven = true;
		else
			isEven = false;
		return isEven;
	}
}
