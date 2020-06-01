import java.util.LinkedList;
import java.util.List;

/*
 * Simulate the motion of a list of Points with specified position and velocity, 
 * that will all eventually converge into a visual representation of a message.
 * 
 * All points are used for the message with constant text, so use the bounds to
 * determine and display the correct message.
 */
public class PointSimulator {
	
	/*
	 * Point with (x, y) coordinates and velocity
	 */
	private class Point {
		
		private int x;
		private int y;
		private int dX;
		private int dY;
		
		public Point(int x, int y, int dX, int dY) {
			this.x = x;
			this.y = y;
			this.dX = dX;
			this.dY = dY;			
		}
		
	}
	
	/*
	 * internal container of all added Points
	 */
	private List<Point> points;
	
	public PointSimulator() {
		// implement with LinkedList rather than ArrayList to eliminate resizing
		points = new LinkedList<>();
	}
	
	/*
	 * Add a point to the PointSimulator
	 */
	public void addPoint(int x, int y, int dX, int dY) {
		points.add(new Point(x, y, dX, dY));
	}
	
	/*
	 * Run the simulation to determine the message
	 * precondition: all points added to PointSimulator
	 */
	

}
