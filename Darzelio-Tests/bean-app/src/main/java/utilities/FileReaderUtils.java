package utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtils {
	public static List<String> getTestData() throws IOException {
		List<String> records = new ArrayList<String>();
		String record;
		
		try (BufferedReader buffer = new BufferedReader(new FileReader("src/test/resources/registeredEmails.txt"))) {		
			while ((record = buffer.readLine()) != null) {
				records.add(record);
			}
		}		
		return records;
	}
}
