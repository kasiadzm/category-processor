package org.kasiadzm.parser.output;

/**
 * The System.out implementation of <code>Printer</code>
 * 
 * @author kasiadzm
 */
public class SystemOutPrinter implements Printer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.kasiadzm.parser.output.Printer#print(java.lang.String)
	 */
	public void print(String text) {
		System.out.print(text);
	}

}
