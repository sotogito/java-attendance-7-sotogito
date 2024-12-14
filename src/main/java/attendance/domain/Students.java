package attendance.domain;

import attendance.constants.RiskType;
import attendance.domain.student.Student;
import java.util.ArrayList;
import java.util.List;

public class Students {
    private final List<Student> students;

    public Students(AttendancesReader reader) {
        String path = "src/main/resources/attendances.csv";
        students = reader.read(path);
    }


    public List<Student> getRiskStudents(){
        List<Student> result = new ArrayList<>();

        for(Student student : students){
            if(!student.getRiskType().equals(RiskType.NOTHING) &&
            !student.getRiskType().equals(RiskType.END)){
                result.add(student);
            }
        }
        return result;
    }

    public Student findStudentByName(String name){
        for(Student student :  students){
            if(student.isSameName(name)){
                return student;
            }
        }
        throw new IllegalArgumentException("등록되지 않은 닉네임입니다.");
    }
}
