package network;
import java.io.File;

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
public class Perceptron {
	
	private double[] X;
	private double[] W;
	private int numInputs;
	private double v; // the sum of inputs and weight products.
	//for testing VV
	double x1, x2;
	double w1, w2;
	double change = 0.01;
	int bias = 1;
    
	/**
	 * Constructor - initializes the perceptron data.
	 * @param inputs
	 * @param bias
	 */
    public Perceptron(int inputs){
        X = new double[inputs];
        W = new double[inputs];
        this.v = 0;
        for(int i = 0; i < numInputs; i++){ // set all weights on a new neuron to zero.
        	W[i] = 1;
        }
        this.numInputs = inputs;
    }
    
    /**
     * 
     * @param x
     * @param y
     */
    public Perceptron(int x, int y, String fileName){
    	this.x1 = x;
    	this.x2 = y;
    	//TODO use file to get weights
    	this.w1 = 1;
    	this.w2 = 1;
    	
    }
    
    /*public Perceptron(int x, int y){
    	this.x1 = x;
    	this.x2 = y;
    	this.w1 = 1;
    	this.w2 = 1;
    	
    }*/
    
    public Perceptron(){
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
    public double testSum(int x1, int x2){
    	this.x1 = x1;
    	this.x2 = x2;
    	return (x1 * this.w1) + (x2 * this.w2) + bias;
    }
    
    /**
     * Sums the inputs by their respective synaptic weights.
     * Matrix style.
     * @return
     */
    public double sumWeightProducts(){
        for(int i = 0; i < numInputs; i++){
        	v += X[i]*W[i];
        }
        return v;
    }
	
    /**
     * Decides if perceptron fires or not.
     * 
     * @return 
     */
	public boolean activate(double v){ // This activation function might be a problem for negative sloping separators
		if(v > 0){
			return true;
		} else{
			return false;
		}
	}
	
	public void setX1(double x){
		this.x1 = x;
	}
	
	public void setX2(double x){
		this.x2 = x;
	}
	
	public void setW1(double x){
		this.w1 = x;
	}
	
	public void setW2(double x){
		this.w2 = x;
	}
	
	public double X1(){
		return this.x1;
	}
	
	public double X2(){
		return this.x2;
	}
	
	public double W1(){
		return this.w1;
	}
	
	public double W2(){
		return this.w2;
	}
	
	public void trainUp(){
		this.w1 += this.x1 * this.change;
		this.w2 += this.x2 * this.change;
	}
	
	public void trainDown(){
		this.w1 -= this.x1 * this.change;
		this.w2 -= this.x2 * this.change;
	}
}
