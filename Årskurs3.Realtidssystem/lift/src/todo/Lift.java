package todo;

public class Lift extends Thread{
	private Monitor monitor;	

	
	public Lift(Monitor monitor) {
		this.monitor = monitor;

	}
	
	public void run() {
		while(true) {
			
			monitor.runElevator();


		}
	}	

}
