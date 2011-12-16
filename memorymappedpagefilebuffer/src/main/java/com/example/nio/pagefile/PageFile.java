/**
 * 
 */
package com.example.nio.pagefile;

import java.nio.file.Path;

import com.example.nio.core.Track;

/**
 * 
 * 
 * @author chira
 */
public interface PageFile {

	/**
	 * Get the {@link Path} to the resource file.
	 * 
	 * @return The absolute path to the resource.
	 */
	public Path getPathToResource();

	/**
	 * Get the {@link Track} that this page file represents.
	 * 
	 * @return The track instance representing this page file.
	 */
	public Track getTrack();
}