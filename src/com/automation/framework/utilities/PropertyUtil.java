package com.automation.framework.utilities;

/**
 * @author manoj
 *
 */
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.automation.framework.core.COCDriverScript;
import com.automation.framework.exceptions.DriverScriptException;
import com.automation.framework.exceptions.WebAdaptorException;

/**
 * This class is used to instantiate the data source using the properties file.
 * Pooled connection creation, closing implementation is provided with this
 * class.
 */
public class PropertyUtil {

	/** The Constant LOG. */
	private static final Logger logger = Logger.getLogger(PropertyUtil.class);

	/** properties. */
	private Properties properties;

	/**
	 * Instantiates a property file.
	 */
	public PropertyUtil() {
		properties = new Properties();
	}

	/**
	 * Instantiates a property file.
	 * 
	 * @param file
	 * @throws WebAdaptorException
	 * 
	 */
	public PropertyUtil(File file) {
		properties = new Properties();
		initPropertyFile(file);
	}

	/**
	 * @param file
	 */
	private void initPropertyFile(File file) {
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info","initPropertyFile(File) - start"); //$NON-NLS-1$
		}
		try {

			initialize(file);
		} catch (DriverScriptException driverScriptException) {
			COCDriverScript.logMessage("error",driverScriptException.getMessage());
			driverScriptException.printStackTrace();

		}
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info","initPropertyFile(String) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Gets the property.
	 * 
	 * @param propertyName
	 * 
	 * @return the property
	 */
	public String getProperty(String propertyName) {
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info","getProperty(String) - start"); //$NON-NLS-1$
		}

		String returnString = properties.getProperty(propertyName);
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info","getProperty(String) - end"); //$NON-NLS-1$
		}
		return returnString;
	}

	/**
	 * Sets properties file
	 * 
	 * @param properties
	 * 
	 */
	public void setMainproperties(Properties properties) {
		this.properties = properties;
	}

	/**
	 * This method handles the loading property file int
	 * 
	 * @param filePath
	 * @throws WebAdaptorException
	 * 
	 */
	public final void initialize(File filePath) throws DriverScriptException {
		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info","initialize(File) - start"); //$NON-NLS-1$
		}

		if (filePath.exists()) {
			InputStream inputStream = null;
			try {
				inputStream = new BufferedInputStream(new FileInputStream(
						filePath));
				Properties props = new Properties();
				props.load(inputStream);
				properties.clear();// clear property
				properties.putAll(props);
			} catch (Exception e) {
				COCDriverScript.logMessage("error","PropertyUtil Init " + e);
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (Exception e2) {
						COCDriverScript.logMessage("error","PropertyUtil Init close is :" + e2);
					}
				}
			}
		} else {
			throw new DriverScriptException(
					"unable to find globalconfig.properties property file");
		}

		if (logger.isDebugEnabled()) {
			COCDriverScript.logMessage("info","initialize(File) - end"); //$NON-NLS-1$
		}
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public Properties getProperties() {
		return properties;
	}
}
