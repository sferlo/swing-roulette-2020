package logic;

public class OddCell extends Cell{

	private boolean isOdd;
	
	public OddCell(int position, int amount) {
		super(position, amount);
	}
	
	public boolean isOdd(int value) {
		if (value % 2 == 0)
			isOdd = false;
		else
			isOdd = true;
		return isOdd;
	}

}
