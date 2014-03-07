package com.kafka.message.server.example.core;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * The Class MailProducer.
 * 
 * @author Abhishek Sharma
 */
public class KafkaMailProducer extends Thread {
	private final kafka.javaapi.producer.Producer<Integer, String> producer;
	private final String topic;
	private final String directoryPath;
	private final Properties props = new Properties();

	/**
	 * Instantiates a new kafka producer.
	 *
	 * @param topic the topic
	 * @param directoryPath the directory path
	 */
	public KafkaMailProducer(String topic, String directoryPath) {
	    props.put("serializer.class", "kafka.serializer.StringEncoder");
	    props.put("metadata.broker.list", "localhost:9092");
		producer = new kafka.javaapi.producer.Producer<Integer, String>(new ProducerConfig(props));
		this.topic = topic;
		this.directoryPath = directoryPath;
	}

	public void run() {
        Path dir = Paths.get(directoryPath);
        try {
			
        	new WatchDir(dir).start();
			
			new ReadDir(dir).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The Class WatchDir.
	 */
	private class WatchDir extends Thread{
		private WatchKey key; 
		private final Path directory;
		private WatchService watcher;
	    
	 
	    @SuppressWarnings("unchecked")
	    <T> WatchEvent<T> cast(WatchEvent<?> event) {
	        return (WatchEvent<T>)event;
	    }
	 
	 
	    /**
	     * Creates a WatchService and registers the given directory
	     */
	    WatchDir(Path directory)  {
	    	this.directory = directory;
	    	try{
		        this.watcher = FileSystems.getDefault().newWatchService();
		        this.key = directory.register(watcher, ENTRY_CREATE);
	    	} catch (IOException ex) {
            	ex.printStackTrace();
            }
	    }
	 
	    /**
	     * Process all events for keys queued to the watcher
	     */
	    public void run() {
	        for (;;) {
	            for (WatchEvent<?> event: key.pollEvents()) {
	                Kind<?> kind = event.kind();
	 
	                // TBD - provide example of how OVERFLOW event is handled
	                if (kind == OVERFLOW) {
	                    continue;
	                }
	 
	                // Context for directory entry event is the file name of entry
	                WatchEvent<Path> ev = cast(event);
	                Path name = ev.context();
	                Path child = directory.resolve(name);
	 
	                 if (kind == ENTRY_CREATE) {
	                    try {
	                        if (!Files.isDirectory(child, NOFOLLOW_LINKS)) {
	                        	readFileContent(child.toFile());
	                         }
	                    } catch (IOException ex) {
	                    	ex.printStackTrace();
	                    }
	                }
	            }
	 
	            boolean valid = key.reset();
	            if (!valid) 
	                break;
	     }
	    }
	    
	}
	
	/**
	 * The Class ReadDir.
	 */
	class ReadDir extends Thread{
		private final Path directory;
		
		ReadDir(Path directory) throws IOException {
	    	this.directory = directory;
	    }
		
		 public void start() {
		        File file = directory.toFile();
      
				List<File> dirMessages = Arrays.asList(file.listFiles(new FileFilter() {
					@Override
					public boolean accept(File pathname) {
						if(pathname.isFile() && !pathname.isHidden()) return true;
						return false;
					}
				}));
				
				for(File temp :dirMessages){
					try {
						readFileContent(temp);
					} catch (IOException ex) {
						ex.printStackTrace();
					}
				}
		 }

	}
	
  /**
   * Read file content.
   *
   * @param file the file
   * @throws IOException Signals that an I/O exception has occurred.
   */
  private void readFileContent(File file) throws IOException{
		
	  	RandomAccessFile aFile = new RandomAccessFile(file, "r");
	    FileChannel inChannel = aFile.getChannel();
	    MappedByteBuffer buffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
	    buffer.load();  
	    StringBuilder strBuilder = new StringBuilder();
	    for (int i = 0; i < buffer.limit(); i++){
	    	strBuilder.append((char) buffer.get());
	    }
	    buffer.clear(); // do something with the data and clear/compact it.
	    inChannel.close();
	    aFile.close();
		
	    
	    producer.send(new KeyedMessage<Integer, String>(topic, strBuilder.toString()));
	    
	    System.out.println(file.getAbsolutePath() + " - content consumed.");
	    
	    file.delete();
	}
	
}
