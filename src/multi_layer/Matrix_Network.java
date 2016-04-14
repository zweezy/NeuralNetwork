package multi_layer;

import java.util.Random;
import java.util.Scanner;

import network.Teacher;
import math.Activation_Functions;
import math.Matrix;
import math.MatrixOperation;

public class Matrix_Network {
	private double LEARNING_RATE = 300;
	public int layers; // the total number of layers
	public int neurons_per_layer[]; // Describes the number of neurons in each layer... neurons[0] is num neurons in layer 1.
	private int num_inputs; // the number of inputs that we use.
	private Matrix X;   // A matrix containing the input values.
	private Matrix[] W; // Array of weight matrices
	private Matrix[] B; // Array of bias matrices
	private Matrix[] Z; // Array of Z matrices
	public Matrix[] A; // Array of layer output matrices
	private Matrix[] P; // Array of P matrices
	private Matrix[] D; // Array of D matrices
	private Matrix[] S; // Array of error correction matrices
	public Matrix Y;   // A matrix of expected values
	public Matrix C;   // A matrix of error values
	
	/**
	 * Generic constructor.
	 */
	public Matrix_Network(){
		
	}
	
	/***************************************************************************
	 * If not more, we initialize the array lengths of the 
	 * arrays above using user input.
	 **************************************************************************/
	public void setUp(){
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
	public void initializeWeights(){
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
	public void initializeP(){
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
	public void feed_forward(double x, double y){
		//TODO make parameter count arbitrary
		Activation_Functions af = new Activation_Functions();
		MatrixOperation o = new MatrixOperation();
		/* Populate input matrix. */
		X.setElement(0, 0, x);
		X.setElement(1, 0, y);
		
		Matrix temp;//for use in the loop
		for(int i = 0; i < layers; i++){
			/* Populate the Z matrix. */
			if(i == 0){ // For input layer.
				temp = o.multiplyM(W[i], X);
				Z[i] = o.add(temp, B[i]);
			} else{ // For all subsequent layers.
				temp = o.multiplyM(W[i], A[i-1]);
				Z[i] = o.add(temp, B[i]);
			}
			/* Populate the A matrix. */
			A[i] = af.a1(Z[i]);
		}
	}
	
	/************************************************************************************
	 * Performs the backpropagation algorithm on the network.
	 ***********************************************************************************/
	public void backPropagate(long itteration){ // fuck it. take that parameter out later.
		MatrixOperation o = new MatrixOperation();
		Activation_Functions af = new Activation_Functions();
		Matrix temp;
		/* Initialize/Update the Error matrices. */
		for(int i = (layers-1); i >= 0; i--){
			D[i] = af.a1_prime(Z[i]);
			if(i == (layers-1)){ // Output layer
				S[i] = o.hadamardProduct(C, D[i]);
			} else{ // not output layer
				temp = o.transpose(W[i+1]);
				P[i] = o.multiplyM(temp, S[i+1]);
				S[i] = o.hadamardProduct(P[i], D[i]);
			}
		}
		/* Initialize/Update the weight matrices. */
		/*for(int i = (layers-1); i >= 0; i--){
			if(i == layers-1 && i != 0){ // output layer
				temp = o.multiplyM(A[i-1], S[i]);
				temp = o.scalar_multiplication(LEARNING_RATE, temp);
				W[i] = o.subtract(W[i], temp);
			} else if(i == 0){ // input layer
				temp = o.multiplyM(X, S[i]);				
				temp = o.scalar_multiplication(LEARNING_RATE, temp);
				W[i] = o.subtract(W[i], temp);
			} else { // hidden layer
				temp = o.multiplyM(A[i-1], S[i]);				
				temp = o.scalar_multiplication(LEARNING_RATE, temp);
				W[i] = o.subtract(W[i], temp);
			}
		}*/
		/* Initialize/Update the biases. */
		/*for(int i = (layers-1); i >= 0; i--){
			temp = o.scalar_multiplication((LEARNING_RATE), S[i]);
			B[i] = o.subtract(B[i], temp);
		}*/
		
		/* Initialize/Update the weight matrices. */
		for(int i = (layers-1); i >= 0; i--){
			if(i == 0){ // For the input layer.
				temp = o.transpose(X);
				temp = o.multiplyM(S[i], temp);
				temp = o.scalar_multiplication((LEARNING_RATE), temp);
				W[i] = o.subtract(W[i], temp);
			} else{ // For any layer that's not input.
				temp = o.transpose(A[i-1]);
				temp = o.multiplyM(S[i], temp);
				temp = o.scalar_multiplication((LEARNING_RATE), temp);
				W[i] = o.subtract(W[i], temp);
			}
		}
		/* Initialize/Update the biases. */
		for(int i = (layers-1); i >= 0; i--){
			temp = o.scalar_multiplication((LEARNING_RATE), S[i]);
			B[i] = o.subtract(B[i], temp);
		}
	}
	
}
