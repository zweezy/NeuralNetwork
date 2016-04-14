package network;
import java.util.Random;
import java.util.Scanner;


public class Brain2 {
	private static double x;
	private static double y;
    
	// neurons
	private static Perceptron2 p1 = new Perceptron2();
	private static Perceptron2 p2 = new Perceptron2();
	private static Perceptron2 C = new Perceptron2();
	
	private static double v1; // out of summation function
	private static double v2; // out of summation function
	private static double v3; // out of summation function
	
	private static double p1out; // = p1.activate(v);
	private static double p2out; // = p1.activate(v);
	private static double Cout; // = p1.activate(v);

    private static boolean actualAbove; // from testClass()
	
	//math
	private static Random rand1 = new Random();
	private static Random rand2 = new Random();
	private static Line l = new Line();
    private static Line1 l1 = new Line1();

    
    
	public static void main(String[] args){
		
		Scanner scan = new Scanner(System.in);
	    System.out.println("How many iterations in Brain2? ");
	    int iterations = scan.nextInt();
	    scan.close();
	    
	    double correctCount = 0;

	    
	    for(int i = 1; i <= iterations; i++){
	    	x = rand1.nextDouble() * 100;
	    	y = rand2.nextDouble() * 100;

	    	v1 = p1.testSum(x, y);
	    	v2 = p2.testSum(x, y);

	    	
	    	p1out = p1.activate(v1);
	    	p2out = p2.activate(v2);
	    	
	    	v3 = C.testSum(p1out, p2out);
	    	Cout = C.activate(v3); // perceptron's final result
	    	
	    	actualAbove = testClass(x, y);
	    	
	    	
	    	if((Cout == 1) && actualAbove){
	    		//do nothing.
	    		correctCount++; // num times it is correct
	    	} else{
	    		//train
	    		if(Cout == 1){ // By default, at this point, actualAbove == false.
	    			p1.trainDown();
	    			p2.trainDown();
	    			C.trainDown();
	    		} else{ // actualAbove == true
	    			p1.trainUp();
	    			p2.trainUp();
	    			C.trainUp();
	    		}
	    	}
	    	double percentCorrect = (correctCount / i)*100;
	    	
	    	// So we're just going to print the percentage correct at each iteration.
	    	System.out.println("Percentage Correct: " + percentCorrect);
	    }
	    
	    
	}
	
	public static boolean testClass(double x, double y){
		if(l.isAbove(x, y) == false && l1.isAbove(x, y)){
			return true;
		}
		return false;
	}
	
}
