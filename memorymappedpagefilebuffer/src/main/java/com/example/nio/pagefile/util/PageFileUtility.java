package com.example.nio.pagefile.util;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.List;

/**
 * All platform specific page file utilities will implement this interface and
 * help prepare and create page files.
 * 
 * The two methods:
 * 
 * {@link PageFileUtility#preparePageFile(String)} and
 * {@link PageFileUtility#createReadWriteMemoryMappedFileBufferQueue(Path, long, long)}
 * 
 * are to be implemented in a platform specific manner.
 * 
 * @author chira
 */
public interface PageFileUtility {

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
	public void preparePageFile(String pageFileLocation) throws IOException;

	/**
	 * Create a queue of buffers that slice a memory mapped, read write file.
	 * Each of these buffers represent a section of the memory mapped file. The
	 * number of files and the size are specified as arguments.
	 * 
	 * Preconditions:
	 * 
	 * 1) The file should exist. 2) The file's size should be the same as
	 * pageSize * numberOfPages.
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
	public List<ByteBuffer> createReadWriteMemoryMappedFileBufferQueue(
			Path pageFileLocation, long pageSize, long numberOfPages)
			throws IOException;
}