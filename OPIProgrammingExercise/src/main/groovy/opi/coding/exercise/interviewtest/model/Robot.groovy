package opi.coding.exercise.interviewtest.model


/**
 * Robot represents the jumping Robot in the problem.  It knows about
 * its current coordinates, the surface grid that it is on, and the
 * direction it is facing.
 * 
 * @author tlomenda
 *
 */
class Robot {
	
	def coordinates
	SurfaceGrid surface
	Direction facingDirection = Direction.N
	
	boolean placeOnSurface(surface, coordinates, direction) {
		// Was this robot on another surface
		if (this.surface && this.coordinates) {
			this.surface.unsetCoordinates(this.coordinates[0], this.coordinates[1])
		}
		
		// Continue with placing on the new service
		this.surface = surface
		
		if (coordinates && surface.setCoordinates(coordinates[0], coordinates[1])) {
			this.coordinates = coordinates
		} else {
			return false	
		}
		
		this.facingDirection = direction
		
		return true
	}
	
	boolean isOffSurface() {
		if (coordinates) {
			if (coordinates[0] < 0 || coordinates[0] >= surface.width) {
				return true
			}
			if (coordinates[1] < 0 || coordinates[1] >= surface.height) {
				return true
			}
			
			return false
		}
		
		return true
	}
	
	void rotateClockwise() {
		facingDirection = facingDirection.rotateClockwise90()
	}
	
	void move(numberOfMoves) {
		def newCoordinates
		if (surface && coordinates && surface.isPathClear(coordinates, facingDirection, numberOfMoves)) {
			if (facingDirection == Direction.N) {
				newCoordinates = getNewCoordinates(0, numberOfMoves)
			} else if (facingDirection == Direction.E) {
				newCoordinates = getNewCoordinates(numberOfMoves, 0)
			} else if (facingDirection == Direction.S) {
				newCoordinates = getNewCoordinates(0, -(numberOfMoves))
			} else if (facingDirection == Direction.W) {
				newCoordinates = getNewCoordinates(-(numberOfMoves), 0)
			}
			
			if (newCoordinates && surface.setCoordinates(newCoordinates)) {
				surface.unsetCoordinates(coordinates);
				coordinates = newCoordinates;
				
				// This is where logic would go to command the 
				// robot's motor to move to said location.
			}
		}
	}
	
	private def getNewCoordinates(final int xOffset, final int yOffset) {
		if (surface) {
			return [coordinates[0] + (xOffset), coordinates[1] + (yOffset)];
		}
		
		return coordinates;
	}
}
