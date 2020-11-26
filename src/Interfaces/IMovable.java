package Interfaces;

/**
 * Specifies functionality of an object capable of moving and turning left or right.
 */
public interface IMovable {
    /**
     * Moves the object.
     */
    void move();

    /**
     * Makes the object turn left.
     */
    void turnLeft();

    /**
     * Makes te object turn right.
     */
    void turnRight();
}
