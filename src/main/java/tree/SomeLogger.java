package tree;

import java.util.logging.Logger;


public class SomeLogger {

    private static Logger logger = Logger.getLogger("logger");

    public static void log(String message) {
        logger.info(message);
    }

    private SomeLogger() {}
}
