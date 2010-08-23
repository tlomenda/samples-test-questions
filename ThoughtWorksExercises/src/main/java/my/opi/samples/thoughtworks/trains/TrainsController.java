/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.trains;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import my.opi.samples.thoughtworks.trains.model.City;
import my.opi.samples.thoughtworks.trains.model.Route;
import my.opi.samples.thoughtworks.trains.model.RouteGraph;
import my.opi.samples.thoughtworks.trains.model.Trip;

/**
 * TrainsController type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class TrainsController {

    private RouteGraph routeMap = new RouteGraph();
    private Map<String, City> cityMap = new HashMap<String, City>();

    public void setup(final String graphSetup) {
        String[] routes = graphSetup.split(",");
        String fromCityStr = null;
        String toCityStr = null;
        City fromCity = null;
        City toCity = null;
        int distance = 0;

        for (int i = 0; i < routes.length; i++) {
            // Assume each City is one character, and distance is an integer
            fromCityStr = routes[i].substring(0, 1);
            toCityStr = routes[i].substring(1, 2);
            distance = Integer.parseInt(routes[i].substring(2));

            if (cityMap.get(fromCityStr) == null) {
                cityMap.put(fromCityStr, new City(fromCityStr));
            }
            if (cityMap.get(toCityStr) == null) {
                cityMap.put(toCityStr, new City(toCityStr));
            }

            // Create the route
            fromCity = cityMap.get(fromCityStr);
            toCity = cityMap.get(toCityStr);
            routeMap.putRoute(new Route(fromCity, toCity, distance));
        }
    }

    public int distanceOfRoute(final String route) {
        String[] cities = route.split("-");

        if (cities != null && cities.length > 0) {

            // Get all the cities
            List<City> citiesInRoute = new ArrayList<City>();
            for (String cityStr : cities) {
                citiesInRoute.add(cityMap.get(cityStr));
            }

            City[] cityList = new City[citiesInRoute.size()];
            return routeMap.distanceOfRoute(citiesInRoute.toArray(cityList));
        }

        return 0;
    }

    public Trip shortestRoute(final String start, final String end) {
        City startCity = cityMap.get(start);
        City endCity = cityMap.get(end);

        return routeMap.shortestTrip(startCity, endCity);
    }

    public List<Trip> tripsWithStopsLessThanOrEqual(final String start, final String end,
        final int maxStops) {
        City startCity = cityMap.get(start);
        City endCity = cityMap.get(end);

        return routeMap.tripsWithStopsLessThanOrEqual(startCity, endCity, maxStops);
    }

    public List<Trip> tripsWithExactStops(final String start, final String end, final int numOfStops) {
        City startCity = cityMap.get(start);
        City endCity = cityMap.get(end);

        return routeMap.tripsWithExactStops(startCity, endCity, numOfStops);
    }

    public List<Trip> tripsWithDistanceLessThan(final String start, final String end,
        final int maxDistance) {
        City startCity = cityMap.get(start);
        City endCity = cityMap.get(end);

        return routeMap.tripsWithDistanceLessThan(startCity, endCity, maxDistance);
    }
}
