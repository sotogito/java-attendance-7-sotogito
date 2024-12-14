package attendance.domain;

import attendance.domain.student.Attendance;
import attendance.domain.student.Student;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AttendancesReader {
    /**
     * 이름
     * 시간 - LocalDataTime
     * 요일 - WeekEnum 에서 Week 벼누 가져오기 -
     *  월요일과 화~금은 출석 시간이 다르다.
     *
     * 가져온 LocalDataTime으로 요일을 가져오고 월요일인 경우 Attendance에 출석 유횽을 업뎅이트한다.
     */

    public List<Student> read(String path){
        //String path = "src/main/resources/attendances.csv";
        List<Student> students = new ArrayList<>(); //데이터 저장 리스트

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;
            br.readLine(); //첫번째 헤더라인 건너뛰기
            while ((line = br.readLine()) != null) {

                String[] splitLine = line.split(",");

                String name = splitLine[0];
                LocalDateTime localDateTime = LocalDateTime.parse(
                        splitLine[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")).withSecond(0);




                Student student = new Student(name);
                if(students.contains(student)){
                    for(Student sameStudent : students){
                        if(sameStudent.equals(student)){
                            sameStudent.addAttendance(new Attendance(localDateTime));
                        }
                    }
                    continue;
                }
                student.addAttendance(new Attendance(localDateTime));
                students.add(student);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("파일 읽기 오류");
        }
        System.out.println(students.size());
        return students;
    }

}
