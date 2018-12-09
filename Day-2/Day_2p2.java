import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day_2p2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		File input = new File("input.txt");
		Set<Character> set_test = new HashSet<Character>();
		Scanner fileIn = new Scanner(input);
		String[] values = new String[250];
		int i = 0;
		while(fileIn.hasNext()) {
			values[i] = fileIn.next();
			i++;
		}
		int max = Integer.MAX_VALUE;
		int index1 = 0;
		int index2 = 0;
		for(int j = 0; j < i - 1; j ++) {
			for(int k = j + 1; k < i; k ++) {
				int diffs = 0;
				for(int l = 0; l < 26; l ++) {
					if(!(values[j].charAt(l) == values[k].charAt(l))) {
						diffs++;
					}					
				}
				if(diffs < max) {
					index1 = k;
					index2 = j;
					max = diffs;
				}				
			}
		}
		System.out.println(values[index1]);
		System.out.println(values[index2]);
		for(int j = 0; j < 26; j ++) {
			if(values[index1].charAt(j) == values[index2].charAt(j)) {
				System.out.print(values[index1].charAt(j));
			}
		}
	}
}
