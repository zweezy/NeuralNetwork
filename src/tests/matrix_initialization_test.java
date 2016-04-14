package tests;

import math.Matrix;
import math.MatrixOperation;

import org.junit.Test;

public class matrix_initialization_test {
	@Test
	public void setting_equal() throws Exception {
		MatrixOperation o = new MatrixOperation();
		Matrix m1 = new Matrix(4,4);
		Matrix m2 = new Matrix(3,3);
		o.populateMatrix(m1);
		o.populateMatrix(m2);
		
		System.out.println("M1:");
		o.printMatrix(m1);
		
		System.out.println("M2:");
		o.printMatrix(m2);
		
		m2.equals(m1);
		
		System.out.println("New m2");
		o.printMatrix(m2);
	}
}
