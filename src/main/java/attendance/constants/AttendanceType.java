package attendance.constants;

import java.time.LocalDateTime;
import java.util.List;

public enum AttendanceType {
    ATTENDANCE("출석"),
    LATE("지각"),
    ABSENCE("결석"),
    ;

    private final String name;

    private static final int MON_START = 13;
    private static final int MON_END = 18;

    private static final int START = 10;
    private static final int END = 18;

    AttendanceType(String name) {

        this.name = name;
    }

    public String getName(){
        return name;
    }


    /**
     * 교육 시간은 월요일은 13:00~18:00, 화요일~금요일은 10:00~18:00이다.
     * 해당 요일의 시작 시각으로부터 5분 초과는 지각으로 간주한다.
     * 해당 요일의 시작 시각으로부터 30분 초과는 결석으로 간주한다.
     * @param localDateTime
     * @return
     */
    public static AttendanceType find(LocalDateTime localDateTime){
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        Week week = Week.find(localDateTime);

        int hour = localDateTime.getHour();
        int min = localDateTime.getMinute();

        if(week.equals(Week.MONDAY)){
            if(hour > MON_START){
                return ABSENCE;
            }if(hour == MON_START){
                if(min <= 5){
                    return ATTENDANCE;
                } else if (min <= 30) {
                    return LATE;
                }
            }
        }

        if(hour > START){
            return ABSENCE;
        }if(hour == START){
            if(min <= 5){
                return ATTENDANCE;
            } else if (min <= 30) {
                return LATE;
            }
        }
        return ATTENDANCE;

    }

}
