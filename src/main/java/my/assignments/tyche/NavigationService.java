package my.assignments.tyche;

import my.assignments.tyche.domain.NavigationCommand;
import my.assignments.tyche.domain.Rover;

/**
 * Created by chrissekaran on 24/01/15.
 */
public interface NavigationService {
    
    void navigateRover(Rover rover, NavigationCommand navigationCommand);

    default void navigateRover(Rover rover, String navigationCommand)   {
        for(char c: navigationCommand.toCharArray())    {
            navigateRover(rover, NavigationCommand.valueOf(Character.toString(c)));
        }
    }
    
}
