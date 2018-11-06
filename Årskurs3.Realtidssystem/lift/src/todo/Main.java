package todo;

import java.util.ArrayList;
import java.util.List;

import done.*;

public class Main {

	public static void main(String[] args) {
		LiftView lv = new LiftView();
		LevelDrawer lD = lv.getLevelDrawer();
		BasketDrawer bD = lv.getBasketDrawer();
		Monitor monitor = new Monitor(lD, bD);
		
		Lift lift = new Lift(monitor);
		List<Person> persons = new ArrayList<Person>();
		
		lift.start();
		
		for(int i = 0; i < 20; i++){
			persons.add(new Person(monitor));
		}
		
		for(Person p: persons){
			p.start();
		}

	}
	
}
