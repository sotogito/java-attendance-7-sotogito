package attendance.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Crews를 싱글톤으로 관리해서 테스트코드에 통과하지 못함
 */
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
