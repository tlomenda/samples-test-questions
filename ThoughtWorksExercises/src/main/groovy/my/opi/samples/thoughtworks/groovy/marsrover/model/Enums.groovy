/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.opi.samples.thoughtworks.groovy.marsrover.model;

import my.opi.samples.thoughtworks.marsrover.model.Direction;

/**
 * Enums type.
 * Description:
 *
 *  @author     utxl172
 *  @see        Checkstyle checks
 *  @version    $LastChangedRevision$  $LastChangedDate$
 */
class Enums {
	enum Direction {
	    N, E, S, W
		
		Direction getDirection(final int degree) {
			if (this == N) {
				if (degree == 90) {
					return E;
				} else if (degree == -90) {
					return W;
				}
			} else if (this == E) {
				if (degree == 90) {
					return S;
				} else if (degree == -90) {
					return N;
				}
			} else if (this == W) {
				if (degree == 90) {
					return N;
				} else if (degree == -90) {
					return S;
				}
			} else if (this == S) {
				if (degree == 90) {
					return W;
				} else if (degree == -90) {
					return E;
				}
			}
			
			// We only support 90 degree turns for this exercise.
			return this;
		}
	}
	
	enum RoverCommand {
	    LEFT("L"), RIGHT("R"), MOVE("M")
		RoverCommand(def value) {
			this.value = value
		}
		private final def value
		def value() { return this.value}
	}
}
