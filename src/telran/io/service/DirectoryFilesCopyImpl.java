package telran.io.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import telran.view.InputOutput;

public class DirectoryFilesCopyImpl implements DirectoryFilesCopy { 

	InputOutput io;
	int maxDepth;
	
	
	@Override
	public void displayDirectoryContent(String directoryPath, int maxDepth, InputOutput io) {
		this.io = io;
		this.maxDepth = maxDepth;
		io.writeObjectLine("displayDirectoryContent");
		displayOnePoint(new File(directoryPath), 1);
	}
	private void displayOnePoint(File currentPoint, int level) {
		boolean flDir = currentPoint.isDirectory();
		io.writeObjectLine(String.format("%s %s: %s", " ".repeat(level*5), currentPoint.getName(),
																	flDir ? "dir" : "file"));
		if(flDir && ((level<=maxDepth) || (maxDepth<0))) {
			for (File f : currentPoint.listFiles()) {
				displayOnePoint(f, level+1);
			}
		}
		// Instead of recursive exit
	}

	@Override
	public long copyFiles(String pathFileSrc, String pathFileDest, boolean flOverwrite) {
		File destFile = new File(pathFileDest);
		if(destFile.exists() && !flOverwrite) {
			return 0;
		} 
		Instant startInterval = Instant.now();
		long totalBytes = 0;
		byte[] buffer = new byte[(int)Runtime.getRuntime().freeMemory()];
		try {
			if(!destFile.exists()) {
				destFile.createNewFile();
			}
			InputStream inputStream = new FileInputStream(pathFileSrc);
			OutputStream outputStream = new FileOutputStream(destFile);
			int nBytesRecord;
			while((nBytesRecord = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, nBytesRecord);
				totalBytes += nBytesRecord;
			} 
			inputStream.close();
			outputStream.close();
		} catch(IOException ex) {
			System.out.println(ex.toString());  
			return 0;
		}
		long timeInterval = ChronoUnit.MILLIS.between(startInterval, Instant.now());
		System.out.println("time="+timeInterval+" millisecs  nBytes="+totalBytes+" bytes");
		return totalBytes/timeInterval;
	}

}
