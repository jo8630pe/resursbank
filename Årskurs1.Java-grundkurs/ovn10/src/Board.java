import java.util.Random;

public class Board {
	private int r;
	private int c;
	private int step;
	private int[][] sq;
	private Random rg;
	
	/** skapar ett rutnät av storlek 5*5 för slumpmässig promenad */
	public Board(){
		step = 0;
		rg = new Random();
		sq = new int[7][7];
		
		for(int j = 0; j < sq.length; j++){
			for(int i = 0; i < sq[j].length; i ++){
				if(((j == 0) || (i == 0)) || ((j == sq.length -1) || (i == sq.length -1))){
					sq[j][i] = -1;
				}
			}
		}
		
	}
	
	public void setStartPosition(int row, int col){
		this.r = row;
		this.c = col;
	}
	
	/** kontrollerar om det är möjligt att gå till någon av grannrutorna */
	public boolean possibleToMove(){
		return sq[r+1][c] == 0 || sq[r-1][c] == 0 || sq[r][c+1] == 0 || sq[r][c-1] == 0;
	}
	/** går till en slumpmässigt vald grannruta. Det förutsätts att det finns minst en grannruta
	 * som inte är besökt */
	public void makeOneStep(){
		boolean found = false;
		while(!found){
			int i = r;
			int k = c;
			int rand = rg.nextInt(4) + 1;
			
			switch(rand){
			
				case 1: k++;
				break;
				
				case 2: k--;
				break;
				
				case 3: i++;
				break;
				
				case 4: i--;
				break;
				
			}
			
			if(sq[i][k] == 0 ){
				found = true;
				step++;
				r = i;
				c = k;
				sq[r][c] = step;
			}
		}
	}
	
	public void print(){
		for(int j = 1; j < sq.length -1; j++){
			for(int i = 1; i < sq[j].length -1; i++){
				if(sq[j][i] < 10){
					System.out.print(sq[j][i] + "  ");
				}
				else{
					System.out.print(sq[j][i] + " ");
				}

			}
			System.out.println();
		}
	}
}
