import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day_9 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileIn = new Scanner(new File("Day-9/input.txt"));
		String input = fileIn.nextLine();
		int numPlayers = Integer.parseInt(input.substring(0, 3));
		int lastValue = Integer.parseInt(input.substring(34, 39));
		
		/* Part 1: ---------------------------------------------------------- */
		MarbleCircle game = new MarbleCircle();
		game.start(numPlayers, lastValue);
		System.out.printf("Part 1 solution: %d\n", game.getHighScore());
	}

}



