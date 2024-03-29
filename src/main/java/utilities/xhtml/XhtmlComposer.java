package utilities.xhtml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import utilities.xml.XmlWriter;

public class XhtmlComposer {

	public static final String XHTML_NS = "http://www.w3.org/1999/xhtml";
	private boolean pretty;

	public boolean isPretty() {
		return pretty;
	}

	public void setPretty(boolean pretty) {
		this.pretty = pretty;
	}

	private Writer dst;

	public String compose(XhtmlDocument doc) throws Exception {
		StringWriter sdst = new StringWriter();
		dst = sdst;
		composeDoc(doc);
		return sdst.toString();
	}

	public String compose(XhtmlNode node) throws Exception {
		StringWriter sdst = new StringWriter();
		dst = sdst;
		writeNode("", node);
		return sdst.toString();
	}

	public void compose(OutputStream stream, XhtmlDocument doc)
			throws Exception {
		byte[] bom = new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };
		stream.write(bom);
		dst = new OutputStreamWriter(stream, "UTF-8");
		composeDoc(doc);
		dst.flush();
	}

	private void composeDoc(XhtmlDocument doc) throws Exception {
		// headers....
		// dst.append("<html>" + (isPretty() ? "\r\n" : ""));
		for (XhtmlNode c : doc.getChildNodes())
			writeNode("  ", c);
		// dst.append("</html>" + (isPretty() ? "\r\n" : ""));
	}

	private void writeNode(String indent, XhtmlNode node) throws Exception {
		if (node.getNodeType() == NodeType.Comment)
			writeComment(indent, node);
		else if (node.getNodeType() == NodeType.DocType)
			writeDocType(node);
		else if (node.getNodeType() == NodeType.Instruction)
			writeInstruction(node);
		else if (node.getNodeType() == NodeType.Element)
			writeElement(indent, node);
		else if (node.getNodeType() == NodeType.Text)
			writeText(node);
		else
			throw new Exception("Unknown node type: "
					+ node.getNodeType().toString());
	}

	private void writeText(XhtmlNode node) throws Exception {
		for (char c : node.getContent().toCharArray()) {
			if (c == '&')
				dst.append("&amp;");
			else if (c == '<')
				dst.append("&lt;");
			else if (c == '>')
				dst.append("&gt;");
			else if (c == '"')
				dst.append("&quot;");
			else if (c == XhtmlNode.NBSP.charAt(0))
				dst.append("&nbsp;");
			else if (c == (char) 0xA7)
				dst.append("&sect;");
			else if (c == (char) 169)
				dst.append("&copy;");
			else if (c == (char) 8482)
				dst.append("&trade;");
			else if (c == (char) 174)
				dst.append("&reg;");
			else
				dst.append(c);
		}
	}

	private void writeComment(String indent, XhtmlNode node) throws IOException {
		dst.append(indent + "<!-- " + node.getContent().trim() + " -->"
				+ (isPretty() ? "\r\n" : ""));
	}

	private void writeDocType(XhtmlNode node) throws IOException {
		dst.append("<!" + node.getContent() + ">\r\n");
	}

	private void writeInstruction(XhtmlNode node) throws IOException {
		dst.append("<?" + node.getContent() + "?>\r\n");
	}

	private String escapeHtml(String s) {
		if (s == null || s.equals(""))
			return null;
		StringBuilder b = new StringBuilder();
		for (char c : s.toCharArray())
			if (c == '<')
				b.append("&lt;");
			else if (c == '>')
				b.append("&gt;");
			else if (c == '"')
				b.append("&quot;");
			else if (c == '&')
				b.append("&amp;");
			else
				b.append(c);
		return b.toString();
	}

	private String attributes(XhtmlNode node) {
		StringBuilder s = new StringBuilder();
		for (String n : node.getAttributes().keySet())
			s.append(" " + n + "=\"" + escapeHtml(node.getAttributes().get(n))
					+ "\"");
		return s.toString();
	}

	private void writeElement(String indent, XhtmlNode node) throws Exception {
		if (!pretty)
			indent = "";

		if (node.getChildNodes().size() == 0)
			dst.append(indent + "<" + node.getName() + attributes(node) + "/>"
					+ (isPretty() ? "\r\n" : ""));
		else {
			boolean act = node.allChildrenAreText();
			if (act || !pretty)
				dst.append(indent + "<" + node.getName() + attributes(node)
						+ ">");
			else
				dst.append(indent + "<" + node.getName() + attributes(node)
						+ ">\r\n");
			if (node.getName() == "head" && node.getElement("meta") == null)
				dst.append(indent
						+ "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"/>"
						+ (isPretty() ? "\r\n" : ""));

			for (XhtmlNode c : node.getChildNodes())
				writeNode(indent + "  ", c);
			if (act)
				dst.append("</" + node.getName() + ">"
						+ (isPretty() ? "\r\n" : ""));
			else if (node.getChildNodes().get(node.getChildNodes().size() - 1)
					.getNodeType() == NodeType.Text)
				dst.append((isPretty() ? "\r\n" + indent : "") + "</"
						+ node.getName() + ">" + (isPretty() ? "\r\n" : ""));
			else
				dst.append(indent + "</" + node.getName() + ">"
						+ (isPretty() ? "\r\n" : ""));
		}
	}

	public void compose(XmlWriter xml, XhtmlNode node) throws Exception {
		if (node.getNodeType() == NodeType.Comment)
			xml.comment(node.getContent(), isPretty());
		else if (node.getNodeType() == NodeType.Element)
			composeElement(xml, node);
		else if (node.getNodeType() == NodeType.Text)
			xml.text(node.getContent());
		else
			throw new Exception("Unhandled node type: "
					+ node.getNodeType().toString());
	}

	private void composeElement(XmlWriter xml, XhtmlNode node)
			throws Exception {
		for (String n : node.getAttributes().keySet())
			xml.attribute(n, node.getAttributes().get(n));
		xml.open(XHTML_NS, node.getName());
		for (XhtmlNode n : node.getChildNodes())
			compose(xml, n);
		xml.close(XHTML_NS, node.getName());
	}

}
