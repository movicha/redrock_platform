package utilities.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

/**
 * XML Writer class.
 */
public class XmlWriterImpl extends OutputStreamWriter implements XmlWriter {
	private boolean xmlHeader = true;
	private String charset;
	private boolean prettyBase;
	private boolean prettyHeader;
	private boolean pendingClose;
	private boolean pendingOpen;
	private String pendingComment;
	private int lineType = LINE_UNIX;
	private OutputStream stream;
	private boolean started = false;
	private String[] specialAttributeNames = new String[] {"id", "name" };
	private boolean sortAttributes;
	private int attributeLineWrap;
	
	public final static int LINE_UNIX = 0;
	public final static int LINE_WINDOWS = 1;

	public XmlWriterImpl(OutputStream stream, String charset) throws UnsupportedEncodingException {
		super(stream, charset);
		this.stream = stream;
		this.charset = charset;
	}

	protected boolean condition(boolean bTest, String message) throws IOException {
		if (!bTest)
			throw new IOException(message);
		return bTest;
	}

	// -- writing context ------------------------------------------------


	
	/**
	 * Returns the encoding.
	 * 
	 * @param charset
	 * @return encoding
	 * @throws IOException
	 */
	public static String getXMLCharsetName(String charset) throws IOException {
		if (charset == null || charset.equals(""))
			return "UTF-8";
		else if (charset.equals("US-ASCII"))
			return "UTF-8";
		else if (XmlUtilities.charSetImpliesAscii(charset))
			return "ISO-8859-1";
		else if (charset.equals("UTF-8"))
			return "UTF-8";
		else if (charset.equals("UTF-16") || charset.equals("UTF-16BE") || charset.equals("UTF-16LE"))
			return "UTF-16";
		else 
			throw new IOException("Unknown charset encoding "+charset);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#start()
	 */
	@Override
	public void start() throws IOException {
		condition(!started, "attempt to start after starting");
		levels.clear();
		attributes = null;
		try {
			if (xmlHeader) {
				write("<?xml version=\"1.0\" encoding=\""+getXMLCharsetName(charset)+"\"?>");
				if (prettyBase || prettyHeader)
					write(lineType == LINE_UNIX ? "\n" : "\r\n");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			throw new IOException(e.getMessage());
		}
		started = true;
	}

	private void checkStarted () throws IOException {
		condition(started, "not started");
	}

	private void checkInElement() throws IOException {
		condition(levels.size() > 0, "not in an element");
	}

	// -- attributes ----------------------------------------------------

	private String[][] attributes;
	
	private void addAttribute(String name, String value) throws IOException {
		addAttribute(name, value, false);
	}

	private void addAttribute(String name, String value, boolean noLines) throws IOException {
		if (!XmlUtilities.isNMToken(name))
			throw new IOException("XML name "+name+" is not valid for value '"+value+"'");

		newLevelIfRequired();
		value = XmlUtilities.escapeXML(value, charset, noLines);

		if (attributes == null) 
			attributes = new String[][] {{name, value}};
		else {
			String[][] newattr = new String[attributes.length+1][];
			for (int i = 0; i < attributes.length; i++) {
				condition(!attributes[i][0].equals(name), "attempt to define attribute with name "+name+" more than once for value '"+value+"'");
				newattr[i] = attributes[i];
			}
			attributes = newattr;
			attributes[attributes.length-1] = new String[] {name, value};
		}
	}

	protected String getAttribute(String name) {
		if (attributes != null) {
			for (int i = 0; i < attributes.length; i++) {
				if (attributes[i][0].equals(name)) {
					return attributes[i][1];
				}
			}			
		}
		return null;
	}
	
	protected void setAttribute(String name, String value) throws IOException {
		newLevelIfRequired();
		if (attributes == null) 
			addAttribute(name, value, false);
		else {
			for (int i = 0; i < attributes.length; i++) {
				if (attributes[i][0].equals(name)) {
					attributes[i][1] = XmlUtilities.escapeXML(value, charset, false);
					return;
				}
			}
			addAttribute(name, value);
		}
	}

	protected void commitAttributes() throws IOException {
		
	}

	
	private boolean nameIsSpecial(String name) {
		for (int i = 0; i < specialAttributeNames.length; i++) {
			String n = specialAttributeNames[i];
			if (n.equalsIgnoreCase(name))
				return true;
		}
		return false;
	}
	
	private void writeAttributes(int col) throws IOException {
		commitAttributes();
		if (attributes != null && sortAttributes)
			sortAttributes();	
		int c = col;
		c = writeAttributeSet(true, c, col);
		writeAttributeSet(false, c, col);
		attributes = null;
	}


	private void sortAttributes() {
		// bubble sort - look, it's easy
		for (int i = 0; i < attributes.length - 1; i++) {
			for (int j = 0; j < attributes.length - 1; j++) {
				if (String.CASE_INSENSITIVE_ORDER.compare(attributes[j][0], attributes[j+1][0]) < 0) {
					String[] t = attributes[j];
					attributes[j] = attributes[j+1];
					attributes[j+1] = t;
				}
			}
		}
		
	}


	private int writeAttributeSet(boolean special, int col, int wrap) throws IOException {
		// first pass: name, id
		if (attributes != null) {
			for (int i=0; i < attributes.length; i++) {
				String[] element = attributes[i];
				if (nameIsSpecial(element[0]) == special) {
					col = col + element[0].length()+element[1].length() + 4;
					if (isPretty() && attributeLineWrap > 0 && col > attributeLineWrap && col > wrap) {
						write(lineType == LINE_UNIX ? "\n" : "\r\n");
						for (int j = 0; j < wrap; j++)
							write(" ");
						col = wrap;
					}
					write(' ');
					write(element[0]);
					write("=\"");
					if (element[1] != null)
						write(element[1]);
					write("\"");
				}
			}
		}
		return col;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#attribute(java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void attribute(String namespace, String name, String value, boolean onlyIfNotEmpty) throws IOException {
		if (!onlyIfNotEmpty || value != null && !value.equals(""))
			attribute(namespace, name, value);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#attribute(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void attribute(String namespace, String name, String value) throws IOException {

		checkStarted();
		if (namespace == null || namespace.equals("")) 
			addAttribute(name, value);
		else
			addAttribute(getNSAbbreviation(namespace)+name, value);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#attribute(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void attribute(String name, String value, boolean onlyIfNotEmpty) throws IOException {
		if (!onlyIfNotEmpty || value != null && !value.equals(""))
			attribute(name, value);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#attribute(java.lang.String, java.lang.String)
	 */
	@Override
	public void attribute(String name, String value) throws IOException {
		checkStarted();
		addAttribute(name, value);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#attributeNoLines(java.lang.String, java.lang.String)
	 */
	@Override
	public void attributeNoLines(String name, String value) throws IOException {
		checkStarted();
		addAttribute(name, value, true);
	}

	// -- levels -------------------------------------------------

	private XmlWriterStateStack levels = new XmlWriterStateStack();

	private void newLevelIfRequired() throws IOException {
		if (!pendingOpen) {
			if (!levels.empty())
				levels.current().seeChild();
			XmlWriterState level = new XmlWriterState();
			level.setPretty(isPretty());
			levels.push(level);
			pendingOpen = true;
		}
	}

	// -- namespaces ---------------------------------------------


	private void defineNamespace(String namespace, String abbrev) throws IOException {
		checkStarted();
		if (namespace != null && !namespace.equals("")) {
			if ("".equals(abbrev))
				abbrev = null;

			newLevelIfRequired();

			levels.current().addNamespaceDefn(namespace, abbrev);
			if (abbrev == null)
				addAttribute("xmlns", namespace);
			else
				addAttribute("xmlns:"+abbrev, namespace);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#findByNamespace(java.lang.String)
	 */
	public XmlNamespace findByNamespace(String namespace) {
		for (int i = levels.size() - 1; i >= 0; i--) {
			XmlNamespace ns = levels.item(i).getDefnByNamespace(namespace);
			if (ns != null)
				return ns;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#namespaceDefined(java.lang.String)
	 */
	@Override
	public boolean namespaceDefined(String namespace) {
		return namespace == null || namespace.equals("") || findByNamespace(namespace) != null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#findByAbbreviation(java.lang.String)
	 */
	public XmlNamespace findByAbbreviation(String abbreviation) {
		for (int i = levels.size() - 1; i >= 0; i--) {
			XmlNamespace ns = levels.item(i).getDefnByAbbreviation(abbreviation);
			if (ns != null)
				return ns;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#abbreviationDefined(java.lang.String)
	 */
	@Override
	public boolean abbreviationDefined(String abbreviation) {
		return findByAbbreviation(abbreviation) != null;
	}

	protected XmlNamespace findDefaultNamespace() {
		for (int i = levels.size() - 1; i >= 0; i--) {
			XmlNamespace ns = levels.item(i).getDefaultNamespace();
			if (ns != null)
				return ns;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#getDefaultNamespace()
	 */
	@Override
	public String getDefaultNamespace() {
		XmlNamespace ns = findDefaultNamespace();
		if (ns == null)
			return null;
		else
			return ns.getNamespace();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#namespace(java.lang.String)
	 */
	@Override
	public void namespace(String namespace) throws IOException {
		if (!namespaceDefined(namespace)) {
			int index = 0;
			while (abbreviationDefined("ns"+Integer.toString(index))) 
				index++;
			defineNamespace(namespace, "ns"+Integer.toString(index));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#defaultNamespace(java.lang.String)
	 * 
	 * Replace defaultNamespace()
	 */
	@Override
	public void setDefaultNamespace(String namespace) throws IOException {
		if ((namespace == null && getDefaultNamespace() != null) ||
				(namespace != null && !namespace.equals(getDefaultNamespace())))
			defineNamespace(namespace, "");			
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#namespace(java.lang.String, java.lang.String)
	 */
	@Override
	public void namespace(String namespace, String abbreviation) throws IOException {
		XmlNamespace ns = findByAbbreviation(abbreviation);
		if (ns == null || !ns.getNamespace().equals(namespace))
			defineNamespace(namespace, abbreviation);
	}


	private String getNSAbbreviation(String namespace) throws IOException {
		if ("http://www.w3.org/XML/1998/namespace".equals(namespace))
			return "xml:";
		
		if (namespace == null || "".equals(namespace))
			return "";
		
		XmlNamespace ns = findByNamespace(namespace);
		if (ns == null)
			throw new IOException("Namespace "+namespace+" is not defined");
		else if (ns.getAbbreviation() == null)
			return "";
		else
			return ns.getAbbreviation()+":";
	}

	// -- public API -----------------------------------------------------------

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#comment(java.lang.String, boolean)
	 */
	@Override
	public void comment(String comment, boolean doPretty) throws IOException {
		checkStarted();
		if (pendingClose) { 
			write('>');
			writePendingComment();
			pendingClose = false;
		}
		if (doPretty) {
			writePretty();
			if (isPretty()) {
				for (int i = 0; i < levels.size(); i++)
					write("  ");
			}
		}
		if (levels.inComment())
			write("<!-- "+comment+" -- >");
		else
			write("<!-- "+comment+" -->");
		if (doPretty && !isPretty())
			writePretty();
	}


	private void writePendingComment() throws IOException {
		if (pendingComment != null) {
			if (isPretty())
				write("   ");
			if (levels.inComment())
				write("<!-- "+pendingComment+" -- >");
			else
				write("<!-- "+pendingComment+" -->");
		}
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#open(java.lang.String, java.lang.String)
	 */
	@Override
	public void open(String namespace, String name) throws IOException {
		open(namespace, name, null);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#open(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void open(String namespace, String name, String comment) throws IOException {
		if (!XmlUtilities.isNMToken(name))
			throw new IOException("XML name "+name+" is not valid");
		checkStarted();
		if (pendingClose) { 
			write('>');
			writePendingComment();
			pendingClose = false;
		}

		if (name == null) {
			throw new IOException("name is null");
		}
		newLevelIfRequired();
		levels.current().setName(name);
		levels.current().setNamespace(namespace);
		int col = writePretty();
		write('<');
		if (namespace == null) {
			write(name);
			col = col + name.length()+1;
		} else {
			String n = getNSAbbreviation(namespace)+name;
			write(n);
			col = col + n.length()+1;
		}
		writeAttributes(col);
		pendingOpen = false;
		pendingClose = true;
		pendingComment = comment;
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#close(java.lang.String)
	 */
	@Override
	public void close(String name) throws IOException {
		checkStarted();
		if (levels.empty())
			throw new IOException("Unable to close null|"+name+", nothing to close");
		if (levels.current().getNamespace() != null || !levels.current().getName().equals(name))
			throw new IOException("Unable to close null|"+name+", found "+levels.current().getNamespace()+"|"+levels.current().getName());
		close();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#close(java.lang.String, java.lang.String)
	 */
	@Override
	public void close(String namespace, String name) throws IOException {
		checkStarted();
		if (levels.empty())
			throw new IOException("Unable to close "+namespace+"|"+name+", nothing to close");
		if (!levels.current().getNamespace().equals(namespace) || !levels.current().getName().equals(name))
			throw new IOException("Unable to close "+namespace+"|"+name+", found "+levels.current().getNamespace()+"|"+levels.current().getName());
		close();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#closeToLevel(int)
	 */
	@Override
	public void closeToLevel(int count) throws IOException {
		while (levels.size() > count)
			close();		
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#close()
	 */
	@Override
	public void close() throws IOException {
		checkStarted();
		if (levels.empty()) {
			super.close();
		} else {
			if (pendingClose) { 
				write("/>");
				writePendingComment();
				pendingClose = false;
			} else {
				if (levels.current().hasChildren())
					writePretty();
				write("</");
				if (levels.current().getNamespace() == null)
					write(levels.current().getName());
				else
					write(getNSAbbreviation(levels.current().getNamespace())+levels.current().getName());
				write('>');
			}
			levels.pop();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#open(java.lang.String)
	 */
	@Override
	public void open(String name) throws IOException {
		open(null, name);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#element(java.lang.String, java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void element(String namespace, String name, String content, boolean onlyIfNotEmpty) throws IOException {
		if (!onlyIfNotEmpty || content != null && !content.equals(""))
			element(namespace, name, content);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#element(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void element(String namespace, String name, String content, String comment) throws IOException {
		if (!XmlUtilities.isNMToken(name))
			throw new IOException("XML name "+name+" is not valid");
		open(namespace, name, comment);
		text(content);
		close();
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#element(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void element(String namespace, String name, String content) throws IOException {
		if (!XmlUtilities.isNMToken(name))
			throw new IOException("XML name "+name+" is not valid");
		open(namespace, name);
		text(content);
		close();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#element(java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public void element(String name, String content, boolean onlyIfNotEmpty) throws IOException {
		if (!onlyIfNotEmpty || content != null && !content.equals(""))
			element(null, name, content);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#element(java.lang.String, java.lang.String)
	 */
	@Override
	public void element(String name, String content) throws IOException {
		element(null, name, content);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#text(java.lang.String)
	 */
	@Override
	public void text(String content) throws IOException {
		text(content, false);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#text(java.lang.String, boolean)
	 * 
	 * Replace escapeText()
	 */
	@Override
	public void text(String content, boolean dontEscape) throws IOException {
		checkInElement();
		if (content != null) {
			if (pendingClose) { 
				write(">");
				writePendingComment();
				pendingClose = false;
			}
			if (dontEscape)
				write(content);
			else
				write(XmlUtilities.escapeXML(content, charset, false));
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#cData(java.lang.String)
	 */
	@Override
	public void cData(String text) throws IOException {
		text("<![CDATA["+text+"]]>");		
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#writeBytes(byte[])
	 */
	@Override
	public void writeBytes(byte[] bytes) throws IOException {
		checkInElement();
		if (pendingClose) { 
			write(">");
			writePendingComment();
			pendingClose = false;
		}
		flush();
		stream.write(bytes);
	}


	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#isPretty()
	 */
	@Override
	public boolean isPretty() throws IOException {
		return (levels == null || levels.empty()) ? prettyBase : levels.current().isPretty();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#setPretty(boolean)
	 */
	@Override
	public void setPretty(boolean pretty) throws IOException {
		if (levels == null || levels.empty())
			this.prettyBase = pretty;
		else 
			levels.current().setPretty(pretty);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#startCommentBlock()
	 */
	@Override
	public void startCommentBlock() throws IOException {
		if (levels.inComment())
			throw new IOException("cannot nest comments");
		levels.current().setInComment(true);
		if (isPretty())
			writePretty();
		write("<!--");
		if (isPretty())
			writePretty();		
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ohf.utilities.xml.XmlWriter#endCommentBlock()
	 */
	@Override
	public void endCommentBlock() throws IOException {
		if (!levels.inComment())
			throw new IOException("cannot close a comment block when it is open");
		if (!levels.current().isInComment())
			throw new IOException("cannot close a comment block when it is open");
		if (isPretty())
			writePretty();
		write("-->");
		if (isPretty())
			writePretty();		
		levels.current().setInComment(false);
	}

	public boolean isSortAttributes() {
		return sortAttributes;
	}

	public void setSortAttributes(boolean sortAttributes) {
		this.sortAttributes = sortAttributes;
	}


	public boolean isPrettyHeader() {
		return prettyHeader;
	}

	public void setPrettyHeader(boolean pretty) {
		this.prettyHeader = pretty;
	}

	public int writePretty() throws IOException {
		return writePretty(true);
	}
	
	public int writePretty(boolean eoln) throws IOException {
		if (isPretty()) {
			if (eoln)
				write(lineType == LINE_UNIX ? "\n" : "\r\n");
			for (int i = 0; i < levels.size() - 1; i++)
				write("  ");
			return (levels.size() - 1) * 2;
		} else
			return 0;
	}

	public int getLineType() {
		return lineType;
	}

	public void setLineType(int lineType) {
		this.lineType = lineType;
	}

	public boolean isXmlHeader() {
		return xmlHeader;
	}

	public void setXmlHeader(boolean xmlHeader) {
		this.xmlHeader = xmlHeader;
	}

	public String[] getSpecialAttributeNames() {
		return specialAttributeNames;
	}

	public void setSpecialAttributeNames(String[] specialAttributeNames) {
		this.specialAttributeNames = specialAttributeNames;
	}

	public int getAttributeLineWrap() {
		return attributeLineWrap;
	}

	public void setAttributeLineWrap(int attributeLineWrap) {
		this.attributeLineWrap = attributeLineWrap;
	}

	public void escapedText(String content) throws IOException {
		text("");
		int i = content.length();
		if (isPretty())
		  while (i > 0 && (content.charAt(i-1) == '\r' || content.charAt(i-1) == '\n'))
			 i--;
		write(content.substring(0, i));
	}	
}

