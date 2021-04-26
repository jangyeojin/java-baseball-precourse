package woowabros.baseball;

import java.util.*;

public class Game {

    private boolean isNewGame = false;
    private List<Integer> randomNumberList;
    int strike = 0;
    int ball = 0;

    //게임 시작
    public void playBaseball(){
        //랜덤숫자 생성
        if(!isNewGame){
            randomNumberList = createRandomNumber();
        }

        //숫자만 입력받기
        List<Integer> inputNumbers = inputNumber();

        //입력값과 랜덤숫자 비교
        compareNumber(randomNumberList, inputNumbers);

        //정답
        if(strike == 3) {continueGame();}
        //오답
        if(strike != 3) {retryGame();}
    }

    //랜덤 숫자 생성
    public List<Integer> createRandomNumber(){
        Integer[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer>  numberList = new ArrayList<>(Arrays.asList(numbers));

        Collections.shuffle(numberList);

        //랜덤숫자중 서로다른 숫자 3개 뽑기
        List<Integer> randomNumberList = new ArrayList<Integer>();
        int index = 0;
        while (randomNumberList.size() < 3){
            randomNumberList.add(numberList.get(index));
            index++;
        }

        System.out.println("랜덤숫자");
        System.out.println(randomNumberList);
        return randomNumberList;
    }

    //숫자 입력 받기
    public List<Integer> inputNumber(){
        Scanner scan = new Scanner(System.in);
        System.out.println("숫자를 입력해주세요 : ");
        while (!scan.hasNextInt()) { //값이 숫자인지 판별
            scan.next();  //값이 숫자가 아니면 버린다.
            System.out.println("숫자를 입력해주세요 : ");
        }
        return splitInputNumber(scan.next());
    }

    //입력 받은 숫자 배열에 넣기
    public List<Integer> splitInputNumber(String inputNumber){
        String[] strArray =  inputNumber.split("");
        List<Integer>  inputNumbers = new ArrayList<>();
        for(String number : strArray) {
            inputNumbers.add(Integer.parseInt(number));
        }
        System.out.println("입력받은숫자");
        System.out.println(inputNumbers);
        return  inputNumbers;
    }

    //입력값과 랜덤 숫자 비교
    public void compareNumber(List<Integer> randomNumberList, List<Integer> inputNumbers){
        int i = 0;
        while (i < 3){
            Integer randomNumber = randomNumberList.get(i);
            Integer inputNumber = inputNumbers.get(i);
            strike = countStrike(randomNumber, inputNumber, strike);
            ball = countBall(randomNumberList, inputNumber, ball);
            i++;
        }
        ball = ball - strike;
    }

    //conut strike
    public int countStrike(Integer randomNumber, Integer inputNumber, int strike){
        if(randomNumber.equals(inputNumber)){
            strike++;
        }
        return strike;
    }

    //count ball
    public int countBall(List<Integer> randomNumberList, Integer inputNumber, int ball){
        if(randomNumberList.contains(inputNumber)){
            ball++;
        }
        return ball;
    }

    //맞춘경우 처리
    public void continueGame(){
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        Scanner scan = new Scanner(System.in);
        while (!scan.hasNextInt()) {
            scan.next();
            System.out.println("숫자를 입력해주세요 : ");
        }

        if("1".equals(scan.next())){
            reset();
            isNewGame = false;
            playBaseball();
        }
    }

    //못 맞춘경우 처리
    public void retryGame(){
        if(strike + ball == 0){
            System.out.println("낫싱");
        }

        if(strike + ball > 0){
            System.out.println(strike + "스트라이크" + ball + "볼");
        }

        reset();
        isNewGame = true;
        playBaseball();
    }

    public void reset(){
        strike = 0;
        ball = 0;
    }
}
