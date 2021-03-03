package swm;

import java.util.Arrays;
import java.util.Scanner;

public class greedy1 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int people_max = scan.nextInt();
		int[] people = new int[people_max];
		for(int i=0; i<people_max; i++) {
			people[i] = scan.nextInt();
		}
		Arrays.sort(people);
		int result = 0;
		for(int i=0; i<people_max; i++) {
			result+=people[i]*(people_max-i);
		}
		System.out.println(result);
	}
}
