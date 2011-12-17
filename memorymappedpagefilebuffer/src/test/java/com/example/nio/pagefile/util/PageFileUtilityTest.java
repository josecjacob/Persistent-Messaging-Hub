/**
 * 
 */
package com.example.nio.pagefile.util;

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

import com.example.nio.pagefile.Constants;
import com.example.nio.pagefile.impl.mac.PageFileMacUtility;

/**
 * Tests for {@link PageFileMacUtility}.
 * 
 * @author chira
 */
public class PageFileUtilityTest {

	private static final File bigFileInTemp = new File("/tmp/bigfile.txt");
	private static final File bigFileInTempDuplicate = new File(
			"/tmp/bigfileduplicate.txt");
	private static final ByteBuffer sampleBuffer = ByteBuffer
			.allocate((int) Constants.PAGE_SIZE);
	private static final ByteBuffer zeroFilledBuffer = ByteBuffer
			.allocate((int) Constants.PAGE_SIZE);

	private PageFileUtility classUnderTest;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		byte sampleByte = 0;
		while (sampleBuffer.hasRemaining())
			sampleBuffer.put(sampleByte++);
		sampleBuffer.rewind();

		while (zeroFilledBuffer.hasRemaining())
			zeroFilledBuffer.put((byte) 0);
		zeroFilledBuffer.rewind();
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

		if (bigFileInTempDuplicate.exists())
			bigFileInTempDuplicate.delete();

		Thread.sleep(100);

		classUnderTest = new PageFileMacUtility();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		// if (bigFileInTemp.exists())
		// bigFileInTemp.delete();
		//
		// if (bigFileInTempDuplicate.exists())
		// bigFileInTempDuplicate.delete();
	}

	/**
	 * Test method for
	 * {@link com.example.nio.pagefile.impl.mac.PageFileMacUtility#preparePageFile(java.nio.file.Path)}
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testPreparePageFile() throws IOException {

		classUnderTest.preparePageFile(bigFileInTemp.getAbsolutePath());
		assertTrue(bigFileInTemp.exists());
		assertEquals(Constants.SIZE_OF_PAGE_FILE,
				Files.size(bigFileInTemp.toPath()));
	}

/**
	 * Test method for
	 * {@link com.example.nio.pagefile.impl.mac.PageFileMacUtility#createReadWriteMemoryMappedFileBufferQueue(java.nio.file.Path, long, long)
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testMemoryMappedFileDirectBufferWithSplitBufferOfPageSize()
			throws IOException {

		classUnderTest.preparePageFile(bigFileInTemp.getAbsolutePath());
		Queue<ByteBuffer> directBufferQueues = classUnderTest
				.createReadWriteMemoryMappedFileBufferQueue(
						bigFileInTemp.toPath(), Constants.PAGE_SIZE,
						Constants.NUMBER_OF_PAGES);
		assertEquals(Constants.NUMBER_OF_PAGES, directBufferQueues.size());
		assertTrue(directBufferQueues instanceof PriorityQueue<?>);
		int i = 1;
		for (ByteBuffer aUnit : directBufferQueues) {
			assertTrue(aUnit.isDirect());
			assertEquals("At iteration: " + i + " size did not match.",
					Constants.PAGE_SIZE, aUnit.capacity());
			assertTrue("At iteration: " + i++
					+ ", expected a zero filled buffer, but it was not.",
					zeroFilledBuffer.equals(aUnit));
		}
	}

/**
	 * Test method for
	 * {@link com.example.nio.pagefile.impl.mac.PageFileMacUtility#createReadWriteMemoryMappedFileBufferQueue(java.nio.file.Path, long, long)
	 * .
	 * 
	 * @throws IOException
	 */
	@Test
	public void testFillAndVerifyQueueBackedByAMemoryMappedFile()
			throws IOException {

		classUnderTest.preparePageFile(bigFileInTemp.getAbsolutePath());
		Queue<ByteBuffer> directBufferQueues = classUnderTest
				.createReadWriteMemoryMappedFileBufferQueue(
						bigFileInTemp.toPath(), Constants.PAGE_SIZE,
						Constants.NUMBER_OF_PAGES);
		assertEquals(Constants.NUMBER_OF_PAGES, directBufferQueues.size());
		assertTrue(directBufferQueues instanceof PriorityQueue<?>);
		for (ByteBuffer aUnit : directBufferQueues)
			fillBufferWithRawData(aUnit);

		int i = 1;
		for (ByteBuffer aUnit : directBufferQueues)
			assertTrue("At iteration: " + i++ + " buffers were not equal.",
					sampleBuffer.equals(aUnit));

		classUnderTest
				.preparePageFile(bigFileInTempDuplicate.getAbsolutePath());
		Queue<ByteBuffer> directBufferQueuesForDuplicate = classUnderTest
				.createReadWriteMemoryMappedFileBufferQueue(
						bigFileInTempDuplicate.toPath(), Constants.PAGE_SIZE,
						Constants.NUMBER_OF_PAGES);

		i = 1;
		for (ByteBuffer aUnit : directBufferQueuesForDuplicate)
			assertTrue("At iteration: " + i++ + " buffers were not equal.",
					zeroFilledBuffer.equals(aUnit));

		for (ByteBuffer aDuplicateUnit : directBufferQueuesForDuplicate)
			aDuplicateUnit.put(directBufferQueues.poll());

		i = 1;
		for (ByteBuffer aDuplicateUnit : directBufferQueuesForDuplicate) {
			aDuplicateUnit.flip();
			assertTrue("At iteration: " + i++ + " size did not match.",
					sampleBuffer.equals(aDuplicateUnit));
		}
	}

	/**
	 * Fill the buffer with raw data that's already set.
	 * 
	 * @param aUnit
	 *            The buffer to be duplicated.
	 */
	private void fillBufferWithRawData(ByteBuffer aUnit) {
		sampleBuffer.rewind();
		aUnit.put(sampleBuffer);
		sampleBuffer.rewind();
		aUnit.rewind();
	}
}
