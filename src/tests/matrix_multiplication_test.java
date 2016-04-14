package tests;

import math.Matrix;
import math.MatrixOperation;

import org.junit.Test;

public class matrix_multiplication_test {
Matrix m1;// = new Matrix(4,5);
Matrix m2;
	
	@Test
	public void transposeTest_1() throws Exception {
		MatrixOperation o = new MatrixOperation();
		m1 = new Matrix(2,3);
		m2 = new Matrix(3,2);
		/* Populate the test matrices. */
		m1.setElement(0, 0, 4.7);
		m1.setElement(0, 1, 11.8);
		m1.setElement(0, 2, 10.9);
		
		m1.setElement(1, 0, 9.7);
		m1.setElement(1, 1, 8.7);
		m1.setElement(1, 2, 16.2);
		
		m2.setElement(0, 0, 19.7);
		m2.setElement(1, 0, 12.5);
		m2.setElement(2, 0, 0.2);
		
		m2.setElement(0, 1, 13.0);
		m2.setElement(1, 1, 18.3);
		m2.setElement(2, 1, 16.1);
		//o.populateMatrix(m1);
		//o.populateMatrix(m2);
		System.out.println("M1:");
		o.printMatrix(m1);
		System.out.println("M2");
		o.printMatrix(m2);
		
		Matrix n = o.multiplyM(m1, m2);
		System.out.println("Matrix Product:");
		o.printMatrix(n);
		
		/*
		 * Expected:
		 *              242.27     452
		 *              303        546.13
		 */
	}
	
	
}
