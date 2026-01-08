package attendance.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

public class Crew {
    private final String nickname;
    private final List<Attendance> attendances = new ArrayList<>();

    public Crew(String nickname) {
        this.nickname = nickname;
    }

    public void addAttendance(Attendance attendance) {
        attendances.add(attendance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Crew crew = (Crew) o;
        return Objects.equals(nickname, crew.nickname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickname);
    }

    @Override
    public String toString() {
        Collections.sort(attendances);

        StringJoiner sj = new StringJoiner("\n");
        sj.add(nickname);
        attendances.forEach(attendance -> sj.add(attendance.toString()));
        return sj.toString();
    }

}
