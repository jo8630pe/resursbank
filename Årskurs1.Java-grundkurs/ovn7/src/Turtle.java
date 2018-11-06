import se.lth.cs.window.SimpleWindow;

public class Turtle {
	private SimpleWindow w;
	private double x;
	private double y;
	private int beta;
	private boolean penDown;
 
  /** skapar en sköldpadda som ritar i ritfönstret w. 
      Från början befinner sig sköldpaddan i punkten xHome,yHome med pennan 
      lyft och huvudet pekande rakt uppåt i fönstret, dvs i negativ y-riktning  
  */
  Turtle(SimpleWindow w, int xHome, int yHome) {
	  x = xHome;
	  y = yHome;
	  this.w = w;
	  penDown = false;
	  beta = 90;
  }

  /** sänker pennan */
  void penDown() {
	  penDown = true;
  }

  /** lyfter pennan */
  void penUp() {
	  penDown = false;
  }

  /** går rakt framåt n pixlar i huvudets riktning */
  void forward(int n) {
	  w.moveTo((int)Math.round(x),(int)Math.round(y));
	  x += n*Math.cos(beta*Math.PI/180);
	  y -= n*Math.sin(beta*Math.PI/180);
	  if(penDown){
		  w.lineTo((int)Math.round(x),(int)Math.round(y)); 
	  } 
  }

  /** vrider huvudet beta grader åt vänster */
  void left(int beta) {
	  this.beta += beta;
 
  }

  /** vrider hvudet beta grader åt höger */
  void right(int beta) {
	  this.beta -= beta;

  }

  /** går till punkten newX,newY utan att rita. 
      Pennans läge och huvudets riktning påverkas inte */
  void jumpTo(int newX, int newY) {
	  x = newX;
	  y = newY;
  }

  /** återställer huvudets riktning till den ursprungliga */
  void turnNorth() {
	  beta = 90;
  }

  /** tar reda på sköldpaddans aktuella x-koordinat */
  int getX() {
	  return (int)Math.round(x);
  }

  /** tar reda på sköldpaddans aktuella y-koordinat */
  int getY() {
	  return (int)Math.round(y);
  }
}
