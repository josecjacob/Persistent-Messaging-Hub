/**
 * 
 */
package com.example.nio.pagefile.impl;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;

import com.example.nio.pagefile.PageFile;

/**
 * The page file registry will help maintain a list of unique page files within
 * the scope of a persistence hub.
 * 
 * @author chira
 */
public final class PageFileRegistry {

	/**
	 * The page file registry data structure.
	 */
	private static Map<String, PageFile> pageFileRegistry = new ConcurrentHashMap<String, PageFile>();

	/**
	 * Is the given page file within the registry or not.
	 * 
	 * @param pageFileName
	 *            The page file name to be verified.
	 * @return If it's present true and if it's not or the page file name
	 *         supplied is null or empty, we return false.
	 */
	public static boolean isPageFileInRegistry(String pageFileName) {

		if (pageFileName != null && !pageFileName.trim().equals("")
				&& pageFileRegistry.containsKey(pageFileName))
			return true;
		else
			return false;
	}

	/**
	 * Add the given page file to the registry.
	 * 
	 * @param pageFileToBeAdded
	 *            The page file to be added.
	 */
	public static void addPageFileToRegistry(PageFile pageFileToBeAdded) {
		if (pageFileToBeAdded != null
				&& pageFileToBeAdded.getPageFileName() != null
				&& !pageFileToBeAdded.getPageFileName().trim().equals("")
				&& !isPageFileInRegistry(pageFileToBeAdded.getPageFileName()))
			pageFileRegistry.put(pageFileToBeAdded.getPageFileName(),
					pageFileToBeAdded);
		else
			throw new IllegalArgumentException(
					"The page file to be added was either null"
							+ "or it's unique page file name was null, "
							+ "empty or was already present in the registry");
	}

	/**
	 * Remove the given page identified by it's unique
	 * {@link PageFile#getPageFileName()} attribute.
	 * 
	 * @param pageFileName
	 *            The page to be removed.
	 */
	public static void removePageFileFromRegistry(String pageFileName) {

		if (pageFileName != null && !pageFileName.trim().equals("")
				&& isPageFileInRegistry(pageFileName))
			pageFileRegistry.remove(pageFileName);
		else
			throw new IllegalArgumentException(
					"Did not find the given page file with name: "
							+ pageFileName + " within the registry");
	}
}
