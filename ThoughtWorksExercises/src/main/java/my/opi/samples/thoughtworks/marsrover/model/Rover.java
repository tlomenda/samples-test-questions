/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.marsrover.model;

/**
 * Rover type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class Rover {
    private static final int DEFAULT_MOVES = 1;
    private Direction direction = Direction.North;
    private SurfaceGrid surface;
    private Point currentPosition;

    /**
     * Return the direction.
     * 
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set the direction.
     * 
     * @param direction
     * the direction to set
     */
    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    /**
     * Set the currentPosition.
     * 
     * @param currentPosition
     * the currentPosition to set
     */
    public void setCurrentPosition(final Point currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Return the currentPosition.
     * 
     * @return the currentPosition
     */
    public Point getCurrentPosition() {
        return currentPosition;
    }

    public void startOnGrid(final SurfaceGrid grid, final Direction direction, final int x,
        final int y) {
        this.surface = grid;
        this.currentPosition = grid.getCoordinates(x, y);
        this.direction = direction;

        // Can I start on the grid at this point ??
        if (!grid.setCoordinates(x, y)) {
            this.currentPosition = null;
        }
    }

    public void rotateRight() {
        // Assume each rotate will be 90 degrees
        direction = direction.getDirection(90);

    }

    public void rotateLeft() {
        // Assume each rotate will be 90 degrees.
        // -90 degrees represents left
        direction = direction.getDirection(-90);
    }

    public void move() {
        move(DEFAULT_MOVES);
    }

    public void move(final int moves) {
        if (currentPosition == null) {
            throw new RuntimeException(
                "The rover is not even on the surface.  Most likely a rover is already occupying that space on the surface");
        }

        // Only move if the path is clear, otherwise do not do anything
        if (surface.isPathClear(currentPosition, direction, moves)) {
            switch (direction) {
            case North:
                moveUp(moves);
                break;
            case East:
                moveRight(moves);
                break;
            case South:
                moveDown(moves);
                break;
            case West:
                moveLeft(moves);
                break;
            default:
                break;
            }
        }
    }

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return currentPosition.getX() + " " + currentPosition.getY() + " " + direction.name();
    }

    private void moveLeft(final int moves) {
        Point moveToPoint = getPointToDriveTo(-(moves), 0);
        moveTo(moveToPoint);
    }

    private void moveRight(final int moves) {
        Point moveToPoint = getPointToDriveTo(moves, 0);
        moveTo(moveToPoint);
    }

    private void moveUp(final int moves) {
        Point moveToPoint = getPointToDriveTo(0, moves);
        moveTo(moveToPoint);
    }

    private void moveDown(final int moves) {
        Point moveToPoint = getPointToDriveTo(0, -(moves));
        moveTo(moveToPoint);
    }

    private void moveTo(final Point point) {
        if (point != null && surface.setCoordinates(point.getX(), point.getY())) {
            surface.leaveCoordinates(currentPosition.getX(), currentPosition.getY());
            currentPosition = point;

            drive();
        }
    }

    private void drive() {
        // This is reserved for the internal components
        // of a Rover that would result in it actually
        // being set in motion to the current point on the surface.
    }

    private Point getPointToDriveTo(final int xOffset, final int yOffset) {
        if (surface != null) {
            Point moveToPoint = new Point(currentPosition.getX() + (xOffset), currentPosition
                .getY()
                + (yOffset));
            return moveToPoint;
        }

        return currentPosition;
    }

}
