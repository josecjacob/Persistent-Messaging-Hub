package com.example.nio.core.impl;

import com.example.nio.core.Track;
import com.example.nio.pagefile.PageFile;

/**
 * This factory will be used to prepare various tracks within the system. Each
 * track is backed by a {@link PageFile} for it's persistence needs.
 * 
 * @author chira
 */
public class TrackFactory {

	/**
	 * @param trackName
	 * @param underlyingPageFile
	 * @return
	 */
	public Track createTrack(String trackName, PageFile underlyingPageFile) {
		return null;
	}
}
