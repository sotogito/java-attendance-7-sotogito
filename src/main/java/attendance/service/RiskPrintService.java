package attendance.service;

import attendance.domain.Students;
import attendance.domain.student.Student;
import java.util.List;
import javax.swing.plaf.PanelUI;

public class RiskPrintService {
    private final Students students;

    public RiskPrintService(Students students) {
        this.students = students;
    }

    public List<Student> getRiskStudent(){
        return students.getRiskStudents();
    }





}
