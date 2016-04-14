package multi_layer;

import java.util.Random;
import java.util.Scanner;

import network.Teacher;
import math.Activation_Functions;
import math.Matrix;
import math.MatrixOperation;

public class Matrix_Network1 {
	private static double LEARNING_RATE = 1;
	private static int layers; // the total number of layers
	private static int neurons_per_layer[]; // Describes the number of neurons in each layer... neurons[0] is num neurons in layer 1.
	private static int num_inputs; // the number of inputs that we use.
	private static Matrix X;   // A matrix containing the input values.
	private static Matrix[] W; // Array of weight matrices
	private static Matrix[] B; // Array of bias matrices
	private static Matrix[] Z; // Array of Z matrices
	private static Matrix[] A; // Array of layer output matrices
	private static Matrix[] P; // Array of P matrices
	private static Matrix[] D; // Array of D matrices
	private static Matrix[] S; // Array of error correction matrices
	private static Matrix Y;   // A matrix of expected values
	private static Matrix C;   // A matrix of error values
	
	private static Random rand1 = new Random();
	private static double x;
	private static double y;
	
	/***************************************************************************
	 * If not more, we initialize the array lengths of the 
	 * arrays above using user input.
	 **************************************************************************/
	private static void setUp(){
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
	    initializeWeights();
	    initializeP();
	}
	
	/**************************************************************************************
	 * Initialize the weight matrix dimensions
	 * and populate the matrices with random numbers.
	 *************************************************************************************/
	private static void initializeWeights(){
		MatrixOperation o = new MatrixOperation();
		for(int i = 0; i < layers; i++){
			if(i == 0){
				W[i] = new Matrix(neurons_per_layer[i], num_inputs);
			} else{
				W[i] = new Matrix(neurons_per_layer[i], neurons_per_layer[i-1]);
			}
			o.populateMatrix(W[i]);
		}
	}
	
	/**************************************************************************************
	 * Initialize the p matrix dimensions.
	 *************************************************************************************/
	private static void initializeP(){
		for(int i = 0; i < (layers-1); i++){ // Dont initialize the last layer... because there isn't P in the last layer.
			if(i == 0){
				P[i] = new Matrix(neurons_per_layer[i], num_inputs);
			} else{
				W[i] = new Matrix(neurons_per_layer[i], neurons_per_layer[i-1]);
			}
		}
	}
	
	/*********************************************************************************************
	 * Perform forward propagation.
	 * @param x
	 * @param y
	 ********************************************************************************************/
	private static void feed_forward(double x, double y){
		//TODO make parameter count arbitrary
		Activation_Functions af = new Activation_Functions();
		MatrixOperation o = new MatrixOperation();
		/* Populate input matrix. */
		X.setElement(0, 0, x);
		X.setElement(1, 0, y);
		
		Matrix temp = new Matrix();//for use in the loop
		for(int i = 0; i < layers; i++){
			/* Populate the Z matrix. */
			if(i == 0){ // For input layer.
				Z[i].equals(o.multiplyM(W[i], X));
				Z[i].equals(o.add(Z[i], B[i]));
			} else{ // For all subsequent layers.
				Z[i].equals(o.multiplyM(W[i], A[i-1]));
				Z[i].equals(o.add(Z[i], B[i]));
			}
			/* Populate the A matrix. */
			A[i].equals(af.a1(Z[i]));
		}
	}
	
	/************************************************************************************
	 * Performs the backpropagation algorithm on the network.
	 ***********************************************************************************/
	private static void backPropagate(long itteration){ //take that parameter out later.
	//private static void backPropagate(){
		MatrixOperation o = new MatrixOperation();
		Activation_Functions af = new Activation_Functions();
		Matrix temp = new Matrix(0,0);
		/* Initialize/Update the Error matrices. */
		for(int i = (layers-1); i >= 0; i--){
			D[i].equals(af.a1_prime(Z[i]));
			if(i == (layers-1)){
				S[i].equals(o.hadamardProduct(C, D[i]));
			} else{
				temp.equals(o.transpose(W[i+1]));
				P[i].equals(o.multiplyM(temp, S[i+1]));
				S[i].equals(o.hadamardProduct(P[i], D[i]));
			}
		}
		/* Initialize/Update the weight matrices. */
		for(int i = (layers-1); i >= 0; i--){
			if(i == 0){ // For the input layer.
				temp.equals(o.transpose(X));
				temp.equals(o.multiplyM(S[i], temp));
				temp.equals(o.scalar_multiplication((LEARNING_RATE), temp));
				W[i].equals(o.subtract(W[i], temp));
				//W[i] = o.add(W[i], temp);
			} else{ // For any layer that's not input.
				temp.equals(o.transpose(A[i-1]));
				temp.equals(o.multiplyM(S[i], temp));
				temp.equals(o.scalar_multiplication((LEARNING_RATE), temp));
				W[i].equals(o.subtract(W[i], temp));
				//W[i] = o.add(W[i], temp);
			}
		}
		/* Initialize/Update the biases. */
		for(int i = (layers-1); i >= 0; i--){
			temp.equals(o.scalar_multiplication((LEARNING_RATE), S[i]));
			B[i].equals(o.subtract(B[i], temp));
		}
	}
	
	/*************************************************************************************
	 * Runs the network.
	 * @param args
	 ************************************************************************************/
	public static void main(String[] args){
		MatrixOperation o = new MatrixOperation();
		Teacher teach = new Teacher();
		setUp();
		
	    double correctCount = 0;
    	double percentCorrect = 0;
    	
    	double n1;
    	double n2;
    	int test = 0; // for use later to count through the output matrix.
    	long i = 1; // count itterations.
    	while(percentCorrect < 93 || (i < 100)){
	    	x = rand1.nextDouble() * 100;
	    	y = rand1.nextDouble() * 100;
	    	feed_forward(x,y);
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
	}
}
