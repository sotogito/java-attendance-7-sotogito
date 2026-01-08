package attendance.domain;

import java.util.ArrayList;
import java.util.List;

public enum Crews {
    CREWS;

    private final List<Crew> crews = new ArrayList<>();

    public void addCrews(List<Crew> crews) {
        this.crews.addAll(crews);
    }

    public List<Crew> getCrews() {
        return crews;
    }

    public List<Crew> getRiskOfExpulsion() {
        List<Crew> riskOfExpulsionCrews = new ArrayList<>();

        for (ExpulsionState expulsionState : ExpulsionState.getSorted()) {
            if (ExpulsionState.해당안됨 == expulsionState) {
                continue;
            }
            for (Crew crew : crews) {
                if (expulsionState == crew.getExpulsionState()) {
                    riskOfExpulsionCrews.add(crew);
                }
            }
        }
        return riskOfExpulsionCrews;
    }


    public Crew findByNickname(String nickname) {
        for (Crew crew : crews) {
            if (crew.isSameNickname(nickname)) {
                return crew;
            }
        }
        throw new IllegalArgumentException("등록되지 않은 닉네임입니다.");
    }

}
