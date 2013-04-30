package utilities.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import utilities.TextFile;
import utilities.json.JsonObject;
import utilities.json.Xml;

public class JsonGenerator {
	public void generate(File source, File dest) throws Exception {
		String xml = TextFile.fileToString(source.getAbsolutePath());
		JsonObject json = Xml.toJsonObject(xml);
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream((dest)));
		json.write(writer);
		writer.flush();
		writer.close();
	}
}
