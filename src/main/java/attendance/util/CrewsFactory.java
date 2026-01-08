package attendance.util;

import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.domain.Crews;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CrewsFactory {

    /// 파일에 있는 날짜들만 출석 등록함
    public static Crews create(List<String> attendances, LocalDateTime today) {
        List<Crew> crews = new ArrayList<>();

        for (String attendance : attendances) {
            String[] split = attendance.split(",");

            if (split.length != 2) {
                throw new IllegalArgumentException("예기치 못한 오류가 발생했습니다.");
            }

            String nickname = split[0];
            LocalDateTime dateTime;
            try {
                dateTime = LocalDateTime.parse(
                        split[1],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
                );
            } catch (Exception e) {
                throw new IllegalArgumentException("예기치 못한 오류가 발생했습니다.");
            }

            Crew newCrew = new Crew(nickname);
            if (crews.contains(newCrew)) {
                for (Crew crew : crews) {
                    if (crew.equals(newCrew)) {
                        crew.addAttendance(new Attendance(dateTime));
                    }
                }
                continue;
            }
            crews.add(newCrew);
            newCrew.addAttendance(new Attendance(dateTime));
        }

        for (Crew crew : crews) {
            crew.addAbsenceUntilToday(LocalDate.of(2024, 12, 1), today);
//            crew.addAbsenceUntilToday(LocalDate.of(2024, 12, 1),
//                    LocalDateTime.of(2024, 12, 26, 12, 12));
        }
        return new Crews(crews);
    }

}
