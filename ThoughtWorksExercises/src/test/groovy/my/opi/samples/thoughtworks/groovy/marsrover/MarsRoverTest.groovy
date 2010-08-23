/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.groovy.marsrover;

import my.opi.samples.thoughtworks.groovy.marsrover.model.Rover;
import my.opi.samples.thoughtworks.groovy.marsrover.model.Enums;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * MarsRoverTest type.
 * Description:
 *
 *  @author     utxl172
 *  @see        Checkstyle checks
 *  @version    $LastChangedRevision$  $LastChangedDate$
 */
class MarsRoverTest {
	
	@Test
	void testTwoRoversOn5By5Surface() {
		Controller roverController = new Controller();
		
		roverController.setup "5 5", "1 2 N", "3 3 E"
		
		// Rover 1
		Rover rover1 = roverController.roverList[0]
		assertNotNull rover1
		
		roverController.executeCommands "LMLMLMLMM", 0
		println(rover1.toString());
		assertEquals(Enums.Direction.N, rover1.facingDirection);
		assertEquals(1, rover1?.coordinates[0]);
		assertEquals(3, rover1?.coordinates[1]);
		
		
		// Rover 2
		Rover rover2 = roverController.roverList[1]
		assertNotNull rover2
		
		roverController.executeCommands "MMRMMRMRRM", 1	
		println(rover2.toString());
		assertEquals(Enums.Direction.E, rover2.facingDirection);
		assertEquals(5, rover2?.coordinates[0]);
		assertEquals(1, rover2?.coordinates[1]);
	}
	
}
