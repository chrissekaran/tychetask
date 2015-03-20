package my.assignments.tyche;

import my.assignments.tyche.domain.Rover;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Created by chrissekaran on 24/01/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class NavigationServiceImplTest {
    
    private NavigationService navigationService;
    
    private int xMax=5,  yMax = 5;


    @Before
    public void setUp() throws Exception {
        navigationService = new NavigationServiceImpl(xMax, yMax);
    }

    @Test
    public void navigateLeftInvokesOrientLeftOnRover() throws Exception {
        Rover roverMock = mock(Rover.class);
        String navigationCommand = "L";
        
        navigationService.navigateRover(roverMock, navigationCommand);

        verify(roverMock, times(1)).orientLeft();
    }

    @Test
    public void navigateRightInvokesOrientRightOnRover() throws Exception {
        Rover roverMock = mock(Rover.class);
        String navigationCommand = "R";

        navigationService.navigateRover(roverMock, navigationCommand);
        verify(roverMock, times(1)).orientRight();
    }

}