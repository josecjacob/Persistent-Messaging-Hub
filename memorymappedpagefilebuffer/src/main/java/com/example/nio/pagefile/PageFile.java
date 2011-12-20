/**
 * 
 */
package com.example.nio.pagefile;

import java.nio.file.Path;
import java.util.List;

import com.example.nio.core.Track;

/**
 * A Page File represents a unit of persistence that is associated with a track.
 * There can be multiple page files per track, each holding pages that represent
 * a persistent message.
 * 
 * The underlying resource, typically is a file that is memory mapped into to
 * speed the process of integration.
 * 
 * Each page file is uniquely identified within the scope of a persistence hub
 * by it's page file name: {@link PageFile#getPageFileName()}.
 * 
 * @author chira
 */
public interface PageFile {

	/**
	 * Get the name of the page file. This is unique within the scope of a
	 * persistence hub.
	 * 
	 * @return The page file name.
	 */
	public String getPageFileName();

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

	/**
	 * Return a queue of {@link Page}s associated with this page file. Each page
	 * is a unit of storage associated with the page file.
	 * 
	 * @return
	 */
	public List<Page> getPages();
}