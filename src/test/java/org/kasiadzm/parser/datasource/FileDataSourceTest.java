package org.kasiadzm.parser.datasource;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class FileDataSourceTest {

	@Rule
	public TemporaryFolder temporaryFolder = new TemporaryFolder();

	@Test
	public void tetsGetElementsByCategoryEmptyFile() throws IOException {
		File output = temporaryFolder.newFile("input.txt");
		FileDataSource datasource = new FileDataSource(output.getPath(), Arrays.asList("NUMBERS"));

		List<String> result = datasource.getElementsByCategory("NUMBERS");

		assertEquals(0, result.size());
	}

	@Test
	public void testGetElementsByCategoryOnlyCategoriesInFile() throws IOException {
		File output = temporaryFolder.newFile("input.txt");

		PrintWriter writer = new PrintWriter(output);
		writer.println("CARS");
		writer.println("CITIES");
		writer.println("TEAMS");
		writer.close();

		FileDataSource datasource = new FileDataSource(output.getPath(), Arrays.asList("CITIES", "TEAMS", "CARS"));

		List<String> numbers = datasource.getElementsByCategory("NUMBERS");
		List<String> cars = datasource.getElementsByCategory("CARS");

		assertEquals(0, numbers.size());
		assertEquals(0, cars.size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void tetsGetElementsByCategoryFileNotFound() {
		new FileDataSource("/junit/input.txt", Arrays.asList("CITIES", "TEAMS", "CARS"));
	}

	@Test(expected = NullPointerException.class)
	public void tetsGetElementsByCategoryInconsistentData() throws IOException {
		File output = temporaryFolder.newFile("input.txt");
		PrintWriter writer = new PrintWriter(output);
		writer.println("CITIES");
		writer.println("TEAMS");
		writer.close();

		new FileDataSource(output.getPath(), Arrays.asList("CARS"));
	}

	@Test
	public void tetsGetElementsByCategoryFileWithSpecialCharacters() throws IOException {
		File output = temporaryFolder.newFile("input.txt");
		PrintWriter writer = new PrintWriter(output);
		writer.println("TEAMS");
		writer.println("\n");
		writer.println("sparta");
		writer.println("&");
		writer.println(" \"");
		writer.close();

		new FileDataSource(output.getPath(), Arrays.asList("TEAMS"));
	}

	@Test
	public void testGetElementsByCategoryCaseInsensitiveCategoriesAsArgs() throws IOException {
		File output = temporaryFolder.newFile("input.txt");

		PrintWriter writer = new PrintWriter(output);
		writer.println("CARS");
		writer.println("VW");
		writer.println("Audi");
		writer.println("OPEL");
		writer.println("NUMBERS");
		writer.println("two");
		writer.println("two");

		writer.close();

		FileDataSource datasource = new FileDataSource(output.getPath(), Arrays.asList("Cars", "numbers"));

		List<String> numbers = datasource.getElementsByCategory("numbers");
		List<String> cars = datasource.getElementsByCategory("Cars");

		assertEquals(2, numbers.size());
		assertEquals(3, cars.size());
	}

	@Test
	public void testGetElementsByCategoryCaseInsensitiveCategoriesInFile() throws IOException {
		File output = temporaryFolder.newFile("input.txt");

		PrintWriter writer = new PrintWriter(output);
		writer.println("animals");
		writer.println("cow");
		writer.println("dog");
		writer.println("DOG");
		writer.println("Cars");
		writer.println("opel");
		writer.println("Audi");
		writer.println("OPEL");

		writer.close();

		FileDataSource datasource = new FileDataSource(output.getPath(), Arrays.asList("ANIMALS", "CARS"));

		List<String> animals = datasource.getElementsByCategory("ANIMALS");
		List<String> cars = datasource.getElementsByCategory("CARS");

		assertEquals(3, animals.size());
		assertEquals(3, cars.size());
	}

	@Test
	public void testGetElementsByCategoryCaseInsensitiveCategoriesBoth() throws IOException {
		File output = temporaryFolder.newFile("input.txt");

		PrintWriter writer = new PrintWriter(output);
		writer.println("Numbers");
		writer.println("one");
		writer.println("Five");
		writer.println("animals");
		writer.println("cow");
		writer.println("cat");
		writer.println("MOOSE");
		writer.println("CARS");
		writer.println("OPEL");
		writer.println("bmw");
		writer.println("Vw");

		writer.close();

		FileDataSource datasource = new FileDataSource(output.getPath(), Arrays.asList("NUMBERS", "cars", "Animals"));

		List<String> numbers = datasource.getElementsByCategory("Numbers");
		List<String> animals = datasource.getElementsByCategory("animals");
		List<String> cars = datasource.getElementsByCategory("CARS");

		assertEquals(2, numbers.size());
		assertEquals(3, animals.size());
		assertEquals(3, cars.size());
	}
}
