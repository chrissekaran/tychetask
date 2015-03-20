package my.assignments.tyche;

import my.assignments.tyche.domain.Rover;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by chrissekaran on 24/01/15.
 */
public class NavigationServiceTest {

    private NavigationService navigationService;

    private int xMax=5,  yMax = 5;

    @Before
    public void setUp() throws Exception {
        navigationService = new NavigationServiceImpl(xMax, yMax);
    }

    @Test
    public void navigationInvokesRoverForEveryLetterOfCommand() throws Exception {
        Rover roverMock = mock(Rover.class);
        String navigationCommand = "LMRLMRMMR";
        navigationService.navigateRover(roverMock, navigationCommand);
        verify(roverMock, times(3)).orientRight();
        verify(roverMock, times(2)).orientLeft();
        verify(roverMock, times(4)).move(xMax, yMax);
    }

    @Test
    public void navigateRoverInvokesRoverOperationRegardlessOfRoverPosition() throws Exception {
        Rover roverMock = mock(Rover.class);
        String navigationCommand = "MMMMMMMMMMMMM";
        navigationService.navigateRover(roverMock, navigationCommand);
        verify(roverMock, times(navigationCommand.length())).move(xMax, yMax);
    }


}
