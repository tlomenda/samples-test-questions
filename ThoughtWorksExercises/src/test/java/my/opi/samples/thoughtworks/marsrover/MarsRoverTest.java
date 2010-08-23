/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.marsrover;

import java.util.List;

import junit.framework.TestCase;
import my.opi.samples.thoughtworks.marsrover.MarsRoverController;
import my.opi.samples.thoughtworks.marsrover.model.Direction;
import my.opi.samples.thoughtworks.marsrover.model.Rover;

/**
 * MarsRoverTest type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class MarsRoverTest extends TestCase {

    public void testTwoMarsRovers() {
        MarsRoverController controller = new MarsRoverController();

        controller.setup("5 5", "1 2 N", "3 3 E");
        List<Rover> rovers = controller.getRovers();

        assertEquals(2, rovers.size());

        Rover rover1 = rovers.get(0);
        controller.executeCommands(rover1, "LMLMLMLMM");
        System.out.println(rover1.toString());
        assertEquals(Direction.North, rover1.getDirection());
        assertEquals(1, rover1.getCurrentPosition().getX());
        assertEquals(3, rover1.getCurrentPosition().getY());

        Rover rover2 = rovers.get(1);
        controller.executeCommands(rover2, "MMRMMRMRRM");
        System.out.println(rover2.toString());
        assertEquals(Direction.East, rover2.getDirection());
        assertEquals(5, rover2.getCurrentPosition().getX());
        assertEquals(1, rover2.getCurrentPosition().getY());
    }
}
