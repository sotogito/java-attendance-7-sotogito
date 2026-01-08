package attendance.controller;

import attendance.domain.Crews;
import attendance.domain.Function;
import attendance.service.AttendanceService;
import attendance.util.CrewsFactory;
import attendance.util.FilePath;
import attendance.util.ResourceReader;
import attendance.view.ExceptionHandler;
import attendance.view.InputView;
import java.time.LocalDateTime;
import java.util.List;

public class AttendanceController {
    private final AttendanceService attendanceService = new AttendanceService();

    public void main() {
//        LocalDateTime today = DateTimes.now();
        LocalDateTime today = LocalDateTime.of(2024, 12, 26, 13, 0);

        try {
            Crews crews = createCrews(today);

            Function function = readFunction(today);
            if (Function.종료 == function) {
                return;
            }


        } catch (IllegalArgumentException e) {
            ExceptionHandler.read(e);
        }
    }

    private Function readFunction(LocalDateTime today) {
        String input = InputView.readFunction(today);

        return Function.find(input);
    }

    private Crews createCrews(LocalDateTime today) {
        List<String> attendanceData = ResourceReader.readFile(FilePath.ATTENDANCES_PATH);

        return CrewsFactory.create(attendanceData, today);
    }

}
