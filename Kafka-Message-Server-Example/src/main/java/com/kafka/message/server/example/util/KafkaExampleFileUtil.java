package com.kafka.message.server.example.util;

import java.io.File;

/**
 * The Class KafkaExampleFileUtil.
 * 
 * @author Abhishek Sharma
 */
public class KafkaExampleFileUtil {
	
	/**
	 * Gets the valid directory path.
	 *
	 * @param filePath the file path
	 * @return the valid directory path
	 */
	public static String getValidDirectoryPath(String filePath){
		if(filePath.lastIndexOf('/') == (filePath.length()-1))
			return filePath;
		else 
			return filePath + "/";
	}
	
	/**
	 * Checks if is valid directory.
	 *
	 * @param filePath the file path
	 * @return the boolean
	 */
	public static boolean isValidDirectory(String filePath){
		if(filePath == null)
			return false;
		
		File temp =null;
		if(filePath.indexOf('/') == (filePath.length()-1))
			temp = new File(filePath);
		else 
			temp = new File(filePath + "/");
		
		if(temp.isDirectory())
			return true;
		
		return false;
	}

}
