package org.kasiadzm.parser;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class CategoryProcessorClientTest {

	private static final String separator = System.getProperty("line.separator");

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Before
	public void setUpStream() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStream() {
		System.setOut(null);
	}

	@Test
	public void testMainWithEmptyArgs() {
		String defaultPath = new StringBuilder(System.getProperty("user.dir")).append("/src/main/resources/input.txt")
				.toString();
		List<String> defaultCategories = Arrays.asList("ANIMALS", "NUMBERS", "CARS");

		String[] args = new String[0];
		String path = CategoryProcessorClient.getPathToInputFile(args);
		List<String> categories = CategoryProcessorClient.getCategoties(args);

		assertEquals(defaultPath, path);
		assertEquals(defaultCategories, categories);
	}

	@Test
	public void testMainWithFilePathArg() {
		String expectedPath = new StringBuilder(System.getProperty("user.dir")).append("/path/to/file/text.txt")
				.toString();
		List<String> defaultCategories = Arrays.asList("ANIMALS", "NUMBERS", "CARS");

		String[] args = new String[] { "/path/to/file/text.txt" };
		String path = CategoryProcessorClient.getPathToInputFile(args);
		List<String> categories = CategoryProcessorClient.getCategoties(args);

		assertEquals(expectedPath, path);
		assertEquals(defaultCategories, categories);
	}

	@Test
	public void testMainWithBothArgs() {
		String expectedPath = new StringBuilder(System.getProperty("user.dir")).append("/path/to/file/text.txt")
				.toString();
		List<String> expectedCategories = Arrays.asList("CITIES");

		String[] args = new String[] { "/path/to/file/text.txt", "CITIES" };
		String path = CategoryProcessorClient.getPathToInputFile(args);
		List<String> categories = CategoryProcessorClient.getCategoties(args);

		assertEquals(expectedPath, path);
		assertEquals(expectedCategories, categories);
	}

	@Test
	public void testProcessCategoriesOnlyAnimals() throws IOException {
		File output = temporaryFolder.newFile("input.txt");

		PrintWriter writer = new PrintWriter(output);
		writer.println("ANIMALS");
		writer.println("cow");
		writer.println("cow");
		writer.println("moose");
		writer.close();

		CategoryProcessorClient client = new CategoryProcessorClient();
		client.processCategories(output.getPath(), Arrays.asList("ANIMALS"));

		String expected = new StringBuilder("ANIMALS:").append(separator).append("cow").append(separator)
				.append("moose").append(separator).toString();

		assertEquals(expected, outContent.toString());
	}

	@Test
	public void testProcessCategoriesNotRegistered() throws IOException {
		File output = temporaryFolder.newFile("input.txt");

		PrintWriter writer = new PrintWriter(output);
		writer.println("COUNTRIES");
		writer.println("Belarus");
		writer.println("Germany");
		writer.println("Ukraine");
		writer.close();

		CategoryProcessorClient client = new CategoryProcessorClient();
		client.processCategories(output.getPath(), Arrays.asList("COUNTRIES"));

		assertEquals("", outContent.toString());
	}
}
