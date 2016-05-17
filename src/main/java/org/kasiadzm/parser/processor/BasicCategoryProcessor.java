package org.kasiadzm.parser.processor;

import java.util.List;

import org.kasiadzm.parser.category.Category;
import org.kasiadzm.parser.datasource.DataSource;

/**
 * @author kasiadzm
 */
public class BasicCategoryProcessor implements CategoryProcessor {

	/** The category datasource */
	private DataSource datasource;

	/**
	 * @param datasource
	 *            the category datasource
	 */
	public BasicCategoryProcessor(DataSource datasource) {
		this.datasource = datasource;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kasiadzm.parser.processor.CategoryProcessor#process(org.kasiadzm.
	 * parser.category.Category)
	 */
	public void process(Category category) {
		List<String> elements = getCategoryElements(category.getName());
		category.display(elements);
	}

	/**
	 * Gets the list of the category elements
	 * 
	 * @param categoryName
	 *            the name of the category
	 * @return the list of the category elements
	 */
	private List<String> getCategoryElements(String categoryName) {
		return datasource.getElementsByCategory(categoryName);
	}

}
