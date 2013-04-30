package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.ArrayList;

public class TextFile {

	public static List<String> readAllLines(String path) throws IOException {
		List<String> result = new ArrayList<String>();
		
		File file = new CSFile(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
		
		while( reader.ready() )
			result.add(reader.readLine());
		
		reader.close();
		return result;
	}
	
	public static void writeAllLines(String path, List<String> lines) throws IOException {
		File file = new CSFile(path);
		FileOutputStream s = new FileOutputStream(file);
		OutputStreamWriter sw = new OutputStreamWriter(s, "UTF-8");
		
		for( String line : lines )
			sw.write(line + "\r\n");
		
		sw.flush();
		s.close();
	}
	
	
    public static void stringToFile(String content, String path) throws Exception {
		File file = new CSFile(path);
		OutputStreamWriter sw = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
		sw.write(content);
		sw.flush();
		sw.close();
	}

  	public static String fileToString(String src) throws Exception {
    	return streamToString(new FileInputStream(new CSFile(src)));
	}

  	public static String streamToString(InputStream input) throws Exception {
	    InputStreamReader sr = new InputStreamReader(input, "UTF-8");
	    StringBuilder b = new StringBuilder();
	    while (sr.ready()) {
	      char c = (char) sr.read();
	      b.append(c);
	    }
	    sr.close();
	    return b.toString();
	}
}
