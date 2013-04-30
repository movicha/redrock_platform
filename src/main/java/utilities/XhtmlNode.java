package utilities;

public interface XhtmlNode {
 	private NodeType node; //decides node type
	private String name; //name variable
	private Dictionary attributes;
	private Map attributes; //Map<String, String> Atributes = new HashMap<String, String>();
	private ArrayList childNodes; //array of XhtmlNodes
	private String content; //content of this XhtmlNode

	public String getNodeType(); //NodeType value
	public void setNodeType(String nodeType); //set NodeType value
	public void setContent(String content);
	public XhtmlNode addTag(String name);
	public XhtmlNode addTag(Integer index, String name);
	public XhtmlNode addComment(String content);
	public XhtmlNode addDocType(String content);
	public XhtmlNode addInstruction(String content);
	public XhtmlNode addText(String content);
	public XhtmlNode addText(Integer index, String content);
	public boolean allChildrenAreText();
	public XhtmlNode getElement(String name);
	public String allText();
	public void attribute(String name, String value);
	public String getAttribute(String name);
	public void setAttribute(String name, String value);
}