package resources;

public interface FHIRResourceDictionary  {
	private Dictionary dataForResource; //distionary of resources
	private String resourceName; //name of resource dictionary
	private String resourceType; //type of resource the dictionary itself is
	
	public void setDataForKey(String key, Data id);
}
