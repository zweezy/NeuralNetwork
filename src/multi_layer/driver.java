package multi_layer;

import java.util.Random;
import java.util.Scanner;

import network.Teacher;
import math.Activation_Functions;
import math.Matrix;
import math.MatrixOperation;

/*****************************************************************************
 * This class will have all the variables declared within the main class
 * so that I don't have any confusion about scope.
 * @author zwick
 */
public class driver {

	/*************************************************************************************
	 * Runs the network.
	 * @param args
	 ************************************************************************************/
	public static void main(String[] args){
		double LEARNING_RATE = 1.0;
		int layers; // the total number of layers
		int neurons_per_layer[]; // Describes the number of neurons in each layer... neurons[0] is num neurons in layer 1.
		int num_inputs; // the number of inputs that we use.
		Matrix X;   // A matrix containing the input values.
		Matrix[] W; // Array of weight matrices
		Matrix[] B; // Array of bias matrices
		Matrix[] Z; // Array of Z matrices
		Matrix[] A; // Array of layer output matrices
		Matrix[] P; // Array of P matrices
		Matrix[] D; // Array of D matrices
		Matrix[] S; // Array of error correction matrices
		Matrix Y;   // A matrix of expected values
		Matrix C;   // A matrix of error values
		
		
		MatrixOperation o = new MatrixOperation();
		Scanner scan = new Scanner(System.in);
	    System.out.println("How many layers are in your network? ");
	    layers = scan.nextInt();
	    neurons_per_layer = new int[layers];
	    /* Initialize the arrays. */
	    W = new Matrix[layers]; // Populate and initialize matrix dimensions later.
	    B = new Matrix[layers];
	    Z = new Matrix[layers];
	    A = new Matrix[layers];
	    P = new Matrix[layers-1]; // Initialize matrix dimensions later.
	    D = new Matrix[layers];
	    S = new Matrix[layers];
	    /* Initialize the matrices. */
	    for(int i = 0; i < layers; i++){
	    	System.out.println("How many neurons in layer " + (i+1) + "?");
	    	neurons_per_layer[i] = scan.nextInt();
	    	B[i] = new Matrix(neurons_per_layer[i], 1);
	    	Z[i] = new Matrix(neurons_per_layer[i], 1);
	    	A[i] = new Matrix(neurons_per_layer[i], 1);
	    	D[i] = new Matrix(neurons_per_layer[i], 1);
	    	S[i] = new Matrix(neurons_per_layer[i], 1);
	    }
	    C = new Matrix(neurons_per_layer[layers-1], 1);
	    Y = new Matrix(neurons_per_layer[layers-1], 1);
	    /* Populate the matrices. */
	    for(int i = 0; i < layers; i++){
	    	o.populate_with_zeroes(B[i]);
	    }
	    System.out.println("How many inputs to the network? ");
	    num_inputs = scan.nextInt();
	    X = new Matrix(num_inputs, 1);
	    scan.close();
	    for(int i = 0; i < layers; i++){
			if(i == 0){
				W[i] = new Matrix(neurons_per_layer[i], num_inputs);
			} else{
				W[i] = new Matrix(neurons_per_layer[i], neurons_per_layer[i-1]);
			}
			o.populateMatrix(W[i]);
		}
	    
	    for(int i = 0; i < (layers-1); i++){ // Dont initialize the last layer... because there isn't P in the last layer.
			if(i == 0){
				P[i] = new Matrix(neurons_per_layer[i], num_inputs);
			} else{
				W[i] = new Matrix(neurons_per_layer[i], neurons_per_layer[i-1]);
			}
		}
	    
	    Random rand1 = new Random();
		double x;
		double y;
		Teacher teach = new Teacher();
		
	    double correctCount = 0;
    	double percentCorrect = 0;
    	
    	double n1;
    	double n2;
    	int test = 0; // for use later to count through the output matrix.
    	long i = 1; // count itterations.
    	while(percentCorrect < 93 || (i < 100)){
	    	x = rand1.nextDouble() * 100;
	    	y = rand1.nextDouble() * 100;
		    	// Feed forward
		    	Activation_Functions af = new Activation_Functions();
				/* Populate input matrix. */
				X.setElement(0, 0, x);
				X.setElement(1, 0, y);
				
				Matrix temp;//for use in the loop
				for(int j = 0; j < layers; j++){
					/* Populate the Z matrix. */
					if(j == 0){ // For input layer.
						temp = o.multiplyM(W[j], X);
						Z[j] = o.add(temp, B[j]);
					} else{ // For all subsequent layers.
						temp = o.multiplyM(W[j], A[j-1]);
						Z[j] = o.add(temp, B[j]);
					}
					/* Populate the A matrix. */
					A[j] = af.a1(Z[j]);
				}
	    	Y = teach.getCorrect1(x, y);
	    	C = o.subtract(A[layers-1], Y);
	    		//backpropagate
				/* Initialize/Update the Error matrices. */
				for(int j = (layers-1); j >= 0; j--){
					D[j] = af.a1_prime(Z[j]);
					if(j == (layers-1)){ // Output layer
						S[j] = o.hadamardProduct(C, D[j]);
					} else{ // not output layer
						temp = o.transpose(W[j+1]);
						P[j] = o.multiplyM(temp, S[j+1]);
						S[j] = o.hadamardProduct(P[j], D[j]);
					}
				}
				
				/* Initialize/Update the weight matrices. */
				for(int j = (layers-1); j >= 0; j--){
					if(j == 0){ // For the input layer.
						temp = o.transpose(X);
						temp = o.multiplyM(S[j], temp);
						temp = o.scalar_multiplication((LEARNING_RATE), temp);
						W[j] = o.subtract(W[j], temp);
					} else{ // For any layer that's not input.
						temp = o.transpose(A[j-1]);
						temp = o.multiplyM(S[j], temp);
						temp = o.scalar_multiplication((LEARNING_RATE), temp);
						W[j] = o.subtract(W[j], temp);
					}
				}
				/* Initialize/Update the biases. */
				for(int j = (layers-1); j >= 0; j--){
					temp = o.scalar_multiplication((LEARNING_RATE), S[j]);
					B[j] = o.subtract(B[j], temp);
				}
	    	
	    	
	    	
	    	for(int j = 0; j < neurons_per_layer[layers-1]; j++){
	    		n1 = A[layers-1].getElement(j, 0); // actual
	    		n2 = Y.getElement(j, 0); // expected
	    		System.out.printf("Actual: %.2f%s%.2f\n", n1, " Expected: ", n2);
	    		if(((n1 > 0.5) && (n2 > 0.5)) || ((n1 < 0.5) && (n2 < 0.5))){
	    			test++;
    			}
	    	}
	    	if(test > 3){
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
