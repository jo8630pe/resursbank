import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Register {
	private ArrayList<Person> p;

	
	public Register(){
		 p = new ArrayList<Person>();
		 
		 p.add(new Person("reOle", "1805"));
		 p.add(new Person("werwOle", "2805"));
		 p.add(new Person("fghgOle", "3805"));
		 p.add(new Person("Ole", "4405"));
		 p.add(new Person("Ogergle", "4505"));
		 p.add(new Person("Oegle", "4805"));
		 p.add(new Person("sssOle", "1005"));
		 p.add(new Person("ooooswwOle", "2005"));
		 sortName(p);
	}
	
	public void insert(String name, String date){
		p.add(new Person(name, date));
		
	}
	
	private void sortName(ArrayList<Person> p){
		
		for(int i = 0; i < p.size() -1; i++){
			int minIndex = i;
			String min = p.get(i).getName();
			for (int k = i + 1; k < p.size(); k++){
				if(p.get(k).getName().compareToIgnoreCase(min) < 0){
					minIndex = k;
					min = p.get(k).getName();
				}
			}
			if(minIndex != i){
				Person temp =  p.get(minIndex);
				p.set(minIndex, p.get(i));
				p.set(i, temp);
			}

		}

	}
	
	public String printByName(){
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < p.size(); i++){
			sb.append(p.get(i).getName() + " " + p.get(i).getDate() + "\n");
			
		}
		return sb.toString();
	}
	
	public String printByDate(){
		StringBuilder sb = new StringBuilder();
		ArrayList<Person> temp = new ArrayList<Person>();
		
		for(int i = 0; i < p.size(); i++){
			temp.add(i, p.get(i));
		}
		
		for(int i = 0; i < temp.size() -1; i++){
			int minIndex = i;
			String min = temp.get(i).getDate();
			for (int k = i + 1; k < temp.size(); k++){
				if(temp.get(k).getDate().compareToIgnoreCase(min) < 0){
					minIndex = k;
					min = temp.get(k).getDate();
				}
			}
			if(minIndex != i){
				Person tem =  temp.get(minIndex);
				temp.set(minIndex, temp.get(i));
				temp.set(i, tem);
			}

		}
		
		for(int i = 0; i < temp.size(); i++){
			sb.append (temp.get(i).getDate() + " " + temp.get(i).getName() + "\n");
			
		}
		return sb.toString();
	
	}
	
}
