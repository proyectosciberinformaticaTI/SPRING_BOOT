package pe.sde.cinet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SLF4JExample {
	final static Logger logger = LoggerFactory.getLogger(SLF4JExample.class);
	 
    public static void main(String[] args) {
        logger.debug("Debug log message");
        logger.info("Info log message");
        logger.error("Error log message");
        
        String variable = "Hello John";
        logger.debug("Printing variable value: " + variable);
    }
}
