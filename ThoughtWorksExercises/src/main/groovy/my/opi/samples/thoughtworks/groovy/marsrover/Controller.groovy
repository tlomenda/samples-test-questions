/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.groovy.marsrover

import my.opi.samples.thoughtworks.groovy.marsrover.model.Enums;
import my.opi.samples.thoughtworks.groovy.marsrover.model.Rover;
import my.opi.samples.thoughtworks.groovy.marsrover.model.SurfaceGrid;
;

/**
 * Controller type.
 * Description:
 *
 *  @author     utxl172
 *  @see        Checkstyle checks
 *  @version    $LastChangedRevision$  $LastChangedDate$
 */
class Controller {
	SurfaceGrid surface
	def roverList = []
	
	void executeCommands(String commandList, int roverIndex) {
		Rover rover = roverList[roverIndex];
		
		if (commandList && rover) {
			for (int i = 0; i < commandList.length(); i++) {
				if (commandList.charAt(i) == Enums.RoverCommand.LEFT.value()) {
					rover.rotate(Enums.RoverCommand.LEFT);
				} else if (commandList.charAt(i) == Enums.RoverCommand.RIGHT.value()) {
					rover.rotate(Enums.RoverCommand.RIGHT);
				} else if (commandList.charAt(i) == Enums.RoverCommand.MOVE.value()) {
					rover.move();
				}
			}
		}
	}
	
	void setup(surfaceConfig, Object[] roverConfig) {
		createSurfaceGrid surfaceConfig
		
		roverConfig?.each {  createRover(it) }
	}
	
	private void createSurfaceGrid(config) {
		def height = 0
		def width = 0 
		def items = config?.split(' ')
		
		if (items  != null && items.size() == 2) {
			width = items[0]
			height = items[1]
		}
		
		this.surface = new SurfaceGrid(width, height)
	}
	
	private void createRover(config) {
		def items = config?.split(' ')
		
		if (items.size() == 3) {
			Rover rover = new Rover()
			
			if (items[2] == Enums.Direction.N.name()) {
				rover.facingDirection = Enums.Direction.N
			} else if (items[2] == Enums.Direction.E.name()) {
				rover.facingDirection = Enums.Direction.E
			} else if (items[2] == Enums.Direction.S.name()) {
				rover.facingDirection = Enums.Direction.S
			} else if (items[2] == Enums.Direction.W.name()) {
				rover.facingDirection = Enums.Direction.W
			}
			
			// Need to start the rover on a grid
			rover.place(surface, [items[0].toInteger(), items[1].toInteger()])
			
			// Add the rover to the control list
			roverList.add(rover);
		}
	}
}
