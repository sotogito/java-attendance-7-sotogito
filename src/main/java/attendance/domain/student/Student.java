package attendance.domain.student;

import attendance.constants.AttendanceType;
import attendance.constants.RiskType;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class Student implements Comparable<Student>{
    private final String name;
    private int lateCount;
    private int absenceCount;
    private int attendanceCount;
    private final List<Attendance> attendances;

    public Student(String name) {
        this.name = name;
        this.attendances = new ArrayList<>();
        this.lateCount = 0;
        this.absenceCount = 0;
        this.attendanceCount = 0;
    }

    public String getName(){
        return name;
    }

    public int getAbsenceCount(){
        return absenceCount;
    }

    public int getLateCount(){
        return lateCount;
    }

    public RiskType getRiskType(){
        return RiskType.find(absenceCount);
    }

    public void sortAttendance(){
        attendances.sort(Comparable::compareTo);
    }


    public void deleteAttendance(Attendance oldAttendance){
        attendances.remove(oldAttendance);
    }

    public boolean isSameName(String otherName){
        if(otherName.equals(name)){
            return true;
        }
        return false;
    }

    public void addAttendance(Attendance newAttendance){
        attendances.add(newAttendance);
    }

    public Attendance findAttendanceByDay(int day){
        for(Attendance attendance : attendances){
            if(attendance.isSameDay(day)){
                return attendance;
            }
        }
        throw new IllegalArgumentException("존재하지 않는 출석일입니다.");
    }
    public int size(){
        return attendances.size();
    }

    public void updateAttendanceType(){
        for(Attendance attendance : attendances){
            if(attendance.getAttendanceType().equals(AttendanceType.ATTENDANCE)){
                attendanceCount++;
            } else if (attendance.getAttendanceType().equals(AttendanceType.LATE)) {
                lateCount++;
            } else if (attendance.getAttendanceType().equals(AttendanceType.ABSENCE)) {
                absenceCount++;
            }
        }
    }

    @Override
    public String toString(){
        StringBuilder printout = new StringBuilder();
        printout.append(name);
        printout.append("\n");
        for(Attendance attendance : attendances){
            printout.append(attendance);
            printout.append("\n");
        }

        printout.append("\n");
        printout.append(String.format("출석 : %d회\n",attendanceCount));
        printout.append(String.format("지각 : %d회\n",lateCount));
        printout.append(String.format("결석 : %d회\n",absenceCount));
        return printout.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(name, student.name);
    }
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(this.absenceCount, o.absenceCount);
    }
}
