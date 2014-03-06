package com.kafka.message.server.example.other.launch;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

import com.kafka.message.server.example.util.KafkaExampleProperty;
import com.kafka.message.server.example.util.KafkaExamplePropertyKey;

public class CreateFile {

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

	public static void main(String args[]) {
		CreateFile createFile;
		
		if(args.length > 2){
			printUsage();
			return;
		} else if(args.length ==2){
			Integer temp = Integer.parseInt(args[1]);
			createFile = new CreateFile(args[0], temp);
		} else if(args.length ==1){
			createFile = new CreateFile(args[0], null);
		} else{
			createFile = new CreateFile(null, null);
		}
		
		try {
			createFile.creatMailContent();
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
			
			Thread.sleep(threadSleepTime * 1000);
		}
	}

	/**
	 * Gets the directory path default value.
	 * 
	 * @return the directory path default value
	 */
	private String getDirectoryPathDefaultValue() {
		return KafkaExampleProperty
				.getPropertyValue(KafkaExamplePropertyKey.MAIL_DIRECTORY);
	}

	/**
	 * Gets the thread sleep time default value.
	 * 
	 * @return the thread sleep time default value
	 */
	private Integer getThreadSleepTimeDefaultValue() {
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
	
	/**
	 * Prints the usage.
	 */
	private static void printUsage(){
		System.out.println("Please provide two or less than two arguments.");
		System.out.println("First argument is directory path.");
		System.out.println("second argument is time in seconds for gap between two file creation.");
	}
}
