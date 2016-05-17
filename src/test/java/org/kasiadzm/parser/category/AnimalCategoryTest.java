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
public class AnimalCategoryTest {

	private static final String separator = System.getProperty("line.separator");

	@Mock
	Printer printer;

	@Test
	public void testDisplayEmptyElements() {
		Category category = new AnimalCategory("ANIMALS", printer);
		category.display(Arrays.asList("", "Horse", " "));

		StringBuilder expected = new StringBuilder("ANIMALS:").append(separator).append("Horse").append(separator);

		verify(printer).print(expected.toString());
	}

	@Test
	public void testDisplayCaseSensitiveElements() {
		Category category = new AnimalCategory("ANIMALS", printer);
		category.display(Arrays.asList("horse", "cow", "Horse", "COW", "HORSE"));

		StringBuilder expected = new StringBuilder("ANIMALS:").append(separator).append("COW").append(separator)
				.append("HORSE").append(separator).append("Horse").append(separator).append("cow").append(separator)
				.append("horse").append(separator);

		verify(printer).print(expected.toString());
	}

	@Test
	public void testDisplayMultiWordsElements() {
		Category category = new AnimalCategory("ANIMALS", printer);
		category.display(Arrays.asList("big horse", "horse", "Horse", "small COW"));

		StringBuilder expected = new StringBuilder("ANIMALS:").append(separator).append("Horse").append(separator)
				.append("horse").append(separator);

		verify(printer).print(expected.toString());

	}

	@Test
	public void testDisplayNoElements() {
		Category category = new AnimalCategory("ANIMALS", printer);
		category.display(new ArrayList<String>());

		StringBuilder expected = new StringBuilder("ANIMALS:").append(separator);
		verify(printer).print(expected.toString());
	}

	@Test
	public void testGetName() {
		Category category = new AnimalCategory("ANIMALS");
		assertEquals("ANIMALS", category.getName());
	}

	@After
	public void tearDown() {
		printer = null;
	}
}
