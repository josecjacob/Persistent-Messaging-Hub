/**
 * 
 */
package com.example.nio.pagefile.impl.mac;

import java.io.File;
import java.io.IOException;

import com.example.nio.core.Track;
import com.example.nio.pagefile.PageFile;
import com.example.nio.pagefile.impl.PageFileFactory;
import com.example.nio.pagefile.util.PageFileUtility;
import com.google.common.base.Preconditions;

/**
 * Create a page file for the Mac platform given it's absolute path. We can also
 * decide to overwrite the file, if it so demands.
 * 
 * @author chira
 */
public class PageFileMacFactory implements PageFileFactory {

	private PageFileUtility platformUtility;

	/**
	 * SingletonHolder is loaded on the first execution of
	 * {@link PageFileMacFactory#getInstance()} or the first access to
	 * SingletonHolder.singleton, not before.
	 */
	private static class SingletonHolder {
		private static volatile PageFileFactory singleton = new PageFileMacFactory();
	}

	/**
	 * Get the singeton instance of the {@link PageFileMacFactory} class.
	 * 
	 * @return An instance of {@link PageFileMacFactory}.
	 */
	public static PageFileFactory getSingletonInstance() {
		return SingletonHolder.singleton;
	}

	/**
	 * The private constructor, to prevent the creation of the factory from
	 * outside of this class.
	 */
	private PageFileMacFactory() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.example.nio.pagefile.impl.PageFileFactory#createPageFile(com.example
	 * .nio.core.Track, java.lang.String, boolean)
	 */
	@Override
	public PageFile createPageFile(final Track track,
			final String absolutePath, final boolean overwrite)
			throws IOException {
		File pageFile = new File(absolutePath);
		if (overwrite) {
			Preconditions.checkState(pageFile.exists());

			return new MacPageFile(pageFile.getAbsolutePath(), track,
					pageFile.toPath());
		} else {
			Preconditions.checkState(!(new File(absolutePath).exists()));

			this.getPlatformUtility().preparePageFile(absolutePath);
			return new MacPageFile(pageFile.getAbsolutePath(), track,
					pageFile.toPath());
		}
	}

	/**
	 * Get the platform specific utility for operations.
	 * 
	 * @return The Platform Specific Utility
	 */
	public PageFileUtility getPlatformUtility() {
		return platformUtility;
	}

	/**
	 * Set the platform specific utility for operations.
	 * 
	 * @param platformUtility
	 *            The Platform Specific Utility to set
	 */
	public void setPlatformUtility(PageFileUtility platformUtility) {
		this.platformUtility = platformUtility;
	}
}