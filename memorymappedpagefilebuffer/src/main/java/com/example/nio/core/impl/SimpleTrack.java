/**
 * 
 */
package com.example.nio.core.impl;

import java.util.List;
import java.util.Map;

import com.example.nio.core.Track;
import com.example.nio.pagefile.PageFile;

/**
 * This is an example track used to validate the design of the system.
 * 
 * @author chira
 * 
 */
public class SimpleTrack implements Track {

	private String trackName;
	private Map<String, PageFile> backingResources;

	/**
	 * The constructor for preparing the track, which takes the track name.
	 * 
	 * @param trackName
	 *            The track name.
	 */
	public SimpleTrack(String trackName) {
		this.trackName = trackName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.nio.core.Track#getTrackName()
	 */
	@Override
	public String getTrackName() {
		return this.trackName;
	}

	/**
	 * Add a page file to this track. If the track is already present, an
	 * {@link IllegalArgumentException} will be thrown.
	 * 
	 * @param aPageFile
	 *            The page file to be added.
	 */
	public void addPageFile(PageFile aPageFile) {
		if (aPageFile != null && aPageFile.getPageFileName() != null
				&& !aPageFile.getPageFileName().trim().equals("")
				&& !backingResources.containsKey(aPageFile.getPageFileName()))
			backingResources.put(aPageFile.getPageFileName(), aPageFile);
		else
			throw new IllegalArgumentException(
					"Page file with the name exists in the system!");
	}

	/**
	 * Remove a page file with it's unique id, the page file name.
	 * 
	 * @param aPageFile
	 */
	public void removePageFile(String aPageFileName) {
		backingResources.remove(aPageFileName);
	}
}
