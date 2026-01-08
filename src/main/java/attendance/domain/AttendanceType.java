package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalTime;

public enum AttendanceType {
    출석, 지각, 결석;

    /**
     * - 월 : 13:00~18:00
     * - 화, 수, 목, 금 : 10:00~18:00
     *
     *
     * - 지각 : 시작 시간으로부터 5분 초과
     * - 결석 : 시작 시간으로부터 30분 초과
     * - 결석 : 오늘 출석하지 않음 = 08:00 ~ 23:00동안
     */

    private final static LocalTime classEndTime = LocalTime.of(18, 0);

    public static AttendanceType find(DayOfWeek dayOfWeek, LocalTime localTime) {
        if (isOverRunningTime(localTime)) {
            return AttendanceType.결석;
        }

        LocalTime start;
        if (dayOfWeek == DayOfWeek.MONDAY) {
            start = LocalTime.of(13, 0);
        } else {
            start = LocalTime.of(10, 0);
        }

        if (localTime.isAfter(start.plusMinutes(30))) {
            return AttendanceType.결석;
        }
        if (localTime.isAfter(start.plusMinutes(5))) {
            return AttendanceType.지각;
        }
        return AttendanceType.출석;
    }

    private static boolean isOverRunningTime(LocalTime localTime) {
        return localTime.isBefore(LocalTime.of(8, 0))
                || localTime.isAfter(LocalTime.of(23, 0));
    }

}
