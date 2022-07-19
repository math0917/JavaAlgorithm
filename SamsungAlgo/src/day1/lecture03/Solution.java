package day1.lecture03;

/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;
// double b = 1.0;
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.StringTokenizer;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
    public static void main(String args[]) throws Exception
    {
		/*
		   아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
		   여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
		   이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.
		   따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다.
		   단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
		 */
        System.setIn(new FileInputStream("src/day1/lecture03/input.txt"));

		/*
		   표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
		 */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

		/*
		   여러 개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
		*/

        for(int test_case = 1; test_case <= T; test_case++)
        {
            st = new StringTokenizer(br.readLine());
            int row, col;
            row = Integer.parseInt(st.nextToken());
            col = Integer.parseInt(st.nextToken());
            int startCol = 0;
            int startRow = 0;
            boolean flag= false;
            int[][] password = new int[row][col];
            for (int i = 0; i <row;i ++){
                st = new StringTokenizer(br.readLine());
                String s1 = st.nextToken();
                for (int j = 0; j < col; j++){
                    password[i][j] = Character.getNumericValue(s1.charAt(j));
                }
            }
            for (int i = 0; i< row; i ++){
                for (int j = 0 ;j < col-55; j++){
                    if ((password[i][j] == 0) && (password[i][j+55] == 1)){
                            startCol = j;
                            startRow = i;
                            flag = true;
                    }

                }
                if (flag){
                    break;
                }
            }


            int[] validate = new int[56];
            for (int i = 0; i< 56; i++){
                validate[i] = password[startRow][startCol + i];
            }
            int[] passwordInt = new int[8];
            HashMap<String, Integer> oneToOne = new HashMap<>();
            oneToOne.put("3211",0);
            oneToOne.put("2221",1);
            oneToOne.put("2122",2);
            oneToOne.put("1411",3);
            oneToOne.put("1132",4);
            oneToOne.put("1231",5);
            oneToOne.put("1114",6);
            oneToOne.put("1312",7);
            oneToOne.put("1213",8);
            oneToOne.put("3112",9);

            for (int i = 0; i < 8; i++){
                int thisNum = 0;
                String thisString = "";
                int count = 0;
                for (int j = 0; j<7; j++){
                    if (thisNum == validate[i*7 + j]){
                        count += 1;
                    }else{
                        thisString += String.valueOf(count);
                        count = 1;
                        thisNum = (thisNum + 1)%2;
                    }
                }

                thisString += String.valueOf(count);
                passwordInt[i] = oneToOne.get(thisString);
            }
            int result = 0;
            for (int i = 0 ;i < 7; i ++){
                if ((i % 2) == 0){
                    result += 3*passwordInt[i];
                }
                else{
                    result += passwordInt[i];
                }
            }
            if ((passwordInt[7]+result)% 10 == 0){
                System.out.println("#" + test_case + " "+ Arrays.stream(passwordInt).sum());
            }
            else{
                System.out.println("#" + test_case + " "+0);
            }
        }
    }
}
