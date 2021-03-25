package day_0320;

import java.util.Scanner;

public class pr2169 {
	static int[][] map;
	static int N;
	static int M;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		N = scan.nextInt();//row
		M = scan.nextInt();//col
		map = new int[M][N];
		int[][] max_left_map = new int[M][N];
		int[][] max_right_map = new int[M][N];
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				map[i][j] = scan.nextInt();
			}
		}
		for(int i=0; i<N; i++) {
			max_right_map[0][i] = max_first(0, i);
			max_left_map[0][i] = max_first(0, i);
		}
		
		for(int i=1; i<M; i++) {
			for(int j=0; j<N; j++) {
				if(j==0) {
					max_right_map[i][j] = map[i][j]+max_right_map[i-1][j];
				}else {
					max_right_map[i][j] = Math.max(map[i][j]+max_right_map[i-1][j], map[i][j]+max_right_map[i][j-1]);
				}
			}
			for(int j=N-1; j>=0; j--) {
				if(j==N-1) {
					max_left_map[i][j] = map[i][j]+max_left_map[i-1][j];
				}else {
					max_left_map[i][j] = Math.max(map[i][j]+max_left_map[i-1][j], map[i][j]+max_left_map[i][j+1]);
				}
			}
			for(int j=0; j<N; j++) {
				map[i][j] = Math.max(max_left_map[i][j], max_right_map[i][j]);
				max_left_map[i][j] = map[i][j];
				max_right_map[i][j] = map[i][j];
			}
		}
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println(" ");
		}
	}	
	
	static int max_first(int m, int n) {
		int result = 0;
		if(n==0 && m == 0) {
			return result = map[m][n];
		}
		if(m==0) {
			for(int i=0; i<=n; i++) {
				result += map[m][i];
			}
		}
		return result;
	}
}
