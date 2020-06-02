import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
	 * nested class Point with (x, y) coordinates and velocity
	 */
	private class Point {
		
		/*
		 * private instance variables to be accessed by PointSimulator class
		 */
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
		
		/*
		 * step function to "advance" one second, updating the position of the
		 * Point based on its velocity
		 */
		public void step() {
			x += dX;
			y += dY;
		}
		
		/*
		 * undoes the step function to return one second
		 */
		public void unstep() {
			x -= dX;
			y -= dY;
		}
	}
	
	/*
	 * internal container of all added Points
	 */
	private List<Point> points;

	/*
	 * track the seconds elapsed, or the number of steps called for each
	 */
	private int secondsElapsed;
	
	public PointSimulator() {
		// implement with LinkedList rather than ArrayList to eliminate resizing
		points = new LinkedList<>();
	}
	
	/*
	 * Add a Point with given properties to the PointSimulator
	 */
	public void addPoint(int x, int y, int dX, int dY) {
		points.add(new Point(x, y, dX, dY));
	}
	
	/*
	 * Run the simulation, stopping when the message solution is found.
	 * precondition: all points added to PointSimulator
	 */
	public void run() {
		
		// keep track of smallest bound for convergence
		int diffMinX = Integer.MAX_VALUE;
		int diffMinY = Integer.MAX_VALUE;
		
		boolean converges = true;
		// points should converge until message is reached, then diverge
		while (converges) {
			// iterator for LinkedList, constant time
			
			// manage current outer bounds of all points in current step
			int boundMinX = Integer.MAX_VALUE;
			int boundMinY = Integer.MAX_VALUE;
			int boundMaxX = Integer.MIN_VALUE;
			int boundMaxY = Integer.MIN_VALUE;
			for (Point point : points) {
				
				int currX = point.x;
				int currY = point.y;
//				System.out.printf("%d x, %d y\n", currX, currY);
				// update bounds
				if (currX > boundMaxX) {
					boundMaxX = currX;
				}
				if (currX < boundMinX) {
					boundMinX = currX;
				}
				if (currY > boundMaxY) {
					boundMaxY = currY;
				}
				if (currY < boundMinY) {
					boundMinY = currY;
				}
				point.step();
			}

			int currDiffX = boundMaxX - boundMinX;
			int currDiffY = boundMaxY - boundMinY;
			
			// was there divergence past the step?
			if ((diffMinX <= currDiffX) || diffMinY <= currDiffY &&
					secondsElapsed > 0) {
				converges = false;
			}
			// still converges, update the minimums
			else {
				if (diffMinX > currDiffX) {
					diffMinX = currDiffX;
				}
				if (diffMinY > currDiffY) {
					diffMinY = currDiffY;
				}
				secondsElapsed++;
			}
		}
		
		// divergence detected at current location of steps
		// all points are two steps ahead, call unstep function to fix
		secondsElapsed -= 2;
		int startX = Integer.MAX_VALUE;
		int startY = Integer.MAX_VALUE;
		int endX = Integer.MIN_VALUE;
		int endY = Integer.MIN_VALUE;
		for (Point point : points) {
			point.unstep();
			point.unstep();
			if (point.x < startX) {
				startX = point.x;
			}
			if (point.y < startY) {
				startY = point.y;
			}
			if (point.x > endX) {
				endX = point.x;
			}
			if (point.y > endY) {
				endY = point.y;
			}			
		}
		// print the message from the diverged bounds, which should contain it
		printMessage(startX, startY, endX, endY);
	}
	
	/*
	 * Return seconds elapsed for part 2, after run() is called
	 */
	public int getTime() {
		return secondsElapsed;
	}
	
	/*
	 * Prints a visual representation of all Points in coordinate bounds, once 
	 * run() has been called. 
	 */
	private void printMessage(int startX, int startY, int endX, int endY) {
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				String toPrint = ".";
				for (Point point : points) {
					if (point.x == x && point.y == y) {
						toPrint = "#";
					}
				}
				System.out.print(toPrint);
			}
			System.out.println("");
		}
	}
}
