/**
 * 
 */
package com.example.nio.pagefile.impl;

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

import com.example.nio.pagefile.PageFile;

/**
 * @author chira
 * 
 */
public class PageFileFactoryTest {

	private String pathToSamplePageFileWhichWillBeDeleted;
	private String pathToSamplePageFileWhichPreExists;

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
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.example.nio.pagefile.impl.PageFileFactory#createPageFile(java.lang.String, boolean)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCreatePageFileWhereNoneExists() throws IOException {

		// delete the created temporary file for the operation to succeed.
		assertTrue(new File(pathToSamplePageFileWhichWillBeDeleted).delete());
		PageFile sampleFile = PageFileFactory.getSingletonInstance()
				.createPageFile(pathToSamplePageFileWhichWillBeDeleted, false);

		assertTrue(sampleFile.getPathToResource().toFile().exists());

		// We cannot check for the track as it does not exist yet.
		// assertNotNull(sampleFile.getTrack());
	}

	/**
	 * Test method for
	 * {@link com.example.nio.pagefile.impl.PageFileFactory#createPageFile(java.lang.String, boolean)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testCreatePageFileWhereItPreExists() throws IOException {

		// delete the created temporary file for the operation to succeed.
		PageFile sampleFile = PageFileFactory.getSingletonInstance()
				.createPageFile(pathToSamplePageFileWhichPreExists, true);

		assertTrue(sampleFile.getPathToResource().toFile().exists());

		// We cannot check for the track as it does not exist yet.
		// assertNotNull(sampleFile.getTrack());
	}
}