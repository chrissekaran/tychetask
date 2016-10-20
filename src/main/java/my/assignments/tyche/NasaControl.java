package my.assignments.tyche;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Main class of this application that starts a session of rover navigation commands
 * 
 * This program keeps accepting rover orientation commands indefinitely as in its current form that is what is 
 * expected so its not the best way of shutting down this program without terminating it.
 *
 * Created by chrissekaran on 23/01/15.
 */
public class NasaControl {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NasaControl.class);

    public static void main(String[] args) {
    	LOGGER.info("Welcome to Nasa Control centre where we control the Mars rovers! \n\nPlease start by entering upper right coordinates of plateau.");
        CommandCenter.commandSession().start();
    }

}
