package pattern.behavioral.chain.of.responsibility;

public class DebugLogger extends AbstractLogger {

    public DebugLogger(int level) {
        this.level = level;
    }

    @Override
    void write(String message) {
        System.err.println("DEBUG >> " + message);
    }
}
