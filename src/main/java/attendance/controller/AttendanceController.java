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
//                    LocalDateTime today = LocalDateTime.of(2024, 12, 15, 13, 0);

        while (true) {
            LocalDateTime today = DateTimes.now();
            createCrews(today);
            try {

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
                if (Function.크루별_출석_기록_확인 == function) {
                    checkAttendanceHistory();
                }
                if (Function.제적_위험자_확인 == function) {
                    checkRiskOfExpulsion();
                }

            } catch (IllegalArgumentException e) {
                ExceptionHandler.read(e);
            }
        }
    }

    private void checkAttendanceHistory() {
        Crew crew = attendanceService.findCrew(InputView.readNickName());

        OutputView.writeAttendanceHistory(crew);
    }

    private void checkRiskOfExpulsion() {
        List<Crew> crews = attendanceService.checkRiskOfExpulsion();

        OutputView.writeRiskOfExpulsion(crews);
    }

    private void editAttendance(LocalDateTime today) {
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
