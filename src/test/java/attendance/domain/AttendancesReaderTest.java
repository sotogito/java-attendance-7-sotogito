package attendance.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AttendancesReaderTest {

    @Test
    void 출력확인(){
        AttendancesReader reader = new AttendancesReader();
        reader.read("src/main/resources/attendances.csv");
    }

}