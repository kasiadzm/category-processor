package org.kasiadzm.parser.category;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kasiadzm.parser.output.Printer;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class NumberCategoryTest {

	private static final String separator = System.getProperty("line.separator");

	@Mock
	Printer printer;

	@Test
	public void testDisplayEmptyElements() {
		Category category = new NumberCategory("NUMBERS", printer);
		category.display(Arrays.asList("", "", "one", " ", "one"));

		StringBuilder expected = new StringBuilder("NUMBERS:").append(separator).append("one:2").append(separator);

		verify(printer).print(expected.toString());
	}

	@Test
	public void testDisplayCaseSensitiveElements() {
		Category category = new NumberCategory("NUMBERS", printer);
		category.display(Arrays.asList("ONE", "one", "five", "Five", "one", "one"));

		StringBuilder expected = new StringBuilder("NUMBERS:").append(separator).append("Five:1").append(separator)
				.append("ONE:1").append(separator).append("five:1").append(separator).append("one:3").append(separator);

		verify(printer).print(expected.toString());
	}

	@Test
	public void testDisplayCountNumber() {
		Category category = new NumberCategory("NUMBERS", printer);
		category.display(Arrays.asList("three", "Three", "three", "three"));

		StringBuilder expected = new StringBuilder("NUMBERS:").append(separator).append("Three:1").append(separator)
				.append("three:3").append(separator);

		verify(printer).print(expected.toString());
	}

	@Test
	public void testDisplayMultiWordsElements() {
		Category category = new NumberCategory("NUMBERS", printer);
		category.display(Arrays.asList("TWO two", "two", "five", "five", "one"));

		StringBuilder expected = new StringBuilder("NUMBERS:").append(separator).append("five:2").append(separator)
				.append("one:1").append(separator).append("two:1").append(separator);

		verify(printer).print(expected.toString());

	}

	@Test
	public void testDisplayNoElements() {
		Category category = new NumberCategory("NUMBERS", printer);
		category.display(new ArrayList<String>());

		StringBuilder expected = new StringBuilder("NUMBERS:").append(separator);
		verify(printer).print(expected.toString());
	}

	@Test
	public void testGetName() {
		Category category = new NumberCategory("NUMBERS");
		assertEquals("NUMBERS", category.getName());
	}

	@After
	public void tearDown() {
		printer = null;
	}

}
