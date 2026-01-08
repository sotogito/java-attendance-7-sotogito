package attendance.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class AttendanceTypeTest {

    @Test
    void 출석확인() {
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        LocalTime localTime = LocalTime.of(13, 0);

        assertThat(AttendanceType.find(dayOfWeek, localTime))
                .isEqualTo(AttendanceType.출석);
    }

    @Test
    void 지각확인() {
        DayOfWeek dayOfWeek = DayOfWeek.MONDAY;
        LocalTime localTime = LocalTime.of(13, 6);

        assertThat(AttendanceType.find(dayOfWeek, localTime))
                .isEqualTo(AttendanceType.지각);
    }

    @Test
    void 결석확인() {
        DayOfWeek dayOfWeek = DayOfWeek.TUESDAY;
        LocalTime localTime = LocalTime.of(10, 50);

        assertThat(AttendanceType.find(dayOfWeek, localTime))
                .isEqualTo(AttendanceType.결석);
    }

}
