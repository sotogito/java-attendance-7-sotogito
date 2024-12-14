package attendance.domain.student;

import attendance.constants.AttendanceType;
import attendance.constants.Week;
import attendance.domain.dto.TimeDto;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.jupiter.api.Test;

public class Attendance implements Comparable<Attendance>{
    private LocalDateTime dateTime;
    private Week dayOfTheWeek;
    private AttendanceType attendanceType;

    public Attendance(LocalDateTime dateTime) {
        /**
         * 여기서 dayOfTheWeek하고 attendanceType세팅
         * 만약에 dayOfTheWeek이 주말인 경우 여기서 예외 ㅈ던지기
         */

        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        Week week = Week.find(dateTime);

        if(week.isWeekend()){
            throw new IllegalArgumentException(String.format("%d월 %d일 %s은 등교일이 아닙니다.",
                    month,day,week.getKorean()));
        }

        attendanceType = AttendanceType.find(dateTime);
        dayOfTheWeek = Week.find(dateTime);
        this.dateTime = dateTime;
    }

    public AttendanceType getAttendanceType(){
        return attendanceType;
    }

    public boolean isSameDay(int day){
        int attendanceDay = dateTime.getDayOfMonth();
        if(day==attendanceDay){
            return true;
        }
        return false;
    }

    public Attendance cloneOldAttendance(){
        return new Attendance(dateTime);
    }


    //note 새로 넣어야함
    public void changeTime(int newHour,int newMin){
        dateTime = dateTime.withHour(newHour).withMinute(newMin);
    }

    public LocalDateTime getNewDateTime(int newHour,int newMin){
        //LocalDateTime newDatetime = dateTime.withHour(newHour).withMinute(newMin);
        LocalDateTime newDatetime = LocalDateTime.of(dateTime.getYear(),dateTime.getMonth(),dateTime.getDayOfMonth(),
                newHour,newMin);
        return newDatetime;
    }

    public String getTimeAndAttendanceTypePrintout(){
        int hour = dateTime.getHour();
        int min = dateTime.getMinute();
        if(min < 10 && hour < 10){
            return String.format("0%d:0%d (%s)\n",dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dayOfTheWeek.getKorean(),
                    dateTime.getHour(),
                    dateTime.getMinute(),
                    attendanceType.getName());
        }

        if(hour < 10){
            return String.format("0%d:%d (%s)\n",dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dayOfTheWeek.getKorean(),
                    dateTime.getHour(),
                    dateTime.getMinute(),
                    attendanceType.getName());
        }
        if(min < 10){
            return String.format("%d:0%d (%s)\n",dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dayOfTheWeek.getKorean(),
                    dateTime.getHour(),
                    dateTime.getMinute(),
                    attendanceType.getName());
        }
        return String.format("%d:%d (%s)",
                dateTime.getHour(),
                dateTime.getMinute(),
                attendanceType.getName());
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("mm:ss");
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();

        int hour = dateTime.getHour();
        int min = dateTime.getMinute();
        /*


        String printoutHour = String.valueOf(hour);
        String printoutMin =String.valueOf(min);
        String printoutMonth =String.valueOf(month);
        String printoutDay = String.valueOf(day);

        if(hour<10){
            printoutHour = "0"+hour;
        }
        if(min < 10){
            printoutHour = "0"+min;
        }
        if(month < 10){
            printoutMonth = "0"+month;
        }
        if(day<10){
            printoutDay = "0"+day;
        }

        if(attendanceType.equals(AttendanceType.ABSENCE)){
            return String.format("%s월 %s일 %s --:-- (%s)",
                    printoutMonth,
                    printoutDay,
                    dayOfTheWeek.getKorean(),
                    attendanceType.getName());
        }

        return String.format("%s월 %s일 %s %s:%s (%s)",
                printoutMonth,
                printoutDay,
                dayOfTheWeek.getKorean(),
                printoutHour,
                printoutMin,
                attendanceType.getName());

         */


        if(attendanceType.equals(AttendanceType.ABSENCE)){
            return String.format("%s월 %s일 %s --:-- (%s)",
                    dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dayOfTheWeek.getKorean(),
                    attendanceType.getName());
        }


        if(min < 10 && hour < 10){
            return String.format("%d월 %d일 %s 0%d:0%d (%s)",
                    dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dayOfTheWeek.getKorean(),
                    dateTime.getHour(),
                    dateTime.getMinute(),
                    attendanceType.getName());
        }

        if(hour < 10){
            return String.format("%d월 %d일 %s 0%d:%d (%s)",dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dayOfTheWeek.getKorean(),
                    dateTime.getHour(),
                    dateTime.getMinute(),
                    attendanceType.getName());
        }
        if(min < 10){
            return String.format("%d월 %d일 %s %d:0%d (%s)",dateTime.getMonthValue(),
                    dateTime.getDayOfMonth(),
                    dayOfTheWeek.getKorean(),
                    dateTime.getHour(),
                    dateTime.getMinute(),
                    attendanceType.getName());
        }
        return String.format("%d월 %d일 %s %d:%d (%s)",dateTime.getMonthValue(),
                dateTime.getDayOfMonth(),
                dayOfTheWeek.getKorean(),
                dateTime.getHour(),
                dateTime.getMinute(),
                attendanceType.getName());


    }

    @Override
    public int compareTo(Attendance o) {
        return this.dateTime.compareTo(o.dateTime);
    }

}
