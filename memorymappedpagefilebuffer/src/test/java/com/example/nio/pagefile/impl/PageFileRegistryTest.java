/**
 * 
 */
package com.example.nio.pagefile.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.example.nio.core.impl.SimpleTrack;
import com.example.nio.pagefile.impl.mac.MacPageFile;

/**
 * @author chira
 * 
 */
public class PageFileRegistryTest {

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
	 * {@link com.example.nio.pagefile.impl.PageFileRegistry#isPageFileInRegistry(java.lang.String)}
	 * .
	 */
	@Test
	public final void testIsPageFileInRegistry() {

		assertTrue(!PageFileRegistry.isPageFileInRegistry(null));
		assertTrue(!PageFileRegistry.isPageFileInRegistry(""));

		PageFileRegistry.addPageFileToRegistry(new MacPageFile("samplePage",
				new SimpleTrack("sampleTrack"), null));

		assertTrue(PageFileRegistry.isPageFileInRegistry("samplePage"));
	}

	/**
	 * Test method for
	 * {@link com.example.nio.pagefile.impl.PageFileRegistry#addPageFileToRegistry(com.example.nio.pagefile.PageFile)}
	 * .
	 */
	@Test
	public final void testAddPageFileToRegistry() {

		try {
			PageFileRegistry.addPageFileToRegistry(null);
			fail("Expected an exception!");
		} catch (IllegalArgumentException ex) {
			// success
		} catch (Exception ex) {
			fail("Did not expect this exception: " + ex);
		}

		PageFileRegistry.addPageFileToRegistry(new MacPageFile("originalPage",
				new SimpleTrack("sampleTrack"), null));
		try {
			PageFileRegistry.addPageFileToRegistry(new MacPageFile(
					"originalPage", new SimpleTrack("sampleTrack"), null));
			fail("Expected an exception indicating a duplicate entry is being made.");
		} catch (IllegalArgumentException ex) {
			// success
		} catch (Exception ex) {
			fail("Did not expect this exception: " + ex);
		}
	}

	/**
	 * Test method for
	 * {@link com.example.nio.pagefile.impl.PageFileRegistry#removePageFileFromRegistry(String)
	 * .
	 */
	@Test
	public final void testRemovePageFileFromRegistry() {

		PageFileRegistry.addPageFileToRegistry(new MacPageFile(
				"pageFileToBeRemoved", new SimpleTrack("sampleTrack"), null));

		PageFileRegistry.removePageFileFromRegistry("pageFileToBeRemoved");

		try {
			PageFileRegistry.removePageFileFromRegistry(null);
			fail("Expected an exception");
		} catch (IllegalArgumentException ex) {
			// success
		} catch (Exception ex) {
			fail("Did not expect this exception: " + ex);
		}
	}
}
