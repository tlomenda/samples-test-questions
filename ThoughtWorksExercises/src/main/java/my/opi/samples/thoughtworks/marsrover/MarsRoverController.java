/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.marsrover;

import java.util.ArrayList;
import java.util.List;

import my.opi.samples.thoughtworks.marsrover.model.Dimension;
import my.opi.samples.thoughtworks.marsrover.model.Direction;
import my.opi.samples.thoughtworks.marsrover.model.Rover;
import my.opi.samples.thoughtworks.marsrover.model.SurfaceGrid;

/**
 * MarsRoverController type. Description:
 * 
 * @author utxl172
 * @see Checkstyle checks
 * @version $LastChangedRevision$ $LastChangedDate$
 */
public class MarsRoverController {

    public static final char CMD_ROTATE_LEFT = 'L';
    public static final char CMD_ROTATE_RIGHT = 'R';
    public static final char CMD_ROTATE_MOVE = 'M';
    public static final String CONFIG_DIRECTION_N = "N";
    public static final String CONFIG_DIRECTION_E = "E";
    public static final String CONFIG_DIRECTION_S = "S";
    public static final String CONFIG_DIRECTION_W = "W";

    private SurfaceGrid surfaceGrid;
    private List<Rover> rovers;

    public void executeCommands(final Rover rover, final String command) {

        if (command != null && rover != null) {
            for (int i = 0; i < command.length(); i++) {
                if (command.charAt(i) == CMD_ROTATE_LEFT) {
                    rover.rotateLeft();
                } else if (command.charAt(i) == CMD_ROTATE_RIGHT) {
                    rover.rotateRight();
                } else if (command.charAt(i) == CMD_ROTATE_MOVE) {
                    rover.move();
                }
            }
        }
    }

    public void setup(final String gridConfig, final String... roverConfigs) {
        List<Rover> roverList = new ArrayList<Rover>();

        // Create the grid
        this.surfaceGrid = parseGridConfig(gridConfig);

        if (roverConfigs != null && roverConfigs.length > 0) {
            for (int i = 0; i < roverConfigs.length; i++) {
                roverList.add(parseRoverConfig(roverConfigs[i]));
            }
        }

        this.rovers = roverList;
    }

    /**
     * Return the surfaceGrid.
     * 
     * @return the surfaceGrid
     */
    public SurfaceGrid getSurfaceGrid() {
        return surfaceGrid;
    }

    /**
     * Return the rovers.
     * 
     * @return the rovers
     */
    public List<Rover> getRovers() {
        return rovers;
    }

    public SurfaceGrid parseGridConfig(final String gridConfig) {
        String[] dimensions = gridConfig.split(" ");

        if (dimensions.length != 2) {
            throw new IllegalArgumentException("Invalid grid configuration for plateau '"
                + gridConfig + "'.");
        }

        Dimension dimension = new Dimension(Integer.parseInt(dimensions[0]), Integer
            .parseInt(dimensions[1]));
        SurfaceGrid grid = new SurfaceGrid(dimension);
        return grid;
    }

    public Rover parseRoverConfig(final String roverConfig) {
        String[] roverConfigItems = roverConfig.split(" ");

        if (roverConfigItems.length != 3) {
            throw new IllegalArgumentException("Invalid rover configuration for '" + roverConfig
                + "'.");
        }

        Rover rover = new Rover();

        if (CONFIG_DIRECTION_N.equals(roverConfigItems[2])) {
            rover.startOnGrid(surfaceGrid, Direction.North, Integer.parseInt(roverConfigItems[0]),
                Integer.parseInt(roverConfigItems[1]));
        } else if (CONFIG_DIRECTION_E.equals(roverConfigItems[2])) {
            rover.startOnGrid(surfaceGrid, Direction.East, Integer.parseInt(roverConfigItems[0]),
                Integer.parseInt(roverConfigItems[1]));
        }
        if (CONFIG_DIRECTION_S.equals(roverConfigItems[2])) {
            rover.startOnGrid(surfaceGrid, Direction.South, Integer.parseInt(roverConfigItems[0]),
                Integer.parseInt(roverConfigItems[1]));
        }
        if (CONFIG_DIRECTION_W.equals(roverConfigItems[2])) {
            rover.startOnGrid(surfaceGrid, Direction.West, Integer.parseInt(roverConfigItems[0]),
                Integer.parseInt(roverConfigItems[1]));
        }
        return rover;
    }
}
