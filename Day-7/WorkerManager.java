import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/*
 * Modular worker manager solution to calculate the total time for a task
 * given a list of prerequisites, configurable for any number of workers
 * 
 * The general order of steps should be known beforehand, and passed into
 * WorkerManager in that same order.
 */
public class WorkerManager {	
	
	/*
	 * workerTimes represents the next earliest "time" the worker at the index
	 */
	private int[] workerTimes;
	
	/*
	 * log for visual representation of worker's tasks
	 */
	private String[] workerLogs;
	
	/*
	 * map of the steps prerequisites
	 */
	TreeMap<String, SortedSet<String>> prereqMap;
	
	/*
	 * set of all the remaining steps to execute
	 */
	SortedSet<String> stepsRemaining;
	
	/*
	 * set of all steps checked, to determine order of assign
	 */
	SortedSet<String> stepsChecked;
	
	/*
	 * map of the finishing time of all the assigned steps
	 */
	TreeMap<String, Integer> stepFinishes;
	
	public WorkerManager(int numWorkers, 
			TreeMap<String, SortedSet<String>> prereqMap,
			SortedSet<String> stepsRemaining) {
		workerTimes = new int[numWorkers];
		workerLogs = new String[numWorkers];
		this.prereqMap = prereqMap;
		this.stepsRemaining = stepsRemaining;
		stepFinishes = new TreeMap<>();
		stepsChecked = new TreeSet<>();
		
		for (int i = 0; i < workerLogs.length; i++) {
			workerLogs[i] = "Worker " + i + ": ";
		}
	}
	
	public void start() {
		StringBuilder stepOrder = new StringBuilder();
		// re-implement order generation code from part 1, without early return
		while (!stepsRemaining.isEmpty()) {
			String currStep = "";
			List<String> stepsToUpdate = new ArrayList<>();
			Iterator<String> stepIter = stepsRemaining.iterator();
			while (stepIter.hasNext()) {
				currStep = stepIter.next();
				SortedSet<String> prereqs = prereqMap.get(currStep);				
				// if no prerequisites, should be the next step
				if (prereqs == null || stepsChecked.containsAll(prereqs)) {
					stepOrder.append(currStep);
					stepsToUpdate.add(currStep);
				}
			}
			// update map
			for(String toUpdate : stepsToUpdate) {
				stepsChecked.add(toUpdate);
				stepsRemaining.remove(toUpdate);
			}			
		}
		System.out.println(stepOrder);
		for (char c : stepOrder.toString().toCharArray()) {
			assign ("" + c);
		}
	}
	
	/*
	 * assign the next step to be completed
	 */
	private void assign(String step) {
		// check worker availability for the earliest relative time
		int earliestInit = Integer.MAX_VALUE;
		int workerToAssign = 0;
		for (int i = 0; i < workerTimes.length; i++) {
			if (workerTimes[i] < earliestInit) {
				earliestInit = workerTimes[i];
				workerToAssign = i;
			}
		}
		
		// check step prerequisites
		if (prereqMap.containsKey(step)) {
			// for each prerequisite step, check its finishing time
			for (String prereq : prereqMap.get(step)) {
				Integer finishingTime = stepFinishes.get(prereq);
				// step can only begin at the finishing time of its last prereq
				if (finishingTime != null && finishingTime > earliestInit) {
					earliestInit = finishingTime;
				}
			}
		}		

		// log worker activity
		workerLogs[workerToAssign] += earliestInit + " " + step + " start, ";
		// add the time to the init time; determine assigned worker's next avail
		workerTimes[workerToAssign] = earliestInit + 
				((int)step.charAt(0) - 4);
		workerLogs[workerToAssign] += workerTimes[workerToAssign] + " finish; ";
		
		// log finishing time of step to map
		stepFinishes.put(step, workerTimes[workerToAssign]);
	}
	
	/*
	 * calculate the current cumulative total time it would take
	 */
	public int calculateTotal() {
		int earliest = 0;
		for (int time : workerTimes) {
			if (time > earliest) {
				earliest = time;
			}
		}
		return earliest;
	}
	
	public void printLogs() {
		for (String log : workerLogs) {
			System.out.println(log);
		}
	}
	
	/*
	 * finish a step, removing it from the map and from other steps prereqs
	 */
	private void finishStep(String step) {
		stepsRemaining.remove(step);
		if (prereqMap.containsKey(step)) {
			prereqMap.remove(step);
		}
		for (String currStep : prereqMap.keySet()) {
			prereqMap.get(currStep).remove(step);
		}
	}
}
