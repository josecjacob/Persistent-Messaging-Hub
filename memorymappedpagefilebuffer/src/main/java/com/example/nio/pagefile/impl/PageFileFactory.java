/**
 * 
 */
package com.example.nio.pagefile.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import com.example.nio.core.Track;
import com.example.nio.pagefile.PageFile;
import com.example.nio.pagefile.util.PageFileUtility;
import com.google.common.base.Preconditions;

/**
 * Create a page file given it's absolute path. We can also decide to overwrite
 * the file, if it so demands.
 * 
 * @author chira
 */
public class PageFileFactory {

	private static volatile PageFileFactory singleton = new PageFileFactory();

	/**
	 * Get the singeton instance of the {@link PageFileFactory} class.
	 * 
	 * @return An instance of {@link PageFileFactory}.
	 */
	public static PageFileFactory getSingletonInstance() {
		return singleton;
	}

	/**
	 * The private constructor, to prevent the creation of the factory from
	 * outside of this class.
	 */
	private PageFileFactory() {
	}

	/**
	 * @param absolutePath
	 *            The absolute path to the file we intent to build.
	 * @param overwrite
	 *            Do we intent to overwrite the underlying file.
	 * @return An instance of {@link PageFile}.
	 * @throws IOException
	 */
	public PageFile createPageFile(final String absolutePath,
			final boolean overwrite) throws IOException {
		if (overwrite) {
			Preconditions.checkState(new File(absolutePath).exists());

			return new PageFile() {

				@Override
				public Track getTrack() {
					return null;
				}

				@Override
				public Path getPathToResource() {
					// TODO Auto-generated method stub
					return new File(absolutePath).toPath();
				}
			};

		} else {
			Preconditions.checkState(!(new File(absolutePath).exists()));

			PageFileUtility.preparePageFile(absolutePath);
			return new PageFile() {

				@Override
				public Track getTrack() {
					return null;
				}

				@Override
				public Path getPathToResource() {
					return new File(absolutePath).toPath();
				}
			};
		}
	}
}