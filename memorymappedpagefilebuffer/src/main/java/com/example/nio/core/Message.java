/**
 * 
 */
package com.example.nio.core;

import java.util.List;
import java.util.Map;

import com.example.nio.pagefile.Page;

/**
 * An IMessage instance will be the core of the messaging infrastructure. The
 * message has a message id, used to uniquely identify this message, within the
 * scope of a {@link Track}. The message also has a metadata field used to store
 * more information about it.
 * 
 * A message is stored within a single {@link Page} or can be split amoung
 * multiple pages if size is a constrain.
 * 
 * Guide to implementers of this interface:
 * 
 * A message, within the scope of a {@link Track}, is uniquely identified in the
 * system using the message id.
 * 
 * @author chira
 */
public interface Message {

	/**
	 * Get the {@link Track} for this message.
	 * 
	 * @return The trac for the message.
	 */
	public Track getMessageTrack();

	/**
	 * Get the message id, uniquely identifying itself in the system.
	 * 
	 * @return The message id.
	 */
	public String getMessageId();

	/**
	 * The metadata map identifying more details of the message.
	 * 
	 * @return An immutable map of metadata elements.
	 */
	public Map<String, String> getMetadata();

	/**
	 * The data stored in the message.
	 * 
	 * @return An array of bytes representing the real message.
	 */
	public byte[] getMessageData();

	/**
	 * The pages used to store this message.
	 * 
	 * @return An list of {@link Page}s representing the real message.
	 */
	public List<Page> getMessageUnits();

}
