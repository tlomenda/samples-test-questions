/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * RouteGraph type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class RouteGraph {
    private Map<City, Set<Route>> routeMap = new HashMap<City, Set<Route>>();

    Set<Route> getRoutesFrom(final City from) {
        return routeMap.get(from);
    }

    public void putRoute(final Route route) {
        Set<Route> routeSet = routeMap.get(route.getFrom());

        if (routeSet == null) {
            routeSet = new HashSet<Route>();
            routeMap.put(route.getFrom(), routeSet);
        }

        // Add the route
        routeSet.add(route);
    }

    public int distanceOfRoute(final City... cities) {
        // Loop through the cities to find the route
        int routeDistance = 0;
        if (cities != null && cities.length >= 2) {
            Set<Route> routes = null;
            Route selectedRoute = null;

            for (int i = 0; i < cities.length - 1; i++) {
                selectedRoute = null;
                routes = routeMap.get(cities[i]);

                if (routes != null) {
                    for (Route route : routes) {
                        if (route.getTo().equals(cities[i + 1])) {
                            selectedRoute = route;
                            break;
                        }
                    }
                }

                if (selectedRoute != null) {
                    routeDistance += selectedRoute.getDistance();
                } else {
                    throw new NoRouteException();
                }
            }
        }

        return routeDistance;
    }

    public Trip shortestTrip(final City start, final City end) {
        Trip shortestTrip = buildShortestTrip(start, end, new HashSet<Route>());

        return shortestTrip;
    }

    public List<Trip> tripsWithStopsLessThanOrEqual(final City start, final City end,
        final int maxStops) {
        List<Trip> trips = new ArrayList<Trip>();
        Stack<Route> routeStack = new Stack<Route>();

        buildTripsWithMaxStopsOrLess(start, end, maxStops, routeStack, trips);

        return trips;
    }

    public List<Trip> tripsWithExactStops(final City start, final City end, final int numOfStops) {
        List<Trip> trips = new ArrayList<Trip>();
        Stack<Route> routeStack = new Stack<Route>();

        buildTripsWithExactStops(start, end, numOfStops, routeStack, trips);

        return trips;
    }

    public List<Trip> tripsWithDistanceLessThan(final City start, final City end,
        final int maxDistance) {
        List<Trip> trips = new ArrayList<Trip>();
        Stack<Route> routeStack = new Stack<Route>();

        buildTripsWithDistanceLessThan(start, end, maxDistance, routeStack, trips);

        return trips;
    }

    private Trip buildShortestTrip(final City start, final City end, final Set<Route> routesChecked) {
        Set<Route> routeSet = routeMap.get(start);

        Trip shortestTrip = null;
        Trip candidateTrip = null;

        if (routeSet != null) {
            List<Route> routes = new ArrayList<Route>(routeSet);

            // Sort the routes from shortest to largest in the list of routes
            // from this node(city)
            Collections.sort(routes);

            for (Route route : routes) {
                // Only look at route of the trip is currently null or the
                // route's distance is less than the current shortest trip
                if (shortestTrip == null || route.getDistance() < shortestTrip.getTotalDistance()) {
                    if (route.getTo().equals(end)) {
                        shortestTrip = new Trip();
                        shortestTrip.pushRoute(route);
                    } else if (!routesChecked.contains(route)) {
                        // Recursion
                        routesChecked.add(route);
                        candidateTrip = buildShortestTrip(route.getTo(), end, routesChecked);

                        if (candidateTrip != null) {
                            candidateTrip.pushRoute(route);
                            if (shortestTrip == null
                                || candidateTrip.getTotalDistance() < shortestTrip
                                    .getTotalDistance()) {
                                shortestTrip = candidateTrip;
                            }
                        }

                        routesChecked.remove(route);
                    }
                }
            }
        }

        System.out.println(shortestTrip);
        return shortestTrip;
    }

    private void buildTripsWithMaxStopsOrLess(final City start, final City end, final int maxStops,
        final Stack<Route> routeStack, final Collection<Trip> trips) {
        Collection<Route> routes = this.routeMap.get(start);

        Stack<Route> tempStack = null;
        Trip tripToAdd = null;

        if (routes != null) {
            for (Route route : routes) {
                if (routeStack.size() == maxStops) {
                    return;
                }

                routeStack.push(route);

                if (route.getTo().equals(end)) {
                    // Build the trip
                    tripToAdd = new Trip();
                    tempStack = new Stack<Route>();
                    tempStack.addAll(routeStack);
                    while (!tempStack.isEmpty()) {
                        tripToAdd.pushRoute(tempStack.pop());
                    }

                    trips.add(tripToAdd);

                } else {
                    buildTripsWithMaxStopsOrLess(route.getTo(), end, maxStops, routeStack, trips);
                }

                routeStack.pop();
            }
        }
    }

    private void buildTripsWithExactStops(final City start, final City end,
        final int numberOfStops, final Stack<Route> routeStack, final Collection<Trip> trips) {
        Collection<Route> routes = this.routeMap.get(start);

        Stack<Route> tempStack = null;
        Trip tripToAdd = null;

        if (routes != null) {
            for (Route route : routes) {
                if (routeStack.size() == numberOfStops) {
                    return;
                }

                routeStack.push(route);

                if (route.getTo().equals(end) && routeStack.size() == numberOfStops) {
                    // Build the trip
                    tripToAdd = new Trip();
                    tempStack = new Stack<Route>();
                    tempStack.addAll(routeStack);
                    while (!tempStack.isEmpty()) {
                        tripToAdd.pushRoute(tempStack.pop());
                    }

                    trips.add(tripToAdd);

                } else {
                    buildTripsWithExactStops(route.getTo(), end, numberOfStops, routeStack, trips);
                }

                routeStack.pop();
            }
        }
    }

    private void buildTripsWithDistanceLessThan(final City start, final City end,
        final int maxDistance, final Stack<Route> routeStack, final Collection<Trip> trips) {
        Collection<Route> routes = this.routeMap.get(start);

        Stack<Route> tempStack = null;
        Trip tripToAdd = null;

        if (routes != null) {
            for (Route route : routes) {
                if (calculateDistance(routeStack) >= maxDistance) {
                    return;
                }

                routeStack.push(route);

                if (route.getTo().equals(end)) {
                    // Build the trip
                    tripToAdd = new Trip();
                    tempStack = new Stack<Route>();
                    tempStack.addAll(routeStack);
                    while (!tempStack.isEmpty()) {
                        tripToAdd.pushRoute(tempStack.pop());
                    }

                    if (tripToAdd.getTotalDistance() < maxDistance) {
                        trips.add(tripToAdd);
                    }

                    // We want to look deeper for another trip
                    if (tripToAdd.getTotalDistance() < maxDistance) {
                        buildTripsWithDistanceLessThan(route.getTo(), end, maxDistance, routeStack,
                            trips);
                    }
                } else {
                    buildTripsWithDistanceLessThan(route.getTo(), end, maxDistance, routeStack,
                        trips);
                }

                routeStack.pop();
            }
        }
    }

    private int calculateDistance(final Collection<Route> routes) {
        int totalDistance = 0;
        if (routes != null) {
            for (Route route : routes) {
                totalDistance += route.getDistance();
            }
        }

        return totalDistance;
    }
}
