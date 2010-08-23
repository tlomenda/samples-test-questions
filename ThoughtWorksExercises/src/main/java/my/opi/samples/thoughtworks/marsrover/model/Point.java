/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.marsrover.model;

/**
 * GridCoordinate type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class Point {

    private int x;
    private int y;

    /**
     * Return the x.
     * 
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Constructor.
     * 
     * @param x
     * @param y
     */
    public Point(final int x, final int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Set the x.
     * 
     * @param x
     * the x to set
     */
    public void setX(final int x) {
        this.x = x;
    }

    /**
     * Return the y.
     * 
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Set the y.
     * 
     * @param y
     * the y to set
     */
    public void setY(final int y) {
        this.y = y;
    }

    /**
     * See Overridden Method.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return Point.coordinatesAsString(x, y);
    }

    public static String coordinatesAsString(final int x, final int y) {
        return new StringBuilder().append("(").append(x).append(",").append(y).append(")")
            .toString();
    }

}
