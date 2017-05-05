package pattern.behavioral.chain.of.responsibility;

/**
 * group of objects that are expected to solve the problem among them.
 *
 * @author ThirupathiReddy V
 *
 */
public class CORExample {

    public static void main(String[] args) {

        final AbstractLogger logger = AbstractLogger.getLogger();

        logger.log(AbstractLogger.DEBUG, "debug");

        logger.log(AbstractLogger.INFO, "info");

        logger.log(AbstractLogger.ERROR, "error");

    }
}
