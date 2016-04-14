package tests;

import org.junit.Test;

import math.Matrix;
import math.MatrixOperation;

public class transpose_test {

	Matrix m1;// = new Matrix(4,5);
	
	@Test
	public void transposeTest_1() throws Exception {
		MatrixOperation o = new MatrixOperation();
		m1 = new Matrix(4,5);
		o.populateMatrix(m1);
		System.out.println("Original Matrix:");
		o.printMatrix(m1);
		
		Matrix n = o.transpose(m1);
		System.out.println("Transposed Matrix:");
		o.printMatrix(n);
	}
	
}
