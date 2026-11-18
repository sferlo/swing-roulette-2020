package logic;

public class DozenCell extends Cell {

	private int[] dozenPos;

	public DozenCell(int position, int score, int dozenRange) {
		super(position, score);
		dozenPos = new int[12];
		setDozenPos(dozenRange);
	}
	
	public int[] getDozenPos() {
		return this.dozenPos;
	}
	
	public boolean isInDozen(int result) {
		for (int i = 0; i < dozenPos.length; i++)
			if (dozenPos[i] == result)
				return true;
		return false;
	}

	public void setDozenPos(int dozenRange) {
		switch(dozenRange) {
		  case 1:
			for (int i = 0; i < 12; i++)
				dozenPos[i] = i+1;
		    break;
		  case 2:
			for (int i = 0; i < 12; i++)
				dozenPos[i] = i+13;
		    break;
		  default: //3rd dozen
			  for (int i = 0; i < 12; i++)
					dozenPos[i] = i+25;
		}
	}
}
