package attendance.view;

import camp.nextstep.edu.missionutils.Console;

public class Reader {

    private final String input;

    private Reader(String input) {
        this.input = input;
    }

    public static Reader read() {
        try {
            String input = Console.readLine().trim();
            if (input.isEmpty()) {
                throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
            }
            return new Reader(input);
        } catch (NullPointerException e) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }

    public String getInput() {
        return input;
    }

}
