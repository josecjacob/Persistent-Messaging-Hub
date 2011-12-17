package com.example.nio.pagefile.impl;

import java.io.IOException;

import com.example.nio.core.Track;
import com.example.nio.pagefile.PageFile;

/**
 * The page file factory will be used to create {@link PageFile}s. It will be
 * implemented by platform specific classes.
 * 
 * @author chira
 */
public interface PageFileFactory {

	/**
	 * @param absolutePath
	 *            The absolute path to the file we intent to build.
	 * @param overwrite
	 *            Do we intent to overwrite the underlying file.
	 * @return An instance of {@link PageFile}.
	 * @throws IOException
	 */
	public PageFile createPageFile(final Track track,
			final String absolutePath, final boolean overwrite)
			throws IOException;
}