
public class Life {
	
	private LifeBoard board;
	
	public Life(LifeBoard board){
		this.board = board;
	}
	
	private int getNeighbours(LifeBoard board, int row, int col){
		int neighbours = 0;
		for(int i = row - 1; i <= row + 1; i++){
			for(int j = col -1; j <= col + 1; j++){
				if(board.get(i, j)){
					neighbours++;
				}
			}
		}
		if(board.get(row, col)){
			return neighbours - 1;
		}
		else{
			return neighbours;
		}
	}
	
	public void newGeneration(){
		boolean[][] newBoard = new boolean[board.getRows()][board.getCols()];
		//
		for(int i = 0; i < board.getRows(); i++){
			
			for(int j = 0; j < board.getCols(); j++){
				
				if(board.get(i, j) && (getNeighbours(board, i, j) == 3) || (board.get(i, j) && (getNeighbours(board, i, j) == 2))){
					newBoard[i][j] = true;
				}
				
				else if(board.get(i, j) && (getNeighbours(board, i, j) >=4)){
					newBoard[i][j] = false;
				}
				
				else if(!board.get(i, j) && (getNeighbours(board, i, j) == 3)){
					newBoard[i][j] = true;
				}
				
			}
			
		}
		for(int i = 0; i < board.getRows(); i++){
			
			for(int j = 0; j < board.getCols(); j++){
				board.put(i, j, newBoard[i][j]);
			}
			
		}

		board.increaseGeneration();
	}
	
	public void flip(int row, int col){
		board.put(row, col, !board.get(row, col));
	}
}
