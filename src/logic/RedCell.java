package logic;

public class RedCell extends Cell{

	private int[] redPositions = new int[] {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
	
	public RedCell(int position, int amount) {
		super(position, amount);
	}
	
	public boolean isRed(int rouletteNumber) {
		for (int i = 0; i < redPositions.length; i++)
			if (redPositions[i] != rouletteNumber)
				return true;
		return false;
	}

}
