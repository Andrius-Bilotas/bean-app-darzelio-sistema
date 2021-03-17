package utilities;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterUtils {
	public static void writeToFile(String content) throws IOException {
		  try (FileWriter fw = new FileWriter("src/test/resources/registeredEmails.txt", true)) {
		    fw.write(content + System.lineSeparator());
		  } catch (IOException e) { 
			  throw new IOException("Was unable to write file with name fileName", e.getCause());
		  }
		}
}

