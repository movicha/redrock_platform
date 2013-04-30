package resources;

public interface NodeType  {
	private String author; //author of node
	private String name; //name of node
	private String descriptionLong, descriptionShort; //description of node in 2 formats
	private String nodeType; //type of this Node

	public String getAllKnownNodeTypes();
	public void setType(String typeOfNode);
}