package math;

/********************************************************************************
 * A class of activation functions.
 * @author zwick
 *******************************************************************************/
public class Activation_Functions {
	
	
	/********************************************************************************
	 * Takes a matrix, Z, and performs the following sigmoidal activation function
	 * on it:
	 *     a1(z) = 1 / (1 + e^-z)
	 * The result is stored in dest, and then returned.
	 * @param m
	 * @return
	 *******************************************************************************/
	public Matrix a1(Matrix Z){
		Matrix dest = new Matrix(Z.rows(), Z.cols());
		double e = Math.exp(1);
		double temp; // for work in the loop
		for(int i = 0; i < Z.rows(); i++){
			for(int j = 0; j < Z.cols(); j++){
				temp = Z.getElement(i, j);
				temp = 1 + Math.pow(e, -temp);
				temp = 1 / temp;
				dest.setElement(i, j, temp);
			}
		}
		return dest;
	}
	
	
	/************************************************************************************
	 * Returns a matrix, dest, which contains
	 * the derivative of the activation function
	 * acting upon the Z matrix, which is passed in as a parameter.
	 * //dest = 1 / (e^z + 2 + e^-z)
	 * dest = 1 / (4(cosh(z/2))^2)
	 * @param Z
	 * @return
	 ***********************************************************************************/
	public Matrix a1_prime(Matrix Z){
		Matrix dest = new Matrix(Z.rows(), Z.cols());
		double e = Math.exp(1);
		double temp; // for work in the loop
		/*for(int i = 0; i < Z.rows(); i++){
			for(int j = 0; j < Z.cols(); j++){
				temp = Z.getElement(i, j);
				temp1 = Math.pow(e, temp) + Math.pow(e, -temp) + 2;
				temp = 1 / temp1;
				dest.setElement(i, j, temp);
			}
		}*/
		///////////////////////////////////////////////////////
		/*for(int i = 0; i < Z.rows(); i++){
			for(int j = 0; j < Z.cols(); j++){
				temp = Z.getElement(i, j);
				temp = temp/2;
				temp = Math.cosh(temp);
				temp = temp * temp;
				temp = temp * 4;
				temp = 1 / temp;
				dest.setElement(i, j, temp);
			}
		}*/
		////////////////////////////////////////////////////////
		for(int i = 0; i < Z.rows(); i++){
			for(int j = 0; j < Z.cols(); j++){
				temp = Z.getElement(i, j);
				temp = 1 + Math.pow(e, -temp);
				temp = 1 / temp;
				/////////////////////
				temp = temp * (1 - temp);
				/////////////////////
				dest.setElement(i, j, temp);
			}
		}
		return dest;
	}
	
}
