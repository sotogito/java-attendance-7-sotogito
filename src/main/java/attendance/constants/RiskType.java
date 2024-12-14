package attendance.constants;

public enum RiskType {
    CAUTION("경고",2),
    INTERVIEW("면담",3),
    END("제적",5),
    NOTHING("낫싱",0);

    private final String name;
    private final int absenceCount;

    RiskType(String name,int absenceCount) {
        this.absenceCount = absenceCount;
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static RiskType find(int absenceCount){
        if(absenceCount > END.absenceCount){
            return END;
        } else if (absenceCount >= INTERVIEW.absenceCount) {
            return INTERVIEW;
        } else if (absenceCount >= CAUTION.absenceCount) {
            return CAUTION;
        }
        return NOTHING;
    }


}
