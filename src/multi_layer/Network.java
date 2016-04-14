package multi_layer;

import java.util.Random;

import math.MatrixOperation;
import network.Teacher;



public class Network {
	/*************************************************************************************
	 * Runs the network.
	 * @param args
	 ************************************************************************************/
	public static void main(String[] args){
		Matrix_Network n = new Matrix_Network();
		Random rand1 = new Random();
		double x;
		double y;
		MatrixOperation o = new MatrixOperation();
		Teacher teach = new Teacher();
		n.setUp();
		
	    double correctCount = 0;
    	double percentCorrect = 0;
    	
    	double n1;
    	double n2;
    	int test = 0; // for use later to count through the output matrix.
    	long i = 1; // count itterations.
    	while(percentCorrect < 93 || (i < 100)){
	    	x = rand1.nextDouble() * 100;
	    	y = rand1.nextDouble() * 100;
	    	n.feed_forward(x,y);
	    	//Y = teach.getCorrect4(x, y);
	    	n.Y = teach.getCorrect1(x, y);
	    	n.C = o.subtract(n.A[n.layers-1], n.Y);
	    	n.backPropagate(i);
	    	for(int j = 0; j < n.neurons_per_layer[n.layers-1]; j++){
	    		n1 = n.A[n.layers-1].getElement(j, 0); // actual
	    		n2 = n.Y.getElement(j, 0); // expected
	    		System.out.printf("Actual: %.2f%s%.2f\n", n1, " Expected: ", n2);
	    		if(((n1 > 0.5) && (n2 > 0.5)) || ((n1 < 0.5) && (n2 < 0.5))){
	    			test++;
    			}
	    	}
	    	if(test > 0){
	    		correctCount++;
	    	}
	    	test = 0;
	    	percentCorrect = (correctCount / i)*100;
	    	System.out.println("Iteration: " + i);
	    	System.out.println("Percent Correct: " + percentCorrect);
	    	System.out.println("Correct Count: "+ correctCount);
	    	i++;
	    }
	}
	
	
	
}
