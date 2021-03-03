package swm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class bfsNdfs {
	static int[][] connection;
	static boolean[] visited;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int max_node = scan.nextInt();
		int max_line = scan.nextInt();
		int start_node = scan.nextInt();
		connection = new int[max_node+1][max_node+1];
		visited = new boolean[max_node+1];
		for(int i=0; i<max_line; i++) {
			int temp1 = scan.nextInt();
			int temp2 = scan.nextInt();
			connection[temp1][temp2] = 1;
			connection[temp2][temp1] = 1;			
		}
		dfs(start_node);
		for(int i=0; i<visited.length; i++) {
			visited[i] = false;
		}
		bfs(start_node);
	}
	
	static void dfs(int node) {
		visited[node] = true;
		System.out.println(node);
		int[] temp = connection[node];
		for(int i=0; i<temp.length; i++) {
			if(temp[i] == 1 && !visited[i]) {
				dfs(i);
			}
		}
	}
	
	static void bfs(int node) {
		Queue<Integer> Q = new LinkedList<Integer>();
		Q.add(node);
		visited[node] = true;
		System.out.println(node);
		while(!Q.isEmpty()) {
			int temp = Q.poll();
			for(int i=1; i<connection.length; i++) {
				if(connection[temp][i] == 1 && !visited[i]) {
					Q.offer(i);
					visited[i] = true;
					System.out.println(i);
				}
			}
		}
	}
}
