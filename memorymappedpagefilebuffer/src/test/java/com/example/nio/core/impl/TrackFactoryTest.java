/**
 * 
 */
package com.example.nio.core.impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.nio.core.Track;

/**
 * @author chira
 * 
 */
public class TrackFactoryTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.example.nio.core.impl.TrackFactory#createTrack(java.lang.String)}
	 * .
	 */
	@Test
	public void testCreateTrack() {

		Track sampleTrack = TrackFactory.getInstance().createTrack(
				"Sample Track");
		assertEquals("Sample Track", sampleTrack.getTrackName());
	}
}
