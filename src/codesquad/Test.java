package codesquad;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static final int STRIKE_COUNT = 3;
	public static final String EXIT = "exit";

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
	public static int[] inning(List<Integer> randNums, List<Integer> userNums){
		int[] score = {0,0}; // strike, ball
		for(int num: userNums){
			if(randNums.contains(num)){ // strike, ball 갯수 세기 
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
	public static void checkBall(int[] score, int num, List<Integer> randNums, List<Integer> userNum){
		if(randNums.indexOf(num) == userNum.indexOf(num)){ // strike
			score[0]++;
		}else{ // ball
			score[1]++;
		}
	}
	
	// 사용자에게 숫자를 입력받아 List<Integer> 리턴
	public static List<Integer> userInput(){
		String[] userInput;
		while(true){
			System.out.println("숫자를 입력해주세요 : ");
			userInput = readFromKeyboard().split("");
			if (checkLength(userInput) && checkDuplication(userInput) && checkRange(userInput)) break;
		}
		return makeList(userInput);
	}
	
	// List<Integer> 생성
	public static List<Integer> makeList(String[] userInput){
		List<Integer> newList = new ArrayList<Integer>();
		for(String s : userInput){
			newList.add(Integer.parseInt(s));
		}
		return newList;
	}
	
	// 사용자에게 입력받은 숫자의 길이 검사
	public static boolean checkLength(String[] userInput){
		if(userInput.length == STRIKE_COUNT){
			return true;
		}else{
			System.out.println("입력한 숫자의 갯수가 3보다 크거나 작습니다.");
			return false;
		}	
	}
	
	// 중복 검사
	public static boolean checkDuplication(String[] userInput){
		ArrayList<String> list = new ArrayList<String>();
		for(String s : userInput){
			if(list.contains(s)){
				System.out.println("중복된 숫자 " + s + "(이)가 있습니다."); return false;
			}else{
				list.add(s);
			}
		}
		return true;
	}
	
	// 숫자가 적절한 범위에 있는지 검사
	public static boolean checkRange(String[] userInput){
		for(String s : userInput){
			int i = Integer.parseInt(s);
			if(i<0 || i>9) return false;
		}
		return true;
	}
	
	// BufferedReader를 활용하여 입력을 받는 메소드
	public static String readFromKeyboard(){
		String input = null;
		try{
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			input = br.readLine();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return input;
	}
	
	// game을 진행하는 메소드
	public static void game(){
		List<Integer> randNums = getRandomNum(STRIKE_COUNT);
		while(true){
			List<Integer> userNums = userInput();
			int[] result = inning(randNums, userNums);
			if(result[0] == STRIKE_COUNT) {
				System.out.println(STRIKE_COUNT + "개의 숫자를 모두 맞히셨습니다!");
				break;
			}
		}
	}
	
	// main()
	public static void main(String[] args) {
		String flag = "";
		while(!(flag.equals(EXIT))){
			game();
			System.out.println("**게임을 종료하시려면 'exit'을, 새로 시작하시려면 아무 키나 눌러주세요.**");
			flag = readFromKeyboard();
		}
	}

}
