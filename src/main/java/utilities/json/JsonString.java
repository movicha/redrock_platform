package utilities.json;

/**
 * The <code>JsonString</code> interface allows a <code>toJsonString()</code> 
 * method so that a class can change the behavior of 
 * <code>JsonObject.toString()</code>, <code>JsonArray.toString()</code>,
 * and <code>JsonWriter.value(</code>Object<code>)</code>. The 
 * <code>toJsonString</code> method will be used instead of the default behavior 
 * of using the Object's <code>toString()</code> method and quoting the result.
 */
public interface JsonString {
	/**
	 * The <code>toJsonString</code> method allows a class to produce its own JSON 
	 * serialization. 
	 * 
	 * @return A strictly syntactically correct JSON text.
	 */
	public String toJsonString();
}
