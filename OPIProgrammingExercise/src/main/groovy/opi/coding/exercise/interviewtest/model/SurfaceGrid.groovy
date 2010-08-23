package opi.coding.exercise.interviewtest.model

/**
 * This represents a flat 2-dimensional surface grid that a jumping robot occupies
 * based on x, y coordinates.
 * 
 * @author tlomenda
 *
 */
class SurfaceGrid {

	int width
	int height
	
	def pointsOccupied = [:]
	public SurfaceGrid(int width, int height) {
		super();
		this.width = width;
		this.height = height;
	}
	
	boolean isCoordinatesOccupied(x, y) {
		if (pointsOccupied[coordinatesKey(x, y)] != null) {
			return true;
		}
		
		return false;
	}
	
	boolean isPathClear(startCoordinates, direction, moves) {
		if (moves >= 1 && startCoordinates) {
			int startX = startCoordinates[0];
			int startY = startCoordinates[1];
			
			if (direction == Direction.N) {
				for (int i = 1; i <= moves; i++) {
					if (isCoordinatesOccupied(startX, startY + i)) {
						return false;
					}
				}
			} else if (direction ==  Direction.E) {
				for (int i = 1; i <= moves; i++) {
					if (isCoordinatesOccupied(startX + i, startY)) {
						return false;
					}
				}
			} else if (direction == Direction.S) {
				for (int i = 1; i <= moves; i++) {
					if (isCoordinatesOccupied(startX, startY - i)) {
						return false;
					}
				}
			} else if (direction == Direction.W) {
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
