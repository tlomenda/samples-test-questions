/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.marsrover.model;

/**
 * Direction type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public enum Direction {
    North,

    East,

    South,

    West;

    public Direction getDirection(final int degree) {
        if (this == North) {
            if (degree == 90) {
                return East;
            } else if (degree == -90) {
                return West;
            }
        } else if (this == East) {
            if (degree == 90) {
                return South;
            } else if (degree == -90) {
                return North;
            }
        } else if (this == West) {
            if (degree == 90) {
                return North;
            } else if (degree == -90) {
                return South;
            }
        } else if (this == South) {
            if (degree == 90) {
                return West;
            } else if (degree == -90) {
                return East;
            }
        }

        // We only support 90 degree turns for this exercise.
        return this;
    }
}
