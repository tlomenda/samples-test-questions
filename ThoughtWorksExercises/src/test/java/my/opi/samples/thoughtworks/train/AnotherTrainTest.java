/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.train;

import junit.framework.TestCase;
import my.opi.samples.thoughtworks.trains.TrainsController;
import my.opi.samples.thoughtworks.trains.model.Trip;

/**
 * TrainTest type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class AnotherTrainTest extends TestCase {
    public void testShortestTripFromAToC() {
        TrainsController trainsController = new TrainsController();

        trainsController.setup("AB5,BC2,AE1,EB2");
        Trip trip = trainsController.shortestRoute("A", "C");

        assertEquals(5, trip.getTotalDistance());
        System.out.println("Shortest Trip between A and C:  " + trip);
    }

    public void testShortestTripFromAToCPart2() {
        TrainsController trainsController = new TrainsController();

        trainsController.setup("AB4,AD6,AE10,BE1,EC1,BC3,DC1");
        Trip trip = trainsController.shortestRoute("A", "C");

        assertEquals(6, trip.getTotalDistance());
        System.out.println("Shortest Trip between A and C:  " + trip);
    }
}
