/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.trains.model;

/**
 * Route type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class Route implements Comparable<Route> {

    private City from;
    private City to;
    private int distance;

    /**
     * Constructor.
     * 
     * @param from
     * @param to
     * @param distance
     */
    public Route(final City from, final City to, final int distance) {
        super();
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    /**
     * Return the from.
     * 
     * @return the from
     */
    public City getFrom() {
        return from;
    }

    /**
     * Set the from.
     * 
     * @param from
     * the from to set
     */
    public void setFrom(final City from) {
        this.from = from;
    }

    /**
     * Return the to.
     * 
     * @return the to
     */
    public City getTo() {
        return to;
    }

    /**
     * Set the to.
     * 
     * @param to
     * the to to set
     */
    public void setTo(final City to) {
        this.to = to;
    }

    /**
     * Return the distance.
     * 
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Set the distance.
     * 
     * @param distance
     * the distance to set
     */
    public void setDistance(final int distance) {
        this.distance = distance;
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    public int hashCode() {
        return this.to.hashCode() + this.from.hashCode() + new Integer(this.distance).hashCode();
    }

    /**
     * See Overridden Method.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Route) {
            Route routeObj = (Route) obj;

            return routeObj.getDistance() == distance && routeObj.getFrom().equals(from)
                && routeObj.getTo().equals(to);
        }

        return false;
    }

    /**
     * See Overridden Method.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return from + "-" + to + "-" + distance;
    }

    /**
     * See Overridden Method.
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(final Route o) {
        if (o == null) {
            return -1;
        }

        if (distance < o.getDistance()) {
            return -1;
        }

        if (distance > o.getDistance()) {
            return 1;
        }

        return 0;
    }

}
