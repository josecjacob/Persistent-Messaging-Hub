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
	 * SingletonHolder is loaded on the first execution of
	 * {@link TrackFactory#getInstance()} or the first access to
	 * SingletonHolder.singleton, not before.
	 */
	private static class SingletonHolder {
		private volatile static TrackFactory singleton = new TrackFactory();
	}

	/**
	 * Get the singleton instance of the {@link TrackFactory} class.
	 * 
	 * @return An instance of {@link TrackFactory}.
	 */
	public static TrackFactory getInstance() {
		return SingletonHolder.singleton;
	}

	/**
	 * The private constructor, to prevent the creation of the factory from
	 * outside of this class.
	 */
	private TrackFactory() {
	}

	/**
	 * Create a {@link Track} given the track name.
	 * 
	 * @param trackName
	 * @param underlyingPageFile
	 * @return
	 */
	public Track createTrack(final String trackName) {

		return new SimpleTrack(trackName);
	}
}
