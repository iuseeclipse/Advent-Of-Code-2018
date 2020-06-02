import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Day_10 {
	
	public static void main(String[] args) throws IOException {
		Scanner fileIn = new Scanner(new File("Day-10/input.txt"));
		PointSimulator ps = new PointSimulator();
		while (fileIn.hasNextLine()) {
			String input = fileIn.nextLine();
			int x = Integer.parseInt(input.substring(10, 16).trim());
			int y = Integer.parseInt(input.substring(17, 24).trim());
			int dX = Integer.parseInt(input.substring(36, 38).trim());
			int dY = Integer.parseInt(input.substring(40, 42).trim());
			ps.addPoint(x, y, dX, dY);
		}
		
		/* Part 1: ---------------------------------------------------------- */
		System.out.printf("Part 1 Solution:\n");
		ps.run();
		
		/* Part 2: ---------------------------------------------------------- */
		System.out.printf("Part 2 Solution: %d\n", ps.getTime());
	}

}
