import se.lth.cs.window.SimpleWindow;

public class ShapeListTest {
	public static void main(String[] args) {
	    	SimpleWindow w = new SimpleWindow(600, 600, "ShapeListTest");
	    	ShapeList shapes = new ShapeList();
	    	int mouseX;
	    	int mouseY;
	    	shapes.insert(new Square(100, 300, 100));
	    	shapes.insert(new Triangle(400, 200, 100));
	    	shapes.insert(new Circle(400, 400, 50));
	    	shapes.insert(new Square(450, 450, 50));
	    	shapes.insert(new Square(200, 200, 35));
	    	shapes.draw(w);
	    	
	    	while(true){
	    		w.waitForMouseClick();
	    		mouseX = w.getMouseX();
	    		mouseY = w.getMouseY();
	    		w.waitForMouseClick();
	    		shapes.findHit(mouseX, mouseY).moveToAndDraw(w, w.getMouseX(), w.getMouseY());;
	    	}
	}
}