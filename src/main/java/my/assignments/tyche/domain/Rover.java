package my.assignments.tyche.domain;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Created by chrissekaran on 24/01/15.
 */
@NotThreadSafe
public class Rover {
    
    private Orientation orientation;

    private int xCoordinate;
    
    private int yCoordinate;

    public Rover(Orientation orientation, 
                 int xCoordinate, 
                 int yCoordinate) {
        this.orientation = orientation;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }
    
    /*
     * Provides an operation on the rover by passing the boundary Coordinates that the rover cannot go beyond.
     * The default coordinates are assumed to be (0,0) as the plateau has assumed to be on the top right Quadrant by design.
     * Responsibility of moving the rover is in the Rover domain object. It is definitely arguable as to whether this functionality 
     * resides here. Considering we are not using the domain for any other relational mapping I find it reasonable to be here as otherwise
     * this domain may become a bit anaemic. {@see <a href="http://en.wikipedia.org/wiki/Anemic_domain_model"/>}
     *
     */
    public void move(int xBoundary, int yBoundary)  {
        
        if(orientation==Orientation.N && (yCoordinate < yBoundary)) {
            yCoordinate++;
        } else if(orientation == Orientation.S && (yCoordinate > 0)) {
            yCoordinate--;   
        } else if(orientation == Orientation.E && (xCoordinate < xBoundary)) {
            xCoordinate++;
        } else if(orientation==Orientation.W && (xCoordinate > 0))  {//If Orientation is not either of N,S or E, then it has to be W and we need not check explicitly
            xCoordinate--;
        }
    }

    
    public void orientRight()   {
        int r = orientation.getRight();
        orientation = Orientation.values()[r];
    } 
    
    public void orientLeft()    {
        int l = orientation.getLeft();
        orientation = Orientation.values()[l];
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    /**
     * This is used to print out the rover coordinates and heading in a fashion that is in an acceptable format at the end of navigation sequence.
     * @return
     */
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(xCoordinate).append(" ");
        sb.append(yCoordinate).append(" ");
        sb.append(orientation);
        return sb.toString();
    }
}
