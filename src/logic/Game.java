package logic;

public class Game {
	private double totalChips;
	private int selectedNumber;
	private Board board;
	private boolean isRangeCell;
	private boolean isColumnCell;
	private boolean isDozenCell;
	private int[] blackPositions = new int[] { 2, 4, 6, 8, 10, 11, 13, 15, 17, 20, 22, 24, 26, 28, 29, 31, 33, 35 };
	private int[] redPositions = new int[] { 1, 3, 5, 7, 9, 12, 14, 16, 18, 19, 21, 23, 25, 27, 30, 32, 34, 36 };
	private int result;

	private double amountWon;

	public Game(double bet, int result) {
		setResult(result);
		this.totalChips = bet;
		board = new Board();
	}

	/**
	 * 
	 * @param result
	 * @param pos
	 * @param amountBet
	 */
	public void play(int pos) {
		if (pos == 45) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isEven(result)) 
				setAmountWon(totalChips);
			else
				setAmountWon(-totalChips);
			
		}else if (pos == 46) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isOdd(result)) 
				setAmountWon(totalChips);
			else
				setAmountWon(-totalChips);
		}else if (pos == 47) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isBlack(result)) 
				setAmountWon(totalChips);
			else
				setAmountWon(-totalChips);
		}else if (pos == 48) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isRed(result)) 
				setAmountWon(totalChips);
			else
				setAmountWon(-totalChips);
		}else if (pos == 43) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isInRange(1, 18, result)) 
				setAmountWon(totalChips);
			else
				setAmountWon(-totalChips);
		}else if (pos == 44) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isInRange(19,36,result)) 
				setAmountWon(totalChips);
			else
				setAmountWon(-totalChips);
		}else if (pos == 40) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isInRange(1,12,result)) 
				setAmountWon(totalChips/2);
			else
				setAmountWon(-totalChips);
		}else if (pos == 41) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			if (isInRange(13,24,result)) 
				setAmountWon(totalChips/2);
			else
				setAmountWon(-totalChips);
		}else if (pos == 42) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isInRange(25,36,result)) 
				setAmountWon(totalChips/2);
			else
				setAmountWon(-totalChips);
		}else if (pos == 37) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isInArrayRange(setColumnPos(1), result)) 
				setAmountWon(totalChips/2);
			else
				setAmountWon(-totalChips);
		}else if (pos == 38) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isInArrayRange(setColumnPos(2), result)) 
				setAmountWon(totalChips/2);
			else
				setAmountWon(-totalChips);
		}else if (pos == 39) {
			if (result == 0 && totalChips >= 10) 
				setAmountWon((int) (totalChips/2));
			else if (result == 0 && totalChips == 5) 
				setAmountWon(0);
			else if (isInArrayRange(setColumnPos(3), result)) 
				setAmountWon(totalChips/2);
			else
				setAmountWon(-totalChips);
		}else {
			if (pos == result)
				setAmountWon(totalChips*35);
			else
				setAmountWon(-totalChips);
		}
	}
	
	public double getAmountWon() {
		return this.amountWon;
	}

	private void setAmountWon(double amountWon) {
		if (amountWon <= 0)
			setTotalChips(0);
		else
			setTotalChips(totalChips += amountWon);
	}
	
	public void setResult(int result) {
		this.result = result;
	}

	public int[] setColumnPos(int columnRange) {
		int array[] = new int[12];
		int iterator = 1;
		switch (columnRange) {
		case 1:
			for (int i = 0; i < 12; i++) { // From 0-11
				if (i == 0)
					array[i] = 1;
				else {
					iterator += 3;
					array[i] = iterator;
				}
			}
			return array;
		case 2:
			iterator = 2;
			for (int i = 0; i < 12; i++) {
				if (i == 0)
					array[i] = 2;
				else {
					iterator += 3;
					array[i] = iterator;
				}
			}
			return array;
		default: // 3rd column
			iterator = 3;
			for (int i = 0; i < 12; i++) {
				if (i == 0)
					array[i] = 3;
				else {
					iterator += 3;
					array[i] = iterator;
				}
			}
			return array;
		}
	}

	public boolean isInArrayRange(int[] array, int result) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == result)
				return true;
		}
		return false;
	}

	private boolean isInRange(int min, int max, int result) {
		return result >= min && result <= max;
	}

	public boolean isEven(int value) {
		if (value % 2 == 0)
			return true;
		else
			return false;
	}

	public boolean isOdd(int value) {
		if (value % 2 == 0)
			return false;
		else
			return true;
	}

	public boolean isBlack(int rouletteNumber) {
		for (int i = 0; i < blackPositions.length; i++)
			if (blackPositions[i] == rouletteNumber)
				return true;
		return false;
	}

	public boolean isRed(int rouletteNumber) {
		for (int i = 0; i < redPositions.length; i++)
			if (redPositions[i] == rouletteNumber)
				return true;
		return false;
	}

	public double getTotalChips() {
		return totalChips;
	}

	public void setTotalChips(double newChipsAmount) {
		this.totalChips = newChipsAmount;
	}

	public int getSelectedNumber() {
		return selectedNumber;
	}

	public void setSelectedNumber(int selectedNumber) {
		this.selectedNumber = selectedNumber;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public boolean isRangeCell() {
		return isRangeCell;
	}

	public void setRangeCell(boolean isRangeCell) {
		this.isRangeCell = isRangeCell;
	}

	public boolean isColumnCell() {
		return isColumnCell;
	}

	public void setColumnCell(boolean isColumnCell) {
		this.isColumnCell = isColumnCell;
	}

	public boolean isDozenCell() {
		return isDozenCell;
	}

	public void setDozenCell(boolean isDozenCell) {
		this.isDozenCell = isDozenCell;
	}
	
	public int getResult() {
		return result;
	}
}
