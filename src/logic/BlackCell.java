package logic;

public class BlackCell extends Cell {
	
	private int[] blackPositions = new int[] {2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35};
	
	public BlackCell(int position, int amount) {
		super(position, amount);
	}
	
	public boolean isBlack(int rouletteNumber) {
		for (int i = 0; i < blackPositions.length; i++)
			if (blackPositions[i] == rouletteNumber)
				return true;
		return false;
	}
	
}
