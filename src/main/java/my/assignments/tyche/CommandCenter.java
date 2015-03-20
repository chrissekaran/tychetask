package my.assignments.tyche;

import com.google.common.annotations.VisibleForTesting;
import my.assignments.tyche.domain.Orientation;
import my.assignments.tyche.domain.Rover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chrissekaran on 24/01/15.
 */
public class CommandCenter {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(CommandCenter.class);

    private static final Pattern quitProgramPattern = Pattern.compile(":q");
    
    private static final Pattern maxCoordinatesPattern = Pattern.compile("\\d*\\s\\d*");

    private static final Pattern roverCoordinatesPattern = Pattern.compile("\\d*\\s\\d*\\s[N,S,E,W]");
    
    private static final Pattern roverMovementCommandPattern= Pattern.compile("[M,L,R]*");

    private int maxXCoordinate;
    
    private int maxYCoordinate;
    
    private LineScanner lineScanner = new LineScanner();
    
    private volatile boolean livestate = true;
    
    static class LineScanner    {
        private static final Scanner SCAN = new Scanner(System.in);
        String fetchUserInput()    {
            return SCAN.nextLine();
        }
    }

    protected static CommandCenter commandSession() {
        return new CommandCenter();
    }

    @VisibleForTesting
    void start() {
        NavigationService navigationService = scanPlateauMaxCoordinates();
        do {
            initializeAndNavigateRover(navigationService);
        } while(livestate);
    }
    
    @VisibleForTesting
    NavigationService scanPlateauMaxCoordinates() {
        final String next = lineScanner.fetchUserInput();
        Matcher m = maxCoordinatesPattern.matcher(next);
        if(m.matches()) {
            String[] maxCoordinates = next.split(" ");
            maxXCoordinate = Integer.parseInt(maxCoordinates[0]);
            maxYCoordinate = Integer.parseInt(maxCoordinates[1]);
            //A different NavigationService for different plateau's
            return new NavigationServiceImpl(maxXCoordinate, maxYCoordinate);
        } else  if(quitProgramPattern.matcher(next).matches()) {
            livestate = false;
            return null;
        } else {
            LOGGER.info("Please enter two positive numbers separated by a space");
            return scanPlateauMaxCoordinates();
        }
    }

    @VisibleForTesting
    void initializeAndNavigateRover(NavigationService navigationService) {
        final String line = lineScanner.fetchUserInput();
        Matcher matcherXY = roverCoordinatesPattern.matcher(line);
        if(matcherXY.matches()) {
            String[] coordinates = line.split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            //Instantiate Rover only if coordinates are within boundary
            if((x <= maxXCoordinate) && (y <= maxYCoordinate))  {
                Orientation o = Orientation.valueOf(coordinates[2]);
                Rover rover = new Rover(o, x, y);
                navigateRover(rover, navigationService);
            }  else  {
                LOGGER.error("Rover coordinates should be within plateau region. Please try again");
            }
        } else if(quitProgramPattern.matcher(line).matches()) {
            livestate = false;
        } else {
            LOGGER.error("Please enter rover xCoordinate, yCoordinate and Orientation[N,S,E,W] separated by spaces");
            initializeAndNavigateRover(navigationService);
        }
    }

    @VisibleForTesting
    void navigateRover(Rover rover, NavigationService navigationService) {
        final String cmd = lineScanner.fetchUserInput();
        Matcher matcherM = roverMovementCommandPattern.matcher(cmd);
        if(matcherM.matches())  {
            navigationService.navigateRover(rover, cmd);
            LOGGER.info(rover.toString());
        } else  {
            LOGGER.error("Please enter rover navigation command using letters L,R,M without spaces");
            navigateRover(rover, navigationService);
        }
    }

    public void setLineScanner(LineScanner lineScanner) {
        this.lineScanner = lineScanner;
    }

    
}
