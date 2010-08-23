package opi.coding.exercise.interviewtest

import java.util.HashMap;
import java.util.Map;

import opi.coding.exercise.interviewtest.model.PhotonTable;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * Test case for the RobotLeavingGridController.  NOTE:  Due to time constraint
 * of this exercise test cases for the model classes were not written.
 * On a real-world project of any size, test cases for these classes would also
 * have been developed.
 * 
 * @author tlomenda
 *
 */
class RobotLeavingGridControllerTest {

	/**
	 * This test case simulates running the program 10000 and outputing the results.
	 */
	@Test
	void testRun10000TimesAlgorithm1AndGenerateReport() {
		int photonsEmitted = 0
		
		Map<Integer, Integer> emittedPhotonRunStats = new HashMap<Integer, Integer>()
		RobotLeavingGridController controller = new RobotLeavingGridController(PhotonTable.DIST_ALGORITHM_1)
		
		for (int i = 0; i < 10000; i++) {
			controller.execute()
			
			photonsEmitted = controller.photonsEmittedList.size()
			
			// Register with the per run stats
			if (emittedPhotonRunStats.containsKey(photonsEmitted)) {
				// Increment the run stats
				emittedPhotonRunStats.put(photonsEmitted, ++emittedPhotonRunStats.get(photonsEmitted))
			} else {
				emittedPhotonRunStats.put(photonsEmitted, 1)
			}
			
			controller.reset()
		}
		
		generateOutput(emittedPhotonRunStats)
	}
	
	/**
	 * This test case simulates running the program 10000 and outputing the results.
	 */
	@Test
	void testRun10000TimesAlgorithm2AndGenerateReport() {
		int photonsEmitted = 0
		
		Map<Integer, Integer> emittedPhotonRunStats = new HashMap<Integer, Integer>()
		RobotLeavingGridController controller = new RobotLeavingGridController(PhotonTable.DIST_ALGORITHM_2)
		
		for (int i = 0; i < 10000; i++) {
			controller.execute()
			
			photonsEmitted = controller.photonsEmittedList.size()
			
			// Register with the per run stats
			if (emittedPhotonRunStats.containsKey(photonsEmitted)) {
				// Increment the run stats
				emittedPhotonRunStats.put(photonsEmitted, ++emittedPhotonRunStats.get(photonsEmitted))
			} else {
				emittedPhotonRunStats.put(photonsEmitted, 1)
			}
			
			controller.reset()
		}
		
		generateOutput(emittedPhotonRunStats)
	}
	
	/**
	 * This test case simulates running the program 10000 and outputing the results.
	 */
	@Test
	void testRun10000TimesAlgorithm3AndGenerateReport() {
		int photonsEmitted = 0
		
		Map<Integer, Integer> emittedPhotonRunStats = new HashMap<Integer, Integer>()
		RobotLeavingGridController controller = new RobotLeavingGridController(PhotonTable.DIST_ALGORITHM_3)
		
		for (int i = 0; i < 10000; i++) {
			controller.execute()
			
			photonsEmitted = controller.photonsEmittedList.size()
			
			// Register with the per run stats
			if (emittedPhotonRunStats.containsKey(photonsEmitted)) {
				// Increment the run stats
				emittedPhotonRunStats.put(photonsEmitted, ++emittedPhotonRunStats.get(photonsEmitted))
			} else {
				emittedPhotonRunStats.put(photonsEmitted, 1)
			}
			
			controller.reset()
		}
		
		generateOutput(emittedPhotonRunStats)
	}
	
	private void generateOutput(def emittedPhotonRunStats) {
		println("======= Photon Emission Distribution =======")
		// print the run stats
		def keySet = emittedPhotonRunStats.keySet().sort()	
		for (key in keySet) {
			println(key + "," + emittedPhotonRunStats.get(key))
		}
	}
}
