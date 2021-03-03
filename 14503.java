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
		//1. ������ġ�� û��
		//2. ���ʰ˻�->û�Ұ��ɽ� ������ȯ �� �̵�->1��
		//3. �ݽð�������� �ݺ�->4���� ���� û�� or ��->1ĭ ����->1��
		//4. ���� �����ϴ� ���� ���̸� �۵�����
		int j=leftside(ahead);
		for(int i=0; i<4; i++) {
			if(nextNum(r, c, j) == 0) {
				temp = 2;
				break;
			}
			j = leftside(j);
		}
		if(temp == 2) {
			System.out.println("["+r+","+c+"] "+j+"/��");
			Algo(nextR(r, j),nextC(c, j), j);
			return;
		}
		if(canGo(r, c, backside(ahead)) == true) {
			int back = backside(ahead);
			System.out.println("["+r+","+c+"] "+back+"/����");
			Algo(nextR(r, back),nextC(c, back), ahead);
			return;
		}
		if(canGo(r, c, backside(ahead)) == false) {
			System.out.println("�� �̻� �� �� ���� ");
			System.out.println(print2());
			return;
		}
	}
	static int dsnb (int ahead) {
		int result = 0; 
		switch (ahead) {
		case 0://��
			result = 4;
			break;
		case 1://��
			result = 3;
			break;
		case 2://��
			result = 2;
			break;
		case 3://�� 
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
		case 1://����
			result = 3;
			break;
		case 2://�Ʒ�
			result = 4;
			break;
		case 3://������
			result = 1;
			break;
		case 4://�� 
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
		case 1://����
			result = 2;
			break;
		case 2://�Ʒ�
			result = 3;
			break;
		case 3://������
			result = 4;
			break;
		case 4://�� 
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
		case 1://����
			if(((c-1)>=col)||(nextNum(r, c, ahead)==1)) {
				result = false;
			}else {
				result = true;
			}
			break;
		case 2://�Ʒ�
			if(((r+1)>=row)||(nextNum(r, c, ahead)==1)) {
				result = false;
			}else {
				result = true;
			}
			break;
		case 3://������
			if(((c+1)>=col)||(nextNum(r, c, ahead)==1)) {
				result = false;
			}else {
				result = true;
			}
			break;
		case 4://�� 
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
		case 1://����
			result = map[r][c-1];
			break;
		case 2://�Ʒ�
			result = map[r+1][c];
			break;
		case 3://������
			result = map[r][c+1];
			break;
		case 4://�� 
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
		case 1://����
			result = r;
			break;
		case 2://�Ʒ�
			result = r+1;
			break;
		case 3://������
			result = r;
			break;
		case 4://�� 
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
		case 1://����
			result = c-1;
			break;
		case 2://�Ʒ�
			result = c;
			break;
		case 3://������
			result = c+1;
			break;
		case 4://�� 
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
