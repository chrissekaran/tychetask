package my.assignments.tyche.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by chrissekaran on 24/01/15.
 */
public class RoverTest {

    @Test
    public void roverCoordinatesChangeOnMove() throws Exception {
        Rover r = new Rover(Orientation.E, 5, 7);
        r.move(7, 9);
        assertThat(r.getXCoordinate(), is(6));
        assertThat(r.getYCoordinate(), is(7));

    }

    @Test
    public void roverOrientationChangeOnMove() throws Exception {
        Rover r = new Rover(Orientation.E, 5, 7);
        r.orientLeft();
        assertThat(r.getXCoordinate(), is(5));
        assertThat(r.getYCoordinate(), is(7));
        assertThat(r.getOrientation(), is(Orientation.N));
    }

    @Test
    public void roverCoordinatesDontChangeOnOrientationOnly() throws Exception {
        Rover r = new Rover(Orientation.E, 5, 7);
        r.orientLeft();
        r.orientLeft();
        
        assertThat(r.getXCoordinate(), is(5));
        assertThat(r.getYCoordinate(), is(7));
        assertThat(r.getOrientation(), is(Orientation.W));
        
    }

    @Test
    public void roverWontGoOutOfBoundaryMaxLimits() throws Exception {
        Rover r = new Rover(Orientation.E, 5, 7);
        r.move(5,7);
        assertThat(r.getXCoordinate(), is(5));
        assertThat(r.getYCoordinate(), is(7));
        r.orientLeft();
        r.move(5, 7);
        assertThat(r.getXCoordinate(), is(5));
        assertThat(r.getYCoordinate(), is(7));
    }

    @Test
    public void roverWontGoOutOfBoundaryMinLimits() throws Exception {
        Rover r = new Rover(Orientation.W, 0, 0);
        r.move(5,7);
        r.move(5, 7);
        assertThat(r.getXCoordinate(), is(0));
        assertThat(r.getYCoordinate(), is(0));
        r.orientLeft();
        r.move(5, 7);
        r.move(5, 7);
        assertThat(r.getXCoordinate(), is(0));
        assertThat(r.getYCoordinate(), is(0));
    }

    @Test
    public void validateToString() throws Exception {
        Rover r = new Rover(Orientation.E, 20, 30);
        assertThat(r.toString(), is("20 30 E"));

    }
}
