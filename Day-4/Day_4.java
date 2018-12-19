import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class Day_4 {
	
	public static void main(String[] args) throws FileNotFoundException {
		File input = new File("input.txt");
		Scanner fileIn = new Scanner(input);
		Set<Integer> guardNums = new HashSet<Integer>();
		List<String> timestamps = new ArrayList<String>();
		while(fileIn.hasNext()) {
			String line = fileIn.nextLine();
			timestamps.add(line);
			if(line.contains("#")) {
				guardNums.add(Integer.parseInt(line.substring(line.indexOf("#")).replaceAll("[^0-9]", "")));
			}
		}
		quickSort(timestamps, 0, timestamps.size() - 1);
		List<List<int[]>> sleepData = new ArrayList<List<int[]>>();
		for(int i = 0; i < guardNums.size(); i ++) {
			sleepData.add(new ArrayList<int[]>());
		}
		int[] sleepTotal = new int[guardNums.size()];
		List<Integer> guardIndex = new ArrayList<Integer>(guardNums);
		int n = 0;
		int slop = 0;
		int awok = 0;
		for(String l : timestamps) {
			System.out.println(l);
			if(l.contains("#")) {
				n = guardIndex.indexOf(Integer.parseInt(l.substring(l.indexOf("#")).replaceAll("[^0-9]", "")));
			}			
			if(l.contains("falls")) {
				slop = Integer.parseInt(l.substring(l.indexOf("]") - 2, l.indexOf("]")));
			}
			if(l.contains("wakes")) {
				awok = Integer.parseInt(l.substring(l.indexOf("]") - 2, l.indexOf("]")));
				sleepTotal[n] += (awok - slop);
				sleepData.get(n).add(new int[]{slop, awok});
			}
		}
		int max = Integer.MIN_VALUE;
		int index_f = 0;
		for(int i = 0; i < sleepTotal.length; i ++) {
			if(sleepTotal[i] > max) {
				max = sleepTotal[i];
				index_f = i;				
			}
		}
		System.out.printf("max sleep: %d, index: %d\n", max, index_f);
		System.out.printf("guard id: %d\n", guardIndex.get(index_f));
		int[] minuteFreq = new int[60];
		for(int[] dub : sleepData.get(index_f)) {
			//System.out.printf("[%d,%d]", dub[0], dub[1]);
			for(int i = dub[0]; i < dub[1]; i ++) {
				minuteFreq[i] ++;
			}
		}
		max = Integer.MIN_VALUE;
		index_f = 0;
		for(int i = 0; i < minuteFreq.length; i ++) {
			if(minuteFreq[i] > max) {
				max = minuteFreq[i];
				index_f = i;
			}
		}
		System.out.println("minute most sleep: " + index_f);
	}
	
//implement a quicksort algorithm
	static int partition(List<String> arr, int low, int high) {
		Long pivot = Long.parseLong(arr.get(high).substring(0, arr.get(high).indexOf("]")).replaceAll("[^0-9]", ""));
		int i = low;
		for(int j = i; j <= high - 1; j ++) {
			if(Long.parseLong(arr.get(j).substring(0, arr.get(j).indexOf("]")).replaceAll("[^0-9]", "")) < pivot) {
				Collections.swap(arr, i, j);
				i ++;
			}
		}
		Collections.swap(arr, i, high);
		return i;
	}
	
	static void quickSort(List<String> arr, int low, int high) {
		if(low < high) {
			int p = partition(arr, low, high);
			quickSort(arr, low, p - 1);
			quickSort(arr, p + 1, high);
		}
	}
}	

