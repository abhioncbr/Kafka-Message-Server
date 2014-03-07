package com.kafka.message.server.example.util;

import java.util.List;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * The Class KafkaExampleCommandLineHandler.
 * 
 * @author Abhishek Sharma
 */
public class KafkaExampleCommandLineHandler {
	private final CommandLine commandLine;
	
	/**
	 * Gets the option.
	 *
	 * @param option the option
	 * @return the option
	 */
	public String getOption(final String option) {
	    if (commandLine!=null && commandLine.hasOption(option)) {
	        return commandLine.getOptionValue(option);
	    }

	    return null;
	}

	/**
	 * Instantiates a new kafka example command line handler.
	 *
	 * @param optionList the option list
	 * @param args the args
	 * @throws ParseException the parse exception
	 */
	public KafkaExampleCommandLineHandler(final List<Option> optionList, String[] args) throws ParseException{
		final CommandLineParser parser = new BasicParser();
		
	    final Options options = new Options();
	    for(Option option: optionList){
	    	options.addOption(option);
	    }

	    commandLine = parser.parse(options, args);
	}
}
