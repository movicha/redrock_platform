package utilities.json;

/**
 * The XmlTokenizer extends the JsonTokenizer to provide additional methods
 * for the parsing of Xml texts.
 * @author JSON.org
 * @version 2010-12-24
 */
public class XmlTokenizer extends JsonTokenizer {
   /** The table of entity values. It initially contains Character values for
    * amp, apos, gt, lt, quot.
    */
   public static final java.util.HashMap entity;

   static {
       entity = new java.util.HashMap(8);
       entity.put("amp",  Xml.AMP);
       entity.put("apos", Xml.APOS);
       entity.put("gt",   Xml.GT);
       entity.put("lt",   Xml.LT);
       entity.put("quot", Xml.QUOT);
   }

    /**
     * Construct an XmlTokenizer from a string.
     * @param s A source string.
     */
    public XmlTokenizer(String s) {
        super(s);
    }

    /**
     * Get the text in the CDATA block.
     * @return The string up to the <code>]]&gt;</code>.
     * @throws JsonException If the <code>]]&gt;</code> is not found.
     */
    public String nextCDATA() throws JsonException {
        char         c;
        int          i;
        StringBuffer sb = new StringBuffer();
        for (;;) {
            c = next();
            if (end()) {
                throw syntaxError("Unclosed CDATA");
            }
            sb.append(c);
            i = sb.length() - 3;
            if (i >= 0 && sb.charAt(i) == ']' &&
                          sb.charAt(i + 1) == ']' && sb.charAt(i + 2) == '>') {
                sb.setLength(i);
                return sb.toString();
            }
        }
    }


    /**
     * Get the next Xml outer token, trimming whitespace. There are two kinds
     * of tokens: the '<' character which begins a markup tag, and the content
     * text between markup tags.
     *
     * @return  A string, or a '<' Character, or null if there is no more
     * source text.
     * @throws JsonException
     */
    public Object nextContent() throws JsonException {
        char         c;
        StringBuffer sb;
        do {
            c = next();
        } while (Character.isWhitespace(c));
        if (c == 0) {
            return null;
        }
        if (c == '<') {
            return Xml.LT;
        }
        sb = new StringBuffer();
        for (;;) {
            if (c == '<' || c == 0) {
                back();
                return sb.toString().trim();
            }
            if (c == '&') {
                sb.append(nextEntity(c));
            } else {
                sb.append(c);
            }
            c = next();
        }
    }


    /**
     * Return the next entity. These entities are translated to Characters:
     *     <code>&amp;  &apos;  &gt;  &lt;  &quot;</code>.
     * @param ampersand An ampersand character.
     * @return  A Character or an entity String if the entity is not recognized.
     * @throws JsonException If missing ';' in Xml entity.
     */
    public Object nextEntity(char ampersand) throws JsonException {
        StringBuffer sb = new StringBuffer();
        for (;;) {
            char c = next();
            if (Character.isLetterOrDigit(c) || c == '#') {
                sb.append(Character.toLowerCase(c));
            } else if (c == ';') {
                break;
            } else {
                throw syntaxError("Missing ';' in Xml entity: &" + sb);
            }
        }
        String string = sb.toString();
        Object object = entity.get(string);
        return object != null ? object : ampersand + string + ";";
    }


    /**
     * Returns the next Xml meta token. This is used for skipping over <!...>
     * and <?...?> structures.
     * @return Syntax characters (<code>< > / = ! ?</code>) are returned as
     *  Character, and strings and names are returned as Boolean. We don't care
     *  what the values actually are.
     * @throws JsonException If a string is not properly closed or if the Xml
     *  is badly structured.
     */
    public Object nextMeta() throws JsonException {
        char c;
        char q;
        do {
            c = next();
        } while (Character.isWhitespace(c));
        switch (c) {
        case 0:
            throw syntaxError("Misshaped meta tag");
        case '<':
            return Xml.LT;
        case '>':
            return Xml.GT;
        case '/':
            return Xml.SLASH;
        case '=':
            return Xml.EQ;
        case '!':
            return Xml.BANG;
        case '?':
            return Xml.QUEST;
        case '"':
        case '\'':
            q = c;
            for (;;) {
                c = next();
                if (c == 0) {
                    throw syntaxError("Unterminated string");
                }
                if (c == q) {
                    return Boolean.TRUE;
                }
            }
        default:
            for (;;) {
                c = next();
                if (Character.isWhitespace(c)) {
                    return Boolean.TRUE;
                }
                switch (c) {
                case 0:
                case '<':
                case '>':
                case '/':
                case '=':
                case '!':
                case '?':
                case '"':
                case '\'':
                    back();
                    return Boolean.TRUE;
                }
            }
        }
    }


    /**
     * Get the next Xml Token. These tokens are found inside of angle
     * brackets. It may be one of these characters: <code>/ > = ! ?</code> or it
     * may be a string wrapped in single quotes or double quotes, or it may be a
     * name.
     * @return a String or a Character.
     * @throws JsonException If the Xml is not well formed.
     */
    public Object nextToken() throws JsonException {
        char c;
        char q;
        StringBuffer sb;
        do {
            c = next();
        } while (Character.isWhitespace(c));
        switch (c) {
        case 0:
            throw syntaxError("Misshaped element");
        case '<':
            throw syntaxError("Misplaced '<'");
        case '>':
            return Xml.GT;
        case '/':
            return Xml.SLASH;
        case '=':
            return Xml.EQ;
        case '!':
            return Xml.BANG;
        case '?':
            return Xml.QUEST;

// Quoted string

        case '"':
        case '\'':
            q = c;
            sb = new StringBuffer();
            for (;;) {
                c = next();
                if (c == 0) {
                    throw syntaxError("Unterminated string");
                }
                if (c == q) {
                    return sb.toString();
                }
                if (c == '&') {
                    sb.append(nextEntity(c));
                } else {
                    sb.append(c);
                }
            }
        default:

// Name

            sb = new StringBuffer();
            for (;;) {
                sb.append(c);
                c = next();
                if (Character.isWhitespace(c)) {
                    return sb.toString();
                }
                switch (c) {
                case 0:
                	return sb.toString();
                case '>':
                case '/':
                case '=':
                case '!':
                case '?':
                case '[':
                case ']':
                    back();
                    return sb.toString();
                case '<':
                case '"':
                case '\'':
                    throw syntaxError("Bad character in a name");
                }
            }
        }
    }
    
    
    /**
     * Skip characters until past the requested string.
     * If it is not found, we are left at the end of the source with a result of false.
     * @param to A string to skip past.
     * @throws JsonException
     */
    public boolean skipPast(String to) throws JsonException {
    	boolean b;
    	char c;
    	int i;
    	int j;
    	int offset = 0;
    	int length = to.length();
        char[] circle = new char[length];
        
        /*
         * First fill the circle buffer with as many characters as are in the
         * to string. If we reach an early end, bail.
         */
        
    	for (i = 0; i < length; i += 1) {
    		c = next();
    		if (c == 0) {
    			return false;
    		}
    		circle[i] = c;
    	}
    	/*
    	 * We will loop, possibly for all of the remaining characters.
    	 */
    	for (;;) {
    		j = offset;
    		b = true;
    		/*
    		 * Compare the circle buffer with the to string. 
    		 */
    		for (i = 0; i < length; i += 1) {
    			if (circle[j] != to.charAt(i)) {
    				b = false;
    				break;
    			}
    			j += 1;
    			if (j >= length) {
    				j -= length;
    			}
    		}
    		/*
    		 * If we exit the loop with b intact, then victory is ours.
    		 */
    		if (b) {
    			return true;
    		}
    		/*
    		 * Get the next character. If there isn't one, then defeat is ours.
    		 */
    		c = next();
    		if (c == 0) {
    			return false;
    		}
    		/*
    		 * Shove the character in the circle buffer and advance the 
    		 * circle offset. The offset is mod n.
    		 */
    		circle[offset] = c;
    		offset += 1;
    		if (offset >= length) {
    			offset -= length;
    		}
    	}
    }
}
