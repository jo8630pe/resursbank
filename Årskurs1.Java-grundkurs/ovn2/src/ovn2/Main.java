package ovn2;
import se.lth.cs.p.ovn.turtle.*;
import se.lth.cs.window.*;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(500, 500, "Turtle");
		Turtle t = new Turtle(w, 350, 350);
		t.penDown();
		Scanner scan = new Scanner(System.in);
		System.out.println("Mata in antalet hörn din månghörning ska ha: ");
		int n = scan.nextInt();
		t.right(360/n);
		for (int i = 0; i < n; i++){
			t.forward(100);
			t.left(360/n);
		}
	}
}
