package attendance.domain.dto;

public class TimeParser {

    public static TimeDto parse(String input){
        input = input.trim();
        if(input.isEmpty()){
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }

        String[] split = input.split(":");

        try {
            int hour = Integer.parseInt(split[0].trim());
            int min = Integer.parseInt(split[1].trim());

            if(hour <0 || hour > 12){
                throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
            } else if (min < 0 || min > 60) {
                throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
            }

            return new TimeDto(hour,min);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("잘못된 형식을 입력하였습니다.");
        }
    }
}
