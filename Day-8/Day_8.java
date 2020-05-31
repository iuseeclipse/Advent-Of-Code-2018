import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
		buildNodes(nodeList, dataList);
		
		/* part 1:----------------------------------------------------------- */
		
		int totalMetadata = 0;
		for (Node node : nodeList) {
			totalMetadata += node.getMetadataSum();
		}
		System.out.printf("Part 1 solution: %d\n", totalMetadata);
		
		/* part 2:----------------------------------------------------------- */
		
		// root node is first to enter recursive stack, so last on the list
		int rootValue = valueOfNode(nodeList.get(nodeList.size() - 1));
		System.out.printf("Part 2 solution: %d\n", rootValue);
	}
	
	/*
	 * Recursive helper to build list of nodes
	 */
	public static void buildNodes(List<Node> list, LinkedList<Integer> data) {
		if (!data.isEmpty()) {
			// determine number of children and metadata
			int numChildren = data.removeFirst();
			int numEntries = data.removeFirst();
			// call recursive helper on children
			Node[] children = new Node[numChildren];
			for (int i = 0; i < numChildren; i++) {
				buildNodes(list, data);
				// new node added to end of list in memory
				children[i] = list.get(list.size() - 1);
			}
			// determine metadata entries
			int[] metadata = new int[numEntries];
			for (int i = 0; i < numEntries; i++) {
				metadata[i] = data.removeFirst();
			}
			list.add(new Node(children, metadata));
		}
	}
	
	/*
	 * Recursive helper to calculate value of a node, from its metadata and
	 * its relative children in index order
	 */
	public static int valueOfNode(Node node) {
		Node[] children = node.getChildren();
		// if node has no children, value given by summation of metadata
		if (children.length == 0) {
			return node.getMetadataSum();
		}
		// otherwise, add the value of child nodes indexed by metadata
		else {
			int totalValue = 0;
			for (int data : node.getMetadata()) {
				// bounds check for index of child
				if (data <= children.length) {
					// value of nth child, subtract 1 for index in array
					totalValue += valueOfNode(children[data - 1]);
				}
			}
			
			return totalValue;
		}
	}
	
	/*
	 * Node class for the tree data structure
	 */
	static class Node {
		
		/*
		 * metadata entries
		 */
		private int[] metadata;
		
		/*
		 * node's children, in index order
		 */
		private Node[] children;
		
		public Node (Node[] children, int[] metadata) {
			this.children = children;
			this.metadata = metadata;
		}
		
		/*
		 * calculate and return the sum of the node's metadata, for part 1
		 */
		public int getMetadataSum() {
			int total = 0;
			for (int value : metadata) {
				total += value;
			}
			return total;
		}
		
		/*
		 * return the node's metadata
		 */
		public int[] getMetadata() {
			return metadata;
		}
		
		/*
		 * return the node's children, in index order
		 */
		public Node[] getChildren() {
			return children;
		}
	}

}
