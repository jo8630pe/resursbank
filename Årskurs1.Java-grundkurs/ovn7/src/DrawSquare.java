import se.lth.cs.window.SimpleWindow;

import java.util.Random;
import java.util.Scanner;


public class DrawSquare {
  public static void main(String[] args) {
    SimpleWindow w = new SimpleWindow(600,600,"Square");
    Turtle t = new Turtle(w,20,20);
    t.penDown();
    Scanner scan = new Scanner(System.in);
    
    System.out.println("Antal kvadrader: ");
    int quantity = scan.nextInt();
    System.out.println("Sidlängd: ");
    int length = scan.nextInt();
    System.out.println("Skillnad i x & y-led: ");
    int delta = scan.nextInt();
    for (int j = 0; j < quantity; j++){
        for (int i = 0; i < 4; i++){
        	t.right(90);
        	t.forward(length);
        }
    	t.jumpTo(t.getX() + delta, t.getY() + delta);
    }
  }
}