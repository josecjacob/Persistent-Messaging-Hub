/**
 * 
 */
package com.example.nio.pagefile.impl.mac;

import java.io.File;
import java.nio.file.Path;

import com.example.nio.core.Track;
import com.example.nio.pagefile.PageFile;

/**
 * The Mac based platform implementation of the {@link PageFile} interface.
 * 
 * @author chira
 */
public class MacPageFile implements PageFile {

	private Path resource;
	private Track track;

	/**
	 * The constructor to initialize the page file entity.
	 * 
	 * @param resource
	 *            The path to the underlying resource.
	 * @param track
	 *            The track for this page file.
	 */
	public MacPageFile(Track track, Path resource) {

		this.resource = resource;
		this.track = track;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.nio.pagefile.PageFile#getPathToResource()
	 */
	@Override
	public Path getPathToResource() {
		return resource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.nio.pagefile.PageFile#getTrack()
	 */
	@Override
	public Track getTrack() {
		return track;
	}

}
