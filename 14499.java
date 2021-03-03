package day2021_01_25;

import java.util.Scanner;

public class dice_map {
	static int[][] map;
	static int[] command_arr;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int max_row = scan.nextInt();
		int max_col = scan.nextInt();
		map = new int[max_row][max_col];
		
		int start_row = scan.nextInt();
		int start_col = scan.nextInt();
		int command_num = scan.nextInt();
		command_arr = new int[command_num];
		
		for(int i=0; i<max_row; i++) {
			for(int j=0; j<max_col; j++) {
				map[i][j] = scan.nextInt();
			}
		}
		
		for(int i=0; i<command_num; i++) {
			command_arr[i] = scan.nextInt();
		}
		//ют╥б
		
		Dice dice = new Dice();
		dice.start_dice(start_row, start_col);
		for(int i=0; i<command_num; i++) {
			int command = command_arr[i];
			if(dice.canGo(command, max_row, max_col)) {
				dice.move_dice(command);
				dice.copyAlgo(map);
				System.out.println(dice.getUp());
			}else {
				System.out.println("0");
			}
		}
	}
}
class Dice{
	private int row;
	private int col;
	private int up;
	private int down;
	private int right;
	private int left;
	private int front;
	private int back;
	
	public void start_dice(int row, int col) {
		this.row = row;
		this.col = col;
		up = 0;
		down = 0;
		right = 0;
		left = 0;
		front = 0;
		back = 0;
	}
	
	public void copyAlgo(int map[][]) {
		int cur_floor = map[row][col];
		if(cur_floor == 0) {
			map[row][col] = down;
		}else {
			down = map[row][col];
			map[row][col] = 0;
		}
	}
	public void move_dice(int command) {
		int temp;
		switch (command) {
			case 1:
				temp = down;
				down = right;
				right = up;
				up = left;
				left = temp;
				col = col+1;
				break;
			case 2:
				temp = down;
				down = left;
				left = up;
				up = right;
				right = temp;
				col = col-1;
				break;
			case 3:
				temp = down;
				down = front;
				front = up;
				up = back;
				back = temp;
				row = row-1;
				break;
			case 4:
				temp = down;
				down = back;
				back = up;
				up = front;
				front = temp;
				row = row+1;
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + command);
		}
	}
	public boolean canGo(int command, int max_row, int max_col) {
		boolean result = true;
		switch (command) {
		case 1:
			if((col+1)>(max_col-1))
				result = false;
			break;
		case 2:
			if((col-1)<0)
				result = false;
			break;
		case 3:
			if((row-1)<0)
				result = false;
			break;
		case 4:
			if((row+1)>(max_row-1))
				result = false;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + command);
		}
		return result;
	}
	public int getUp() {
		return up;
	}
}
