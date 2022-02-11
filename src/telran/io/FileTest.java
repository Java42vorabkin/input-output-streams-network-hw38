package telran.io;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import java.util.Arrays;

class FileTest {
	File nodeFile = new File("file.txt");
	File nodeDir = new File("dir1/dir2");
	@BeforeEach
	void setUp() throws Exception { 
		nodeFile.delete();
		nodeDir.delete();
	}

	@Test
	void testInitial() throws IOException {
		
		assertFalse(nodeFile.exists());
		assertFalse(nodeDir.exists());
		nodeFile.createNewFile();
		nodeDir.mkdirs();
		assertTrue(nodeFile.exists());
		assertTrue(nodeDir.exists());
		assertTrue(nodeFile.isFile());
		assertTrue(nodeDir.isDirectory());
		File nodeFile1 = new File("dir1/file1.txt");
		nodeFile1.createNewFile();
		File nodeDir1 = new File("dir1");
		System.out.println(nodeDir1.getName());
		Arrays.stream(nodeDir1.listFiles()).forEach(n -> System.out.printf("  %s: %s\n"
				,n.getName(), n.isDirectory() ? "dir" : "file"));
//		dir1
//		  dir2: dir
//		  file1.txt: file
		
		
		
	}
	@Test
	void copyTest() throws IOException {
		InputStream is = new FileInputStream("srcFile.txt");
		File destFile = new File("destFile.txt");
		System.out.printf("file %s exists : %s\n", destFile.getName(), destFile.exists());
		OutputStream os = new FileOutputStream(destFile);
		byte[] buffer = new byte[is.available()]; //works only for small files
		System.out.printf("read from input stream returns: %d\n", is.read(buffer));
		os.write(buffer);
		is.close();
		os.close();
	}
	@Test
	void divisionByZeroTest()  {
		System.out.println("\ndivisionByZeroTest begins...");
		int ai=4, bi = 0;
		try {
			System.out.println("Integer OK  "+ai/bi);
		} catch (ArithmeticException ex) {
			System.out.println("Integer " + ex.toString());
		}
		double ad = 3.14, bd = 0.0;
		System.out.println("Double OK  "+ad/bd);
		float af = 3.14f, bf = 0.0f;
		System.out.println("Float OK  "+ af/bf);
		System.out.println("divisionByZeroTest ends\n");
	}
	@Test
	void largeAllocation() { 
		System.out.println("Ilya");
		long len1 = Runtime.getRuntime().freeMemory();
		long len2 = Runtime.getRuntime().freeMemory()*2;
		System.out.println(len1+ "   " + len2);
		byte[] buffer1 = new byte[(int)Runtime.getRuntime().freeMemory()]; 
		byte[] buffer2 = new byte[(int)Runtime.getRuntime().freeMemory()*2];
		System.out.println(buffer1.length + "  " + buffer2.length);
		
	}

}
