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

    /**
     * 닉네임을 입력해 주세요.
     * 이든
     * 등교 시간을 입력해 주세요.
     * 09:59
     *
     * 12월 13일 금요일 09:59 (출석)
     */

    /**dk`~~dk  아 추가 추ㅏㄱ다 추ㅏㄱ하ㅏ는거
     *   - 미래 날짜로 수정하는 경우
     *   - 주말 또는 공휴일에 수정하는 경우
     *   - 08:00~23:00 등교 시간이 아닌 경우
     *   - 이미 출석했는데 다시 수정할 경우
     */



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
        /**
         * 수정 날짜가 현재 시간보다 미래일 경우 -> 먼저 해야됨
         *
         */
        /**
         * 주말이면 안되
         */


        LocalDateTime attendanceDate = today.withHour(attendanceTime.hour()).withMinute(attendanceTime.min());
        Attendance newAttendance = new Attendance(attendanceDate);
        student.addAttendance(newAttendance);

        return newAttendance;
    }

    public Student findStudent(String name){
        return students.findStudentByName(name);
    }

}
