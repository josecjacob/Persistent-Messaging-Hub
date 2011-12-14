/**
 * 
 */
package com.example.nio.pagedfileio.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author chira
 * 
 */
public class PageFileUtilityTest {

	File bigFileInTemp = new File("/tmp/bigfile.txt");

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
	 * Make sure the intended file we plan to act upon does not exist.
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		if (bigFileInTemp.exists())
			bigFileInTemp.delete();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test method for
	 * {@link com.example.nio.pagedfileio.util.PageFileUtility#preparePageFile(java.nio.file.Path)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testPreparePageFile() throws IOException {

		PageFileUtility.preparePageFile(bigFileInTemp.getAbsolutePath());
		assertTrue(bigFileInTemp.exists());
		assertEquals(Constants.SIZE_OF_PAGE_FILE,
				Files.size(bigFileInTemp.toPath()));
	}

/**
	 * Test method for
	 * {@link com.example.nio.pagedfileio.util.PageFileUtility#createMemoryMappedFileBufferQueue(java.nio.file.Path, long, long)
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMemoryMappedFileDirectBufferWithSplitBufferOfPageSize()
			throws IOException {

		PageFileUtility.preparePageFile(bigFileInTemp.getAbsolutePath());
		Queue<ByteBuffer> directBufferQueues = PageFileUtility
				.createMemoryMappedFileBufferQueue(bigFileInTemp.toPath(),
						Constants.PAGE_SIZE, Constants.NUMBER_OF_PAGES);
		assertEquals(Constants.NUMBER_OF_PAGES, directBufferQueues.size());
		assertTrue(directBufferQueues instanceof PriorityQueue<?>);
		int i = 1;
		for (ByteBuffer aUnit : directBufferQueues) {
			assertTrue(aUnit.isDirect());
			assertEquals("At iteration: " + i++ + " size did not match.",
					Constants.PAGE_SIZE, aUnit.capacity());
		}
	}
}
