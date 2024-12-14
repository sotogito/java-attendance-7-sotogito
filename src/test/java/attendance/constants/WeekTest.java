package attendance.constants;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class WeekTest {

    @Test
    void week_반환_확인(){
        LocalDateTime localDateTime = LocalDate.of(2024,12,12).atTime(00,11);
        Week week = Week.find(localDateTime);
        assertEquals(week.getKorean(),"목요일");
    }

    @Test
    void 크리스마스_반환_확인(){
        LocalDateTime localDateTime = LocalDate.of(2024,12,25).atTime(00,11);
        Week week = Week.find(localDateTime);
        assertTrue(week.isWeekend());
    }

}