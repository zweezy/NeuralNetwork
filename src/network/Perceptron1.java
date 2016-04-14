package network;
/**
 * 
 * @author zwick
 *
 * A simple model of a perceptron. 
 * Perceptrons represent a single neuron
 * which can either fire or not, depending on the input.
 * They can distinguish between two classes as long as
 * those classes are linearly separable... which means 
 * a line can be drawn between them.
 */
public class Perceptron1 {
	
	double x1, x2;
	double w1, w2;
	double change = 0.1;
	double bias = -50;
    
    public Perceptron1(){
    	this.w1 = 1;
    	this.w2 = 1;
    }
    
    /**
     * Summation function for test.
     * Sets class input fields to the current parameters.
     * @param x1
     * @param x2
     * @return
     */
    public double testSum(double x1, double x2){
    	this.x1 = x1;
    	this.x2 = x2;
    	//return (x1 * this.w1) + (x2 * this.w2) - 100; // the -100 is the bias. Don't remove it. Very important!
    	return (x1 * this.w1) + (x2 * this.w2) + bias;

    }
	
    /**
     * Decides if perceptron fires or not.
     * 
     * @return 
     */
	public boolean activate(double v){
		if(v > 0){
			return true;
		} else{
			return false;
		}
	}
	
	public void trainUp(){
		w1 += (x1 * change);
		w2 += (x2 * change);
		//bias += change; // pretty sure this is the correct way, but...
		bias++;
	}
	
	public void trainDown(){
		w1 -= (x1 * change);
		w2 -= (x2 * change);
		//bias -= change;
		bias--;
	}
}
