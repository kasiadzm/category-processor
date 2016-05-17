package org.kasiadzm.parser.datasource;

import java.util.List;

/**
 * @author kasiadzm
 */
public interface DataSource {

	/**
	 * Gets the list of the category elements
	 * 
	 * @param categoryName
	 *            the name of the category
	 * @return the list of the category elements
	 */
	public List<String> getElementsByCategory(String categoryName);

}
