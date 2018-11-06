
public class LifeController {

	public static void main(String[] args) {
		LifeBoard board = new LifeBoard(15, 15);
		LifeView window = new LifeView(board);
		Life life = new Life(board);
		window.drawBoard();
		
		while (true) {	
			switch(window.getCommand()){
			
			case 1:
				life.flip(window.getRow(), window.getCol());
				window.update();
				break;
				
			case 2:
				life.newGeneration();
				window.update();
				break;
				
			case 3: 
				System.exit(0);
			break;
			
			default:
				break;
			}
		}

	}

}
