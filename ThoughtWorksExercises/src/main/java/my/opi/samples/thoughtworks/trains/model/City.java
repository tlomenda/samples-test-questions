/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.trains.model;

/**
 * City type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class City {
    private String name;

    /**
     * Constructor.
     * 
     * @param name
     */
    public City(final String name) {
        super();
        this.name = name;
    }

    /**
     * Return the name.
     * 
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name.
     * 
     * @param name
     * the name to set
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * See Overridden Method.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return name;
    }

}
