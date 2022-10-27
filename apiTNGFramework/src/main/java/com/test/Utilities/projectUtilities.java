package com.test.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.test.constants.SourcePaths;



public class projectUtilities {
	
public static FileInputStream inputfile=null;
public static FileOutputStream outputfile = null;	
	public Properties loadFile(String filename) {
		
		Properties propertyFile = new Properties();
		String propertyFilepath = null;
		String jsonFilepath = null;
		switch(filename) {
			case "loginSchema" :
				jsonFilepath = SourcePaths.LOGIN_SCHEMA_JSON_PATH;
				break;
			case "readSchema" :
				jsonFilepath = SourcePaths.READ_SCHEMA_JSON_PATH;
				break;
			case "loginCredentials" :
				propertyFilepath = SourcePaths.LOGIN_DETAILS_PROPERTIES_PATH;
				break;
			case "createDetails" :
				propertyFilepath = SourcePaths.CREATE_DETAILS_PROPERTIES_PATH;
				break;
			case "updateDetails" :
				propertyFilepath = SourcePaths.UPDATE_DETAILS_PROPERTIES_PATH;
				break;
			case "deleteDetails" :
				propertyFilepath = SourcePaths.DELETE_DETAILS_PROPERTIES_PATH;
				break;
			default :
				System.out.println("Wrong file name");
		}
		try {
			inputfile = new FileInputStream(propertyFilepath);
			if(filename.equalsIgnoreCase("updateDetails")) {
				outputfile = new FileOutputStream(propertyFilepath,true); 
			}
			propertyFile.load(inputfile);
		}catch(IOException e1) {
			e1.printStackTrace();
		}
		//System.out.println(filename +" loaded");
		return propertyFile;
	}
	
	
	public String getPropertyValue(String key, String filename) {
		Properties propertyFile = loadFile(filename);
		String value = propertyFile.getProperty(key);
		//System.out.println("We got "+value +" for "+key);
		try {
			inputfile.close();
		}catch(IOException e2) {
			e2.printStackTrace();
		}
		return value;  
	}
	public void setProperty(String key,String value, String filename) {
		Properties propertyFile = loadFile(filename);
		//if(filename.equalsIgnoreCase(filename))
		propertyFile.setProperty(key, value);
		try {
			propertyFile.store(outputfile, filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
