package attendance.controller;

import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.domain.Function;
import attendance.service.AttendanceService;
import attendance.util.CrewsFactory;
import attendance.util.FilePath;
import attendance.util.ResourceReader;
import attendance.view.ExceptionHandler;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class AttendanceController {
    private final AttendanceService attendanceService = new AttendanceService();

    public void main() {
        while (true) {
//            LocalDateTime today = LocalDateTime.of(2024, 12, 26, 13, 0);
            LocalDateTime today = DateTimes.now();

            try {
                createCrews(today);

                Function function = readFunction(today);
                if (Function.종료 == function) {
                    return;
                }

                if (Function.출석_확인 == function) {
                    checkAttendance(today);
                }
                if (Function.출석_수정 == function) {
                    editAttendance(today);
                }

            } catch (IllegalArgumentException e) {
                ExceptionHandler.read(e);
            }
        }
    }

    private void editAttendance(LocalDateTime today) {
        /**
         * 이름
         * 날짜
         * 변경 시간
         *
         * 변경전 -> 변경 후
         */
        Crew crew = attendanceService.findCrew(InputView.readNickName());
        int day = InputView.readDay();
        LocalTime time = InputView.readTime();

        Map<String, Attendance> result = attendanceService.editAttendance(
                today, crew, day, time
        );

        OutputView.writeEditAttendance(result.get("old"), result.get("new"));
    }

    private void checkAttendance(LocalDateTime today) {
        attendanceService.validateToday(today);
        Crew crew = attendanceService.findCrew(InputView.readNickName());
        LocalTime time = InputView.readTime();

        Attendance attendance = attendanceService.checkAttendance(today, crew, time);
        OutputView.writeCheckAttendance(attendance);
    }

    private Function readFunction(LocalDateTime today) {
        String input = InputView.readFunction(today);

        return Function.find(input);
    }

    private void createCrews(LocalDateTime today) {
        List<String> attendanceData = ResourceReader.readFile(FilePath.ATTENDANCES_PATH);

        CrewsFactory.create(attendanceData, today);
    }

}
