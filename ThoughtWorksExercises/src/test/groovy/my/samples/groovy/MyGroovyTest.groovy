/*
 * Copyright (c) 2008 by Carlson Wagonlit Travel.  All rights reserved.
 *
 */
package my.samples.groovy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * MyGroovyTest type.
 * Description:
 *
 *  @author     utxl172
 *  @see        Checkstyle checks
 *  @version    $LastChangedRevision$  $LastChangedDate$
 */
class MyGroovyTest {
	
	@Test
	void testSomething() {
		MyTestGroovy test = new MyTestGroovy()
		
		test.myInt = 34
		
		assertEquals 34, test.myInt
	}
}
