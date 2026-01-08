package attendance.service;

import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.domain.DayOfWeekKorean;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.List;
import java.util.Map;

public class AttendanceService {

    public Crew findCrew(String nickname) {
        return Crews.CREWS.findByNickname(nickname);
    }

    public void validateToday(LocalDateTime today) {
        DayOfWeekKorean dayOfWeekKorean = DayOfWeekKorean.find(today.getDayOfWeek());
        if (dayOfWeekKorean.isWeekend()) {
            throw new IllegalArgumentException(String.format(
                    "%d월 %d일 %s은 등교일이 아닙니다.",
                    today.getMonthValue(),
                    today.getDayOfMonth(),
                    dayOfWeekKorean.getKorean()
            ));
        }
    }

    public Attendance checkAttendance(LocalDateTime today, Crew crew, LocalTime time) {
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

    public Map<String, Attendance> editAttendance(
            LocalDateTime today,
            Crew crew,
            int day,
            LocalTime time
    ) {

        LocalDate editDay = today.toLocalDate().withDayOfMonth(day);
        if (editDay.isAfter(today.toLocalDate())) {
            throw new IllegalArgumentException("아직 수정할 수 없습니다.");
        }
        DayOfWeekKorean dayOfWeekKorean = DayOfWeekKorean.find(editDay.getDayOfWeek());
        if (dayOfWeekKorean.isWeekend() ||
                (editDay.getMonth() == Month.DECEMBER && editDay.getDayOfMonth() == 25)) {
            throw new IllegalArgumentException(
                    String.format("%d월 %d일 %s은 등교일이 아닙니다.",
                            editDay.getMonthValue(),
                            editDay.getDayOfMonth(),
                            dayOfWeekKorean.getKorean()
                    )
            );

        }
        LocalDateTime newDate = editDay.atTime(time);

        Attendance oldAttendance = crew.findAttendance(editDay);
        Attendance newAttendance = new Attendance(newDate);

        crew.deleteAttendance(oldAttendance);
        crew.addAttendance(newAttendance);

        return Map.of(
                "old", oldAttendance,
                "new", newAttendance
        );
    }

    public List<Crew> checkRiskOfExpulsion() {
        return Crews.CREWS.getRiskOfExpulsion();
    }

}
