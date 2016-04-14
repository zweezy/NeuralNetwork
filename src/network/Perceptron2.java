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
public class Perceptron2 {
	
	double x1, x2;
	double w1, w2;
	double change = 0.1;
	double bias = 1;
    
    public Perceptron2(){
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
    	return (x1 * this.w1) + (x2 * this.w2) + bias; // the -100 is the bias. Don't remove it. Very important!
    }
	
    /**
     * Decides if perceptron fires or not.
     * 
     * @return 
     */
	public double activate(double v){
		if(v > 0){
			return 1;
		} else{
			return 0;
		}
	}
	
	public void trainUp(){
		this.w1 += this.x1 * this.change;
		this.w2 += this.x2 * this.change;
		bias += change;
	}
	
	public void trainDown(){
		this.w1 -= this.x1 * this.change;
		this.w2 -= this.x2 * this.change;
		bias -= change;
	}
}

