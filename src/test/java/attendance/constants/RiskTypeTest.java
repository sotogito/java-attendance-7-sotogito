package attendance.constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RiskTypeTest {

    @Test
    void 반환_확인(){
        int absenceCount = 6;
        assertEquals(RiskType.find(absenceCount),RiskType.END);
    }

}