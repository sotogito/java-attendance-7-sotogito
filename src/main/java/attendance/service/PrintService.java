package attendance.service;

import attendance.domain.Students;
import attendance.domain.student.Attendance;
import attendance.domain.student.Student;
import java.util.List;

public class PrintService {
    private final Students students;

    public PrintService(Students students) {
        this.students = students;
    }


    //note 바로 출력하면 됨
    public Student findAttendanceHistory(String name){
        Student student = students.findStudentByName(name);
        student.updateAttendanceType();
        student.sortAttendance();
        return student;
    }

    /**
     *   ["12월 02일 월요일 13:00 (출석)",
     *     "12월 03일 화요일 10:00 (출석)",
     *     "12월 04일 수요일 10:00 (출석)",
     *     "12월 05일 목요일 10:00 (출석)",
     *     "12월 06일 금요일 10:00 (출석)",
     *     "12월 09일 월요일 13:00 (출석)",
     *     "12월 11일 수요일 --:-- (결석)",
     *     "12월 12일 목요일 10:31 (결석)",
     *     "출석: 7회",
     *     "지각: 0회",
     *     "결석: 2회",
     *     "경고 대상자"]
     */

}
