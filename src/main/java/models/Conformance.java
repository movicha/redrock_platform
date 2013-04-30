package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resources.Code;
import resources.CodeImpl;
import resources.Coding;
import resources.DateTime;
import resources.Element;
import resources.EnumFactory;
import resources.Extension;

import utilities.Uri;
import utilities.UriImpl;

public interface Conformance extends Resource {
	public enum RestfulConformanceMode {
		client, // The application acts as a server for this resource
		server, // The application acts as a client for this resource
		Null; // added to help the parsers
		public static RestfulConformanceMode fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("client".equals(codeString))
				return client;
			if ("server".equals(codeString))
				return server;
			throw new Exception("Unknown RestfulConformanceMode code '"
					+ codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case client:
				return "client";
			case server:
				return "server";
			default:
				return "?";
			}
		}
	}

	public class RestfulConformanceModeEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("client".equals(codeString))
				return RestfulConformanceMode.client;
			if ("server".equals(codeString))
				return RestfulConformanceMode.server;
			throw new Exception("Unknown RestfulConformanceMode code '"
					+ codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == RestfulConformanceMode.client)
				return "client";
			if (code == RestfulConformanceMode.server)
				return "server";
			return "?";
		}
	}

	public enum RestfulOperation {
		read, // Read the current state of the resource
		vread, // Read the state of a specific version of the resource
		update, // Update an existing resource by its id (or create it if it is
				// new)
		delete, // Delete a resource
		history, // Retrieve the update history for the resource
		validate, // Check that the content would be acceptable as an update
		updates, // Get a list of prior updates to resources of this type,
					// optionally with some filter criteria
		create, // Create a new resource with a server assigned id
		search, // Supports search operations using the parameters described in
				// the profile
		Null; // added to help the parsers
		public static RestfulOperation fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("read".equals(codeString))
				return read;
			if ("vread".equals(codeString))
				return vread;
			if ("update".equals(codeString))
				return update;
			if ("delete".equals(codeString))
				return delete;
			if ("history".equals(codeString))
				return history;
			if ("validate".equals(codeString))
				return validate;
			if ("updates".equals(codeString))
				return updates;
			if ("create".equals(codeString))
				return create;
			if ("search".equals(codeString))
				return search;
			throw new Exception("Unknown RestfulOperation code '" + codeString
					+ "'");
		}

		public String toCode() {
			switch (this) {
			case read:
				return "read";
			case vread:
				return "vread";
			case update:
				return "update";
			case delete:
				return "delete";
			case history:
				return "history";
			case validate:
				return "validate";
			case updates:
				return "updates";
			case create:
				return "create";
			case search:
				return "search";
			default:
				return "?";
			}
		}
	}

	public class RestfulOperationEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("read".equals(codeString))
				return RestfulOperation.read;
			if ("vread".equals(codeString))
				return RestfulOperation.vread;
			if ("update".equals(codeString))
				return RestfulOperation.update;
			if ("delete".equals(codeString))
				return RestfulOperation.delete;
			if ("history".equals(codeString))
				return RestfulOperation.history;
			if ("validate".equals(codeString))
				return RestfulOperation.validate;
			if ("updates".equals(codeString))
				return RestfulOperation.updates;
			if ("create".equals(codeString))
				return RestfulOperation.create;
			if ("search".equals(codeString))
				return RestfulOperation.search;
			throw new Exception("Unknown RestfulOperation code '" + codeString
					+ "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == RestfulOperation.read)
				return "read";
			if (code == RestfulOperation.vread)
				return "vread";
			if (code == RestfulOperation.update)
				return "update";
			if (code == RestfulOperation.delete)
				return "delete";
			if (code == RestfulOperation.history)
				return "history";
			if (code == RestfulOperation.validate)
				return "validate";
			if (code == RestfulOperation.updates)
				return "updates";
			if (code == RestfulOperation.create)
				return "create";
			if (code == RestfulOperation.search)
				return "search";
			return "?";
		}
	}

	public enum SearchParamType {
		integer, // Search parameter must be a simple whole number
		string, // Search parameter is a simple string, like a name part (search
				// usually functions on partial matches)
		text, // Search parameter is on a long string (i.e. a text filter type
				// search)
		date, // Search parameter is on a date (and should support -before and
				// -after variants). The date format is the standard XML format,
				// though other formats may be supported
		token, // Search parameter is on a fixed value string (i.e. search has
				// an exact match)
		qtoken, // Search parameter is a pair of fixed value strings, namespace
				// and value, separated by a "#". The namespace is usually a
				// uri, such as one of the defined code systems and is optional
				// when searching
		Null; // added to help the parsers
		public static SearchParamType fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("integer".equals(codeString))
				return integer;
			if ("string".equals(codeString))
				return string;
			if ("text".equals(codeString))
				return text;
			if ("date".equals(codeString))
				return date;
			if ("token".equals(codeString))
				return token;
			if ("qtoken".equals(codeString))
				return qtoken;
			throw new Exception("Unknown SearchParamType code '" + codeString
					+ "'");
		}

		public String toCode() {
			switch (this) {
			case integer:
				return "integer";
			case string:
				return "string";
			case text:
				return "text";
			case date:
				return "date";
			case token:
				return "token";
			case qtoken:
				return "qtoken";
			default:
				return "?";
			}
		}
	}

	public class SearchParamTypeEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("integer".equals(codeString))
				return SearchParamType.integer;
			if ("string".equals(codeString))
				return SearchParamType.string;
			if ("text".equals(codeString))
				return SearchParamType.text;
			if ("date".equals(codeString))
				return SearchParamType.date;
			if ("token".equals(codeString))
				return SearchParamType.token;
			if ("qtoken".equals(codeString))
				return SearchParamType.qtoken;
			throw new Exception("Unknown SearchParamType code '" + codeString
					+ "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == SearchParamType.integer)
				return "integer";
			if (code == SearchParamType.string)
				return "string";
			if (code == SearchParamType.text)
				return "text";
			if (code == SearchParamType.date)
				return "date";
			if (code == SearchParamType.token)
				return "token";
			if (code == SearchParamType.qtoken)
				return "qtoken";
			return "?";
		}
	}

	public enum SearchRepeatBehavior {
		single, // The search parameter may only occur once
		union, // When the search parameter occurs more than once, match
				// resources with any of the values
		intersection, // When the search parameter occurs more than once, match
						// resources with all of the values
		Null; // added to help the parsers
		public static SearchRepeatBehavior fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("single".equals(codeString))
				return single;
			if ("union".equals(codeString))
				return union;
			if ("intersection".equals(codeString))
				return intersection;
			throw new Exception("Unknown SearchRepeatBehavior code '"
					+ codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case single:
				return "single";
			case union:
				return "union";
			case intersection:
				return "intersection";
			default:
				return "?";
			}
		}
	}

	public class SearchRepeatBehaviorEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("single".equals(codeString))
				return SearchRepeatBehavior.single;
			if ("union".equals(codeString))
				return SearchRepeatBehavior.union;
			if ("intersection".equals(codeString))
				return SearchRepeatBehavior.intersection;
			throw new Exception("Unknown SearchRepeatBehavior code '"
					+ codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == SearchRepeatBehavior.single)
				return "single";
			if (code == SearchRepeatBehavior.union)
				return "union";
			if (code == SearchRepeatBehavior.intersection)
				return "intersection";
			return "?";
		}
	}

	public enum MessageConformanceEventMode {
		sender, // The application sends requests and receives responses
		receiver, // The application receives requests and sends responses
		Null; // added to help the parsers
		public static MessageConformanceEventMode fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("sender".equals(codeString))
				return sender;
			if ("receiver".equals(codeString))
				return receiver;
			throw new Exception("Unknown MessageConformanceEventMode code '"
					+ codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case sender:
				return "sender";
			case receiver:
				return "receiver";
			default:
				return "?";
			}
		}
	}

	public class MessageConformanceEventModeEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("sender".equals(codeString))
				return MessageConformanceEventMode.sender;
			if ("receiver".equals(codeString))
				return MessageConformanceEventMode.receiver;
			throw new Exception("Unknown MessageConformanceEventMode code '"
					+ codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == MessageConformanceEventMode.sender)
				return "sender";
			if (code == MessageConformanceEventMode.receiver)
				return "receiver";
			return "?";
		}
	}

	public enum DocumentMode {
		producer, // The application produces documents of the specified type
		consumer, // The application consumes documents of the specified type
		Null; // added to help the parsers
		public static DocumentMode fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("producer".equals(codeString))
				return producer;
			if ("consumer".equals(codeString))
				return consumer;
			throw new Exception("Unknown DocumentMode code '" + codeString
					+ "'");
		}

		public String toCode() {
			switch (this) {
			case producer:
				return "producer";
			case consumer:
				return "consumer";
			default:
				return "?";
			}
		}
	}

	public class DocumentModeEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("producer".equals(codeString))
				return DocumentMode.producer;
			if ("consumer".equals(codeString))
				return DocumentMode.consumer;
			throw new Exception("Unknown DocumentMode code '" + codeString
					+ "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == DocumentMode.producer)
				return "producer";
			if (code == DocumentMode.consumer)
				return "consumer";
			return "?";
		}
	}

	public class Publisher implements Element {
		/**
		 * Name of Organization
		 */
		private String name;

		/**
		 * Address of Organization
		 */
		private Address address;

		/**
		 * Contacts for Organization relevant to this conformance statement. May
		 * be website, email, phone numbers, etc.
		 */
		private List<Contact> contact = new ArrayList<Contact>();

		public String getName() {
			return this.name;
		}

		public void setName(String value) {
			this.name = value;
		}

		public String getNameSimple() {
			return this.name == null ? null : this.name;
		}

		public void setNameSimple(String value) {
			if (value == null)
				this.name = null;
			else {
				if (this.name == null)
					this.name = new String();
				this.name = value;
			}
		}

		public Address getAddress() {
			return this.address;
		}

		public void setAddress(Address value) {
			this.address = value;
		}

		public List<Contact> getContact() {
			return this.contact;
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Implementation implements Element {
		/**
		 * Information about the specific implementation
		 */
		private String description;

		/**
		 * The base URL for the implementation. This forms the base for REST
		 * interfaces as well as the mailbox and document interfaces.
		 */
		private Uri url;

		public String getDescription() {
			return this.description;
		}

		public void setDescription(String value) {
			this.description = value;
		}

		public String getDescriptionSimple() {
			return this.description == null ? null : this.description;
		}

		public void setDescriptionSimple(String value) {
			if (value == null)
				this.description = null;
			else {
				if (this.description == null)
					this.description = new String();
				this.description = value;
			}
		}

		public Uri getUrl() {
			return this.url;
		}

		public void setUrl(Uri value) {
			this.url = value;
		}

		public Uri getUrlSimple() {
			return this.url == null ? null : this.url;
		}

		public void setUrlSimple(Uri value) {
			if (value == null)
				this.url = null;
			else {
				if (this.url == null)
					this.url = new UriImpl();
				this.url = value;
			}
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Software implements Element {
		/**
		 * Name software is known by
		 */
		private String name;

		/**
		 * Version covered by this statement
		 */
		private String version;

		/**
		 * Date this version of the software released
		 */
		private DateTime releaseDate;

		public String getName() {
			return this.name;
		}

		public void setName(String value) {
			this.name = value;
		}

		public String getNameSimple() {
			return this.name == null ? null : this.name;
		}

		public void setNameSimple(String value) {
			if (value == null)
				this.name = null;
			else {
				if (this.name == null)
					this.name = new String();
				this.name = value;
			}
		}

		public String getVersion() {
			return this.version;
		}

		public void setVersion(String value) {
			this.version = value;
		}

		public String getVersionSimple() {
			return this.version == null ? null : this.version;
		}

		public void setVersionSimple(String value) {
			if (value == null)
				this.version = null;
			else {
				if (this.version == null)
					this.version = new String();
				this.version = value;
			}
		}

		public DateTime getReleaseDate() {
			return this.releaseDate;
		}

		public void setReleaseDate(DateTime value) {
			this.releaseDate = value;
		}

		public String getReleaseDateSimple() {
			return this.releaseDate == null ? null : this.releaseDate
					.getValue();
		}

		public void setReleaseDateSimple(String value) {
			if (value == null)
				this.releaseDate = null;
			else {
				if (this.releaseDate == null)
					this.releaseDate = new DateTime();
				this.releaseDate.setValue(value);
			}
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Proposal implements Element {
		/**
		 * Provides details about the intention and scope of the proposal
		 */
		private String description;

		public String getDescription() {
			return this.description;
		}

		public void setDescription(String value) {
			this.description = value;
		}

		public String getDescriptionSimple() {
			return this.description == null ? null : this.description;
		}

		public void setDescriptionSimple(String value) {
			if (value == null)
				this.description = null;
			else {
				if (this.description == null)
					this.description = new String();
				this.description = value;
			}
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Rest implements Element {
		/**
		 * Identifies whether this portion of the statement is describing
		 * ability to initiate or receive restful operations
		 */
		private Enum<RestfulConformanceMode> mode;

		/**
		 * Provides documentation about the system's restful capabilities that
		 * apply across all applications, such as security
		 */
		private String documentation;

		/**
		 * Identifies the restful capabilities of the solution for a specific
		 * resource type
		 */
		private List<Resource> resource = new ArrayList<Resource>();

		public Enum<RestfulConformanceMode> getMode() {
			return this.mode;
		}

		public void setMode(Enum<RestfulConformanceMode> value) {
			this.mode = value;
		}

		public RestfulConformanceMode getModeSimple() {
			return this.mode == null ? null : this.mode.valueOf(RestfulConformanceMode.class, this.mode.name());
		}

		public void setModeSimple(RestfulConformanceMode value) {
			if (value == null)
				this.mode = null;
			else
				this.mode = value;
		}

		public String getDocumentation() {
			return this.documentation;
		}

		public void setDocumentation(String value) {
			this.documentation = value;
		}

		public String getDocumentationSimple() {
			return this.documentation == null ? null : this.documentation;
		}

		public void setDocumentationSimple(String value) {
			if (value == null)
				this.documentation = null;
			else {
				if (this.documentation == null)
					this.documentation = new String();
				this.documentation = value;
			}
		}

		public List<Resource> getResource() {
			return this.resource;
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Resource implements Element {
		/**
		 * Identifies the resource exposed via the restful interface
		 */
		private Code type;

		/**
		 * Identifies the profile that describes the solution's support for the
		 * resource, including any constraints on cardinality, bindings, lengths
		 * or other limitations, along with the search parameters supported
		 */
		private Uri profile;

		/**
		 * Identifies a restful operation supported by the solution
		 */
		private List<Operation> operation = new ArrayList<Operation>();

		/**
		 * A flag for whether the server is able to return past versions as part
		 * of the vRead operation
		 */
		private Boolean history;

		/**
		 * Defines additional search parameters for implementations to support
		 * and/or make use of
		 */
		private List<SearchParam> searchParam = new ArrayList<SearchParam>();

		public Code getType() {
			return this.type;
		}

		public void setType(Code value) {
			this.type = value;
		}

		public String getTypeSimple() {
			return this.type == null ? null : this.type.getCodeText();
		}

		public void setTypeSimple(String value) {
			if (value == null)
				this.type = null;
			else {
				if (this.type == null)
					this.type = new CodeImpl();
				this.type.setCodeText(value);
			}
		}

		public Uri getProfile() {
			return this.profile;
		}

		public void setProfile(Uri value) {
			this.profile = value;
		}

		public Uri getProfileSimple() {
			return this.profile == null ? null : this.profile;
		}

		public void setProfileSimple(Uri value) {
			if (value == null)
				this.profile = null;
			else {
				if (this.profile == null)
					this.profile = new UriImpl();
				this.profile = value;
			}
		}

		public List<Operation> getOperation() {
			return this.operation;
		}

		public Boolean getHistory() {
			return this.history;
		}

		public void setHistory(Boolean value) {
			this.history = value;
		}

		public boolean getHistorySimple() {
			return this.history == null ? null : this.history;
		}

		public void setHistorySimple(boolean value) {
			if (value == false)
				this.history = null;
			else {
				if (this.history == null)
					this.history = new Boolean(value);
				this.history = value;
			}
		}

		public List<SearchParam> getSearchParam() {
			return this.searchParam;
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Operation implements Element {
		/**
		 * Identifies which operation is supported
		 */
		private Enum<RestfulOperation> code;

		/**
		 * Provides guidance specific to the implementation of this operation,
		 * such as 'delete is a logical delete' or 'updates are only allowed
		 * with version id' or 'creates permitted from pre-authorized
		 * certificates only'
		 */
		private String documentation;

		public Enum<RestfulOperation> getCode() {
			return this.code;
		}

		public void setCode(Enum<RestfulOperation> value) {
			this.code = value;
		}

		public RestfulOperation getCodeSimple() {
			return this.code == null ? null : this.code.valueOf(RestfulOperation.class, this.code.name());
		}

		public void setCodeSimple(RestfulOperation value) {
			if (value == null)
				this.code = null;
			else
				this.code = value;
		}

		public String getDocumentation() {
			return this.documentation;
		}

		public void setDocumentation(String value) {
			this.documentation = value;
		}

		public String getDocumentationSimple() {
			return this.documentation == null ? null : this.documentation;
		}

		public void setDocumentationSimple(String value) {
			if (value == null)
				this.documentation = null;
			else {
				if (this.documentation == null)
					this.documentation = new String();
				this.documentation = value;
			}
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class SearchParam implements Element {
		/**
		 * Corresponds to the name of the standard or custom search parameter
		 */
		private String name;

		/**
		 * A formal reference to where this parameter was first defined, so that
		 * a client can be confident of the meaning of the search parameter
		 */
		private Uri source;

		/**
		 * The type of value a search parameter refers to, and how the content
		 * is interpreted
		 */
		private Enum<SearchParamType> type;

		/**
		 * Whether multiple uses of the parameter are allowed in searches, and
		 * if they are, how the multiple values are understood
		 */
		private Enum<SearchRepeatBehavior> repeats;

		/**
		 * For standard parameters, provides additional information on how the
		 * parameter is used in this solution. For custom parameters, provides a
		 * description of what the parameter does
		 */
		private String documentation;

		public String getName() {
			return this.name;
		}

		public void setName(String value) {
			this.name = value;
		}

		public String getNameSimple() {
			return this.name == null ? null : this.name;
		}

		public void setNameSimple(String value) {
			if (value == null)
				this.name = null;
			else {
				if (this.name == null)
					this.name = new String();
				this.name = value;
			}
		}

		public Uri getSource() {
			return this.source;
		}

		public void setSource(Uri value) {
			this.source = value;
		}

		public Uri getSourceSimple() {
			return this.source == null ? null : this.source;
		}

		public void setSourceSimple(Uri value) {
			if (value == null)
				this.source = null;
			else {
				if (this.source == null)
					this.source = new UriImpl();
				this.source = value;
			}
		}

		public Enum<SearchParamType> getType() {
			return this.type;
		}

		public void setType(Enum<SearchParamType> value) {
			this.type = value;
		}

		public SearchParamType getTypeSimple() {
			return this.type == null ? null : this.type.valueOf(SearchParamType.class, this.type.name());
		}

		public void setTypeSimple(SearchParamType value) {
			if (value == null)
				this.type = null;
			else
				this.type = value;
		}

		public Enum<SearchRepeatBehavior> getRepeats() {
			return this.repeats;
		}

		public void setRepeats(Enum<SearchRepeatBehavior> value) {
			this.repeats = value;
		}

		public SearchRepeatBehavior getRepeatsSimple() {
			return this.repeats == null ? null : this.repeats.valueOf(SearchRepeatBehavior.class, this.repeats.toString());
		}

		public void setRepeatsSimple(SearchRepeatBehavior value) {
			if (value == null)
				this.repeats = null;
			else
				this.repeats = value;
		}

		public String getDocumentation() {
			return this.documentation;
		}

		public void setDocumentation(String value) {
			this.documentation = value;
		}

		public String getDocumentationSimple() {
			return this.documentation == null ? null : this.documentation;
		}

		public void setDocumentationSimple(String value) {
			if (value == null)
				this.documentation = null;
			else {
				if (this.documentation == null)
					this.documentation = new String();
				this.documentation = value;
			}
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Messaging implements Element {
		/**
		 * The address to which messages and/or replies are to be sent.
		 */
		private Uri endpoint;

		/**
		 * Provides documentation about the system's messaging capabilities for
		 * this endpoint not otherwise documented by the conformance statement.
		 * For example, process for becoming an authorized messaging exchange
		 * partner.
		 */
		private String documentation;

		/**
		 * Describes the solution's support for an event at this end point.
		 */
		private List<Event> event = new ArrayList<Event>();

		public Uri getEndpoint() {
			return this.endpoint;
		}

		public void setEndpoint(Uri value) {
			this.endpoint = value;
		}

		public Uri getEndpointSimple() {
			return this.endpoint == null ? null : this.endpoint;
		}

		public void setEndpointSimple(Uri value) {
			if (value == null)
				this.endpoint = null;
			else {
				if (this.endpoint == null)
					this.endpoint = new UriImpl();
				this.endpoint = value;
			}
		}

		public String getDocumentation() {
			return this.documentation;
		}

		public void setDocumentation(String value) {
			this.documentation = value;
		}

		public String getDocumentationSimple() {
			return this.documentation == null ? null : this.documentation;
		}

		public void setDocumentationSimple(String value) {
			if (value == null)
				this.documentation = null;
			else {
				if (this.documentation == null)
					this.documentation = new String();
				this.documentation = value;
			}
		}

		public List<Event> getEvent() {
			return this.event;
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Event implements Element {
		/**
		 * Identifies the supported messaging event
		 */
		private Code code;

		/**
		 * The mode of this event declaration - whether application is sender or
		 * receiver
		 */
		private Enum<MessageConformanceEventMode> mode;

		/**
		 * Identifies the messaging transport protocol(s) supported by this
		 * endpoint
		 */
		private List<Coding> protocol = new ArrayList<Coding>();

		/**
		 * Identifies the resource associated with the event. This is the
		 * resource that defines the event.
		 */
		private Code focus;

		/**
		 * Information about the request for this event
		 */
		private Uri request;

		/**
		 * Information about the response for this event
		 */
		private Uri response;

		/**
		 * Guidance on how this event is handled, such as internal system
		 * trigger points, business rules, etc.
		 */
		private String documentation;

		public Code getCode() {
			return this.code;
		}

		public void setCode(Code value) {
			this.code = value;
		}

		public String getCodeSimple() {
			return this.code == null ? null : this.code.getCodeText();
		}

		public void setCodeSimple(String value) {
			if (value == null)
				this.code = null;
			else {
				if (this.code == null)
					this.code = new CodeImpl();
				this.code.setCodeText(value);
			}
		}

		public Enum<MessageConformanceEventMode> getMode() {
			return this.mode;
		}

		public void setMode(Enum<MessageConformanceEventMode> value) {
			this.mode = value;
		}

		public MessageConformanceEventMode getModeSimple() {
			return this.mode == null ? null : this.mode.valueOf(MessageConformanceEventMode.class, this.mode.name());
		}

		public void setModeSimple(MessageConformanceEventMode value) {
			if (value == null)
				this.mode = null;
			else
				this.mode = value;
		}

		public List<Coding> getProtocol() {
			return this.protocol;
		}

		public Code getFocus() {
			return this.focus;
		}

		public void setFocus(Code value) {
			this.focus = value;
		}

		public String getFocusSimple() {
			return this.focus == null ? null : this.focus.getCodeText();
		}

		public void setFocusSimple(String value) {
			if (value == null)
				this.focus = null;
			else {
				if (this.focus == null)
					this.focus = new CodeImpl();
				this.focus.setCodeText(value);
			}
		}

		public Uri getRequest() {
			return this.request;
		}

		public void setRequest(Uri value) {
			this.request = value;
		}

		public Uri getRequestSimple() {
			return this.request == null ? null : this.request;
		}

		public void setRequestSimple(Uri value) {
			if (value == null)
				this.request = null;
			else {
				if (this.request == null)
					this.request = new UriImpl();
				this.request = value;
			}
		}

		public Uri getResponse() {
			return this.response;
		}

		public void setResponse(Uri value) {
			this.response = value;
		}

		public Uri getResponseSimple() {
			return this.response == null ? null : this.response;
		}

		public void setResponseSimple(Uri value) {
			if (value == null)
				this.response = null;
			else {
				if (this.response == null)
					this.response = new UriImpl();
				this.response = value;
			}
		}

		public String getDocumentation() {
			return this.documentation;
		}

		public void setDocumentation(String value) {
			this.documentation = value;
		}

		public String getDocumentationSimple() {
			return this.documentation == null ? null : this.documentation;
		}

		public void setDocumentationSimple(String value) {
			if (value == null)
				this.documentation = null;
			else {
				if (this.documentation == null)
					this.documentation = new String();
				this.documentation = value;
			}
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Document implements Element {
		/**
		 * The mode of this event declaration - whether application is sender or
		 * receiver
		 */
		private Enum<DocumentMode> mode;

		/**
		 * Describes how the application supports or uses the specified document
		 * profile. For example, when are documents created, what action is
		 * taken with consumed documents, etc.
		 */
		private String documentation;

		/**
		 * Constraint on a resource used in the document
		 */
		private Uri profile;

		public Enum<DocumentMode> getMode() {
			return this.mode;
		}

		public void setMode(Enum<DocumentMode> value) {
			this.mode = value;
		}

		public DocumentMode getModeSimple() {
			return this.mode == null ? null : this.mode.valueOf(DocumentMode.class, mode.name());
		}

		public void setModeSimple(DocumentMode value) {
			if (value == null)
				this.mode = null;
			else
				this.mode = value;
		}

		public String getDocumentation() {
			return this.documentation;
		}

		public void setDocumentation(String value) {
			this.documentation = value;
		}

		public String getDocumentationSimple() {
			return this.documentation == null ? null : this.documentation;
		}

		public void setDocumentationSimple(String value) {
			if (value == null)
				this.documentation = null;
			else {
				if (this.documentation == null)
					this.documentation = new String();
				this.documentation = value;
			}
		}

		public Uri getProfile() {
			return this.profile;
		}

		public void setProfile(Uri value) {
			this.profile = value;
		}

		public Uri getProfileSimple() {
			return this.profile == null ? null : this.profile;
		}

		public void setProfileSimple(Uri value) {
			if (value == null)
				this.profile = null;
			else {
				if (this.profile == null)
					this.profile = new UriImpl();
				this.profile = value;
			}
		}

		@Override
		public String getXmlId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void setXmlId(String xmlId) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Extension> getExtensions() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasExtensions() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public DateTime getDate();

	public void setDate(DateTime value);

	public String getDateSimple();

	public void setDateSimple(String value);

	public Publisher getPublisher();

	public void setPublisher(Publisher value);

	public Implementation getImplementation();

	public void setImplementation(Implementation value);

	public Software getSoftware();

	public void setSoftware(Software value);

	public Proposal getProposal();

	public void setProposal(Proposal value);

	public Id getVersion();

	public void setVersion(Id value);

	public String getVersionSimple();

	public void setVersionSimple(String value);

	public Boolean getAcceptUnknown();

	public void setAcceptUnknown(Boolean value);

	public boolean getAcceptUnknownSimple();

	public void setAcceptUnknownSimple(boolean value);

	public List<Rest> getRest();

	public List<Messaging> getMessaging();

	public List<Document> getDocument();
}
