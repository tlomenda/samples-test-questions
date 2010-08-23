/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package opi.coding.exercise.interviewtest.model

/**
 * Enums type.
 * Description:
 *
 *  @author     tlomenda
 *  @see        Checkstyle checks
 *  @version    $LastChangedRevision$  $LastChangedDate$
 */
public enum Direction {
    N, E, S, W
	
	Direction rotateClockwise90() {
		if (this == N) {
				return E;
		} else if (this == E) {
				return S;
		} else if (this == W) {
				return N;
		} else if (this == S) {
				return W;
		}
	}
}
