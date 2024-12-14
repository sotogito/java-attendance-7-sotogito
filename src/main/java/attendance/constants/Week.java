package attendance.constants;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;

public enum Week {
    SUNDAY(DayOfWeek.SUNDAY,"일요일",true),
    MONDAY(DayOfWeek.MONDAY,"월요일",false),
    TUESDAY(DayOfWeek.TUESDAY,"화요일",false),
    WEDNESDAY(DayOfWeek.WEDNESDAY,"수요일",false),
    THURSDAY(DayOfWeek.THURSDAY,"목요일",false),
    FRIDAY(DayOfWeek.FRIDAY,"금요일",false),
    SATURDAY(DayOfWeek.SATURDAY,"토요일",true),
    CHRISTMAS(DayOfWeek.WEDNESDAY,"수요일",true);

    private final DayOfWeek dayOfWeek;
    private final String korean;
    private final boolean isWeekend;

    Week(DayOfWeek dayOfWeek, String korean, boolean isWeekend) {
        this.dayOfWeek = dayOfWeek;
        this.korean = korean;
        this.isWeekend = isWeekend;
    }

    public boolean isWeekend(){
        return isWeekend;
    }

    public String getKorean(){
        return korean;
    }

    //note 크리스마스 어떻게 처리하지?
    public static Week find(LocalDateTime localDateTime){
        DayOfWeek day = localDateTime.getDayOfWeek();
        int month = localDateTime.getMonthValue();
        int dayNumber = localDateTime.getDayOfMonth();

        if(month == 12 && dayNumber == 25){
            return CHRISTMAS;
        }


        for(Week week : Week.values()){
            if(week.dayOfWeek.equals(day)){
                return week;
            }
        }
        throw new IllegalArgumentException("요일 반환 오류");
    }

}
