package network;

import math.Matrix;



/**
 * A class of matrix operations
 * @author zwick
 *
 */
public class MatrixOperations {
	
	
	/**
	 * Multiply m1*m2 and store the results in dest
	 * @param m1
	 * @param m2
	 * @param dest
	 * @return 
	 */
	public Matrix multiplyM(Matrix m1, Matrix m2, Matrix dest){
		int rows = m1.rows();
		int cols = m2.cols();
		int num = 0;
		int n;
		if(m1.cols() == m2.rows()){
			n = m1.cols();
		} else {
			//Can't multiply?
			return null;
		}
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				for(int k = 0; k < n; k++){
					num += m1.getElement(i, k) * m2.getElement(k, j);
				}
				dest.setElement(i, j, num);
			}
		}
		return dest;
	}
	
	/**
	 * Adds matrix a to matrix b and stores the result in dest.
	 * @param a
	 * @param b
	 * @param dest
	 * @return dest.
	 */
	public Matrix addMatrix(Matrix a, Matrix b, Matrix dest){
		//TODO
		return dest;
	}
	
	/**
	 * Subtracts matrix b from matrix a and stores the result in dest.
	 * @param a
	 * @param b
	 * @param dest
	 * @return dest.
	 */
	public Matrix subtractMatrix(Matrix a, Matrix b, Matrix dest){
		//TODO
		return dest;
	}
	
	/**
	 * Divide each number in a matrix by the maximum
	 * value in that matrix, making each value between
	 * 0 and 1.
	 * @param m
	 * @param max
	 */
	public void scaleData(Matrix m, double max){
		int rows = m.rows();
		int cols = m.cols();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				m.setElement(i, j, (m.getElement(i, j)/max));
			}
		}
	}
	
	/**
	 * Find the maximum value in a matrix.
	 * @param m
	 * @return
	 */
	public double findMax(Matrix m){
		int rows = m.rows();
		int cols = m.cols();
		double temp = m.getElement(0, 0);
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(m.getElement(i, j) > temp){
					temp = m.getElement(i, j);
				}
			}
		}
		return temp;
	}

}
