package attendance.domain;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

public class Attendance implements Comparable<Attendance> {
    private LocalDateTime dateTime;
    private DayOfWeekKorean dayOfWeekKorean;
    private AttendanceType attendanceType;

    public Attendance(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        LocalTime localTime = dateTime.toLocalTime();

        dayOfWeekKorean = DayOfWeekKorean.find(dayOfWeek);
        attendanceType = AttendanceType.find(dayOfWeek, localTime);
        this.dateTime = dateTime;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public DayOfWeekKorean getDayOfWeekKorean() {
        return dayOfWeekKorean;
    }

    public AttendanceType getAttendanceType() {
        return attendanceType;
    }

    private Attendance(LocalDateTime dateTime, AttendanceType attendanceType) {
        this.dateTime = dateTime;
        this.attendanceType = attendanceType;
        dayOfWeekKorean = DayOfWeekKorean.find(dateTime.getDayOfWeek());
    }

    public static Attendance createAbsence(LocalDate localDate) {
        return new Attendance(localDate.atStartOfDay(), AttendanceType.결석);
    }


    public boolean isSameDate(LocalDate date) {
        LocalDate localDate = dateTime.toLocalDate();

        return date.isEqual(localDate);
    }

    @Override
    public int compareTo(Attendance o) {
        return this.dateTime.compareTo(o.dateTime);
    }

    @Override
    public String toString() {
        StringJoiner sj = new StringJoiner(" ");
        String date = dateTime.format(DateTimeFormatter.ofPattern("MM월 dd일"));
        String dayOfWeek = dayOfWeekKorean.getKorean();
        String time = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
        String attendanceState = String.format("(%s)", attendanceType.getKorean());


        if (attendanceType == AttendanceType.결석 &&
                (dateTime.toLocalTime().equals(LocalTime.of(0,0)))
        ) {
            time = "--:--";
        }

        sj.add(date)
                .add(dayOfWeek)
                .add(time)
                .add(attendanceState);

        return sj.toString();
    }

}
