package attendance.controller;

import attendance.constants.Function;
import attendance.domain.AttendancesReader;
import attendance.domain.Students;
import attendance.domain.dto.TimeDto;
import attendance.domain.dto.TimeParser;
import attendance.domain.student.Attendance;
import attendance.domain.student.Student;
import attendance.service.AttendanceService;
import attendance.service.EditAttendanceService;
import attendance.service.PrintService;
import attendance.service.RiskPrintService;
import attendance.view.Input;
import attendance.view.Output;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDateTime;
import java.util.List;

public class MainController {
    private AttendanceService attendanceService;
    private EditAttendanceService editAttendanceService;
    private PrintService printService;
    private RiskPrintService riskPrintService;

    public MainController() {
        Students students = new Students(new AttendancesReader());
        attendanceService = new AttendanceService(students);
        editAttendanceService = new EditAttendanceService(students);
        printService = new PrintService(students);
        riskPrintService = new RiskPrintService(students);
    }

    public void run(){
        while (true){
            LocalDateTime nowDate = DateTimes.now();
            Function function = inputFunction();


            if(function.equals(Function.QUIT)){
                break;
            }
            if(function.equals(Function.ATTENDANCE_CHECK)){
                while (true){
                    try{
                        attendanceService.validateDate(nowDate);
                        Student student = attendanceService.findStudent(Input.inputStudentName());
                        TimeDto timeDto = TimeParser.parse(Input.inputTime());

                        Attendance attendance = attendanceService.addAttendance(nowDate,student,timeDto);

                        System.out.println(attendance);
                        break;
                    }catch (IllegalArgumentException e){
                        Output.printError(e.getMessage());
                        break;
                    }
                }
            } else if (function.equals(Function.EDIT_ATTENDANCE)) {
                Student student = editAttendanceService.findStudent(Input.inputToEditStudentName());
                int day  = Input.inputChangeDay();
                TimeDto timeDto = TimeParser.parse(Input.inputTime());


                String printout = editAttendanceService.informationPrintout(student,day,nowDate,timeDto);
                System.out.println(printout);

            } else if (function.equals(Function.PRINT_ATTENDANCE)) {
                Student student = printService.findAttendanceHistory(Input.inputStudentName());
                System.out.println(student);

            }else if(function.equals(Function.PRINT_RISK_STUDENT)){
                List<Student> students = riskPrintService.getRiskStudent();
                students.sort(Comparable::compareTo);

                for(Student student : students){
                    System.out.printf("- %s : 결석 %d회, 지각 %d회 (%s),"
                            + student.getName(),student.getAbsenceCount(),student.getLateCount(),
                            student.getRiskType().getName());
                }
            }
        }
    }




    public Function inputFunction(){
        while (true){
            try{
                return Function.find(Input.inputFunction());
            }catch (IllegalArgumentException e){
                Output.printError(e.getMessage());
            }
        }
    }

}
