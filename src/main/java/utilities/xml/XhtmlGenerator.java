package utilities.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import utilities.Utilities;
import utilities.xml.XhtmlGeneratorAdorner.XhtmlGeneratorAdornerState;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.ProcessingInstruction;
import org.w3c.dom.Text;

public class XhtmlGenerator {

	private XhtmlGeneratorAdorner adorner;
	
  	public XhtmlGenerator(XhtmlGeneratorAdorner adorner) {
    	super();
    	this.adorner = adorner;
  	}

  	public String generateInsert(Document doc, String name, String desc) throws Exception {
	    StringWriter out = new StringWriter();
    
	    out.write("<div class=\"example\">\r\n");
	    out.write("<p>Example Instance \""+name+"\""+(desc == null ? "" : ": "+ Utilities.escapeXml(desc))+"</p>\r\n"); 
	    out.write("<pre class=\"xml\">\r\n");

	    XhtmlGeneratorAdornerState state = null; // adorner == null ? new XhtmlGeneratorAdornerState("", "") : adorner.getState(this, null, null);
	    for (int i = 0; i < doc.getChildNodes().getLength(); i++)
		     writeNode(out, doc.getChildNodes().item(i), state);
    
	    out.write("</pre>\r\n");
	    out.write("</div>\r\n");
	    out.flush();
	    return out.toString();
  	}

  public void generate(Document doc, File xhtml, String name, String desc) throws Exception {
		FileOutputStream outs = new FileOutputStream(xhtml);
		OutputStreamWriter out = new OutputStreamWriter(outs);
		
		out.write("<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\" lang=\"en\">\r\n");
		out.write("<head>\r\n");
		out.write(" <title>Example Instance for "+name+"</title>\r\n");
		out.write(" <link rel=\"Stylesheet\" href=\"fhir.css\" type=\"text/css\" media=\"screen\"/>\r\n");
		out.write("</head>\r\n");
		out.write("<body>\r\n");
	    out.write("<p>&nbsp;</p>\r\n");	
	    out.write("<div class=\"example\">\r\n");
	    out.write("<p>"+Utilities.escapeXml(desc)+"</p>\r\n"); 
	    out.write("<pre class=\"xml\">\r\n");

	    XhtmlGeneratorAdornerState state = null; // adorner == null ? new XhtmlGeneratorAdornerState("", "") : adorner.getState(this, null, null);
			for (int i = 0; i < doc.getChildNodes().getLength(); i++)
				writeNode(out, doc.getChildNodes().item(i), state);
		
	    out.write("</pre>\r\n");
	    out.write("</div>\r\n");
		out.write("</body>\r\n");
		out.write("</html>\r\n");
		out.flush();
		outs.close();
	}

	private void writeNode(Writer out, Node node, XhtmlGeneratorAdornerState state) throws Exception {
		if (node.getNodeType() == Node.ELEMENT_NODE)
			writeElement(out, (Element) node, state);
		else if (node.getNodeType() == Node.TEXT_NODE)
			writeText(out, (Text) node);
		else if (node.getNodeType() == Node.COMMENT_NODE)
			writeComment(out, (Comment) node);
		else if (node.getNodeType() == Node.PROCESSING_INSTRUCTION_NODE)
			writeProcessingInstruction(out, (ProcessingInstruction) node);
		else if (node.getNodeType() != Node.ATTRIBUTE_NODE)
			throw new Exception("Unhandled node type");
	}

	private void writeProcessingInstruction(Writer out, ProcessingInstruction node) {
		
		
	}

	private void writeComment(Writer out, Comment node) throws DOMException, IOException {
		out.write("<span class=\"xmlcomment\">&lt;!-- "+node.getTextContent()+" --&gt;</span>");
	}

	private void writeText(Writer out, Text node) throws DOMException, IOException {
		out.write("<b>"+escapeHtml(Utilities.escapeXml(node.getTextContent()))+"</b>");
	}

	private void writeElement(Writer out, Element node, XhtmlGeneratorAdornerState state) throws Exception {
		out.write("<span class=\"xmltag\">&lt;"+node.getNodeName()+"</span>");
		if (node.hasAttributes()) {
			out.write("<span class=\"xmlattr\">");
			for (int i = 0; i < node.getAttributes().getLength(); i++) {
			  if (adorner != null) {
		      XhtmlGeneratorAdornerState attrState = adorner.getAttributeMarkup(this, state, node, node.getAttributes().item(i).getNodeName(), node.getAttributes().item(i).getTextContent());
		      out.write(" "+node.getAttributes().item(i).getNodeName()+"=\"<span class=\"xmlattrvalue\">"+attrState.getPrefix()+escapeHtml(node.getAttributes().item(i).getTextContent())+attrState.getSuffix()+"</span>\"");
			  } else
				out.write(" "+node.getAttributes().item(i).getNodeName()+"=\"<span class=\"xmlattrvalue\">"+escapeHtml(node.getAttributes().item(i).getTextContent())+"</span>\"");
			}
			out.write("</span>");
	
		}
		if (node.hasChildNodes()) {
			out.write("<span class=\"xmltag\">&gt;</span>");
			XhtmlGeneratorAdornerState newstate = adorner == null ? new XhtmlGeneratorAdornerState("", "") : adorner.getState(this, state, node);
			out.write(newstate.getPrefix());
			for (int i = 0; i < node.getChildNodes().getLength(); i++)
				writeNode(out, node.getChildNodes().item(i), newstate);
			
      out.write(newstate.getSuffix());
			out.write("<span class=\"xmltag\">&lt;/"+node.getNodeName()+"&gt;</span>");
		}
		else 
			out.write("<span class=\"xmltag\">/&gt;</span>");
	}
	
	private String escapeHtml(String doco) {
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
		  else if (c == '\t')
			  b.append("  ");
		  else if (c == '\'')
			  b.append("&apos;");
		  else if (c == '"')
			  b.append("&quot;");
		  else 
			  b.append(c);
		}		
		return b.toString();
	}	
}
