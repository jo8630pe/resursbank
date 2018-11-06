package mountain;

public class Side {
	private Point p1, p2;

	
	public Side(Point p1, Point p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	@Override
	public int hashCode() {
		return p1.hashCode() + p2.hashCode();
	}
	
	@Override
	public boolean equals(Object obj) {
		Side temp = (Side) obj;
		
		if(((this.p1 == temp.p1) && (this.p2 == temp.p2)) || ((this.p1 == temp.p2) && (this.p2 == temp.p1))) {
			return true;
		}

		return false;
	}

	public Point getPointA() {
		return p1;
	}


	public Point getPointB() {
		return p2;
	}
	

}
