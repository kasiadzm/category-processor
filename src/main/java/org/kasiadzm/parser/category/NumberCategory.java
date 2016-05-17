package org.kasiadzm.parser.category;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.kasiadzm.parser.output.Printer;
import org.kasiadzm.parser.output.SystemOutPrinter;

/**
 * The number implementation of <code>Category</code>
 * 
 * @author kasiadzm
 */
public class NumberCategory implements Category {

	/** The name of the category */
	private String name;

	/** The printer to display the category elements */
	private Printer printer;

	/**
	 * @param name
	 *            the name of the category
	 */
	public NumberCategory(String name) {
		this(name, new SystemOutPrinter());
	}

	/**
	 * @param name
	 *            the name of the category
	 * @param printer
	 *            the printer to display the category elements
	 */
	public NumberCategory(String name, Printer printer) {
		this.name = name;
		this.printer = printer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kasiadzm.parser.category.Category#display(java.util.List)
	 */
	public void display(List<String> elements) {
		StringBuilder text = new StringBuilder(name).append(":").append(separator);

		if (elements != null) {
			Set<String> uniqueSet = new TreeSet<String>(elements);

			for (String temp : uniqueSet) {
				if (isValidElement(temp)) {
					text.append(temp).append(":").append(Collections.frequency(elements, temp)).append(separator);
				}
			}
		}

		printer.print(text.toString());
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kasiadzm.parser2.Category#getName()
	 */
	public String getName() {
		return name;
	}

}
