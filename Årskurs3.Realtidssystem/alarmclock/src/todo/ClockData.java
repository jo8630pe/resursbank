package todo;

import done.ClockInput;
import done.ClockOutput;
import se.lth.cs.realtime.semaphore.MutexSem;
import se.lth.cs.realtime.semaphore.Semaphore;

public class ClockData {

	private int alarmTime;
	private int currentTime;
	private Semaphore mutex;
	private static Semaphore counting;
	private int counter;
	private ClockOutput o;
	private ClockInput i;
	private int previousMode;
	private int newMode;

	public ClockData(ClockOutput o, ClockInput i) {
		this.o = o;
		this.i = i;
		counting = i.getSemaphoreInstance();
		mutex = new MutexSem();
		counter = -1;
		previousMode = newMode = ClockInput.SHOW_TIME;
	}

	public int tick(){ 
		int time = getTime();
		o.showTime(time);
		if((alarmTimeEqualsCurrentTime(time) || (counter < 20 && counter > 0)) && i.getAlarmFlag()){
			o.doAlarm();
			System.out.println(counter);
			counter--;
		}
		return time;
	}

	private int getTime() {
		int time;
		
		mutex.take();
		currentTime++;
		currentTime = formatTime(currentTime);
		time = currentTime;
		mutex.give();
		
		return time;
	}
	
	private int formatTime(int currentTime) {
		int HH = currentTime / 10000;
		int MM = (currentTime / 100) % 100;
		int SS = currentTime % 100;
		
		
		if(SS >= 60){
			MM++;
			SS = SS % 60;
		}
		
		if(MM >= 60){
			HH++;
			MM = MM % 60;
		}
		if(HH >= 24){
			HH = 0;
			MM = 0; 
			SS = 0;
		}
		currentTime = (HH * 10000) + (MM * 100) + SS;
		
		return currentTime;
	}
	
	public void setTime(int HHMMSS) {
		mutex.take();
		currentTime = HHMMSS;
		mutex.give();
	}
	
	public boolean alarmTimeEqualsCurrentTime(int time){
		boolean alarm = false;
		mutex.take();
		if(alarmTime == time){
			counter = 20;
			alarm = true;
		}
		mutex.give();
		return alarm;
	}

	public void actOnButtonEvent() {
		counting.take();
		counter = 20;
		previousMode = newMode;
		newMode = i.getChoice();
		
		if(newMode == ClockInput.SHOW_TIME && previousMode == ClockInput.SET_ALARM){
			mutex.take();
			alarmTime = i.getValue();
			mutex.give();
		}
		else if(newMode == ClockInput.SHOW_TIME && previousMode == ClockInput.SET_TIME){
			mutex.take();
			currentTime = i.getValue();
			mutex.give();
		}
	}
}