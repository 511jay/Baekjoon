package jhlee_java;

import java.util.Arrays;
import java.util.Scanner;

public class card {
	static int prices[];
	static int max_prices[];
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int card = scan.nextInt();
		int[] price = new int[card];
		for(int i=0; i<card; i++) {
			price[i] = scan.nextInt();
		}
		prices = price;
		max_prices = prices;
		for(int i=1; i<=card; i++) {
			Algo(i);
		}
		System.out.println(max_prices[card-1]);
	}
	static void Algo(int n) {
		int key = n%2;
		switch (key) {
		case 0:
			Algo0(n);
			break;
		case 1:
			Algo1(n);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + key);
		}
	}
	
	static void Algo0(int n) {
		int half = n/2;
		int N = n-2;
		int[] temp = new int[half+1];
		for(int i=0; i<half; i++) {
			temp[i] = max_prices[N-i]+max_prices[i];
		}
		temp[half]=max_prices[n-1];
		Arrays.sort(temp);
		max_prices[N+1] = temp[temp.length-1];
	}
	
	static void Algo1(int n) {
		if(n==1) {
			max_prices[0] = prices[0];
			return;
		}
		int half = (n-1)/2;
		int N = n-2;
		int[] temp = new int[half+1];
		for(int i=0; i<half; i++) {
			temp[i] = max_prices[N-i]+max_prices[i];
		}
		temp[half]=max_prices[n-1];
		Arrays.sort(temp);
		max_prices[N+1] = temp[temp.length-1];
	}
}
