package logic;

public class MyPlay {
	private int boxNumber;
	private String boxName;
	private double betAmount;
	
	public MyPlay (int boxNumber, String boxName, double betAmount) {
		this.boxNumber = boxNumber;
		this.boxName = boxName;
		this.betAmount = betAmount;
	}
	
	public String toString() {
		return boxName + "  " + betAmount + " €";
	}

	public int getBoxNumber() {
		return boxNumber;
	}

	public void setBoxNumber(int boxNumber) {
		this.boxNumber = boxNumber;
	}

	public String getBoxName() {
		return boxName;
	}

	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}

	public double getBetAmount() {
		return betAmount;
	}

	public void setBetAmount(double betAmount) {
		this.betAmount = betAmount;
	}
}
