public class LifeBoard {
	private int rows;
	private int cols;
	private int genNbr;
	private boolean[][] bd;

	/** Skapar en spelplan med rows rader och cols kolonner. Spelplanen �r fr�n
	    b�rjan tom, dvs alla rutorna �r tomma och generationsnumret �r 1. */	
	public LifeBoard(int rows, int cols) {
		bd = new boolean[rows][cols];
		this.rows = rows;
		this.cols = cols;
		genNbr = 1;
	}

	/** Ger true om det finns en individ i rutan med index row, col, false annars. 
	    Om index row, col �r utanf�r spelplanen returneras false */
	public boolean get(int row, int col) {
		if((row >= rows || row < 0) || (col >= cols || col < 0)){
			return false;
		}
		else{
			return bd[row][col];
		}
	}

	/** Lagrar v�rdet val i rutan med index row, col */
	public void put(int row, int col, boolean val) {
		bd[row][col] = val;
	}

	/** Tar reda p� antalet rader */
	public int getRows() {
		return rows;
	}

 	/** Tar reda p� antalet kolonner */
	public int getCols() {
		return cols;
	}

	/** Tar reda p� aktuellt generationsnummer */
	public int getGeneration() {
		return genNbr;
	}

	/** �kar generationsnumret med ett */
	public void increaseGeneration() {
		genNbr++;
	}
}