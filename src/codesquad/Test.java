package codesquad;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {

	// random한 난수 생성
	public static List<Integer> getRandomNum(int length){
		List<Integer> result = new ArrayList<Integer>();
		while(result.size() <length){
			int randNum = (int)(Math.random()*(9-1+1))+1; // 1~9 사이의 난수생성
			if (!result.contains(randNum)) result.add(randNum);	
		}
		return result;
	}
	
	// 스트라이크, 볼 갯수 카운트
	public static int[] inning(List<Integer> randNums, List<String> userNums){
		int[] score = {0,0}; // strike, ball
		for(String num: userNums){
			if(randNums.contains(Integer.parseInt(num))){ // strike, ball 갯수 세기 
				checkBall(score, num, randNums, userNums);
			}
		}
		printStatus(score);
		return score;
	}

	// 현재 strike 갯수와 ball 갯수를 콘솔창에 출력
	public static void printStatus(int[] score){
		if (score[0] != 0) System.out.print(score[0] + " 스트라이크 ");
		if (score[1] != 0){
			System.out.println(score[1] + "볼");
		}else{
			System.out.println();
		}
	}
	
	// ball인지 strike인지 체크
	public static void checkBall(int[] score, String num, List<Integer> randNums, List<String> userNum){
		if(randNums.indexOf(Integer.parseInt(num)) == userNum.indexOf(num)){ // strike
			score[0]++;
		}else{ // ball
			score[1]++;
		}
	}
	
	public static void main(String[] args) {
		List<Integer> randNums = getRandomNum(3);
		Scanner sc = new Scanner(System.in);
		
		while(true){
			System.out.println("숫자를 입력해주세요 : ");
			String[] userNum = sc.nextLine().split("");
			List<String> userNums = Arrays.asList(userNum);
			
			int[] result = inning(randNums, userNums);
			
			if(result[0] == 3){
				System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
				break;
			}
		}
		sc.close();
	}

}
