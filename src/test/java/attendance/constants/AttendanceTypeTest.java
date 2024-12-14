package attendance.constants;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class AttendanceTypeTest {

    @Test
    void 출석_타입_반환_확인(){
        LocalDateTime localDateTime = LocalDate.of(2024,12,10).atTime(10,6);
        AttendanceType attendanceType = AttendanceType.find(localDateTime);
        assertEquals(attendanceType,AttendanceType.LATE);

    }

}