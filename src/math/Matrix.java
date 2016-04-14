package math;

import java.util.ArrayList;
import java.util.List;

/********************************************************
 * This class represents a standard nxm matrix.
 * 
 * @author zwick
 *
 *******************************************************/
public class Matrix {
	private int numRows;
	private int numCols;
	private double[][] m;
	
	//private List<List<Double>> matrix = new ArrayList<List<Double>>();
	
	
	/*****************************************************
	 * Empty constructor.
	 ****************************************************/
	public Matrix(){
	}
	
	/****************************************************
	 * 
	 * @param rows
	 * @param cols
	 ****************************************************/
	public Matrix(int rows, int cols){
		this.numRows = rows;
		this.numCols = cols;
		this.m = new double[rows][cols];
	}
	
	/*****************************************************
	 * Returns number of columns in this matrix.
	 * @return numCols
	 ****************************************************/
	public int cols(){
		return this.numCols;
	}
	
	/*****************************************************
	 * Returns number of rows in this matrix.
	 * @return numRows
	 ****************************************************/
	public int rows(){
		return this.numRows;
	}
	
	/*****************************************************
	 * Sets an element at (row, col) in the matrix = E
	 * @param row
	 * @param col
	 * @param E - the thing you're putting in the matrix.
	 *****************************************************/
	public void setElement(int row, int col, double E){
		m[row][col] = E;
	}

	/*****************************************************
	 * Gets the element at the given row and column.
	 * @param row
	 * @param col
	 * @return
	 ****************************************************/
	public double getElement(int row, int col){
		return m[row][col];
	}

	/****************************************************
	 * Set this matrix equal to another matrix.
	 * @param m
	 ***************************************************/
	public void equals(Matrix m1){
		this.numCols = m1.cols();
		this.numRows = m1.rows();
		this.m = new double[numRows][numCols];
		for(int i = 0; i < this.numRows; i++){
			for(int j = 0; j < this.numCols; j++){
				this.m[i][j] = m1.getElement(i, j);
			}
		}
	}
}
