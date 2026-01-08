package attendance.service;

import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.domain.Crews;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AttendanceService {

    public Attendance checkAttendance(LocalDateTime today, String nickname, LocalTime time) {
        Crew crew = Crews.CREWS.findByNickname(nickname);
        try {
            today = today
                    .withHour(time.getHour())
                    .withMinute(time.getMinute());
        } catch (Exception e) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }

        Attendance attendance = new Attendance(today);
        crew.addAttendance(attendance);

        return attendance;
    }

}
