package attendance.domain;

import java.time.DayOfWeek;

public enum DayOfWeekKorean {
    일(DayOfWeek.SUNDAY, "일", true),
    월(DayOfWeek.MONDAY, "월", false),
    화(DayOfWeek.TUESDAY, "화", false),
    수(DayOfWeek.WEDNESDAY, "수", false),
    목(DayOfWeek.THURSDAY, "목", false),
    금(DayOfWeek.FRIDAY, "금", false),
    토(DayOfWeek.SATURDAY, "토", true);

    private final DayOfWeek dayOfWeek;
    private final String korean;
    private final boolean isWeekend;

    DayOfWeekKorean(DayOfWeek dayOfWeek, String korean, boolean isWeekend) {
        this.dayOfWeek = dayOfWeek;
        this.korean = korean;
        this.isWeekend = isWeekend;
    }

    public String getKorean() {
        return korean + "요일";
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public static boolean isWeekend(DayOfWeek other) {
        for (DayOfWeekKorean dayOfTheWeek : DayOfWeekKorean.values()) {
            if (dayOfTheWeek.dayOfWeek.equals(other)) {
                return dayOfTheWeek.isWeekend;
            }
        }
        return false;
    }

    public static DayOfWeekKorean find(DayOfWeek dayOfWeek) {
        for (DayOfWeekKorean dayOfTheWeek : DayOfWeekKorean.values()) {
            if (dayOfTheWeek.dayOfWeek.equals(dayOfWeek)) {
                return dayOfTheWeek;
            }
        }
        throw new IllegalArgumentException("요일 오류");
    }

}
