/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.groovy.marsrover.model;



/**
 * Surfacegrid type.
 * Description:
 *
 *  @author     utxl172
 *  @see        Checkstyle checks
 *  @version    $LastChangedRevision$  $LastChangedDate$
 */
class SurfaceGrid {
	int height
	int width
	
	def pointsOccupied = [:]
	
	SurfaceGrid(width, height) {
		width = width
		height = height
	}
	
	boolean isCoordinatesOccupied(x, y) {
		println(pointsOccupied[coordinatesKey(x, y)])
		if (pointsOccupied[coordinatesKey(x, y)] != null) {
			return true;
		}
		
		return false;
	}
	
	boolean isPathClear(startCoordinates, direction, moves) {
		if (moves >= 1 && startCoordinates) {
			int startX = startCoordinates[0];
			int startY = startCoordinates[1];
			
			if (direction == Enums.Direction.N) {
				for (int i = 1; i <= moves; i++) {
					if (isCoordinatesOccupied(startX, startY + i)) {
						return false;
					}
				}
			} else if (direction ==  Enums.Direction.E) {
				for (int i = 1; i <= moves; i++) {
					if (isCoordinatesOccupied(startX + i, startY)) {
						return false;
					}
				}
			} else if (direction == Enums.Direction.S) {
				for (int i = 1; i <= moves; i++) {
					if (isCoordinatesOccupied(startX, startY - i)) {
						return false;
					}
				}
			} else if (direction == Enums.Direction.W) {
				for (int i = 1; i <= moves; i++) {
					if (isCoordinatesOccupied(startX - i, startY)) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	synchronized boolean setCoordinates(x, y) {
		if (isCoordinatesOccupied(x, y)) {
			// already occupied
			return false;
		}
		
		pointsOccupied[coordinatesKey(x, y)] = [x,y];
		return true;
	}
	
	boolean unsetCoordinates(x, y) {
		if (!isCoordinatesOccupied(x, y)) {
			// this is not occupied
			return false;
		}
		
		pointsOccupied.remove(coordinatesKey(x, y));
		return true;
	}
	
	private String coordinatesKey(x, y) {
		return """($x,$y)"""
	}
}
