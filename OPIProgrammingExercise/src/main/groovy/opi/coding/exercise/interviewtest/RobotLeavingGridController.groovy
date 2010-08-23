package opi.coding.exercise.interviewtest

import java.util.ArrayList;
import java.util.List;

import opi.coding.exercise.interviewtest.model.Direction;
import opi.coding.exercise.interviewtest.model.Photon;
import opi.coding.exercise.interviewtest.model.PhotonTable;
import opi.coding.exercise.interviewtest.model.Robot;
import opi.coding.exercise.interviewtest.model.SurfaceGrid;

/**
 * Represents the controller of the problem.  The execute() takes in a maxMoves parameter to safeguard
 * against the robot never leaving to grid based on photon emissions.  Although unlikely to happen, the
 * safeguard provides a threshold of moves so that the program does not loop indefinitely.
 * 
 * @author tlomenda
 *
 */
class RobotLeavingGridController {
	
	SurfaceGrid surface = new SurfaceGrid(20, 20)
	Robot robot
	PhotonTable photonTable
	
	// Distribution list will contain 100 elements
	List<Photon> photonsEmittedList = new ArrayList<Photon>()
	

	public RobotLeavingGridController(def distributionAlgorithm) {
		super();
		
		surface = new SurfaceGrid(20, 20)
		robot = new Robot()
		photonTable = new PhotonTable(distributionAlgorithm)
		
		initializeForExecution()
	}
	
	void execute() {
		int photonCount = 0
		
		while (!robot.isOffSurface()) {
			// Determine emitted proton based on defined probability distribution
			int index = Math.round(Math.random() * (photonTable.photonDistributionList.size()-1))
			
			Photon emitted = photonTable.photonDistributionList[index]
			                                        
			// Rotate and move the robot
			for (int i=0; i < emitted.lowEnergy; i++) {
				robot.rotateClockwise()
			}
			
			robot.move(emitted.highEnergy)
			
			// Add the photon to the list
			photonsEmittedList.add(emitted)
		}
		
		
	}
	
	void reset() {
		initializeForExecution()
	}
	
	void initializeForExecution() {
		// Place the robot on the grid
		robot.placeOnSurface(surface, [9,9], Direction.N)
		
		photonsEmittedList.clear()
		
	}
}
