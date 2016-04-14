package math;

/******************************************************************
 * A class of cost functions and their derivatives.
 * @author zwick
 *
 *****************************************************************/
public class Cost_Functions {
	
	/********************************************************************************
	 * Takes a matrix, Z, and performs the following cost function
	 * on it:
	 *     C = [(y - a)^2] / 2
	 * The result is stored in dest, and then returned.
	 * @param m
	 * @return
	 *******************************************************************************/
	public Matrix C1(Matrix y, Matrix a){
		if(y.rows() != a.rows() || y.cols() != a.cols()){
			return null;
		}
		Matrix dest = new Matrix(y.rows(), y.cols());
		double temp; // for work in the loop
		for(int i = 0; i < y.rows(); i++){
			for(int j = 0; j < y.cols(); j++){
				//TODO
			}
		}
		return dest;
	}
	
	
	
	
	
	
}
