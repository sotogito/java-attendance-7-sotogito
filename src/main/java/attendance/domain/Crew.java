package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Crew {
    private final String nickname;
    private final List<Attendance> attendances = new ArrayList<>();

    public Crew(String nickname) {
        this.nickname = nickname;
    }

    public boolean isSameNickname(String nickname) {
        return nickname.equals(this.nickname);
    }

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
    }

    public void addAbsenceUntilToday(LocalDate targetStartDate, LocalDateTime today) {
        LocalDate targetDate = targetStartDate;

        do {
            DayOfWeek dayOfWeek = targetDate.getDayOfWeek();
            DayOfWeekKorean dayOfWeekKorean = DayOfWeekKorean.find(dayOfWeek);
            if (targetDate.getMonth() == Month.DECEMBER && targetDate.getDayOfMonth() == 25) {
                targetDate = targetDate.plusDays(1);
                continue;
            }
            if (dayOfWeekKorean.isWeekend()) {
                targetDate = targetDate.plusDays(1);
                continue;
            }

            boolean isContainAttendance = isContainAttendance(targetDate);
            if (!isContainAttendance) {
                attendances.add(Attendance.createAbsence(targetDate));
            }
            targetDate = targetDate.plusDays(1);
        } while (targetDate.isBefore(today.toLocalDate()));
    }

    private boolean isContainAttendance(LocalDate targetDate) {
        for (Attendance attendance : attendances) {
            if (attendance.isSameDate(targetDate)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        return Objects.equals(nickname, crew.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }

    @Override
    public String toString() {
        Collections.sort(attendances);

        StringJoiner sj = new StringJoiner("\n");
        sj.add(nickname);
        attendances.forEach(attendance -> sj.add(attendance.toString()));
        return sj.toString();
    }

}
