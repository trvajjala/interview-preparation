package pattern.behavioral.chain.of.responsibility;

public class InfoLogger extends AbstractLogger {

    public InfoLogger(int level) {
        this.level = level;
    }

    @Override
    void write(String message) {

        System.out.println("INFO >> " + message);
    }
}
