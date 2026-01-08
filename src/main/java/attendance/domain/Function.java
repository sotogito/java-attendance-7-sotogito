package attendance.domain;

import java.util.Objects;

public enum Function {
    출석_확인("1", "출석 확인"),
    출석_수정("2", "출석 수정"),
    크루별_출석_기록_확인("3", "크루별 출석 기록 확인"),
    제적_위험자_확인("4", "제적 위험자 확인"),
    종료("Q", "종료");

    private final String value;
    private final String korean;

    Function(String value, String korean) {
        this.value = value;
        this.korean = korean;
    }

    public static Function find(String value) {
        for (Function function : Function.values()) {
            if (Objects.equals(function.value, value)) {
                return function;
            }
        }

        throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
    }

    @Override
    public String toString() {
        return String.format("%s. %s", value, korean);
    }

}
