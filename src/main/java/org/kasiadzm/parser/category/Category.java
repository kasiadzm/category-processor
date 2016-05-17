package org.kasiadzm.parser.category;

import java.util.List;

/**
 * @author kasiadzm
 */
public interface Category {

	/** The line separator */
	public static final String separator = System.getProperty("line.separator");

	/**
	 * Gets the name of the category.
	 * 
	 * @return the name of the category
	 */
	public String getName();

	/**
	 * Displays the elements of the category.
	 * 
	 * @param elements
	 *            the list of the category elements
	 */
	public void display(List<String> elements);

}
