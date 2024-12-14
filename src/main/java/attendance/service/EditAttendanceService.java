package attendance.service;

import attendance.domain.Students;
import attendance.domain.dto.TimeDto;
import attendance.domain.student.Attendance;
import attendance.domain.student.Student;
import java.time.LocalDateTime;

public class EditAttendanceService {
    private final Students students;


    public EditAttendanceService(Students students) {
        this.students = students;
    }


    public Attendance findAttendance(String name,int changeDay){
        Student student = students.findStudentByName(name);
        Attendance attendance = student.findAttendanceByDay(changeDay);
        return attendance;
    }

    public Attendance getOldAttendance(Attendance attendance){
        return attendance.cloneOldAttendance();
    }

    public Attendance changeAttendanceDate(Attendance attendance,LocalDateTime today , TimeDto newTime){
        attendance.changeTime(newTime.hour(),newTime.min());
        return attendance;
    }

    //note 아니면 이거 한번에
    public String informationPrintout(Student student,int changeDay,LocalDateTime today , TimeDto newTime){
        String printout = "%s -> %s 수정 완료!";

        //Student student = students.findStudentByName(name);
        Attendance attendance = student.findAttendanceByDay(changeDay);

        Attendance oldAttendance = attendance.cloneOldAttendance();
        LocalDateTime newDateTime = attendance.getNewDateTime(newTime.hour(),newTime.min());

        student.deleteAttendance(attendance);
        Attendance newAttendance = new Attendance(newDateTime);

        student.addAttendance(newAttendance);



        return String.format(printout,oldAttendance,newAttendance.getTimeAndAttendanceTypePrintout());
    }

    public Student findStudent(String name){
        return students.findStudentByName(name);
    }


}
