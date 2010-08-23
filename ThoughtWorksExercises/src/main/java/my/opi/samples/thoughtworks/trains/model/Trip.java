/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.trains.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Trip type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class Trip {

    private Stack<Route> routes = new Stack<Route>();
    private int totalDistance;

    /**
     * Return the totalDistance.
     * 
     * @return the totalDistance
     */
    public int getTotalDistance() {
        return totalDistance;
    }

    /**
     * Set the totalDistance.
     * 
     * @param totalDistance
     * the totalDistance to set
     */
    public void setTotalDistance(final int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getTotalRoutes() {
        return routes.size();
    }

    public void pushRoute(final Route route) {
        routes.push(route);

        // Add to total
        totalDistance += route.getDistance();
    }

    public void removeRoute(final Route route) {
        routes.remove(route);

        // Add to total
        totalDistance -= route.getDistance();
    }

    public List<Route> getRoutesAsOrderedList() {
        Stack<Route> tempStack = new Stack<Route>();
        List<Route> routeList = new ArrayList<Route>();
        tempStack.addAll(routes);

        while (!tempStack.empty()) {
            routeList.add(tempStack.pop());
        }

        return routeList;
    }

    /**
     * See Overridden Method.
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder tripStr = new StringBuilder();
        List<Route> routeList = getRoutesAsOrderedList();

        tripStr.append(this.totalDistance).append("(");

        for (int i = 0; i < routeList.size(); i++) {
            tripStr.append(routeList.get(i).getFrom());
            if (i < routeList.size() - 1) {
                tripStr.append("-");
            } else {
                tripStr.append("-");
                tripStr.append(routeList.get(i).getTo());
            }
        }

        tripStr.append(")");
        return tripStr.toString();
    }
}
