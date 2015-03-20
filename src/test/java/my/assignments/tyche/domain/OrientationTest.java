package my.assignments.tyche.domain;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by chrissekaran on 24/01/15.
 */
public class OrientationTest {

    @Test
    public void orientationWhenNorthShowsAppropriateLeftAndRight() throws Exception {
        Orientation orientation = Orientation.N;
        Orientation leftOfNorth = Orientation.values()[orientation.getLeft()];
        Orientation rightOfNorth = Orientation.values()[orientation.getRight()];
        assertThat(leftOfNorth, is(Orientation.W));
        assertThat(rightOfNorth, is(Orientation.E));
    }
}
