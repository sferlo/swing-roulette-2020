package logic;

public class ColumnCell extends Cell {

	int[] columnPos;
	
	public ColumnCell(int position, int score, int columnRange) {
		super(position, score);
		columnPos = new int[12];
		setColumnPos(columnRange);
	}

	public int[] getColumnPos() {
		return columnPos;
	}
	
	/**
	 * Sets the array with the values of the desired column
	 * @param columnRange
	 */
	public void setColumnPos(int columnRange) {
		int iterator = 1;
		switch(columnRange) {
		  case 1:
			for (int i = 0; i < 12; i++) { //From 0-11
				if (i == 0)
					columnPos[i] = 1;
				else {
					iterator += 3;
					columnPos[i] = iterator;
				}
			}
		    break;
		  case 2:
			  iterator = 2;
			  for (int i = 0; i < 12; i++) { 
					if (i == 0)
						columnPos[i] = 2;
					else {
						iterator += 3;
						columnPos[i] = iterator;
					}
				}
		    break;
		  default: //3rd column
			  iterator = 3;
			  for (int i = 0; i < 12; i++) { 
					if (i == 0)
						columnPos[i] = 3;
					else {
						iterator += 3;
						columnPos[i] = iterator;
					}
				}
		    break;
		}
	}
}
