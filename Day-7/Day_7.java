import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.TreeMap;

public class Day_7 {
	
	/*
	 * keep an ordered set of each step not yet determined in the order
	 */
	private static SortedSet<String> stepsRemaining;
	
	/*
	 * keep a map of each step and its set of prerequisites, alphabetically
	 */
	private static TreeMap<String, SortedSet<String>> prereqMap;
	
	public static void main(String[] args) throws FileNotFoundException {
		/* part 1: ---------------------------------------------------------- */ 
		Scanner fileIn = new Scanner(new File("Day-7/input.txt"));
		prereqMap = new TreeMap<>();
		stepsRemaining = new TreeSet<>();
		
		// initialize the map, associating each step with its prerequisites
		initMap(fileIn);
		StringBuilder stepOrder = new StringBuilder();		
		
		// iterate in order to find the first valid step, without prereqs
		while (!stepsRemaining.isEmpty()) {
			String currStep = "";
			Iterator<String> stepIter = stepsRemaining.iterator();
			boolean done = false;
			while (stepIter.hasNext() && !done) {
				currStep = stepIter.next();
				SortedSet<String> prereqSet = prereqMap.get(currStep);
				
				// if no prerequisites, should be the next step
				if (prereqSet == null || prereqSet.isEmpty()) {
					stepOrder.append(currStep);
					done = true;
				}
			}
			// update map
			if (!currStep.equals("")) {
				finishStep (currStep);
			}			
		}
		System.out.println("Part 1 solution: " + stepOrder);
		
		/* Part 2: ---------------------------------------------------------- */
		// reset scanner and map
		fileIn = new Scanner(new File("Day-7/input.txt"));
		initMap(fileIn);
		System.out.println(prereqMap);
		// use WorkerManager class to log/calculate total time
		WorkerManager wm = new WorkerManager(5, prereqMap, stepsRemaining);
		wm.start();
		wm.printLogs();
		System.out.println("Part 2 solution: " + wm.calculateTotal());
	}
	
	/*
	 * populate the map based on input of prerequisites
	 * fileIn points to the input file containing step prereqs
	 */
	private static void initMap(Scanner fileIn) {
		while (fileIn.hasNextLine()) {
			String currLine = fileIn.nextLine ();
			// step to be completed first at index 5, for step at index 36
			String currStep = "" + currLine.charAt(36);
			String prereq = "" + currLine.charAt(5);
			// put operation for the set
			stepsRemaining.add(currStep);
			stepsRemaining.add(prereq);
			// put operation for the map
			SortedSet<String> currSet = 
					prereqMap.putIfAbsent(currStep, new TreeSet<>());
			// putIfAbsent returns null if was absent, set to new empty set
			if (currSet == null) {
				currSet = prereqMap.get(currStep);
			}
			// add the prerequisite step to the set within the map
			currSet.add(prereq);
		}
	}
	
	/*
	 * finish a step, removing it from the map and from other steps prereqs
	 */
	private static void finishStep(String step) {
		stepsRemaining.remove(step);
		if (prereqMap.containsKey(step)) {
			prereqMap.remove(step);
		}
		for (String currStep : prereqMap.keySet()) {
			prereqMap.get(currStep).remove(step);
		}
	}
}
