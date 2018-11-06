import se.lth.cs.window.SimpleWindow;

public class Triangle extends Shape {
	private int side;

	public Triangle(int x, int y, int side) {
		super(x, y);
		this.side = side;
	}

	public void draw(SimpleWindow w) {
		double heightFactor = Math.sqrt(3) / 2;
		w.moveTo(x, y);
		w.lineTo(x + side, y);
		w.lineTo(x + (side/2), (int) Math.round(y - (side * heightFactor)));
		w.lineTo(x, y);
		
	}

}
