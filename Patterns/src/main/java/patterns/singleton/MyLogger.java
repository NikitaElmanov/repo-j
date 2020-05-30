package patterns.singleton;

public class MyLogger {
    private static MyLogger myLogger;
    private static String message = "";

    public static MyLogger getLogger() {
        if (myLogger == null) {
            myLogger = new MyLogger();
        }
        return myLogger;
    }

    public void writeIntoLogger(String newStr) {
        message += newStr + "\r\n";
    }

    public String showLoggerInfo() {
        System.out.println(message);
        return message;
    }

    private MyLogger() {}
}
