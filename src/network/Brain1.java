package network;
import java.util.Random;
import java.util.Scanner;


public class Brain1 {
	private static double x;
	private static double y;
	
	private static double v; // out of summation function
	
	private static boolean perceptronAbove; // = p1.activate(v);
    private static boolean actualAbove; // from the line
    
	// Input layer of neurons
	private static Perceptron1 p1 = new Perceptron1();
	//math
	private static Random rand1 = new Random();
	private static Random rand2 = new Random();
	private static Line l1 = new Line();
    //private static Line1 l1 = new Line1();

    
    
	public static void main(String[] args){
	    
	    Scanner scan = new Scanner(System.in);
	    System.out.println("How many iterations in Brain1? ");
	    int iterations = scan.nextInt();
	    scan.close();
	    
	    double correctCount = 0;
	    //double wrongCount = 0;
	    double actAbovePercentTest = 0;
	    double perAbovePercentTest = 0;
    	double yOverX = 0;
    	double percentCorrect = 0;
    	
	    //for(int i = 1; i <= iterations; i++){
    	long i = 1;
    	while(percentCorrect < 90 || percentCorrect == 100){
	    	x = rand1.nextDouble() * 100;
	    	y = rand2.nextDouble() * 100;
	    	//System.out.println("x = " + x);
	    	//System.out.println("y = " + y);

	    	v = p1.testSum(x, y);
	    	
	    	perceptronAbove = p1.activate(v);
	    	actualAbove = l1.isAbove(x, y);
	    	
	    	//System.out.println("Itteration: " + i);
	    	
	    	if(perceptronAbove == actualAbove){
	    		//do nothing.
	    		correctCount++; // num times it is correct
	    	} else{
	    		//train
	    		if(perceptronAbove == true){ // By default, at this point, actualAbove == false.
	    			p1.trainDown();
	    		} else{ // actualAbove == true
	    			p1.trainUp();
	    		}
	    	}
	    	percentCorrect = (correctCount / i)*100;
	    	i++;
	    	System.out.println("Iteration: " + i);
	    	if(actualAbove){
	    		actAbovePercentTest++;
	    	}
	    	if(perceptronAbove){
	    		perAbovePercentTest++;
	    	}
	    	if(y > x){
	    		yOverX++;
	    	}
	    	// So we're just going to print the percentage correct at each iteration.
	    	//System.out.println("correct" + correctCount);
	    	System.out.println("Percentage Correct: " + percentCorrect);
	    	//System.out.println("Percent actually above: " + (actAbovePercentTest / i) * 100);
	    	//System.out.println("Percent perceptron above: " + (perAbovePercentTest / i) * 100);

	    	//System.out.println("Y over X: " + (yOverX / i) * 100);

	    }
		
	}
}

