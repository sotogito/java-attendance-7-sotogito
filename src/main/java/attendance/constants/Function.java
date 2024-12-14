package attendance.constants;

/**
 * 1. 출석 확인
 * 2. 출석 수정
 * 3. 크루별 출석 기록 확인
 * 4. 제적 위험자 확인
 * Q. 종료
 * 4
 */
public enum Function {
    ATTENDANCE_CHECK("1","출석 확인"),
    EDIT_ATTENDANCE("2","출석 수정"),
    PRINT_ATTENDANCE("3","크루별 출석 기록 확인"),
    PRINT_RISK_STUDENT("4","제적 위험자 확인"),
    QUIT("Q","종료");

    private final String value;
    private final String name;

    Function(String value,String name) {
        this.name = name;
        this.value = value;
    }

    public static Function find(String input){
        for(Function function : Function.values()){
            if(function.value.equals(input)){
                return function;
            }
        }
        throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
    }

    @Override
    public String toString(){
        return String.format("%s. %s\n",value,name);
    }
}
