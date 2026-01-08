package attendance.domain;

public enum ExpulsionState {
    경고("경고", 2),
    면담("면담", 3),
    제적("제적", 6),
    해당안됨("해당안됨",0);

    private final String value;
    private final int minAbsence;

    ExpulsionState(String value, int minAbsence) {
        this.value = value;
        this.minAbsence = minAbsence;
    }

    public String getValue() {
        return value;
    }

    public static ExpulsionState findByAbsenceCount(int count) {
        if (count >= ExpulsionState.제적.minAbsence) {
            return ExpulsionState.제적;
        }
        if (count >= ExpulsionState.면담.minAbsence) {
            return ExpulsionState.면담;
        }
        if (count >= ExpulsionState.경고.minAbsence) {
            return ExpulsionState.경고;
        }
        return ExpulsionState.해당안됨;
    }

}
