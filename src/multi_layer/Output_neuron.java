package multi_layer;

import math.Matrix;
import math.MatrixOperation;

public class Output_neuron {
	
	Matrix input; // I commented this out, because I'm gonna pass it in as a parameter.
	Matrix weight;
	double bias;
	Matrix Z;
	Matrix output;
	
	
	/*******************************************************************************************
	 * Generic constructor
	 ******************************************************************************************/
	public Output_neuron(){
		
	}
	
	/*******************************************************************************************
	 * Takes an input and adds it to the input matrix.
	 * It also modifies the weight matrix to make sure it has 
	 * enough weights. 
	 * @param in
	 ******************************************************************************************/
	public void addInput(Matrix in){
		//this.input.add(in);
		//this.weight.add(in); // the number of weights must be equal to the number of inputs
	}
	
	/*******************************************************************************************
	 * Multiplies the inputs by the weights and adds the bias.
	 * The results are stored in Z
	 ******************************************************************************************/
	public void summation_function(){
		MatrixOperation o = new MatrixOperation();
		this.Z = o.multiplyM(input, weight);
		double temp;
		for(int i = 0; i < Z.rows(); i++){
			temp = Z.getElement(i, 0);
			Z.setElement(i, 0, temp);
		}
	}
	
	
	
	
	
}
