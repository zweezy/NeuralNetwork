package network;

import math.Matrix;

public class Teacher {

	/**
	 * Returns correct classification when there are 4 output neurons.
	 * @param x
	 * @param y
	 * @return
	 */
	public Matrix getCorrect4(double x, double y){
		Matrix dest = new Matrix(4,1);
		for(int i = 0; i < 4; i++){
			dest.setElement(i, 0, 0); // initialize everything to 0
		}
		Line l = new Line(); // positive slope
		Line1 l1 = new Line1(); // negative slope
		if(l.isAbove(x, y) && l1.isAbove(x, y)){
			dest.setElement(0, 0, 1);
		} else if(!l.isAbove(x, y) && l1.isAbove(x, y)){
			dest.setElement(1, 0, 1);
		} else if(!l.isAbove(x, y) && !l1.isAbove(x, y)){
			dest.setElement(2, 0, 1);
		} else if(l.isAbove(x, y) && !l1.isAbove(x, y)){
			dest.setElement(3, 0, 1);
		}
		return dest;
	}
	
	/**
	 * Returns correct output with negative sloping line and single output neuron.
	 * @param x
	 * @param y
	 * @return
	 */
	public Matrix getCorrect1(double x, double y){
		Matrix dest = new Matrix(1,1);
		double temp = -x + 100;
		if(y > temp){
			dest.setElement(0, 0, 1);
		} else{
			dest.setElement(0, 0, 0);
		}
		return dest;
	}
	
	
	
	
}
