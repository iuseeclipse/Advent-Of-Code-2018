import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class Day_6 {
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner fileIn = new Scanner(new File("input.txt"));
		List<int[]> coords = new ArrayList<int[]>();
		while(fileIn.hasNext()) {
			String in = fileIn.nextLine();
			int x = Integer.parseInt(in.substring(0, in.indexOf(",")));
			int y = Integer.parseInt(in.substring(in.indexOf(",") + 2));
			coords.add(new int[] {x, y});
		}
		int[][] map = new int[357 - 54][356 - 50];
		System.out.println(map[54-54][300-50]);
		for(int[] coord : coords) {
			List <int[]> coords_check = new ArrayList<int[]>(coords);
			coords_check.remove(coords.indexOf(coord));
			int abs_dist = 1;
			int x = coord[0];
			int y = coord[1];
			//System.out.printf("[%d, %d]: ", x, y);
			int a = 2;
			boolean cont = true;
			while(cont && a < 100000) {
				x = coord[0];
				cont = true;
				while(cont && a < 100000) {
					x++;
					//System.out.printf("x = %d, y = %d\n", x, y);
					for(int[] check : coords_check) {
						if((Math.abs(x - coord[0]) + Math.abs(y - coord[1])) >= (Math.abs(x- check[0]) + Math.abs(y - check[1]))) {
							cont = false;
						}
					}
					if(cont)
						a++;
				}
				x = coord[0];
				cont = true;
				while(cont && a < 100000) {
					x--;
					//System.out.printf("x = %d, y = %d\n", x, y);
					for(int[] check : coords_check) {
						if((Math.abs(x - coord[0]) + Math.abs(y - coord[1])) >= (Math.abs(x- check[0]) + Math.abs(y - check[1]))) {
							cont = false;
						}
					}
					if(cont)
						a++;
				}
				x = coord[0];
				y++;
				cont = true;
				//System.out.printf("x = %d, y = %d\n", x, y);
				for(int[] check : coords_check) {
					if((Math.abs(x - coord[0]) + Math.abs(y - coord[1])) >= (Math.abs(x- check[0]) + Math.abs(y - check[1]))) {
						cont = false;
					}
				}
				if(cont)
					a++;
			}
			cont = true;
			x = coord[0];
			y = coord[1] - 1;
			while(cont && a < 100000) {
				x = coord[0];
				cont = true;
				while(cont && a < 100000) {
					x++;
					//System.out.printf("x = %d, y = %d\n", x, y);
					for(int[] check : coords_check) {
						if((Math.abs(x - coord[0]) + Math.abs(y - coord[1])) >= (Math.abs(x- check[0]) + Math.abs(y - check[1]))) {
							cont = false;
						}
					}
					if(cont)
						a++;
				}
				x = coord[0];
				cont = true;
				while(cont && a < 100000) {
					x--;
					//System.out.printf("x = %d, y = %d\n", x, y);
					for(int[] check : coords_check) {
						if((Math.abs(x - coord[0]) + Math.abs(y - coord[1])) >= (Math.abs(x- check[0]) + Math.abs(y - check[1]))) {
							cont = false;
						}
					}
					if(cont)
						a++;
				}
				x = coord[0];
				y--;
				cont = true;
				//System.out.printf("x = %d, y = %d\n", x, y);
				for(int[] check : coords_check) {
					if((Math.abs(x - coord[0]) + Math.abs(y - coord[1])) >= (Math.abs(x- check[0]) + Math.abs(y - check[1]))) {
						cont = false;
					}
				}
				if(cont)
					a++;
			}
			System.out.printf("area = %d\n", a);
		}
		
	}
}
