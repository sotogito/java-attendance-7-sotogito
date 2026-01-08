package attendance.view;

import attendance.domain.Attendance;
import attendance.domain.AttendanceType;
import java.time.format.DateTimeFormatter;

public class OutputView {

    public static void writeCheckAttendance(Attendance attendance) {
        System.out.println(attendance);
    }

    public static void writeEditAttendance(
            Attendance oldAttendance,
            Attendance newAttendance
    ) {
        String formatted = newAttendance.getDateTime()
                .format(DateTimeFormatter.ofPattern("HH:mm"));
        AttendanceType attendanceType = newAttendance.getAttendanceType();

        System.out.print(oldAttendance);
        System.out.print(" -> ");
        System.out.printf("%s (%s)\n", formatted, attendanceType.getKorean());
    }
}
