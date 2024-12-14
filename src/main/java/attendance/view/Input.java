package attendance.view;

import attendance.constants.Function;
import camp.nextstep.edu.missionutils.Console;

public class Input {
    public static String inputFunction(){
        for(Function function : Function.values()){
            System.out.println(function);
        }
        return Console.readLine();
    }

    public static String inputStudentName(){
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }


    public static String inputToEditStudentName(){
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        return Console.readLine();
    }


    public static int inputChangeDay(){
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");

        try {
            return Integer.parseInt(Console.readLine());
        }catch (NumberFormatException e){
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }

    }

    public static String inputTime(){
        System.out.println("언제로 변경하겠습니까?");
        return Console.readLine();
    }
}
