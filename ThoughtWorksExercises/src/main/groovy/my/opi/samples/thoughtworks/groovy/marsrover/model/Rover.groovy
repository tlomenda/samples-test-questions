/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.groovy.marsrover.model;

/**
 * Rover type.
 * Description:
 *
 *  @author     utxl172
 *  @see        Checkstyle checks
 *  @version    $LastChangedRevision$  $LastChangedDate$
 */
class Rover {
	
	def coordinates
	SurfaceGrid surface
	Enums.Direction facingDirection = Enums.Direction.N 
	
	boolean place(surface, coordinates) {
		
		// Was this rover on another surface
		if (this.surface && this.surface != surface && this.coordinates) {
			this.surface.unsetCoordinates(this.coordinates[0], this.coordinates[1])
		}
		
		// Continue with placing on the new service
		this.surface = surface
		
		if (coordinates && surface.setCoordinates(coordinates[0], coordinates[1])) {
			this.coordinates = coordinates
		} else {
			return false	
		}
		
		return true
	}
	
	void rotate(command) {
		if (command == Enums.RoverCommand.LEFT) {
			facingDirection = facingDirection.getDirection(-90)
		} else if (command == Enums.RoverCommand.RIGHT) {
			facingDirection = facingDirection.getDirection(90)
		}
	}
	
	void move() {
		move(1)
	}
	
	void move(numberOfMoves) {
		def newCoordinates
		
		if (surface && coordinates && surface.isPathClear(coordinates, facingDirection, numberOfMoves)) {
			if (facingDirection == Enums.Direction.N) {
				newCoordinates = getNewCoordinates(0, numberOfMoves)
			} else if (facingDirection == Enums.Direction.E) {
				newCoordinates = getNewCoordinates(numberOfMoves, 0)
			} else if (facingDirection == Enums.Direction.S) {
				newCoordinates = getNewCoordinates(0, -(numberOfMoves))
			} else if (facingDirection == Enums.Direction.W) {
				newCoordinates = getNewCoordinates(-(numberOfMoves), 0)
			}
			
			if (newCoordinates && surface.setCoordinates(newCoordinates)) {
				surface.unsetCoordinates(coordinates);
				coordinates = newCoordinates;
				
				drive();
			}
		}
	}
	
	private void drive() {
		// Inner workings of Rover to drive to coordinates
	}
	
	private def getNewCoordinates(final int xOffset, final int yOffset) {
		if (surface) {
			return [coordinates[0] + (xOffset), coordinates[1] + (yOffset)];
		}
		
		return coordinates;
	}
}
