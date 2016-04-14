package network;
/*import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList; */
import java.util.Random;
import java.util.Scanner;

public class Brain {
		public static void main(String[] args){
			Random rand = new Random();
			int x; // = rand.nextInt(1000) + 1; // random int between 1 and 1000
			int y; // = rand.nextInt(1000) + 1;
			double change = 0.1; // amount to adjust w by.
			
			// Input layer
			Perceptron p1 = new Perceptron(); // Just need the one, for testing.
			
			double v; // = p1.testSum();
		    boolean perceptronAbove; // = p1.activate(v);
		    boolean actualAbove; // from the line
		    
		    Line l = new Line();
		    
		    Scanner scan = new Scanner(System.in);
		    System.out.println("How many iterations? ");
		    long iterations = scan.nextLong();
		    scan.close();
		    
		    //int[] cRecord = new int[iterations]; // keep track of number of correct outputs in a row as the program progresses and gets better. The values in this array should get bigger over time.
		    //int[] wRecord = new int[iterations]; // same as above, except for wrong. And values should get smaller.
		    int correctCount = 0;
		    int wrongCount = 0;
		    
		    
		    for(long i = 1; i <= iterations; i++){
		    	x = rand.nextInt(100) + 1;
		    	y = rand.nextInt(100) + 1;
		    	System.out.println("X: " + x);
		    	System.out.println("Y: " + y);
		    	System.out.println("W1: " + p1.W1());
		    	System.out.println("W2: " + p1.W2());
		    	
		    	v = p1.testSum(x, y);
		    	perceptronAbove = p1.activate(v);
		    	actualAbove = l.isAbove(x, y);
		    	
		    	System.out.println("Itteration: " + i);
		    	
		    	if(perceptronAbove == actualAbove){
		    		//do nothing.
		    		correctCount++; // num times in a row that it is correct
		    		//wrongCount = 0;
		    	} else{
		    		//train
		    		//correctCount = 0;
		    		wrongCount++;
		    		if(perceptronAbove == true){ // By default, at this point, actualAbove == false.
		    			// train down: w - change
		    			p1.setW1(p1.W1() - x*change);
		    			p1.setW2(p1.W2() - y*change);
		    		} else{
		    			// train up: w + change
		    			p1.setW1(p1.W1() + x*change);
		    			p1.setW2(p1.W2() + y*change);
		    		}
		    	}
		    	System.out.println("Correct count: " + correctCount);
	    		System.out.println("Wrong count: " + wrongCount);
		    }
			
		}
}
