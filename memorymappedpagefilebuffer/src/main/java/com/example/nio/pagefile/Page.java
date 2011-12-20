/**
 * 
 */
package com.example.nio.pagefile;

import com.example.nio.core.Message;

/**
 * Represents a page within a {@link PageFile}. A page is a sequence of bytes,
 * but with data about a message stored within it.
 * 
 * This unit can be reused by re filling and draining it as one pleases.
 * 
 * A message can be split among multiple pages if size does not permit. This is
 * an implementation detail for the {@link Message} object.
 * 
 * @author chira
 */
public interface Page {

	/**
	 * Get the size of this page and how much data can be stored within it.
	 * 
	 * @return
	 */
	public int getPageSize();
}
