package com.example.nio.pagefile.impl.mac;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.PriorityQueue;
import java.util.Queue;

import com.example.nio.pagefile.Constants;
import com.example.nio.pagefile.util.PageFileUtility;
import com.google.common.base.Preconditions;

/**
 * The Mac specific implementation of the {@link PageFileUtility} interface.
 * 
 * @author chira
 */
public class PageFileMacUtility implements PageFileUtility {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.nio.pagefile.util.PageFileUtility#preparePageFile(java.lang
	 * .String)
	 */
	@Override
	public void preparePageFile(String pageFileLocation) throws IOException {

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.nio.pagefile.util.PageFileUtility#
	 * createReadWriteMemoryMappedFileBufferQueue(java.nio.file.Path, long,
	 * long)
	 */
	@Override
	public Queue<ByteBuffer> createReadWriteMemoryMappedFileBufferQueue(
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
