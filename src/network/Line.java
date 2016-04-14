package network;

	/*
	 * The typical 1:1 line.
	 *         *
	 *       *
	 *     *
	 *   *
	 * *
	 ***********************
	 * y = x
	 */
public class Line {

	/**
	 * Decides if a point is either 
	 * above or below the line: y = x.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isAbove(double x, double y){
		if(y > x){
			return true;
		} else{
			return false;
		}
	}
	
}
