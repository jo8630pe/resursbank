package mountain;

import java.util.HashMap;
import fractal.Fractal;
import fractal.TurtleGraphics;

public class Mountain extends Fractal {
	private Point a, b, c;
	private double dev;
	HashMap<Side, Point> map;
	
	public Mountain(Point a, Point b, Point c, double dev) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
		this.dev = dev;
		map = new HashMap<Side, Point>();
	}
	@Override
	public String getTitle() {
		return "Mountain";
	}

	/** Draws the fractal.  
	 * @param turtle the turtle graphic object
	 */
	public void draw(TurtleGraphics turtle) {
		fractalLine(turtle, order,a,b,c,dev);
	}

	/* 
	 * Recursive method: Draws a recursive line of the triangle. 
	 */
	private void fractalLine(TurtleGraphics turtle, int order, Point a, Point b, Point c,double dev) {
		
		
		
		if (order == 0) {
			turtle.moveTo(a.getX(), a.getY());
			turtle.forwardTo(b.getX(), b.getY());
			turtle.forwardTo(c.getX(), c.getY());
			turtle.forwardTo(a.getX(), a.getY());
		} 
		else {
			
			Point p1 = midPoint(a,b,dev);
			Point p2 = midPoint(b,c,dev);
			Point p3 = midPoint(a,c,dev);
			
			
			fractalLine(turtle, order-1,a,p1,p3,dev/2);
			fractalLine(turtle, order-1,p1,b,p2,dev/2);
			fractalLine(turtle, order-1,p3,p2,c,dev/2);
			fractalLine(turtle, order-1,p1,p2,p3,dev/2);

		}
	}
	
	private Point midPoint(Point start, Point end, double dev) {
		Side tempSide = new Side(start,end);
		
		if(map.containsKey(tempSide)) {
			Point tempPoint = map.get(tempSide);
			map.remove(tempSide);
			return tempPoint;
		}
		
		else {
			Point newPoint = new Point((start.getX() + end.getX())/2, ((start.getY() + end.getY())/2) + (int)(RandomUtilities.randFunc(dev)));
			map.put(tempSide, newPoint);
			return  newPoint;
		}
		
	}
	
	
	
//	public static double randFunc(double dev) {
//		double t = dev * Math.sqrt(-2 * Math.log(Math.random()));
//		
//		if (Math.random() < 0.5) {
//			t = -t;
//		}
//		return t;
//	}

}
