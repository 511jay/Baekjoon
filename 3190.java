package BJ;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class pr3190 {
	static int[][] board;
	static int board_size;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		board_size = scan.nextInt();
		board = new int[board_size][board_size];
		int apple_max = scan.nextInt();
		int[][] apple_location = new int [apple_max][2];
		for(int i=0; i<apple_max; i++) {
			apple_location[i][0] = scan.nextInt();
			apple_location[i][1] = scan.nextInt();
			board[apple_location[i][0]-1][apple_location[i][1]-1] = 1;
		}
		int snake_moving_max = scan.nextInt();
		int[][] moving_event_time = new int[snake_moving_max][2];
		for(int i=0; i<snake_moving_max; i++) {
			moving_event_time[i][0] = scan.nextInt();
			String str = scan.next();
			if(str.charAt(0) == 'D') {
				moving_event_time[i][1] = 1;//1=D
			}else if(str.charAt(0) == 'L') {
				moving_event_time[i][1] = 2;//2=L
			}
		}
		move_queue mQue = new move_queue();
		int time = 1;
		int[] snake_location = {0, 0};
		board[snake_location[0]][snake_location[1]] = 2;
		int ahead = 1;//1=µ¿, 2=³², 3=¼­, 4=ºÏ
		mQue.queAdd(snake_location[0], snake_location[1]);
		while(!game_stop(snake_location, ahead)) {
			int[] next_location = new int[2];
			switch (ahead) {
			case 1:
				next_location[0] = snake_location[0];
				next_location[1] = snake_location[1]+1;
				break;
			case 2: 
				next_location[0] = snake_location[0]+1;
				next_location[1] = snake_location[1];
				break;
			case 3: 
				next_location[0] = snake_location[0];
				next_location[1] = snake_location[1]-1;
				break;
			case 4: 
				next_location[0] = snake_location[0]-1;
				next_location[1] = snake_location[1];
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + ahead);
			}
			if(board[next_location[0]][next_location[1]] == 1) {
				board[next_location[0]][next_location[1]] = 2;
			}else {
				board[next_location[0]][next_location[1]] = 2;
				int[] delete = mQue.quePoll();
				board[delete[0]][delete[1]] = 0;
			}
			snake_location[0] = next_location[0];
			snake_location[1] = next_location[1];
			mQue.queAdd(snake_location[0], snake_location[1]);
			for(int i=0; i<moving_event_time.length; i++) {
				if(moving_event_time[i][0] == time) {
					if(moving_event_time[i][1] == 1) {
						switch (ahead) {
						case 1:
							ahead = 2;
							break;
						case 2:
							ahead = 3;
							break;
						case 3:
							ahead = 4;
							break;
						case 4:
							ahead = 1;
							break;
						default:
							throw new IllegalArgumentException("Unexpected value: " + ahead);
						}
					}else if(moving_event_time[i][1] == 2){
						switch (ahead) {
						case 1:
							ahead = 4;
							break;
						case 2:
							ahead = 1;
							break;
						case 3:
							ahead = 2;
							break;
						case 4:
							ahead = 3;
							break;
						default:
							throw new IllegalArgumentException("Unexpected value: " + ahead);
						}
					}
				}
			}
			time++;
		}
		System.out.println("time: "+ time);
	}
	
	static boolean game_stop(int[] location, int ahead) {
		boolean result = false;
		switch (ahead) {
		case 1: 
			if(location[1]+1>=board_size) {
				result = true;
			}
			else {
				if((board[location[0]][location[1]+1]==2)) {
					result = true;
				}
			}
			break;
		case 2:
			if(location[0]+1>=board_size) {
				result = true;
			}
			else {
				if((board[location[0]+1][location[1]]==2)) {
					result = true;
				}
			}
			break;
		case 3:
			if(location[1]-1<0) {
				result = true;
			}
			else {
				if((board[location[0]][location[1]-1]==2)) {
					result = true;
				}
			}
			break;
		case 4:
			if(location[0]-1<0) {
				result = true;
			}
			else {
				if((board[location[0]-1][location[1]]==2)) {
					result = true;
				}
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ahead);
		}
		return result;
	}
	static class move_queue{
		Queue<Integer> row = new LinkedList<Integer>();
		Queue<Integer> col = new LinkedList<Integer>();
		public void queAdd(int r, int c) {
			row.add(r);
			col.add(c);
		}
		public int[] quePoll() {
			int[] result = new int[2];
			result[0] = row.poll();
			result[1] = col.poll();
			return result;
		}
	}
}
