package math;

import java.util.Random;

public class MatrixOperation {
	
	/********************************************************
	 * Multiply m1*m2 and store the results in dest.
	 * @param m1
	 * @param m2
	 * @return dest
	 * @author zwick
	 ********************************************************/
	public Matrix multiplyM(Matrix m1, Matrix m2){
		Matrix dest = new Matrix(m1.rows(), m2.cols());
		if(m1.cols() != m2.rows()){ // Can't multiply
			return null;
		}
		double temp; // = 0;
		for(int i = 0; i < m1.rows(); i++){
			for(int j = 0; j < m2.cols(); j++){
				temp = 0;
				for(int k = 0; k < m1.cols(); k++){
					temp += m1.getElement(i, k) * m2.getElement(k, j);
				}
				dest.setElement(i, j, temp);
			}
		}
		return dest;
	}
	
	/**
	 * Transpose Matrix m, and return the result.
	 * @param m
	 * @return
	 */
	public Matrix transpose(Matrix m1){
		Matrix dest = new Matrix(m1.cols(), m1.rows());
		for(int i = 0; i < m1.cols(); i++){
			for(int j = 0; j < m1.rows(); j++){
				dest.setElement(i, j, m1.getElement(j, i));
			}
		}
		return dest;
	}
	
	/*********************************************************************
	 * Prints out a 2D matrix, with the 
	 * numbers formatted to 2 decimal places.
	 * @param matrix
	 * @param rows
	 * @param cols
	 * @author zwick
	 ********************************************************************/
	public void printMatrix(Matrix m){
		int rows = m.rows();
		int cols = m.cols();
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				System.out.format("%.1f%s",m.getElement(i, j), "  ");
			}
			System.out.println();
		}
	}
	
	/******************************************************************************
	 * Populates the matrix with random numbers
	 * between 0 & 20.
	 * @param m
	 *****************************************************************************/
	public void populateMatrix(Matrix m){
		Random rand = new Random();
		double x;
		int rows = m.rows();
		int cols = m.cols();
		for(int i = 0; i < rows; i++){ // populate the matrix
			for(int j = 0; j < cols; j++){
				x = rand.nextDouble() * 5; // some random number between 0 & 20
				m.setElement(i, j, x);
			}
		}
	}
	
	public void populate_with_zeroes(Matrix m){
		int rows = m.rows();
		int cols = m.cols();
		for(int i = 0; i < rows; i++){ // populate the matrix
			for(int j = 0; j < cols; j++){
				m.setElement(i, j, 0);
			}
		}
	}
	
	/************************************************************************************
	 * Performs Hadamard product on m1 and m2.
	 * @param m1
	 * @param m2
	 * @return
	 ***********************************************************************************/
	public Matrix hadamardProduct(Matrix m1, Matrix m2){
		if(m1.rows() != m2.rows() || m1.cols() != m2.cols()){
			return null;
		}
		Matrix dest = new Matrix(m1.rows(), m1.cols());
		double temp;
		for(int i = 0; i < m1.rows(); i++){
			for(int j = 0; j < m1.cols(); j++){
				temp = m1.getElement(i, j) * m2.getElement(i, j);
				dest.setElement(i, j, temp);
			}
		}
		return dest;
	}
	
	/******************************************************************
	 * Add the values in m1 to the values in m2
	 * and return the result as dest.
	 * dest = m1 + m2
	 * @param m1
	 * @param m2
	 * @return
	 *****************************************************************/
	public Matrix add(Matrix m1, Matrix m2){
		if(m1.rows() != m2.rows() || m1.cols() != m2.cols()){
			System.out.println("Nigga, you stupid.");
			return null;
		}
		Matrix dest = new Matrix(m1.rows(), m1.cols());
		double temp;
		for(int i = 0; i < m1.rows(); i++){
			for(int j = 0; j < m1.cols(); j++){
				temp = m1.getElement(i, j) + m2.getElement(i, j);
				dest.setElement(i, j, temp);
			}
		}
		return dest;
	}
	
	
	/******************************************************************
	 * Subtract the values in m2 from the values in m1
	 * and return the result as dest.
	 * dest = m1 - m2
	 * @param m1
	 * @param m2
	 * @return
	 *****************************************************************/
	public Matrix subtract(Matrix m1, Matrix m2){
		if(m1.rows() != m2.rows() || m1.cols() != m2.cols()){
			return null;
		}
		Matrix dest = new Matrix(m1.rows(), m1.cols());
		double temp;
		for(int i = 0; i < m1.rows(); i++){
			for(int j = 0; j < m1.cols(); j++){
				temp = m1.getElement(i, j) - m2.getElement(i, j);
				dest.setElement(i, j, temp);
			}
		}
		return dest;
	}
	
	/****************************************************************************
	 * Perform scalar multiplication on a matrix.
	 * @param m1
	 * @param m2
	 * @return
	 ***************************************************************************/
	public Matrix scalar_multiplication(double scalar, Matrix m1){
		Matrix dest = new Matrix(m1.rows(), m1.cols());
		double temp;
		for(int i = 0; i < m1.rows(); i++){
			for(int j = 0; j < m1.cols(); j++){
				temp = m1.getElement(i, j) * scalar;
				dest.setElement(i, j, temp);
			}
		}
		return dest;
	}
	
	/****************************************************************************
	 * Perform scalar division on a matrix.
	 * @param scalar
	 * @param m1
	 * @return
	 ***************************************************************************/
	public Matrix scalar_division(double scalar, Matrix m1){
		Matrix dest = new Matrix(m1.rows(), m1.cols());
		double temp;
		for(int i = 0; i < m1.rows(); i++){
			for(int j = 0; j < m1.cols(); j++){
				temp = m1.getElement(i, j) / scalar;
				dest.setElement(i, j, temp);
			}
		}
		return dest;
	}
	
	/***********************************************************
	 * Do gausian elimination on matrix, m1.
	 * 
	 * I threw this algorithm together quite quickly, so it
	 * probably needs some work. - Zwick
	 * 
	 * @param m1
	 * @param dest
	 * @return
	 **********************************************************/
	public Matrix gaussElimination(Matrix m1) throws Exception{
		int rows = m1.rows();
		int cols = m1.cols();
		double E = 0; // had to, because now it's initialized in the try-catch... and used outside of the try-catch
		double temp[] = new double[cols];
		for(int i = 0; i < rows; i++){
			try{
				//TODO this method isn't complete
				E = m1.getElement(i, i); // What if there are more rows than columns...
			} catch (Exception e){
				System.out.println("More rows than columns");
			}
			for(int j = 0; j < cols; j++){
				m1.setElement(i, j, m1.getElement(i, j)/E);
			}
			for(int k = i; k < rows; k++){
				for(int z = 0; z < cols; z++){
					if(k <= (rows - 2)){
						temp[z] = m1.getElement(i, z) * -m1.getElement((k+1), z);
						m1.setElement(k+1, z, m1.getElement(k+1, z) + temp[z]);
					} else{
						// TODO
					}
				}
			}
		}
		return m1;
	}
	
	/******************************************************************************
	 * Returns index of the max value in the Matrix m1
	 * @param m1
	 * @return
	 *****************************************************************************/
	public int[] get_Max(Matrix m1){
		int index[] = new int[2];
		double temp = m1.getElement(0, 0);
		for(int i = 0; i < m1.rows(); i++){
			for(int j = 0; j < m1.cols(); j++){
				if(temp < m1.getElement(i, j)){
					temp = m1.getElement(i, j);
					index[0] = i;
					index[1] = j;
				}
			}
		}
		return index;
	}
	
	
	
	
	
	
	
	
	
	
	
}
