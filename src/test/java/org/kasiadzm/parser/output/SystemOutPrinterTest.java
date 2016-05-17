package org.kasiadzm.parser.output;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SystemOutPrinterTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStream() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStream() {
		System.setOut(null);
	}

	@Test
	public void testPrint() {
		Printer printer = new SystemOutPrinter();
		String expected = "text";
		printer.print(expected);

		assertEquals(expected, outContent.toString());
	}

}
