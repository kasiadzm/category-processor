package org.kasiadzm.parser.processor;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kasiadzm.parser.category.Category;
import org.kasiadzm.parser.datasource.DataSource;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CategoryProcessorTest {

	BasicCategoryProcessor processor;

	@Mock
	DataSource datasource;

	@Mock
	Category category;

	@Before
	public void setUp() {
		processor = new BasicCategoryProcessor(datasource);
	}

	@Test
	public void testProcess() {
		when(category.getName()).thenReturn("COUNTRIES");
		when(datasource.getElementsByCategory("COUNTRIES")).thenReturn(Arrays.asList("Germany", "Poland"));

		processor.process(category);

		verify(datasource).getElementsByCategory("COUNTRIES");
		verify(category).display(Arrays.asList("Germany", "Poland"));
	}

	@After
	public void tearDown() {
		datasource = null;
		category = null;
		processor = null;
	}

}
