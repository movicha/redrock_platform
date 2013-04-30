package utilities;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.Map;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Utilities {
	 /**
     * Returns the plural form of the word in the string.
     * 
     * Examples:
     * 
     * <pre>
     *   inflector.pluralize(&quot;post&quot;)               #=&gt; &quot;posts&quot;
     *   inflector.pluralize(&quot;octopus&quot;)            #=&gt; &quot;octopi&quot;
     *   inflector.pluralize(&quot;sheep&quot;)              #=&gt; &quot;sheep&quot;
     *   inflector.pluralize(&quot;words&quot;)              #=&gt; &quot;words&quot;
     *   inflector.pluralize(&quot;the blue mailman&quot;)   #=&gt; &quot;the blue mailmen&quot;
     *   inflector.pluralize(&quot;CamelOctopus&quot;)       #=&gt; &quot;CamelOctopi&quot;
     * </pre>
     * 
     * 
     * 
     * Note that if the {@link Object#toString()} is called on the supplied object, so this method works for non-strings, too.
     * 
     * 
     * @param word the word that is to be pluralized.
     * @return the pluralized form of the word, or the word itself if it could not be pluralized
     * @see #singularize(Object)
     */
    public static String pluralizeMe( String word ) {
    	Inflector inf = new Inflector();
    	return inf.pluralize(word);
    }
    
  
	public static boolean IsInteger(String string) {
		try {
			int i = Integer.parseInt(string);
			return i != i+1;
		} catch (Exception e) {
			return false;
		}
	}
	
	public static String escapeXml(String doco) {
		if (doco == null)
			return "";
		
		StringBuilder b = new StringBuilder();
		for (char c : doco.toCharArray()) {
		  if (c == '<')
			  b.append("&lt;");
		  else if (c == '>')
			  b.append("&gt;");
		  else if (c == '&')
			  b.append("&amp;");
      else if (c == '"')
        b.append("&quot;");
		  else 
			  b.append(c);
		}		
		return b.toString();
	}

	
	public static String capitalize(String s) {
		if( s == null ) return null;
		if( s.length() == 0 ) return s;
		if( s.length() == 1 ) return s.toUpperCase();
		
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	
	
	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if(!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;

		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();
			destination.transferFrom(source, 0, source.size());
		}
		finally {
			if(source != null) {
				source.close();
			}
			if(destination != null) {
				destination.close();
			}
		}
	}

	public static boolean checkFolder(String dir, List<String> errors) {
		if (!new CSFile(dir).exists()) {
			errors.add("Unable to find directory "+dir);
			return false;
		} else {
			return true;
		}
	}

	public static boolean checkFile(String purpose, String dir, String file, List<String> errors) {
		if (!new CSFile(dir+file).exists()) {
			errors.add("Unable to find "+purpose+" file "+file+" in "+dir);
			return false;
		} else {
			return true;
		}
	}

	public static String asCSV(List<String> strings) {
		StringBuilder s = new StringBuilder();
		boolean first = true;
		for (String n : strings) {
			if (!first)
				s.append(",");
			s.append(n);
			first = false;
		}
		return s.toString();
	}

	public static String asHtmlBr(String prefix, List<String> strings) {
		StringBuilder s = new StringBuilder();
		boolean first = true;
		for (String n : strings) {
			if (!first)
				s.append("<br/>");	
			s.append(prefix);
			s.append(n);
			first = false;
		}
		return s.toString();
	}

	public static void clearDirectory(String folder) {
		String[] files = new CSFile(folder).list();
		for (String f : files) {
			File fh = new CSFile(folder+File.separatorChar+f);
			if (fh.isDirectory()) 
				clearDirectory(fh.getAbsolutePath());
			fh.delete();
		}
	}

	public static void createDirectory(String path) {
		new CSFile(path).mkdirs();    
	}

	public static String changeFileExt(String name, String ext) {
		if (name.lastIndexOf('.') > -1)
			return name.substring(0, name.lastIndexOf('.')) + ext;
		else
			return name+ext;
	}

	public static String cleanupTextString( String contents ) {
		if( contents == null || contents.trim().equals("") )
			return null;
		else
			return contents.trim();
	}


	public static boolean noString(String v) {
		return v == null || v.equals("");
	}


	public static byte[] transform(Map<String, byte[]> files, byte[] source, byte[] xslt) throws Exception {
		TransformerFactory f = TransformerFactory.newInstance();
		StreamSource xsrc = new StreamSource(new ByteArrayInputStream(xslt));
		f.setURIResolver(new ZipUriResolver(files));
		Transformer t = f.newTransformer(xsrc);

		t.setURIResolver(new ZipUriResolver(files));
		StreamSource src = new StreamSource(new ByteArrayInputStream(source));
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		StreamResult res = new StreamResult(out);
		t.transform(src, res);
		return out.toByteArray();    
	}

	public static void bytesToFile(byte[] content, String filename) throws Exception {
		FileOutputStream out = new FileOutputStream(filename);
		out.write(content);
		out.close();
	}


	public static void transform(String xsltDir, String source, String xslt, String dest) throws Exception {
		/* default java approach, but this doesn't support xslt2
		TransformerFactory f = TransformerFactory.newInstance();
		StreamSource xsrc = new StreamSource(new FileInputStream(xslt));
		f.setURIResolver(new UriResolver(xsltDir));
		Transformer t = f.newTransformer(xsrc);

		t.setURIResolver(new UriResolver(xsltDir));
		StreamSource src = new StreamSource(new FileInputStream(source));
		StreamResult res = new StreamResult(new FileOutputStream(dest));
		t.transform(src, res);
		*/
		TransformerFactory f = TransformerFactory.newInstance();
		StreamSource xsrc = new StreamSource(new FileInputStream(xslt));
		f.setURIResolver(new UriResolver(xsltDir));
		Transformer t = f.newTransformer(xsrc);

		t.setURIResolver(new UriResolver(xsltDir));
		StreamSource src = new StreamSource(new FileInputStream(source));
		StreamResult res = new StreamResult(new FileOutputStream(dest));
		t.transform(src, res);
	}


	public static String appendSlash(String definitions) {
		return definitions.endsWith(File.separator) ? definitions : definitions+File.separator;
	}


	public static String fileTitle(String file) {
		String s = new File(file).getName();
		return s.substring(0, s.indexOf("."));
	}


	public static String normaliseEolns(String value) {
		return value.replace("\r\n", "\r").replace("\n", "\r").replace("\r", "\r\n");
	}


	public static String unescapeXml(String xml) throws Exception {
		if (xml == null)
			return null;

		StringBuilder b = new StringBuilder();
		int i = 0;
		while (i < xml.length()) {
			if (xml.charAt(i) == '&') {
		 		StringBuilder e = new StringBuilder();
				i++;
				while (xml.charAt(i) != ';') {
					e.append(xml.charAt(i));
					i++;
				}
				if (e.toString().equals("lt")) 
					b.append("<");
				else if (e.toString().equals("gt")) 
					b.append(">");
				else if (e.toString().equals("amp")) 
					b.append("&");
				else if (e.toString().equals("quot")) 
					b.append("\"");
				else
					throw new Exception("unknown XML entity \""+e.toString()+"\"");
			} else
				b.append(xml.charAt(i));
			i++;
		}   
		return b.toString();
	}

//	public static void checkCase(String filename) {
//		File f = new CSFile(filename);
//		if (!f.getName().equals(filename))
//			throw new Exception("Filename  ")
//  } 
}