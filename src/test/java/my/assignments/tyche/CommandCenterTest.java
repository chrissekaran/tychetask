package my.assignments.tyche;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * Created by chrissekaran on 24/01/15.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommandCenterTest {

    @InjectMocks
    private CommandCenter commandCenter;
    @Mock
    private CommandCenter.LineScanner lineScanner;

    @Before
    public void setUp() throws Exception {
        commandCenter = new CommandCenter();
        commandCenter.setLineScanner(lineScanner);
    }

    @Test
    public void startCommandSessionScansUserInput() throws Exception {
        when(lineScanner.fetchUserInput())
                .thenReturn("12 23")
                .thenReturn("3 4 N")
                .thenReturn("MMRLLM")
                .thenReturn("33 22")
                .thenReturn(":q");
        commandCenter.start();

        verify(lineScanner, times(5)).fetchUserInput();
    }

    @Test
    public void invalidPlateauCoordinatesTriggersRescanWhenPatternDoesntMatch() throws Exception {
        when(lineScanner.fetchUserInput())
                .thenReturn("12 23 ")
                .thenReturn(" 3 4")
                .thenReturn("MMRLLM")
                .thenReturn("23 22");
                //.thenReturn(":q");
        NavigationService ns = commandCenter.scanPlateauMaxCoordinates();
        verify(lineScanner, atLeast(4)).fetchUserInput();
        assertNotNull(ns);
    }

    @Test
    public void quitReturnsNullBeforeExit() throws Exception {
        when(lineScanner.fetchUserInput())
                .thenReturn("12 23 ")
                .thenReturn(" 3 4")
                .thenReturn("MMRLLM")
                .thenReturn(":q");
        
        NavigationService ns = commandCenter.scanPlateauMaxCoordinates();
        verify(lineScanner, atLeast(4)).fetchUserInput();
        assertNull(ns);
    }

    @Test
    public void differentNavigationServiceInstanceForEveryPlateau() throws Exception {
        when(lineScanner.fetchUserInput())
                .thenReturn("12 23 ")
                .thenReturn("3 4")
                .thenReturn("5 55")
                .thenReturn(":q");

        NavigationService ns1 = commandCenter.scanPlateauMaxCoordinates();
        NavigationService ns2 = commandCenter.scanPlateauMaxCoordinates();
        NavigationService ns3 = commandCenter.scanPlateauMaxCoordinates();
        
        assertNotSame(ns1, ns2);
        assertNotSame(ns2, ns3);
    }
    
    @Test
    public void initializeAndNavigateRoverOnlyRetriesOnBadCoordinatesPattern() throws Exception {
        NavigationService navigationServiceMock = mock(NavigationService.class);
        when(lineScanner.fetchUserInput())
                .thenReturn("4 5 S");
        commandCenter.initializeAndNavigateRover(navigationServiceMock);

        verify(lineScanner).fetchUserInput();
    }
    
}
