package com.example.nio.pagefile;

/**
 * The list of general constants that we will use within the project. There can
 * be specific constants that represent platform specific information.
 * 
 * @author chira
 */
public interface Constants {

	/*
	 * Page File related constants.
	 */
	public static final int BYTE = 1;
	public static final int KILO_BYTE = 1024 * BYTE;
	public static final long PAGE_SIZE = 4 * KILO_BYTE;
	public static final long NUMBER_OF_PAGES = 1024;
	public static final long SIZE_OF_PAGE_FILE = NUMBER_OF_PAGES * PAGE_SIZE;
}
