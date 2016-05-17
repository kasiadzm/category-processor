package org.kasiadzm.parser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.kasiadzm.parser.category.AnimalCategory;
import org.kasiadzm.parser.category.CarCategory;
import org.kasiadzm.parser.category.Category;
import org.kasiadzm.parser.category.NumberCategory;
import org.kasiadzm.parser.datasource.DataSource;
import org.kasiadzm.parser.datasource.FileDataSource;
import org.kasiadzm.parser.processor.BasicCategoryProcessor;
import org.kasiadzm.parser.processor.CategoryProcessor;

/**
 * @author kasiadzm
 */
public class CategoryProcessorClient {
	/** The user working directory */
	private final static String userDir = System.getProperty("user.dir");

	/** The registered categories container */
	private static HashMap<String, Class<? extends Category>> registeredCategories;

	static {
		registeredCategories = new HashMap<String, Class<? extends Category>>();

		registeredCategories.put("ANIMALS", AnimalCategory.class);
		registeredCategories.put("NUMBERS", NumberCategory.class);
		registeredCategories.put("CARS", CarCategory.class);
	}

	/**
	 * Processes the registered categories
	 * 
	 * @param pathToInputFile
	 *            the path to the input file
	 * @param categories
	 *            the list of the categories
	 */
	public void processCategories(String pathToInputFile, List<String> categories) {
		DataSource dataSource = new FileDataSource(pathToInputFile, categories);
		CategoryProcessor processor = new BasicCategoryProcessor(dataSource);

		for (String categoryName : categories) {
			Class<? extends Category> categoryClass = registeredCategories.get(categoryName);
			if (categoryClass != null) {
				try {
					Category category = categoryClass.getConstructor(String.class).newInstance(categoryName);
					processor.process(category);
				} catch (Exception e) {
					throw new RuntimeException("Can't create an instance of the category", e);
				}
			}
		}
	}

	/**
	 * Gets the path to input file from main args
	 * 
	 * @param args
	 *            main args
	 * @return the path to input file
	 */
	public static String getPathToInputFile(String[] args) {
		String pathToInputFile = new StringBuilder(userDir).append("/src/main/resources/input.txt").toString();
		if (args.length >= 1) {
			pathToInputFile = new StringBuilder(userDir).append(args[0]).toString();
		}
		return pathToInputFile;
	}

	/**
	 * Gets the list of the categories from main args
	 * 
	 * @param args
	 *            main args
	 * @return the list of the categories
	 */
	public static List<String> getCategoties(String[] args) {
		List<String> categories = Arrays.asList("ANIMALS", "NUMBERS", "CARS");
		if (args.length >= 2) {
			categories = Arrays.asList(args[1].split(","));
		}
		return categories;
	}

	public static void main(String[] args) {
		new CategoryProcessorClient().processCategories(getPathToInputFile(args), getCategoties(args));
	}
}
