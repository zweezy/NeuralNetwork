package February2016;

import java.util.Random;

import math.Matrix;
import math.MatrixOperation;
import network.Teacher;

/***************************************************************************
 * This class is the papa bear. The networks are controlled within here.
 * @author zwick
 **************************************************************************/
public class Brain {
	
	
	/*************************************************************************************
	 * Runs the network.
	 * @param args
	 ************************************************************************************/
	public static void main(String[] args){
		MatrixOperation o = new MatrixOperation();
		Teacher teach = new Teacher();
		int npl[] = {2,4}; // neurons per layer
		Neural_Unit network = new Neural_Unit(2, npl, 2); // create the network
		Random rand1 = new Random();
		
	    double correctCount = 0;
    	double percentCorrect = 0;

    	double instantaneous_p = 0; // percent of outputs that are correct this go round
    	double sum_p = 0; // sum of all p's
    	double avg_p = 0; // avg outputs correct over lifecycle
    	double inputs[] = new double[2];
    	Matrix expect = new Matrix(0,0);
    	Matrix output = new Matrix(0,0);
    	double educated_correct = 1;
    	double educated_count = 1;
    	double educated_percent_correct = 0;
    	int test = 0; // for use later to count through the output matrix.
    	long i = 1; // count itterations.
    	while(percentCorrect < 93 || (i < 100)){
	    	inputs[0] = rand1.nextDouble() * 100;
	    	inputs[1] = rand1.nextDouble() * 100;
	    	network.feed_forward(inputs);
	    	expect.equals(teach.getCorrect4(inputs[0], inputs[1]));
	    	network.backPropagate(expect);
	    	output.equals(network.getOutput());
	    	System.out.println("*********** Start *************");
	    	o.printMatrix(expect);
	    	System.out.println();
	    	o.printMatrix(output);
	    	System.out.println("************ End **************");
	    	for(int j = 0; j < output.rows(); j++){
	    		if(((output.getElement(j, 0) >= 0.5) && (expect.getElement(j, 0) > 0.05)) || ((output.getElement(j, 0) < 0.5) && (expect.getElement(j, 0) < 0.05))){
	    			test++;
	    			instantaneous_p++;
	    		}
	    	}
	    	instantaneous_p = (instantaneous_p / 4)*100;
	    	sum_p += instantaneous_p;
	    	if(test > 3){
	    		correctCount++;
	    		educated_correct++;
	    	}
	    	test = 0;
	    	percentCorrect = (correctCount / i)*100;
	    	educated_percent_correct = (educated_correct / educated_count)*100;
	    	avg_p = (sum_p/i);
	    	instantaneous_p = 0;
	    	System.out.println("Iteration: " + i);
	    	System.out.println("Percent Correct: " + percentCorrect);
	    	System.out.println("Educated Percent Correct: " + educated_percent_correct);
	    	System.out.println("Avg Percent Out Correct: " + avg_p);
	    	educated_count++;
	    	i++;
	    	if(educated_count == 10000){
	    		educated_count = 1;
	    		educated_correct = 1;
	    	}
	    }
	}
	
	
	/*
	 * public static void main(String[] args){
		MatrixOperation o = new MatrixOperation();
		Teacher teach = new Teacher();
		Random rand1 = new Random();
		double x;
		double y;
		
		int npl[] = {2,4}; // neurons per layer
		Neural_Unit network = new Neural_Unit(2, npl, 2); // create the network
		
	    double correctCount = 0;
    	double percentCorrect = 0;
    	
    	double n1;
    	double n2;
    	double inputs[] = new double[2];
    	Matrix Y = new Matrix(0,0);
    	int test = 0; // for use later to count through the output matrix.
    	long i = 1; // count itterations.
    	while(percentCorrect < 93 || (i < 100)){
	    	inputs[0] = rand1.nextDouble() * 100;
	    	inputs[1] = rand1.nextDouble() * 100;
	    	network.feed_forward(inputs);
	    	Y.equals(teach.getCorrect4(x, y));
	    	//Y.equals(teach.getCorrect1(x, y));
	    	C.equals(o.subtract(A[layers-1], Y));
	    	backPropagate(i);
	    	for(int j = 0; j < neurons_per_layer[layers-1]; j++){
	    		n1 = A[layers-1].getElement(j, 0);
	    		n2 = Y.getElement(j, 0);
	    		System.out.printf("Actual: %.2f%s%.2f\n", n1, " Expected: ", n2);
	    		if(((n1 > 0.5) && (n2 > 0.5)) || ((n1 < 0.5) && (n2 < 0.5))){
	    			test++;
    			}
	    	}
	    	if(test > 2){
	    		correctCount++;
	    	} else{
				//backPropagate(i);
			}
	    	test = 0;
	    	percentCorrect = (correctCount / i)*100;
	    	System.out.println("Iteration: " + i);
	    	System.out.println("Percent Correct: " + percentCorrect);
	    	System.out.println("Correct Count: "+ correctCount);
	    	i++;
	    }
	 */
	
	
	
	
	
	
	
	
	
	
	
	
}
