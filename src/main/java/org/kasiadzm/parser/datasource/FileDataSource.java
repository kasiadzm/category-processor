package org.kasiadzm.parser.datasource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The text file implementation of <code>DataSource</code>
 * 
 * @author kasiadzm
 */
public class FileDataSource implements DataSource {

	/** The container of the categories with the corresponding elements list */
	private Map<String, List<String>> categoryMap = new HashMap<String, List<String>>();

	/**
	 * The constructor.
	 * 
	 * @param pathToFile
	 *            the path to the input file
	 * @param categories
	 *            the predefined categories
	 */
	public FileDataSource(String pathToFile, List<String> categories) {
		parseFileToCategories(pathToFile, categories);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.kasiadzm.parser.datasource.DataSource#getElementsByCategory(java.
	 * lang.String)
	 */
	public List<String> getElementsByCategory(String categoryName) {
		List<String> elements = categoryMap.get(categoryName.toLowerCase());
		if (elements == null) {
			elements = Collections.<String> emptyList();
		}
		return elements;
	}

	/**
	 * Parses the file to the predefined categories.
	 *
	 * @param pathToFile
	 *            the path to the input file
	 * @param categories
	 *            the predefined categories
	 */
	private void parseFileToCategories(String pathToFile, List<String> categories) {
		for (String category : categories) {
			categoryMap.put(category.toLowerCase(), new ArrayList<String>());
		}

		FileInputStream fis = null;
		Scanner scanner = null;
		try {
			fis = new FileInputStream(pathToFile);
			scanner = new Scanner(fis);

			putLinesToMap(scanner);

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("The value of the path to file argument is invalid", e);
		} finally {
			if (scanner != null) {
				scanner.close();
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// nothing to do
				}
			}

		}
	}

	/**
	 * Iterates thru scanner and puts lines to map.
	 */
	private void putLinesToMap(Scanner scanner) {
		String strLine = "";
		List<String> currentCategory = null;

		while (scanner.hasNextLine()) {
			strLine = scanner.nextLine().trim();

			if (isValidElement(strLine) && !containSpecialCharacter(strLine)) {
				if (categoryMap.containsKey(strLine.toLowerCase())) {
					currentCategory = categoryMap.get(strLine.toLowerCase());
				} else {
					currentCategory.add(strLine);
				}
			}
		}
	}

	/**
	 * Checks if the element is valid
	 * 
	 * @param element
	 *            the element to check
	 * @return <code>true</code> if the element is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isValidElement(String element) {
		if (element.trim().isEmpty() || element.contains(" ")) {
			return false;
		}
		return true;
	}

	/**
	 * Checks if the string contains a special character
	 * 
	 * @param str
	 *            the string to check
	 * @return <code>true</code> if the string contains a special character,
	 *         otherwise <code>false</code>
	 */
	private boolean containSpecialCharacter(String str) {
		return str.matches("[^A-Za-z0-9 ]");
	}

}
