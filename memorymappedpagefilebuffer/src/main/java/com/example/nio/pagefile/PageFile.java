/**
 * 
 */
package com.example.nio.pagefile;

import java.nio.file.Path;

import com.example.nio.core.Track;

/**
 * A Page File represents a unit of persistence that is associated with a track.
 * There can be multiple page files per track, each holding pages that represent
 * a persistent message.
 * 
 * The underlying resource, typically is a file that is memory mapped into to
 * speed the process of integration.
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