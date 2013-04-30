package utilities.json;

import java.util.Iterator;

/**
* This provides static methods to convert an XML text into a JsonObject,
* and to covert a JsonObject into an XML text.
* @author JSON.org
* @version 2011-02-11
*/
public class Xml {

    /** The Character '&'. */
    public static final Character AMP = new Character('&');

    /** The Character '''. */
    public static final Character APOS = new Character('\'');

    /** The Character '!'. */
    public static final Character BANG = new Character('!');

    /** The Character '='. */
    public static final Character EQ = new Character('=');

    /** The Character '>'. */
    public static final Character GT = new Character('>');

    /** The Character '<'. */
    public static final Character LT = new Character('<');

    /** The Character '?'. */
    public static final Character QUEST = new Character('?');

    /** The Character '"'. */
    public static final Character QUOT = new Character('"');

    /** The Character '/'. */
    public static final Character SLASH = new Character('/');

    /**
	* Replace special characters with XML escapes:
	* <pre>
	* &amp; <small>(ampersand)</small> is replaced by &amp;amp;
	* &lt; <small>(less than)</small> is replaced by &amp;lt;
	* &gt; <small>(greater than)</small> is replaced by &amp;gt;
	* &quot; <small>(double quote)</small> is replaced by &amp;quot;
	* </pre>
	* @param string The string to be escaped.
	* @return The escaped string.
	*/
    public static String escape(String string) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = string.length(); i < length; i++) {
            char c = string.charAt(i);
            switch (c) {
            case '&':
                sb.append("&amp;");
                break;
            case '<':
                sb.append("&lt;");
                break;
            case '>':
                sb.append("&gt;");
                break;
            case '"':
                sb.append("&quot;");
                break;
            case '\'':
                sb.append("&apos;");
                break;
            default:
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    /**
	* Throw an exception if the string contains whitespace.
	* Whitespace is not allowed in tagNames and attributes.
	* @param string
	* @throws JsonException
	*/
    public static void noSpace(String string) throws JsonException {
 		int i, length = string.length();
		if (length == 0) {
			throw new JsonException("Empty string.");
		}

		for (i = 0; i < length; i += 1) {
			if (Character.isWhitespace(string.charAt(i))) {
				throw new JsonException("'" + string +
				"' contains a space character.");
			}
		}
    }

    /**
	* Scan the content following the named tag, attaching it to the context.
	* @param x The XmlTokenizer containing the source string.
	* @param context The JsonObject that will include the new material.
	* @param name The tag name.
	* @return true if the close tag is processed.
	* @throws JsonException
	*/
    private static boolean parse(XmlTokenizer x, JsonObject context,
                                 String name) throws JsonException {
        char c;
        int i;
        JsonObject jsonobject = null;
        String string;
        String tagName;
        Object token;

		// Test for and skip past these forms:
		// <!-- ... -->
		// <! ... >
		// <![ ... ]]>
		// <? ... ?>
		// Report errors for these forms:
		// <>
		// <=
		// <<

        token = x.nextToken();

		// <!

        if (token == BANG) {
            c = x.next();
            if (c == '-') {
                if (x.next() == '-') {
                    x.skipPast("-->");
                    return false;
                }
                x.back();
            } else if (c == '[') {
                token = x.nextToken();
                if (token.equals("CDATA")) {
                    if (x.next() == '[') {
                        string = x.nextCDATA();
                        if (string.length() > 0) {
                            context.accumulate("content", string);
                        }
                        return false;
                    }
                }
                throw x.syntaxError("Expected 'CDATA['");
            }
            i = 1;
            do {
                token = x.nextMeta();
                if (token == null) {
                    throw x.syntaxError("Missing '>' after '<!'.");
                } else if (token == LT) {
                    i += 1;
                } else if (token == GT) {
                    i -= 1;
                }
            } while (i > 0);
            return false;
        } else if (token == QUEST) {

		// <?

            x.skipPast("?>");
            return false;
        } else if (token == SLASH) {

		// Close tag </

         	token = x.nextToken();
            if (name == null) {
                throw x.syntaxError("Mismatched close tag " + token);
            }
            if (!token.equals(name)) {
                throw x.syntaxError("Mismatched " + name + " and " + token);
            }
            if (x.nextToken() != GT) {
                throw x.syntaxError("Misshaped close tag");
            }
            return true;

        } else if (token instanceof Character) {
            throw x.syntaxError("Misshaped tag");

		// Open tag <

        } else {
            tagName = (String)token;
            token = null;
            jsonobject = new JsonObject();
            for (;;) {
                if (token == null) {
                    token = x.nextToken();
                }

			// attribute = value

                if (token instanceof String) {
                    string = (String)token;
                    token = x.nextToken();
                    if (token == EQ) {
                        token = x.nextToken();
                        if (!(token instanceof String)) {
                            throw x.syntaxError("Missing value");
                        }
                        jsonobject.accumulate(string,
                         Xml.stringToValue((String)token));
                        token = null;
                    } else {
                        jsonobject.accumulate(string, "");
                    }

				// Empty tag <.../>

                } else if (token == SLASH) {
                    if (x.nextToken() != GT) {
                        throw x.syntaxError("Misshaped tag");
                    }
                    if (jsonobject.length() > 0) {
                        context.accumulate(tagName, jsonobject);
                    } else {
                     context.accumulate(tagName, "");
                    }
                    return false;

				// Content, between <...> and </...>

                } else if (token == GT) {
                    for (;;) {
                        token = x.nextContent();
                        if (token == null) {
                            if (tagName != null) {
                                throw x.syntaxError("Unclosed tag " + tagName);
                            }
                            return false;
                        } else if (token instanceof String) {
                            string = (String)token;
                            if (string.length() > 0) {
                                jsonobject.accumulate("content",
                                 Xml.stringToValue(string));
                            }

						// Nested element

                        } else if (token == LT) {
                            if (parse(x, jsonobject, tagName)) {
                                if (jsonobject.length() == 0) {
                                    context.accumulate(tagName, "");
                                } else if (jsonobject.length() == 1 &&
                                       jsonobject.opt("content") != null) {
                                    context.accumulate(tagName,
                                     jsonobject.opt("content"));
                                } else {
                                    context.accumulate(tagName, jsonobject);
                                }
                                return false;
                            }
                        }
                    }
                } else {
                    throw x.syntaxError("Misshaped tag");
                }
            }
        }
    }


    /**
	* Try to convert a string into a number, boolean, or null. If the string
	* can't be converted, return the string. This is much less ambitious than
	* JsonObject.stringToValue, especially because it does not attempt to
	* convert plus forms, octal forms, hex forms, or E forms lacking decimal
	* points.
	* @param string A String.
	* @return A simple JSON value.
	*/
    public static Object stringToValue(String string) {
        if (string.equals("")) {
            return string;
        }
        if (string.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (string.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        if (string.equalsIgnoreCase("null")) {
            return JsonObject.NULL;
        }
        if (string.equals("0")) {
            return new Integer(0);
        }

		// If it might be a number, try converting it. If that doesn't work,
		// return the string.

        try {
			char initial = string.charAt(0);
			boolean negative = false;
			if (initial == '-') {
				initial = string.charAt(1);
				negative = true;
			}
			if (initial == '0' && string.charAt(negative ? 2 : 1) == '0') {
				return string;
			}
			if ((initial >= '0' && initial <= '9')) {
                if (string.indexOf('.') >= 0) {
                    return Double.valueOf(string);
                } else if (string.indexOf('e') < 0 && string.indexOf('E') < 0) {
                    Long myLong = new Long(string);
                    if (myLong.longValue() == myLong.intValue()) {
                        return new Integer(myLong.intValue());
                    } else {
                        return myLong;
                    }
                }
			}
        } catch (Exception ignore) {}
        return string;
    }

    
    /**
	* Convert a well-formed (but not necessarily valid) XML string into a
	* JsonObject. Some information may be lost in this transformation
	* because JSON is a data format and XML is a document format. XML uses
	* elements, attributes, and content text, while JSON uses unordered
	* collections of name/value pairs and arrays of values. JSON does not
	* does not like to distinguish between elements and attributes.
	* Sequences of similar elements are represented as JsonArrays. Content
	* text may be placed in a "content" member. Comments, prologs, DTDs, and
	* <code>&lt;[ [ ]]></code> are ignored.
	* @param string The source string.
	* @return A JsonObject containing the structured data from the XML string.
	* @throws JsonException
	*/
    public static JsonObject toJsonObject(String string) throws JsonException {
        JsonObject jo = new JsonObject();
        XmlTokenizer x = new XmlTokenizer(string);
        while (x.more() && x.skipPast("<")) {
            parse(x, jo, null);
        }
        return jo;
    }


    /**
	* Convert a JsonObject into a well-formed, element-normal XML string.
	* @param object A JsonObject.
	* @return A string.
	* @throws JsonException
	*/
    public static String toString(Object object) throws JsonException {
        return toString(object, null);
    }


    /**
	* Convert a JsonObject into a well-formed, element-normal XML string.
	* @param object A JsonObject.
	* @param tagName The optional name of the enclosing tag.
	* @return A string.
	* @throws JsonException
	*/
    public static String toString(Object object, String tagName)
            throws JsonException {
        StringBuffer sb = new StringBuffer();
        int i;
        JsonArray ja;
        JsonObject jo;
        String key;
        Iterator keys;
        int length;
        String string;
        Object value;
        if (object instanceof JsonObject) {

			// Emit <tagName>

            if (tagName != null) {
                sb.append('<');
                sb.append(tagName);
                sb.append('>');
            }

			// Loop thru the keys.

            jo = (JsonObject)object;
            keys = jo.keys();
            while (keys.hasNext()) {
                key = keys.next().toString();
                value = jo.opt(key);
                if (value == null) {
                 value = "";
                }
                if (value instanceof String) {
                    string = (String)value;
                } else {
                    string = null;
                }

				// Emit content in body

                if (key.equals("content")) {
                    if (value instanceof JsonArray) {
                        ja = (JsonArray)value;
                        length = ja.length();
                        for (i = 0; i < length; i += 1) {
                            if (i > 0) {
                                sb.append('\n');
                            }
                            sb.append(escape(ja.get(i).toString()));
                        }
                    } else {
                        sb.append(escape(value.toString()));
                    }

					// Emit an array of similar keys

                } else if (value instanceof JsonArray) {
                    ja = (JsonArray)value;
                    length = ja.length();
                    for (i = 0; i < length; i += 1) {
                        value = ja.get(i);
                        if (value instanceof JsonArray) {
                            sb.append('<');
                            sb.append(key);
                            sb.append('>');
                            sb.append(toString(value));
                            sb.append("</");
                            sb.append(key);
                            sb.append('>');
                        } else {
                            sb.append(toString(value, key));
                        }
                    }
                } else if (value.equals("")) {
                    sb.append('<');
                    sb.append(key);
                    sb.append("/>");

					// Emit a new tag <k>

                } else {
                    sb.append(toString(value, key));
                }
            }
            if (tagName != null) {

				// Emit the </tagname> close tag

                sb.append("</");
                sb.append(tagName);
                sb.append('>');
            }
            return sb.toString();

			// XML does not have good support for arrays. If an array appears in a place
			// where XML is lacking, synthesize an <array> element.

        } else {
            if (object.getClass().isArray()) {
                object = new JsonArray(object);
            }
            if (object instanceof JsonArray) {
                ja = (JsonArray)object;
                length = ja.length();
                for (i = 0; i < length; i += 1) {
                    sb.append(toString(ja.opt(i), tagName == null ? "array" : tagName));
                }
                return sb.toString();
            } else {
                string = (object == null) ? "null" : escape(object.toString());
                return (tagName == null) ? "\"" + string + "\"" :
                    (string.length() == 0) ? "<" + tagName + "/>" :
                    "<" + tagName + ">" + string + "</" + tagName + ">";
            }
        }
    }
}