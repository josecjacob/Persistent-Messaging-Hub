/**
 * 
 */
package com.example.nio.pagefile.impl.mac;

import java.nio.file.Path;
import java.util.List;

import com.example.nio.core.Track;
import com.example.nio.pagefile.Page;
import com.example.nio.pagefile.PageFile;

/**
 * The Mac based platform implementation of the {@link PageFile} interface.
 * 
 * @author chira
 */
public class MacPageFile implements PageFile {

	private Path resource;
	private Track track;
	private String pageFileName;

	/**
	 * The constructor to initialize the page file entity.
	 * 
	 * @param pageFileName
	 *            The name of this page file.
	 * @param resource
	 *            The path to the underlying resource.
	 * @param track
	 *            The track for this page file.
	 */
	public MacPageFile(String pageFileName, Track track, Path resource) {

		this.pageFileName = pageFileName;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.nio.pagefile.PageFile#getPages()
	 */
	@Override
	public List<Page> getPages() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.example.nio.pagefile.PageFile#getPageFileName()
	 */
	@Override
	public String getPageFileName() {
		return pageFileName;
	}

}
