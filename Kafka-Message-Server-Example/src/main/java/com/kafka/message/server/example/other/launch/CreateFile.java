package com.kafka.message.server.example.other.launch;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.ParseException;

import com.kafka.message.server.example.util.KafkaExampleCommandLineHandler;
import com.kafka.message.server.example.util.KafkaExampleFileUtil;
import com.kafka.message.server.example.util.KafkaExampleProperty;
import com.kafka.message.server.example.util.KafkaExamplePropertyKey;

/**
 * The Class CreateFile.
 * 
 * @author Abhishek Sharma
 */
public class CreateFile {
	private static final String PATH = "path";
	private static final String SLEEP_TIME = "sleepTime";
	

	private final String directoryPath;
	private final Integer threadSleepTime;

	/**
	 * Instantiates a new creates the file.
	 *
	 * @param directoryPath the directory path
	 * @param threadSleepTime the thread sleep time
	 */
	public CreateFile(String directoryPath, Integer threadSleepTime) {

		if (directoryPath == null || "".equals(directoryPath)) {
			this.directoryPath = getDirectoryPathDefaultValue();
		} else {
			this.directoryPath = directoryPath;
		}

		if (threadSleepTime == null) {
			this.threadSleepTime = getThreadSleepTimeDefaultValue();
		} else {
			this.threadSleepTime = threadSleepTime;
		}

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		CreateFile createFile;
		
		KafkaExampleCommandLineHandler commandLine;
		try {
			commandLine = new  KafkaExampleCommandLineHandler(getProducerOptions(), args);
			
			String path = commandLine.getOption(PATH);
			String sleepTime  = commandLine.getOption(SLEEP_TIME);

			
			createFile = new CreateFile(path !=null ? KafkaExampleFileUtil.getValidDirectoryPath(path) : getDirectoryPathDefaultValue()
					, sleepTime !=null ? Integer.parseInt(sleepTime) : getThreadSleepTimeDefaultValue());
			createFile.creatMailContent();

		} catch (ParseException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creat mail content.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	private void creatMailContent() throws IOException, InterruptedException {
		while(true){
			String fileName = directoryPath + "File-" + new Date();
			
			FileOutputStream fout = new FileOutputStream( fileName );
			FileChannel fc = fout.getChannel();
			
			ByteBuffer buffer = ByteBuffer.allocate( 1024 );
			buffer.put( getStaticFileContent(fileName));
			buffer.flip();
			
			fc.write( buffer );
			
			buffer.clear();
			fc.close();
			fout.close();
			
			System.out.println("created file - "  + fileName);
			
			Thread.sleep(threadSleepTime * 1000);
		}
	}

	/**
	 * Gets the directory path default value.
	 * 
	 * @return the directory path default value
	 */
	private static String getDirectoryPathDefaultValue() {
		return KafkaExampleProperty
				.getPropertyValue(KafkaExamplePropertyKey.MAIL_DIRECTORY);
	}

	/**
	 * Gets the thread sleep time default value.
	 * 
	 * @return the thread sleep time default value
	 */
	private static Integer getThreadSleepTimeDefaultValue() {
		return Integer
				.parseInt(KafkaExampleProperty
						.getPropertyValue(KafkaExamplePropertyKey.FILE_CREATE_THREAD_SLEEP_TIME));
	}
	
	/**
	 * Gets the static file content.
	 *
	 * @param fileName the file name
	 * @return the static file content
	 */
	private static byte[] getStaticFileContent(String fileName){
		StringBuilder content = new StringBuilder();
		
		content.append("File Name - " + fileName);
		content.append("The first  line");
		content.append("The second line");
		content.append("The third  line");
		content.append("The fourth line");
		
		return content.toString().getBytes();
	}
	
	
	private static List<Option> getProducerOptions(){
		List<Option> optionList = new ArrayList<Option>();
		
		Option pathOption = new Option(PATH, PATH, true, "directory path where file is going to be created");
		Option sleepOption = new Option(SLEEP_TIME, SLEEP_TIME, true, "time difference between two files creation");

		optionList.add(pathOption);
		optionList.add(sleepOption);
		
		return optionList;
	}
}
