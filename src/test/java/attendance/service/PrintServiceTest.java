package attendance.service;

import static org.junit.jupiter.api.Assertions.*;

import attendance.domain.AttendancesReader;
import attendance.domain.Students;
import attendance.domain.student.Student;
import org.junit.jupiter.api.Test;

class PrintServiceTest {

    @Test
    void 출력확인(){
        Students students = new Students(new AttendancesReader());
        PrintService printService = new PrintService(students);

        Student student = printService.findAttendanceHistory("이든");

        System.out.println(student);
    }

}