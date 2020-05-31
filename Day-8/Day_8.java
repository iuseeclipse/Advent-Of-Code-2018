import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Day_8 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileIn = new Scanner(new File("Day-8/input.txt"));		
		
		// process input file into LinkedList for O(1) removal
		LinkedList<Integer> dataList = new LinkedList<>();
		while (fileIn.hasNextInt()) {
			// determine header params
			dataList.add(fileIn.nextInt());
		}
		
		// populate list of nodes with recursive helper
		List<Node> nodeList = new ArrayList<>();
		helper(nodeList, dataList);
		
		/* part 1:----------------------------------------------------------- */
		
		int totalMetadata = 0;
		for (Node node : nodeList) {
			totalMetadata += node.getMetadataSum();
		}
		System.out.printf("Part 1 solution: %d\n", totalMetadata);
		
		/* part 2:----------------------------------------------------------- */
		
//		System.out.printf("Part 2 solution: %d\n", totalMetadata);
	}
	
	/*
	 * Recursive helper to build list of nodes
	 */
	public static void helper(List<Node> list, LinkedList<Integer> data) {
		if (!data.isEmpty()) {
			// determine number of children and metadata
			int numChildren = data.removeFirst();
			int numEntries = data.removeFirst();
			// call recursive helper on children
			for (int i = 0; i < numChildren; i++) {
				helper(list, data);
			}
			// determine metadata entries
			int[] metadata = new int[numEntries];
			for (int i = 0; i < numEntries; i++) {
				metadata[i] = data.removeFirst();
			}	
			list.add(new Node(numChildren, metadata));
		}
	}
	
	/*
	 * Node class for the tree data structure
	 */
	static class Node {
		
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
		
		// calculate and return the sum of the node's metadata, for part 1
		public int getMetadataSum() {
			int total = 0;
			for (int value : metadata) {
				total += value;
			}
			return total;
		}
		
	}

}
