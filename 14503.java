package jhlee_java;

import java.util.Scanner;

public class robot {
	static int[][] map;
	static int row;
	static int col;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		row = scan.nextInt();
		col = scan.nextInt();
		int r = scan.nextInt();
		int c = scan.nextInt();
		int ahead = scan.nextInt();
		map = new int[row][col];
		for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){      
                map[i][j] = scan.nextInt();
            }
        }
		Algo(r, c, dsnb(ahead));
	}
	static void Algo(int r, int c, int ahead) {
		map[r][c] = 2;
		int temp = 1;
		//1. 현재위치를 청소
		//2. 왼쪽검사->청소가능시 방향전환 후 이동->1번
		//3. 반시계반향으로 반복->4방향 전부 청소 or 벽->1칸 후진->1번
		//4. 만약 후진하는 곳이 벽이면 작동중지
		int j=leftside(ahead);
		for(int i=0; i<4; i++) {
			if(nextNum(r, c, j) == 0) {
				temp = 2;
				break;
			}
			j = leftside(j);
		}
		if(temp == 2) {
			System.out.println("["+r+","+c+"] "+j+"/쪽");
			Algo(nextR(r, j),nextC(c, j), j);
			return;
		}
		if(canGo(r, c, backside(ahead)) == true) {
			int back = backside(ahead);
			System.out.println("["+r+","+c+"] "+back+"/후진");
			Algo(nextR(r, back),nextC(c, back), ahead);
			return;
		}
		if(canGo(r, c, backside(ahead)) == false) {
			System.out.println("더 이상 갈 수 없음 ");
			System.out.println(print2());
			return;
		}
	}
	static int dsnb (int ahead) {
		int result = 0; 
		switch (ahead) {
		case 0://북
			result = 4;
			break;
		case 1://동
			result = 3;
			break;
		case 2://남
			result = 2;
			break;
		case 3://서 
			result = 1;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ahead);
		}
		return result;
	}
	static int backside(int ahead) {
		int result = 0; 
		switch (ahead) {
		case 1://왼쪽
			result = 3;
			break;
		case 2://아래
			result = 4;
			break;
		case 3://오른쪽
			result = 1;
			break;
		case 4://위 
			result = 2;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ahead);
		}
		return result;
	}
	static int leftside(int ahead) {
		int result = 0; 
		switch (ahead) {
		case 1://왼쪽
			result = 2;
			break;
		case 2://아래
			result = 3;
			break;
		case 3://오른쪽
			result = 4;
			break;
		case 4://위 
			result = 1;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ahead);
		}
		return result;
	}
	static boolean canGo(int r, int c, int ahead) {
		boolean result = false;
		switch (ahead) {
		case 1://왼쪽
			if(((c-1)>=col)||(nextNum(r, c, ahead)==1)) {
				result = false;
			}else {
				result = true;
			}
			break;
		case 2://아래
			if(((r+1)>=row)||(nextNum(r, c, ahead)==1)) {
				result = false;
			}else {
				result = true;
			}
			break;
		case 3://오른쪽
			if(((c+1)>=col)||(nextNum(r, c, ahead)==1)) {
				result = false;
			}else {
				result = true;
			}
			break;
		case 4://위 
			if(((r-1)>=row)||(nextNum(r, c, ahead)==1)) {
				result = false;
			}else {
				result = true;
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ahead);
		}
		return result;
	}
	static int nextNum(int r, int c, int ahead) {
		int result = 0; 
		switch (ahead) {
		case 1://왼쪽
			result = map[r][c-1];
			break;
		case 2://아래
			result = map[r+1][c];
			break;
		case 3://오른쪽
			result = map[r][c+1];
			break;
		case 4://위 
			result = map[r-1][c];
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ahead);
		}
		return result;
	}
	static int nextR(int r, int ahead) {
		int result = 0; 
		switch (ahead) {
		case 1://왼쪽
			result = r;
			break;
		case 2://아래
			result = r+1;
			break;
		case 3://오른쪽
			result = r;
			break;
		case 4://위 
			result = r-1;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ahead);
		}
		return result;
	}
	static int nextC(int c, int ahead) {
		int result = 0; 
		switch (ahead) {
		case 1://왼쪽
			result = c-1;
			break;
		case 2://아래
			result = c;
			break;
		case 3://오른쪽
			result = c+1;
			break;
		case 4://위 
			result = c;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + ahead);
		}
		return result;
	}
	static int print2() {
		int count = 0;
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(map[i][j] == 2)
					count++;
			}
		}
		return count;
	}
}
