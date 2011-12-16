/**
 * 
 */
package com.example.nio.core;

/**
 * This interface identifies a unique stream, used to drop {@link Message}s into
 * the messaging hub.
 * 
 * @author chira
 */
public interface Track {

	/**
	 * Gets the name of the track.
	 * 
	 * @return the trackName.
	 */
	public String getTrackName();
}