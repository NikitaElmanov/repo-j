package patterns.generative.singleton;

public class Runner {
    public static void main(String[] args) {
        MyLogger.getLogger().writeIntoLogger("first ...");
        MyLogger.getLogger().writeIntoLogger("second ...");
        MyLogger.getLogger().writeIntoLogger("Third ...");

        MyLogger.getLogger().showLoggerInfo();
    }
}
