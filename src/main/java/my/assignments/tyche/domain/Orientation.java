package my.assignments.tyche.domain;

/**
 * Orientation gives us the heading of the robot and tells us which direction it is pointing to.
 * The constructor takes in two integer values and these are the cardinal values of the left and right Orientation's of the specific Orientation.
 * Using enum cardinals like this would not be hugely extendable if there are say Orientations for every degree of angle but in a small case like this seems suitable
 *
 * Created by chrissekaran on 24/01/15.
 */
public enum Orientation {
           //Cardinal values of Enums
    N(3,2),//0
    S(2,3),//1
    E(0,1),//2
    W(1,0);//3

    private int left;
    private int right;

    Orientation(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
    
}