
public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	public void move(int dx, int dy){
		x += dx;
		y += dy;
	}
	
	public String toString(){
		return x + " " + y;
	}
	public double distanceTo(Point p){
		int dx = x - p.getX();
		int dy = y - p.getY();
		
		return Math.sqrt(dx*dx + dy*dy);
	}
	
}
