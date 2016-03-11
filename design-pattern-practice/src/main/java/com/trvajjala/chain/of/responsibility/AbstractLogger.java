package com.trvajjala.chain.of.responsibility;

public abstract class AbstractLogger {

    public static int DEBUG = 1;
    public static int INFO = 2;
    public static int ERROR = 3;

    public static AbstractLogger getLogger() {

        final AbstractLogger logger = new DebugLogger(AbstractLogger.DEBUG);

        final AbstractLogger infoLogger = new InfoLogger(AbstractLogger.INFO);

        final AbstractLogger errorLogger = new ErrorLogger(AbstractLogger.ERROR);

        infoLogger.nextLogger(errorLogger);

        logger.nextLogger(infoLogger);

        return logger;
    }

    protected int level;

    protected AbstractLogger nextLogger;

    public void nextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    public void log(int level, String message) {

        if (this.level >= level) {
            write(message);
        } else if (nextLogger != null) {
            nextLogger.log(level, message);
        }

    }

    abstract void write(String message);

}
