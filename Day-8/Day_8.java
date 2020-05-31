import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day_8 {
	
	public static void main(String[] args) throws FileNotFoundException {
		/* part 1: ---------------------------------------------------------- */
		Scanner fileIn = new Scanner(new File("Day-8/inputSmall.txt"));
		
		// populate list of nodes
		List<Node> nodeList = new ArrayList<>();
		List<Integer> dataList = new ArrayList<>();
		// process input
		while (fileIn.hasNextInt()) {
			// determine header params
			dataList.add(fileIn.nextInt());
		}
	}
	
	/*
	 * Recursive helper to build list of nodes
	 */
	public static void helper(List<Node> list, List<Integer> data) {
		
	}
	
	/*
	 * Node class for the tree data structure
	 */
	class Node {
		
		/*
		 * header values
		 */
		private int numChildren;
		private int numEntries;
		/*
		 * metadata entries
		 */
		private int[] metadata;
		
		public Node (int numChildren, int[] metadata) {
			this.numChildren = numChildren;
			this.numEntries = metadata.length;
			this.metadata = metadata;
		}
		
	}

}
