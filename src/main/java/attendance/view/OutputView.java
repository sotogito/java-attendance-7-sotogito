package attendance.view;

import attendance.domain.Attendance;
import attendance.domain.AttendanceType;
import attendance.domain.Crew;
import attendance.domain.ExpulsionState;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
        System.out.printf("%s (%s) 수정 완료!\n", formatted, attendanceType.getKorean());
    }

    public static void writeRiskOfExpulsion(List<Crew> crews) {
        for (Crew crew : crews) {
            int absence = crew.getTotalAbsence();
            int late = crew.getAttendanceCountByType(AttendanceType.지각);
            ExpulsionState expulsionState = crew.getExpulsionState();

            System.out.printf("- %s: 결석 %d회, 지각 %d회 (%s)\n",
                    crew.getNickname(),
                    absence,
                    late,
                    expulsionState.getValue()
            );
        }
    }

    public static void writeAttendanceHistory(Crew crew) {
        System.out.println();
        System.out.printf("이번 달 %s의 출석 기록입니다.\n\n", crew.getNickname());

        System.out.println(crew);
        System.out.println("\n");
        System.out.printf("출석: %d회\n", crew.getAttendanceCountByType(AttendanceType.출석));
        System.out.printf("지각: %d회\n", crew.getAttendanceCountByType(AttendanceType.지각));
        System.out.printf("결석: %d회\n", crew.getTotalAbsence());
        System.out.println();
        System.out.printf("%s 대상자입니다.\n", crew.getExpulsionState().getValue());
    }

}
