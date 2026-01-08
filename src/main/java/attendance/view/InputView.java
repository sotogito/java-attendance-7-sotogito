package attendance.view;

import attendance.domain.DayOfWeekKorean;
import attendance.domain.Function;
import java.time.LocalDateTime;

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

}
