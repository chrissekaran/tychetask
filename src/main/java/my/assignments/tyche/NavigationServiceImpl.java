package my.assignments.tyche;

import my.assignments.tyche.domain.NavigationCommand;
import my.assignments.tyche.domain.Rover;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Simple service interface that is responsible for navigating a Robot and calling specific actions on a {@code Robot} or {code @Robot}'s
 *
 * Created by chrissekaran on 24/01/15.
 */
public class NavigationServiceImpl implements NavigationService {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(NavigationServiceImpl.class);
    
    private int xBoundary;
    
    private int yBoundary;

    public NavigationServiceImpl(int xBoundary, int yBoundary) {
        this.xBoundary = xBoundary;
        this.yBoundary = yBoundary;
    }

    public void navigateRover(Rover rover, NavigationCommand navigationCommand) {
        switch (navigationCommand)  {
            case M: moveRover(rover); break;
            case L: turnRoverLeft(rover); break;
            case R: turnRoverRight(rover); break;
            default: LOGGER.error("Invalid Navigation command -  {}. Please try again.", navigationCommand);
        }
        LOGGER.debug("Last command {} -> {}", navigationCommand, String.valueOf(rover));
    }

    private void turnRoverRight(Rover rover) {
        rover.orientRight();
    }

    private void turnRoverLeft(Rover rover) {
        rover.orientLeft();
    }

    private void moveRover(Rover rover) {
        rover.move(xBoundary, yBoundary);
    }

}
