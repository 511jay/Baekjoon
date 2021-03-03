package swm;

import java.util.Scanner;

public class greedy2 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int coin_kind = scan.nextInt();
		int goal = scan.nextInt();
		int[] coins = new int[coin_kind];
		for(int i=0; i<coin_kind; i++) {
			coins[i] = scan.nextInt();
		}
		int sum = 0;
		int all_coins = 0;
		while(sum != goal) {
			for(int i=coin_kind-1; i>=0; i--) {
				if(sum+coins[i]>goal) {
					continue;
				}else {
					sum+=coins[i];
					all_coins++;
					break;
				}
			}
		}
		System.out.println(all_coins);
	}
}
