import se.lth.cs.window.SimpleWindow;

public class Turtle {
	private SimpleWindow w;
	private int x;
	private int y;
	private int beta;
	private boolean penDown;
 
  /** skapar en sköldpadda som ritar i ritfönstret w. 
      Från början befinner sig sköldpaddan i punkten xHome,yHome med pennan 
      lyft och huvudet pekande rakt uppåt i fönstret, dvs i negativ y-riktning  
  */
  Turtle(SimpleWindow w, int xHome, int yHome) {
	  x = xHome;
	  y = yHome;
	  w = new SimpleWindow(xHome,yHome, "Turtle");
	  penDown = false;
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
	  w.lineTo(n*Math.cos(beta) - x, arg1);
  }

  /** vrider huvudet beta grader åt vänster */
  void left(int beta) {
  }

  /** vrider hvudet beta grader åt höger */
  void right(int beta) {
  }

  /** går till punkten newX,newY utan att rita. 
      Pennans läge och huvudets riktning påverkas inte */
  void jumpTo(int newX, int newY) {
  }

  /** återställer huvudets riktning till den ursprungliga */
  void turnNorth() {
  }

  /** tar reda på sköldpaddans aktuella x-koordinat */
  int getX() {
  }

  /** tar reda på sköldpaddans aktuella y-koordinat */
  int getY() {
  }
}
