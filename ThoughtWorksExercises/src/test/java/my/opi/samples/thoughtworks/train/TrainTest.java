/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.train;

import java.util.List;

import junit.framework.TestCase;
import my.opi.samples.thoughtworks.trains.TrainsController;
import my.opi.samples.thoughtworks.trains.model.NoRouteException;
import my.opi.samples.thoughtworks.trains.model.Trip;

/**
 * TrainTest type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class TrainTest extends TestCase {

    private TrainsController trainsController;

    /**
     * See Overridden Method.
     * 
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();

        trainsController = new TrainsController();

        trainsController.setup("AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7");
    }

    public void testDistanceABC() {
        int distance = trainsController.distanceOfRoute("A-B-C");
        assertEquals(9, distance);
        System.out.println("Distance (A-B-C):\t" + distance);
    }

    public void testDistanceAD() {
        int distance = trainsController.distanceOfRoute("A-D");
        assertEquals(5, trainsController.distanceOfRoute("A-D"));
        System.out.println("Distance (A-D):\t\t" + distance);
    }

    public void testDistanceADC() {
        int distance = trainsController.distanceOfRoute("A-D-C");
        assertEquals(13, trainsController.distanceOfRoute("A-D-C"));
        System.out.println("Distance (A-D-C):\t" + distance);
    }

    public void testDistanceAEBCD() {
        int distance = trainsController.distanceOfRoute("A-E-B-C-D");
        assertEquals(22, trainsController.distanceOfRoute("A-E-B-C-D"));
        System.out.println("Distance (A-E-B-C-D):\t" + distance);
    }

    public void testDistanceAED() {
        try {
            trainsController.distanceOfRoute("A-E-D");
            fail();
        } catch (Exception e) {
            System.out.println("Distance (A-E-D):\tNO SUCH ROUTE");
            assertTrue(e instanceof NoRouteException);
        }
    }

    public void testShortestTripFromAToC() {
        Trip trip = trainsController.shortestRoute("A", "C");

        assertEquals(9, trip.getTotalDistance());
        System.out.println("Shortest Trip between A and C:  " + trip);
    }

    public void testShortestTripFromBToB() {
        Trip trip = trainsController.shortestRoute("B", "B");

        assertEquals(9, trip.getTotalDistance());
        System.out.println("Shortest Trip between B and B:  " + trip);
    }

    public void testTripsFromCToCWithMaxStops3() {
        List<Trip> trips = trainsController.tripsWithStopsLessThanOrEqual("C", "C", 3);

        assertEquals(2, trips.size());
        System.out.println("----");
        System.out.println("C to C Trips with 3 Stops or Less");
        System.out.println("Number:  " + trips.size());
        for (Trip trip : trips) {
            System.out.println(trip);
        }
        System.out.println("----");
    }

    public void testTripsFromAToCWithExactly4Stops() {
        List<Trip> trips = trainsController.tripsWithExactStops("A", "C", 4);

        assertEquals(3, trips.size());
        System.out.println("----");
        System.out.println("C to C Trips with Exactly 4 Stops");
        System.out.println("Number:  " + trips.size());
        for (Trip trip : trips) {
            System.out.println(trip);
        }
        System.out.println("----");
    }

    public void testTripsFromCtoCWithDistanceLessThan30() {
        System.out.println("----");
        List<Trip> trips = trainsController.tripsWithDistanceLessThan("C", "C", 30);

        assertEquals(7, trips.size());
        System.out.println("C to C Trips with Trips less than 30");
        System.out.println("Number:  " + trips.size());
        for (Trip trip : trips) {
            System.out.println(trip);
        }
        System.out.println("----");
    }
}
