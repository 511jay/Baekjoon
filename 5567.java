package jhlee_java;

import java.util.ArrayList;
import java.util.Scanner;

public class marry {
	static int listSize;
	static int[][] list;
	static ArrayList<Integer> arrList;
	static ArrayList<Integer> arrList2;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int people = scan.nextInt();
		listSize = scan.nextInt();
		list = new int[listSize][2];
		arrList = new ArrayList<Integer>();
		arrList2 = new ArrayList<Integer>();
		for(int i=0; i<listSize; i++) {
			list[i][0] = scan.nextInt();
			list[i][1] = scan.nextInt();
		}
		System.out.println(Algo());
	}
	static int Algo() {
		for(int i=0; i<listSize; i++) {
			if(list[i][0] == 1) {
				arrList.add(list[i][1]);
			}
		}
		for(int i=0; i<arrList.size(); i++) {
			for(int j=0; j<listSize; j++) {
				if(list[j][0] == arrList.get(i) && (arrList.contains(list[j][1]) == false)) {
					arrList2.add(list[j][1]);
				}
			}
		}
		return arrList.size()+arrList2.size();
	}
}
