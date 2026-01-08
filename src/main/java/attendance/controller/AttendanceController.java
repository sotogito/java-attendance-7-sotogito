package attendance.controller;

import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.util.CrewsFactory;
import attendance.util.FilePath;
import attendance.util.ResourceReader;
import attendance.view.ExceptionHandler;
import java.util.List;

public class AttendanceController {

    public void main() {
        try{
            Crews crews = createCrews();
            for(Crew crew : crews.getCrews()) {
                System.out.println(crew);
            }



        }catch (IllegalArgumentException e) {
            ExceptionHandler.read(e);
        }
    }

    private Crews createCrews() {
        List<String> attendanceData = ResourceReader.readFile(FilePath.ATTENDANCES_PATH);

        return CrewsFactory.create(attendanceData);
    }
}
