package attendance.view;

public class ExceptionHandler {

    private final static String EXCEPTION_FORMAT = "\n[ERROR] %s\n\n";

    public static void read(IllegalArgumentException e) {
        throw new IllegalArgumentException(String.format(EXCEPTION_FORMAT, e.getMessage()));
    }

}
