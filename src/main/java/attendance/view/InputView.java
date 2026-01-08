package attendance.view;

import attendance.domain.DayOfWeekKorean;
import attendance.domain.Function;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputView {

    public static String readFunction(LocalDateTime today) {
        System.out.printf("오늘은 %d월 %d일 %s입니다. 기능을 선택해 주세요.\n",
                today.getMonthValue(),
                today.getDayOfMonth(),
                DayOfWeekKorean.find(today.getDayOfWeek()).getKorean()
        );
        for (Function function : Function.values()) {
            System.out.println(function);
        }
        return Reader.read().getInput();
    }

    public static int readDay() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");

        try {
            return Integer.parseInt(Reader.read().getInput());
        }catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }

    public static String readNickName() {
        System.out.println("닉네임을 입력해 주세요.");

        return Reader.read().getInput();
    }

    public static LocalTime readTime() {
        System.out.println("등교 시간을 입력해 주세요.");

        try {
            return LocalTime.parse(
                    Reader.read().getInput(),
                    DateTimeFormatter.ofPattern("HH:mm")
            );
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }

}
