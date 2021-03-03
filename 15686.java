package jhlee_java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class chicken {
	static int[][] city;
	static ArrayList<String> saved_arr;
	static int count2;
	static int[][] houses;
	static int[][] chickens;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int N = scan.nextInt();
		int M =scan.nextInt();
		saved_arr = new ArrayList<String>();
		int[][] temp = {
				{1, 2, 0, 2, 1},
				{1, 2, 0, 2, 1},
				{1, 2, 0, 2, 1},
				{1, 2, 0, 2, 1},
				{1, 2, 0, 2, 1}	
		};
		city=temp;
		int count = 0;
		count2 = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(city[i][j] == 1) {
					count++;
				}else if(city[i][j] == 2) {
					count2++;
				}
					
			}
		}
		int[] arr = new int[count2];
		boolean[] visit = new boolean[count2];
		houses = new int[count][2];
		chickens = new int[count2][2];
		int v = 0;
		int v2 = 0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(city[i][j] == 1) {
					houses[v][0] = i;
					houses[v][1] = j;
					v++;
				}else if(city[i][j] == 2) {
					chickens[v2][0] = i;
					chickens[v2][1] = j;
					arr[v2] = v2+1;//1번 2번 ~M번 까지
					v2++;
				}
			}
		}
		combination(arr, visit, 0, M);
		int[][] saved = new int[saved_arr.size()][M]; 
		
		for(int i=0; i<saved_arr.size(); i++) {
			for(int j=0; j<saved_arr.get(i).length(); j++) {
				saved[i][j] = Character.getNumericValue(saved_arr.get(i).charAt(j));
			}
		}
		int[] city_chicken = new int[saved_arr.size()];
		
		for(int j=0; j<saved_arr.size(); j++) {
			int sum = 0;
			for(int i=0; i<v; i++) {
				sum+=chicken_distance(i, saved[j], M);
			}
			city_chicken[j] = sum;
			System.out.println(city_chicken[j]);
		}
		
		Arrays.sort(city_chicken);
		System.out.println("최소 길이: "+city_chicken[0]);
	}
	static void combination(int[] arr, boolean[] visit, int deep, int r) {
		if(r == 0) {
			save(arr, visit);
			return;
		}
		if(deep == arr.length) {
			return;
		}
		else {
			visit[deep] = true;
			combination(arr, visit, deep+1, r-1);
			visit[deep] = false;
			combination(arr, visit, deep+1, r);
		}
	}
	static void save(int[] arr, boolean[] visit) {
		String str = "";
        for(int i = 0; i < arr.length; i++) {
            if(visit[i] == true)
                str+=arr[i];
        }
        saved_arr.add(str);
    }
	static int distance(int a, int b, int c, int d) {
		int R = a-c;
		int C = b-d;
		if(R<0)
			R = -R;
		if(C<0)
			C = -C;
		return R+C;
	}
	static int chicken_distance(int house_num, int[] chicken_arr, int M) {
		int[] distances = new int[M]; 
		for(int i=0; i<M; i++) {
			int a = houses[house_num][0];
			int b = houses[house_num][1];
			int c = chickens[chicken_arr[i]-1][0];
			int d = chickens[chicken_arr[i]-1][1];
			distances[i] = distance(a, b, c, d);
		}
		Arrays.sort(distances);
		return distances[0];
	}
}
