package attendance.service;

import attendance.constants.AttendanceType;
import attendance.constants.Week;
import attendance.domain.Students;
import attendance.domain.dto.TimeDto;
import attendance.domain.student.Attendance;
import attendance.domain.student.Student;
import java.time.LocalDateTime;

public class AttendanceService {
    private final Students students;

    public AttendanceService(Students students) {
        this.students = students;
    }

    public void validateDate(LocalDateTime today){
        //AttendanceType.validateSchoolStartTime(today);
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        Week week = Week.find(today);
        if(Week.find(today).isWeekend()){
            throw new IllegalArgumentException(String.format("%d월 %d일 %s은 등교일이 아닙니다.",month,day,week.getKorean()));
        }
    }

    public Attendance addAttendance(LocalDateTime today ,Student student, TimeDto attendanceTime){
        LocalDateTime attendanceDate = today.withHour(attendanceTime.hour()).withMinute(attendanceTime.min());
        Attendance newAttendance = new Attendance(attendanceDate);
        student.addAttendance(newAttendance);

        return newAttendance;
    }

    public Student findStudent(String name){
        return students.findStudentByName(name);
    }

}
