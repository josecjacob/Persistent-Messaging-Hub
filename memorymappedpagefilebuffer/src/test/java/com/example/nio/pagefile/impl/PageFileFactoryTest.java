/**
 * 
 */
package com.example.nio.pagefile.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermissions;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.nio.core.Track;
import com.example.nio.pagefile.PageFile;
import com.example.nio.pagefile.impl.mac.PageFileMacFactory;
import com.example.nio.pagefile.impl.mac.PageFileMacUtility;

/**
 * @author chira
 * 
 */
public class PageFileFactoryTest {

	private String pathToSamplePageFileWhichWillBeDeleted;
	private String pathToSamplePageFileWhichPreExists;

	private Track sampleTrack;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		((PageFileMacFactory) PageFileMacFactory.getSingletonInstance())
				.setPlatformUtility(new PageFileMacUtility());
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
		pathToSamplePageFileWhichWillBeDeleted = Files
				.createTempFile(
						"pathToSamplePageFileWhichWillBeDeleted",
						"",
						PosixFilePermissions
								.asFileAttribute(PosixFilePermissions
										.fromString("rw-r--r--"))).toFile()
				.getAbsolutePath();

		pathToSamplePageFileWhichPreExists = Files
				.createTempFile(
						"pathToSamplePageFileWhichPreExists",
						"",
						PosixFilePermissions
								.asFileAttribute(PosixFilePermissions
										.fromString("rw-r--r--"))).toFile()
				.getAbsolutePath();

		sampleTrack = new Track() {

			@Override
			public String getTrackName() {
				return "Sample Track";
			}
		};
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.example.nio.pagefile.impl.mac.PageFileMacFactory#createPageFile(java.lang.String, boolean)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCreatePageFileWhereNoneExists() throws IOException {

		// delete the created temporary file for the operation to succeed.
		assertTrue(new File(pathToSamplePageFileWhichWillBeDeleted).delete());
		PageFile sampleFile = PageFileMacFactory.getSingletonInstance()
				.createPageFile(sampleTrack,
						pathToSamplePageFileWhichWillBeDeleted, false);

		assertTrue(sampleFile.getPathToResource().toFile().exists());

		assertNotNull(sampleFile.getTrack());
		assertEquals(sampleTrack.getTrackName(), sampleFile.getTrack()
				.getTrackName());
	}

	/**
	 * Test method for
	 * {@link com.example.nio.pagefile.impl.mac.PageFileMacFactory#createPageFile(java.lang.String, boolean)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCreatePageFileWhereItPreExists() throws IOException {

		// delete the created temporary file for the operation to succeed.
		PageFile sampleFile = PageFileMacFactory.getSingletonInstance()
				.createPageFile(sampleTrack,
						pathToSamplePageFileWhichPreExists, true);

		assertTrue(sampleFile.getPathToResource().toFile().exists());

		assertNotNull(sampleFile.getTrack());
		assertEquals(sampleTrack.getTrackName(), sampleFile.getTrack()
				.getTrackName());
	}
}