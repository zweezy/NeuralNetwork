package network;

public class Line1 {
	/**
	 * Decides if a point is either 
	 * above or below the line: y = -x + 100.
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isAbove(double x, double y){
		//double temp = -((x - 7) * (x - 7)) + 100;
		double temp = -x + 100;
		if(y > temp){
			return true;
		} else{
			return false;
		}
	}
}
