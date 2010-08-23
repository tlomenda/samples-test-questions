/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.marsrover.model;

/**
 * Dimension type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class Dimension {
    private int height;
    private int width;

    /**
     * Constructor.
     * 
     * @param height
     * @param width
     */
    public Dimension(final int width, final int height) {
        super();
        this.height = height;
        this.width = width;
    }

    /**
     * Return the height.
     * 
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Set the height.
     * 
     * @param height
     * the height to set
     */
    public void setHeight(final int height) {
        this.height = height;
    }

    /**
     * Return the width.
     * 
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the width.
     * 
     * @param width
     * the width to set
     */
    public void setWidth(final int width) {
        this.width = width;
    }

}
