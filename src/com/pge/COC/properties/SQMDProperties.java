package com.pge.COC.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SQMDProperties {

public Properties properties = null;
	
	/**
	 * Constructor
	 */
	public SQMDProperties() {
		/** Load Properties File ***/
		this.properties = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("SQMD.properties");
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
