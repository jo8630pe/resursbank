import java.util.ArrayList;

import se.lth.cs.window.SimpleWindow;

public class ShapeList {
	private ArrayList<Shape> sh;
	
	public ShapeList(){
		sh = new ArrayList<Shape>();
	}
	public void insert(Shape s){
		sh.add(s);
	}
	public void draw(SimpleWindow w){
		for(int i = 0; i < sh.size(); i++){
			sh.get(i).draw(w);
		}
	}
	public Shape findHit(int xc, int yc){
		for(int i = 0; i < sh.size(); i++){
			if(sh.get(i).near(xc, yc)){
				return sh.get(i);
			}
			
		}
		return null;
	}

}
