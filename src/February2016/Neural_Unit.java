package February2016;

import java.util.Scanner;

import math.Activation_Functions;
import math.Matrix;
import math.MatrixOperation;


/*********************************************************************
 * This class represents a single feed-forward neural netowrk.
 * There is no main method in here. Only contains the variables
 * and methods needed for a neural net.
 * @author zwick
 ********************************************************************/
public class Neural_Unit {
	private double LEARNING_RATE = 0.2;
	private int layers; // the total number of layers
	private int neurons_per_layer[]; // Describes the number of neurons in each layer... neurons[0] is num neurons in layer 1.
	private int num_inputs; // the number of inputs that we use.
	private Matrix X;   // A matrix containing the input values.
	private Matrix[] W; // Array of weight matrices
	private Matrix[] B; // Array of bias matrices
	private Matrix[] Z; // Array of Z matrices
	private Matrix[] A; // Array of layer output matrices
	private Matrix[] P; // Array of P matrices
	private Matrix[] D; // Array of D matrices
	private Matrix[] S; // Array of error correction matrices
	private Matrix Y;   // A matrix of expected values
	private Matrix Output; // The output matrix
	private Matrix C;   // A matrix of error values
	
	/***************************************************************************
	 * If not more, we initialize the array lengths of the 
	 * arrays above using the parameters.
	 * @param lyrs - Number of layers in the network.
	 * @param npl - An array containing the number of neurons per payer.
	 * @param num_in - Number of inputs.
	 **************************************************************************/
	public Neural_Unit(int lyrs, int npl[], int num_in){
		MatrixOperation o = new MatrixOperation();
	    this.layers = lyrs;
	    /* Initialize Neurons per layer. */
	    this.neurons_per_layer = new int[this.layers];
	    for(int i = 0; i < this.layers; i++){
	    	this.neurons_per_layer[i] = npl[i];
	    }
	    
	    /* Initialize the arrays. */
	    this.W = new Matrix[this.layers]; // Populate and initialize matrix dimensions later.
	    this.B = new Matrix[this.layers];
	    this.Z = new Matrix[this.layers];
	    this.A = new Matrix[this.layers];
	    this.P = new Matrix[this.layers-1]; // All but output layer.
	    this.D = new Matrix[this.layers];
	    this.S = new Matrix[this.layers];
	    /* Initialize the matrices. */
	    for(int i = 0; i < layers; i++){
	    	this.B[i] = new Matrix(this.neurons_per_layer[i], 1);
	    	this.Z[i] = new Matrix(this.neurons_per_layer[i], 1);
	    	this.A[i] = new Matrix(this.neurons_per_layer[i], 1);
	    	this.D[i] = new Matrix(this.neurons_per_layer[i], 1);
	    	this.S[i] = new Matrix(this.neurons_per_layer[i], 1);
	    }
	    this.C = new Matrix(this.neurons_per_layer[this.layers-1], 1);
	    this.Y = new Matrix(this.neurons_per_layer[this.layers-1], 1);
	    this.Output = new Matrix(this.neurons_per_layer[this.layers-1], 1);
	    this.num_inputs = num_in;
	    this.X = new Matrix(this.num_inputs, 1);
	    initializeWeights();
	    initializeP();
	    /* Populate the matrices. */
	    for(int i = 0; i < this.layers; i++){
	    	o.populate_with_zeroes(this.B[i]);
	    }
	}
	
	/**************************************************************************************
	 * Initialize the weight matrix dimensions
	 * and populate the matrices with zeros.
	 *************************************************************************************/
	private void initializeWeights(){
		MatrixOperation o = new MatrixOperation();
		for(int i = 0; i < this.layers; i++){
			if(i == 0){
				this.W[i] = new Matrix(this.neurons_per_layer[i], this.num_inputs);
			} else{
				this.W[i] = new Matrix(this.neurons_per_layer[i], this.neurons_per_layer[i-1]);
			}
			o.populate_with_zeroes(this.W[i]);
		}
	}
	
	/**************************************************************************************
	 * Initialize the p matrix dimensions.
	 *************************************************************************************/
	private void initializeP(){
		MatrixOperation o = new MatrixOperation();
		for(int i = 0; i < (layers-1); i++){ // Dont initialize the last layer... because there isn't P in the last layer.
			this.P[i] = new Matrix(this.neurons_per_layer[i], 1);
			o.populate_with_zeroes(this.P[i]);
		}
	}
	
	/*********************************************************************************************
	 * Perform forward propagation.
	 * @param x[] - An array of inputs
	 ********************************************************************************************/
	public void feed_forward(double x[]){
		//TODO make parameter count arbitrary
		Activation_Functions af = new Activation_Functions();
		MatrixOperation o = new MatrixOperation();
		/* Populate input matrix. */
		for(int i = 0; i < this.num_inputs; i++){
			this.X.setElement(i, 0, x[i]);
		}
		Matrix temp = new Matrix(0, 0);//for use in the loop
		for(int i = 0; i < this.layers; i++){
			/* Populate the Z matrix. */
			if(i == 0){ // For input layer.
				temp.equals(o.multiplyM(this.W[i], this.X));
				this.Z[i].equals(o.add(temp, this.B[i]));
			} else{ // For all subsequent layers.
				temp.equals(o.multiplyM(this.W[i], this.A[i-1]));
				this.Z[i].equals(o.add(temp, this.B[i]));
			}
			/* Populate the A matrix. */
			this.A[i].equals(af.a1(this.Z[i]));
		}
		setOutput();
	}
	
	/************************************************************************************
	 * Performs the backpropagation algorithm on the network.
	 * @param expected - A matrix of expected values.
	 ***********************************************************************************/
	public void backPropagate(Matrix expected){
		MatrixOperation o = new MatrixOperation();
		Activation_Functions af = new Activation_Functions();
		/* Initialize expected values. */
		this.Y.equals(expected);
		/* Initialize Cost vector. */
		this.C.equals(o.subtract(this.A[this.layers-1], this.Y));
		//////////// Experimental C ////////////////////
		// C = (expected - actual)^2 / 2
		/*this.C.equals(o.subtract(Y, A[layers-1]));
		this.C.equals(o.hadamardProduct(C, C));
		this.C.equals(o.scalar_division(2, C));*/
		/////////// End Experimental C /////////////////
		
		Matrix temp = new Matrix(0, 0);
		/* Initialize/Update the Error matrices. */
		for(int i = (this.layers-1); i >= 0; i--){
			this.D[i].equals(af.a1_prime(Z[i]));
			if(i == (this.layers-1)){ // Output layer
				this.S[i].equals(o.hadamardProduct(this.C, this.D[i]));
			} else{ // not output layer
				temp.equals(o.transpose(W[i+1]));
				this.P[i].equals(o.multiplyM(temp, this.S[i+1]));
				this.S[i].equals(o.hadamardProduct(this.P[i], this.D[i]));
			}
		}
		/* Initialize/Update the weight matrices. */
		for(int i = (this.layers-1); i >= 0; i--){
			if(i == 0){ // For the input layer.
				temp.equals(o.transpose(this.X));
			} else{ // For any layer that's not input.
				temp.equals(o.transpose(this.A[i-1]));
			}
			temp.equals(o.multiplyM(this.S[i], temp));
			temp.equals(o.scalar_multiplication((this.LEARNING_RATE), temp));
			this.W[i].equals(o.subtract(this.W[i], temp));
		}
		/* Initialize/Update the biases. */
		for(int i = (layers-1); i >= 0; i--){
			temp.equals(o.scalar_multiplication((this.LEARNING_RATE), this.S[i]));
			this.B[i].equals(o.subtract(this.B[i], temp));
		}
	}
	
	private void setOutput(){
		MatrixOperation o = new MatrixOperation();
		int index[] = new int[2];
		index[0] = o.get_Max(this.A[layers-1])[0];
		index[1] = o.get_Max(this.A[layers-1])[1];
		o.populate_with_zeroes(Output);
		Output.setElement(index[0], index[1], 1);
		/*for(int i = 0; i < this.neurons_per_layer[this.layers-1]; i++){
			if(this.A[this.layers-1].getElement(i, 0) > 0.5){
				this.Output.setElement(i, 0, 1);
			} else{
				this.Output.setElement(i, 0, 0);
			}
		}*/
	}
	
	public Matrix getOutput(){
		return this.Output;
		//return this.A[layers-1];
	}
	
}
