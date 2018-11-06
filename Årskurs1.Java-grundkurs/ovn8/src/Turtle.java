import se.lth.cs.window.SimpleWindow;

public class Turtle {
	private SimpleWindow w;
	private double x;
	private double y;
	private int beta;
	private boolean penDown;
 
  /** skapar en sk�ldpadda som ritar i ritf�nstret w. 
      Fr�n b�rjan befinner sig sk�ldpaddan i punkten xHome,yHome med pennan 
      lyft och huvudet pekande rakt upp�t i f�nstret, dvs i negativ y-riktning  
  */
  Turtle(SimpleWindow w, int xHome, int yHome) {
	  x = xHome;
	  y = yHome;
	  this.w = w;
	  penDown = false;
	  beta = 90;
  }

  /** s�nker pennan */
  void penDown() {
	  penDown = true;
  }

  /** lyfter pennan */
  void penUp() {
	  penDown = false;
  }

  /** g�r rakt fram�t n pixlar i huvudets riktning */
  void forward(int n) {
	  w.moveTo((int)Math.round(x),(int)Math.round(y));
	  x += n*Math.cos(beta*Math.PI/180);
	  y -= n*Math.sin(beta*Math.PI/180);
	  if(penDown){
		  w.lineTo((int)Math.round(x),(int)Math.round(y)); 
	  } 
  }

  /** vrider huvudet beta grader �t v�nster */
  void left(int beta) {
	  this.beta += beta;
 
  }

  /** vrider hvudet beta grader �t h�ger */
  void right(int beta) {
	  this.beta -= beta;

  }

  /** g�r till punkten newX,newY utan att rita. 
      Pennans l�ge och huvudets riktning p�verkas inte */
  void jumpTo(int newX, int newY) {
	  x = newX;
	  y = newY;
  }

  /** �terst�ller huvudets riktning till den ursprungliga */
  void turnNorth() {
	  beta = 90;
  }

  /** tar reda p� sk�ldpaddans aktuella x-koordinat */
  int getX() {
	  return (int)Math.round(x);
  }

  /** tar reda p� sk�ldpaddans aktuella y-koordinat */
  int getY() {
	  return (int)Math.round(y);
  }
}
