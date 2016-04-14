package tests;

import math.Matrix;
import math.MatrixOperation;

import org.junit.Test;

public class hadamond_test {
	
	@Test
	public void hadamondTest_1() throws Exception {
		MatrixOperation o = new MatrixOperation();
		Matrix m1 = new Matrix(2,1);
		Matrix m2 = new Matrix(2,1);
		
		o.populateMatrix(m1);
		o.populateMatrix(m2);
		System.out.println("Matrix 1:");
		o.printMatrix(m1);
		
		System.out.println("Matrix 2:");
		o.printMatrix(m2);
		
		double a = m1.getElement(0,0);
		double b = m2.getElement(0, 0);
		double c = a*b;
		System.out.println("a: " + a);
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		
		
		
		Matrix n = o.hadamardProduct(m1, m2);
		// It passes... just a rounding error in the print statement. 
		// What I mean is... it prints the wrong numbers for the original 
		// Matrix
		System.out.println("Hadamond Product:");
		o.printMatrix(n);
	}
	
	
	
}
