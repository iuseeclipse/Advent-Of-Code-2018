import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Day_6p2 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileIn = new Scanner(new File("input.txt"));
		List<int[]> coords = new ArrayList<int[]>();
		while(fileIn.hasNext()) {
			String in = fileIn.nextLine();
			int x = Integer.parseInt(in.substring(0, in.indexOf(",")));
			int y = Integer.parseInt(in.substring(in.indexOf(",") + 2));
			coords.add(new int[] {x, y});
		}
		int a = 0;
		for(int i = 50; i < 370; i ++) {
			for(int j = 50; j < 370; j ++) {
				int dist = 0;
				for(int[] coord : coords) {
					dist += Math.abs(i - coord[0]) + Math.abs(j - coord[1]);
				}
				if(dist < 10000) {
					a++;
				}
			}
		}
		System.out.println(a);
	}
}
