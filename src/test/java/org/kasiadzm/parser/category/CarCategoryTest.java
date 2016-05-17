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
public class CarCategoryTest {

	private static final String separator = System.getProperty("line.separator");

	@Mock
	Printer printer;

	@Test
	public void testDisplayEmptyElements() {
		Category category = new CarCategory("CARS", printer);
		category.display(Arrays.asList("BMW", "", "Audi", " "));

		StringBuilder expected = new StringBuilder("CARS:").append(separator)
				.append("audi (4d9fa555e7c23996e99f1fb0e286aea8)").append(separator)
				.append("bmw (71913f59e458e026d6609cdb5a7cc53d)").append(separator);

		verify(printer).print(expected.toString());
	}

	@Test
	public void testDisplayCaseSensitiveElements() {
		Category category = new CarCategory("CARS", printer);
		category.display(Arrays.asList("Audi", "OPEL", "opel", "Opel", "AUDI"));

		StringBuilder expected = new StringBuilder("CARS:").append(separator)
				.append("audi (4d9fa555e7c23996e99f1fb0e286aea8)").append(separator)
				.append("opel (f65b7d39472c52142ea2f4ea5e115d59)").append(separator);

		verify(printer).print(expected.toString());
	}

	@Test
	public void testDisplayMultiWordsElements() {
		Category category = new CarCategory("CARS", printer);
		category.display(Arrays.asList("nice VW", "red bmw", "AUDI"));

		StringBuilder expected = new StringBuilder("CARS:").append(separator)
				.append("audi (4d9fa555e7c23996e99f1fb0e286aea8)").append(separator);
		verify(printer).print(expected.toString());

	}

	@Test
	public void testDisplayNoElements() {
		Category category = new CarCategory("CARS", printer);
		category.display(new ArrayList<String>());

		StringBuilder expected = new StringBuilder("CARS:").append(separator);
		verify(printer).print(expected.toString());
	}

	@Test
	public void testGetName() {
		Category category = new CarCategory("CARS");
		assertEquals("CARS", category.getName());
	}

	@After
	public void tearDown() {
		printer = null;
	}

}
