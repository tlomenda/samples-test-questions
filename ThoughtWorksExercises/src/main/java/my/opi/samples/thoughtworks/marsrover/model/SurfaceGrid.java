/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.marsrover.model;

import java.util.HashMap;
import java.util.Map;

/**
 * SurfaceGrid type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class SurfaceGrid {
    private Dimension dimension;
    private Map<String, Point> pointsOccupied = new HashMap<String, Point>();

    public SurfaceGrid(final Dimension dimension) {
        this.dimension = dimension;
    }

    public int getXBounds() {
        return dimension.getWidth();
    }

    public int getYBounds() {
        return dimension.getHeight();
    }

    public Point getCoordinates(final int x, final int y) {
        Point point = pointsOccupied.get(coordinatesKey(x, y));

        if (point == null && isInBounds(x, y)) {
            return new Point(x, y);
        }

        return point;
    }

    public synchronized boolean setCoordinates(final int x, final int y) {
        if (isCoordinatesOccupied(x, y)) {
            // already occupied
            return false;
        }

        Point point = new Point(x, y);
        pointsOccupied.put(coordinatesKey(x, y), point);
        return true;
    }

    public synchronized boolean leaveCoordinates(final int x, final int y) {
        if (!isCoordinatesOccupied(x, y)) {
            // this is not occupied
            return false;
        }

        pointsOccupied.remove(coordinatesKey(x, y));
        return true;
    }

    public boolean isPathClear(final Point startPoint, final Direction direction, final int moves) {
        if (moves >= 1) {
            int startX = startPoint.getX();
            int startY = startPoint.getY();

            if (direction == Direction.North) {
                for (int i = 1; i <= moves; i++) {
                    if (isCoordinatesOccupied(startX, startY + i)) {
                        return false;
                    }
                }
            } else if (direction == Direction.East) {
                for (int i = 1; i <= moves; i++) {
                    if (isCoordinatesOccupied(startX + i, startY)) {
                        return false;
                    }
                }
            } else if (direction == Direction.South) {
                for (int i = 1; i <= moves; i++) {
                    if (isCoordinatesOccupied(startX, startY - i)) {
                        return false;
                    }
                }
            } else if (direction == Direction.West) {
                for (int i = 1; i <= moves; i++) {
                    if (isCoordinatesOccupied(startX - i, startY)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isCoordinatesOccupied(final int x, final int y) {
        if (pointsOccupied.get(coordinatesKey(x, y)) != null) {
            return true;
        }

        return false;
    }

    public boolean isInBounds(final int x, final int y) {
        return x <= getXBounds() && x >= 0 && y <= getYBounds() && y >= 0;
    }

    private String coordinatesKey(final int x, final int y) {
        return Point.coordinatesAsString(x, y);
    }
}
