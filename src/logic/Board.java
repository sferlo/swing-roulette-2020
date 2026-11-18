package logic;

public class Board {
	public static final int DIM = 48;
	Cell[] cells;
	
	public Board() {
		cells = new Cell[DIM]; //Normal Cells
		for (int i = 0; i <= DIM - 12; i++) {
			cells[i] = new Cell(i,0) {};
		}
		int counter = 1;	   //Column cells 
		for (int i = DIM-11 ; i < DIM - 9; i++) {
			cells[i] = new ColumnCell(i, 0, counter);
			counter++;
		}
		counter = 1;		   //Dozen cells
		for (int i = DIM - 9; i < DIM - 6; i++) {
			cells[i] = new DozenCell(i, 0, counter);
			counter++;
		}					   
		counter = 1;		   //Range cells
		for (int i = DIM - 6; i < DIM - 4 ; i++) {
			cells[i] = new RangeCell(i, 0, counter);
			counter++;
		}					   //
		cells[DIM-4] = new EvenCell(DIM-4, 0);
		cells[DIM-3] = new OddCell(DIM-3, 0);
		cells[DIM-2] = new BlackCell(DIM-1, 0);
		cells[DIM-1] = new RedCell(DIM, 0);
	}
	
	public Cell[] getCells() {
		return cells;
	}
	public void setCells(Cell[] cells) {
		this.cells = cells;
	}
}
