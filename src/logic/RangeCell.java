package logic;

public class RangeCell extends Cell {
	
	private int[] range;
	int rangePos;
	
	public RangeCell(int position, int score, int rangePos) {
		super(position, score);
		this.rangePos = rangePos;
		range = new int[18];
		setRangePos(rangePos);
	}

	public int[] getRange() {
		return this.range;
	}
	
	public int getRangePos() {
		return this.rangePos;
	}
	
	public void setRangePos(int pos) {
		if (pos == 1) {
			for (int i = 0; i < 18; i++)
				range[i] = i+1;
		}else {
			for (int i = 0; i < 18; i++)
				range[i] = i+19;
		}
	}
}
