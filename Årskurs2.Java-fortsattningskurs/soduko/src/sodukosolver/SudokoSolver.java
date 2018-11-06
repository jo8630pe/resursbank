package sodukosolver;

public class SudokoSolver{
	//	Ett Sudoku representeras lämpligen av en klass som har en heltalsmatris motsvarande sudokuns
	//	rutnät som attribut.
	private int matrix[][];

	//	I klassen ska det bl.a. finnas en konstruktor
	public SudokoSolver(int matrix[][]) {
		this.matrix = matrix;
	}

	public int getValue(int row, int col) {
		return matrix[row][col];
	}

	public void setValue(int row, int col, int value) {
		matrix[row][col] = value;
	}

	/*Den viktigaste metoden i klassen är den som löser problemet. Utgående från ett delvis fyllt
	rutnät ska denna metod hitta en lösning eller meddela att ingen lösning finns. Problemet ska
	lösas rekursivt med s.k. backtrackingteknik. Här kommer vi alltid att gå framåt element efter element rad efter rad i
	vårt sökande efter lösning.*/

	public boolean solve(int i, int j) {
		//Aktuell ruta är inte från början fylld
		if(matrix[i][j] == 0) {
			//Då provar man i tur och ordning att fylla den med något av talen 1..9.
			for(int n = 1;n <=9; n++) {
				/*För varje sådant val kontrollerar man först att det är
				möjligt med hänsyn till reglerna för Sudoku.*/
				if(numberIsOk(i, j, n)) {
					/*Om det är möjligt fyller man i rutan och gör
					ett rekursivt anrop för nästa ruta.*/
					matrix[i][j] = n;
					/*Med nästa ruta avses här rutan till höger om aktuell ruta
					eller (om aktuell ruta är den sista på en rad) 
					första rutan på nästa rad.*/
					
					//kontrollerar vilken ruta vi ska till
					if(i <= 8 && j < 8) {

						if(solve(i, j+1)) {
							return true;
						}
						else {
							matrix[i][j] = 0;
						}
					}

					else if(j == 8 && i < 8) {
						if(solve(i+1, 0)) {
							return true;
						}
						else {
							matrix[i][j] = 0;
						}
					}
					else if(j == 8 && i == 8){
						return true;
					}

				}

			}
			/*Om det inte går att fylla aktuell ruta med något av alternativen 
			eller om de rekursiva anropen returnerar false för alla de alternativ 
			man provar, så markeras aktuell ruta som som ej ifylld (backtracking)
			och man returnerar false.*/
			return false;

		}
		/*Aktuell ruta är från början fylld (av användaren). Då ska vi inte prova några alternativ utan
		bara kontrollera att det som är ifyllt är ok enligt reglerna. Om så är fallet görs ett rekursivt
		anrop för nästa ruta och resultatet av detta returneras. Om den ifyllda rutan däremot inte
		uppfyller villkoren har man misslyckats och returnerar false.*/

		if(numberIsOk(i , j, matrix[i][j])) {

			if(i <= 8 && j < 8) {
				return solve(i,j+1);	
			}

			else if(j == 8 && i < 8) {
				return solve(i+1,0);	
			}
			
			else if(j == 8 && i == 8){
				return true;
			}
		}
		
		return false;
		
	}

	private boolean numberIsOk(int row, int col, int n) {
		if(rowNotOK(row,col,n) || colNotOK(row,col,n) || sectionNotOK(row,col,n)) {
			return false;
		}
		else {
			return true;
		}
	}

	private boolean sectionNotOK(int row, int col, int n) {
		//ta fram rad och kolumn för rätt sektion
		int sectionRow = row - (row % 3);
		int sectionCol = col - (col % 3);
		//gå igenom sektion och kolla efter dubletter
		for(int i = sectionRow; i < sectionRow + 3; i++) {
			
			for(int j = sectionCol; j < sectionCol + 3; j++) {
				
				if(matrix[i][j] == n && (i != row && j != col)) {
					return true;
				}
			}
		}	
		return false;
	}

	private boolean colNotOK(int row, int col, int n) {
		for(int i = 0; i < 9; i++){

			if(matrix[i][col] == n && row != i) {
				return true;
			}
		}
		return false;
	}

	private boolean rowNotOK(int row, int col, int n) {
		for(int i = 0; i < 9; i++){

			if(matrix[row][i] == n && col != i) {
				return true;
			}

		}
		return false;
	}
}
