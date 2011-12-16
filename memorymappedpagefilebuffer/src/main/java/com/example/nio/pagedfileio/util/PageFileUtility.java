package com.example.nio.pagedfileio.util;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.Queue;

import com.google.common.base.Preconditions;

public class PageFileUtility {

	/**
	 * Prepare a page file that will be used as the backing store for all queued
	 * operations.
	 * 
	 * @param pageFileLocation
	 *            The fully qualified path to the file. This should be the
	 *            absolute path to the file.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	public static void preparePageFile(String pageFileLocation)
			throws IOException {

		Preconditions.checkState(!new File(pageFileLocation).exists());

		ProcessBuilder launcher = new ProcessBuilder();
		launcher.redirectErrorStream(true);

		launcher.command("/bin/dd", // The Command
				"if=/dev/zero", // The input file, chose this for speed
				"of=" + pageFileLocation, // The output file
				"bs=" + Constants.PAGE_SIZE, // The page size
				"count=" + Constants.NUMBER_OF_PAGES); // The total pages we
														// need in the file
		Process dd = launcher.start(); // And launch a new process

		// Wait to be sure.
		try {
			dd.waitFor();
		} catch (InterruptedException ex) {
			throw new IllegalStateException(ex);
		}
	}

	/**
	 * Create a queue of buffers that slice a memory mapped, read write file.
	 * Each of these buffers represent a section of the memory mapped file. The
	 * number of files and the size are specified as arguments.
	 * 
	 * Preconditions:
	 * 
	 * <p>
	 * 1) The file should exist.
	 * <p>
	 * 2) The file's size should be the same as pageSize * numberOfPages.
	 * 
	 * @param pageFileLocation
	 *            The fully qualified path to the file. This should be the
	 *            absolute path to the file.
	 * @param pageSize
	 *            The size of each page in the file.
	 * @param numberOfPages
	 *            The number of pages within the file.
	 * @return The queue of buffers that are slices of the memory mapped file,
	 *         each the size of the page file.
	 * @throws IOException
	 *             If an I/O error occurs.
	 */
	public static Queue<ByteBuffer> createReadWriteMemoryMappedFileBufferQueue(
			Path pageFileLocation, long pageSize, long numberOfPages)
			throws IOException {
		Preconditions.checkState(pageFileLocation.toFile().exists());
		Preconditions.checkState(pageSize * numberOfPages == Files
				.size(pageFileLocation));
		FileChannel rwChannel = new RandomAccessFile(pageFileLocation.toFile(),
				"rws").getChannel();

		Queue<ByteBuffer> buffers = new PriorityQueue<ByteBuffer>();
		for (int i = 0; i < Constants.NUMBER_OF_PAGES; i++)
			buffers.add(rwChannel.map(FileChannel.MapMode.READ_WRITE, i
					* pageSize, pageSize));
		return buffers;
	}
}
