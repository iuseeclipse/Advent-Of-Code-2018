import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day_2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		File input = new File("Day-2/input.txt");
		Set<Character> set_test = new HashSet<Character>();
		Scanner fileIn = new Scanner(input);
		int[] freq = new int[26];
		int dubs = 0;
		int trips = 0;
		while(fileIn.hasNext()) {
			boolean has_dub = false;
			boolean has_trip = false;
			String id = fileIn.next();
			for(int i = 0; i < 26; i ++) {
				if(!set_test.add(id.charAt(i))) {
					freq[(int)id.charAt(i) - 97]++;
				}
			}
			for(int fr : freq) {
				System.out.println(fr);
				if(fr == 1) has_dub = true;
				if(fr == 2) has_trip = true;
			}
			if(has_dub) dubs++;
			if(has_trip) trips++;
			freq = new int[26];
			set_test = new HashSet<Character>();
		}
		System.out.println(dubs * trips);
	}
}
