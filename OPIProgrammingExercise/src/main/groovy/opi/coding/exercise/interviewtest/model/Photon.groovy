package opi.coding.exercise.interviewtest.model

/**
 * Represents a photon of a certain frequency emitted based on electron high/low energy levels.
 * 
 * @author tlomenda
 *
 */
class Photon {
	// the high and low energy transition is captured and is used to define the frequency
	// at which a proton is emitted
	int highEnergy
	int lowEnergy
	public Photon(int highEnergy, int lowEnergy) {
		super()
		this.highEnergy = highEnergy
		this.lowEnergy = lowEnergy
	}
	
	public String toString() {
		return """(${highEnergy},${lowEnergy})""" 
	};
}
