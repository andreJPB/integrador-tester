package com.betha.educaweb.integrador.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

	/**
	 * 
	 * @param path
	 * @param file
	 * @return
	 * @throws IllegalArgumentException
	 *             caso o argumento informado não corresponda à um arquivo
	 *             .properties válido
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static Properties read(String file) throws IllegalArgumentException {
		Properties prop = new Properties();
		try {
			
			prop.load(new FileInputStream(file));
			return prop;
		} catch (IOException e) {
			
			throw new IllegalArgumentException(e.getCause());
		}

	}

}
