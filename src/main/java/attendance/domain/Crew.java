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

    public String getNickname() {
        return nickname;
    }

    public ExpulsionState getExpulsionState() {
        int totalAbsence = getTotalAbsence();

        return ExpulsionState.findByAbsenceCount(totalAbsence);
    }

    public int getTotalAbsence() {
        int late = getAttendanceCountByType(AttendanceType.지각);
        int absence = getAttendanceCountByType(AttendanceType.결석);
        int addAbsenceFromLate = late / 3;

        return absence + addAbsenceFromLate;
    }

    public int getAttendanceCountByType(AttendanceType type) {
        int total = 0;

        for (Attendance attendance : attendances) {
            AttendanceType attendanceType = attendance.getAttendanceType();
            if (attendanceType == type) {
                total++;
            }
        }
        return total;
    }

    public Attendance findAttendance(LocalDate date) {
        for (Attendance attendance : attendances) {
            if (attendance.isSameDate(date)) {
                return attendance;
            }
        }
        throw new IllegalArgumentException("아직 수정할 수 없습니다.");
    }

    public void deleteAttendance(Attendance attendance) {
        attendances.remove(attendance);
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
        attendances.forEach(attendance -> sj.add(attendance.toString()));

        return sj.toString();
    }

}
