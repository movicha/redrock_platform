package models;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import resources.Coding;
import resources.Element;
import resources.EnumFactory;
import resources.Extension;

import utilities.Base64Binary;
import utilities.Base64BinaryImpl;

public interface SecurityEvent extends Resource {

	public enum SecurityEventEventAction {
		C, // Create
		R, // Read/View/Print/Query
		U, // Update
		D, // Delete
		E, // Execute
		Null; // added to help the parsers
		public static SecurityEventEventAction fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("C".equals(codeString))
				return C;
			if ("R".equals(codeString))
				return R;
			if ("U".equals(codeString))
				return U;
			if ("D".equals(codeString))
				return D;
			if ("E".equals(codeString))
				return E;
			throw new Exception("Unknown SecurityEventEventAction code '"
					+ codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case C:
				return "C";
			case R:
				return "R";
			case U:
				return "U";
			case D:
				return "D";
			case E:
				return "E";
			default:
				return "?";
			}
		}
	}

	public class SecurityEventEventActionEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("C".equals(codeString))
				return SecurityEventEventAction.C;
			if ("R".equals(codeString))
				return SecurityEventEventAction.R;
			if ("U".equals(codeString))
				return SecurityEventEventAction.U;
			if ("D".equals(codeString))
				return SecurityEventEventAction.D;
			if ("E".equals(codeString))
				return SecurityEventEventAction.E;
			throw new Exception("Unknown SecurityEventEventAction code '"
					+ codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == SecurityEventEventAction.C)
				return "C";
			if (code == SecurityEventEventAction.R)
				return "R";
			if (code == SecurityEventEventAction.U)
				return "U";
			if (code == SecurityEventEventAction.D)
				return "D";
			if (code == SecurityEventEventAction.E)
				return "E";
			return "?";
		}
	}

	public enum SecurityEventEventOutcome {
		_0, // Success
		_4, // Minor failure
		_8, // Serious failure
		_12, // Major failure
		Null; // added to help the parsers
		public static SecurityEventEventOutcome fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("0".equals(codeString))
				return _0;
			if ("4".equals(codeString))
				return _4;
			if ("8".equals(codeString))
				return _8;
			if ("12".equals(codeString))
				return _12;
			throw new Exception("Unknown SecurityEventEventOutcome code '"
					+ codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case _0:
				return "0";
			case _4:
				return "4";
			case _8:
				return "8";
			case _12:
				return "12";
			default:
				return "?";
			}
		}
	}

	public class SecurityEventEventOutcomeEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("0".equals(codeString))
				return SecurityEventEventOutcome._0;
			if ("4".equals(codeString))
				return SecurityEventEventOutcome._4;
			if ("8".equals(codeString))
				return SecurityEventEventOutcome._8;
			if ("12".equals(codeString))
				return SecurityEventEventOutcome._12;
			throw new Exception("Unknown SecurityEventEventOutcome code '"
					+ codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == SecurityEventEventOutcome._0)
				return "0";
			if (code == SecurityEventEventOutcome._4)
				return "4";
			if (code == SecurityEventEventOutcome._8)
				return "8";
			if (code == SecurityEventEventOutcome._12)
				return "12";
			return "?";
		}
	}

	public enum NetworkType {
		name, // Machine Name, including DNS name
		ip, // IP Address
		phone, // Telephone Number
		Null; // added to help the parsers
		public static NetworkType fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("name".equals(codeString))
				return name;
			if ("ip".equals(codeString))
				return ip;
			if ("phone".equals(codeString))
				return phone;
			throw new Exception("Unknown NetworkType code '" + codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case name:
				return "name";
			case ip:
				return "ip";
			case phone:
				return "phone";
			default:
				return "?";
			}
		}
	}

	public class NetworkTypeEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("name".equals(codeString))
				return NetworkType.name;
			if ("ip".equals(codeString))
				return NetworkType.ip;
			if ("phone".equals(codeString))
				return NetworkType.phone;
			throw new Exception("Unknown NetworkType code '" + codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == NetworkType.name)
				return "name";
			if (code == NetworkType.ip)
				return "ip";
			if (code == NetworkType.phone)
				return "phone";
			return "?";
		}
	}

	public enum ObjectType {
		_1, // Person
		_2, // System Object
		_3, // Organization
		_4, // Other
		Null; // added to help the parsers
		public static ObjectType fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("1".equals(codeString))
				return _1;
			if ("2".equals(codeString))
				return _2;
			if ("3".equals(codeString))
				return _3;
			if ("4".equals(codeString))
				return _4;
			throw new Exception("Unknown ObjectType code '" + codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case _1:
				return "1";
			case _2:
				return "2";
			case _3:
				return "3";
			case _4:
				return "4";
			default:
				return "?";
			}
		}
	}

	public class ObjectTypeEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("1".equals(codeString))
				return ObjectType._1;
			if ("2".equals(codeString))
				return ObjectType._2;
			if ("3".equals(codeString))
				return ObjectType._3;
			if ("4".equals(codeString))
				return ObjectType._4;
			throw new Exception("Unknown ObjectType code '" + codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == ObjectType._1)
				return "1";
			if (code == ObjectType._2)
				return "2";
			if (code == ObjectType._3)
				return "3";
			if (code == ObjectType._4)
				return "4";
			return "?";
		}
	}

	public enum ObjectRole {
		_1, // Patient
		_2, // Location
		_3, // Report
		_4, // Resource
		_5, // Master file
		_6, // User
		_7, // List
		_8, // Doctor
		_9, // Subscriber
		_10, // Guarantor
		_11, // Security User Entity
		_12, // Security User Group
		_13, // Security Resource
		_14, // Security Granularity Definition
		_15, // Provider
		_16, // Data Destination
		_17, // Data Repository
		_18, // Schedule
		_19, // Customer
		_20, // Job
		_21, // Job Stream
		_22, // Table
		_23, // Routing Criteria
		_24, // Query
		Null; // added to help the parsers
		public static ObjectRole fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("1".equals(codeString))
				return _1;
			if ("2".equals(codeString))
				return _2;
			if ("3".equals(codeString))
				return _3;
			if ("4".equals(codeString))
				return _4;
			if ("5".equals(codeString))
				return _5;
			if ("6".equals(codeString))
				return _6;
			if ("7".equals(codeString))
				return _7;
			if ("8".equals(codeString))
				return _8;
			if ("9".equals(codeString))
				return _9;
			if ("10".equals(codeString))
				return _10;
			if ("11".equals(codeString))
				return _11;
			if ("12".equals(codeString))
				return _12;
			if ("13".equals(codeString))
				return _13;
			if ("14".equals(codeString))
				return _14;
			if ("15".equals(codeString))
				return _15;
			if ("16".equals(codeString))
				return _16;
			if ("17".equals(codeString))
				return _17;
			if ("18".equals(codeString))
				return _18;
			if ("19".equals(codeString))
				return _19;
			if ("20".equals(codeString))
				return _20;
			if ("21".equals(codeString))
				return _21;
			if ("22".equals(codeString))
				return _22;
			if ("23".equals(codeString))
				return _23;
			if ("24".equals(codeString))
				return _24;
			throw new Exception("Unknown ObjectRole code '" + codeString + "'");
		}

		public String toCode() {
			switch (this) {
			case _1:
				return "1";
			case _2:
				return "2";
			case _3:
				return "3";
			case _4:
				return "4";
			case _5:
				return "5";
			case _6:
				return "6";
			case _7:
				return "7";
			case _8:
				return "8";
			case _9:
				return "9";
			case _10:
				return "10";
			case _11:
				return "11";
			case _12:
				return "12";
			case _13:
				return "13";
			case _14:
				return "14";
			case _15:
				return "15";
			case _16:
				return "16";
			case _17:
				return "17";
			case _18:
				return "18";
			case _19:
				return "19";
			case _20:
				return "20";
			case _21:
				return "21";
			case _22:
				return "22";
			case _23:
				return "23";
			case _24:
				return "24";
			default:
				return "?";
			}
		}
	}

	public class ObjectRoleEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("1".equals(codeString))
				return ObjectRole._1;
			if ("2".equals(codeString))
				return ObjectRole._2;
			if ("3".equals(codeString))
				return ObjectRole._3;
			if ("4".equals(codeString))
				return ObjectRole._4;
			if ("5".equals(codeString))
				return ObjectRole._5;
			if ("6".equals(codeString))
				return ObjectRole._6;
			if ("7".equals(codeString))
				return ObjectRole._7;
			if ("8".equals(codeString))
				return ObjectRole._8;
			if ("9".equals(codeString))
				return ObjectRole._9;
			if ("10".equals(codeString))
				return ObjectRole._10;
			if ("11".equals(codeString))
				return ObjectRole._11;
			if ("12".equals(codeString))
				return ObjectRole._12;
			if ("13".equals(codeString))
				return ObjectRole._13;
			if ("14".equals(codeString))
				return ObjectRole._14;
			if ("15".equals(codeString))
				return ObjectRole._15;
			if ("16".equals(codeString))
				return ObjectRole._16;
			if ("17".equals(codeString))
				return ObjectRole._17;
			if ("18".equals(codeString))
				return ObjectRole._18;
			if ("19".equals(codeString))
				return ObjectRole._19;
			if ("20".equals(codeString))
				return ObjectRole._20;
			if ("21".equals(codeString))
				return ObjectRole._21;
			if ("22".equals(codeString))
				return ObjectRole._22;
			if ("23".equals(codeString))
				return ObjectRole._23;
			if ("24".equals(codeString))
				return ObjectRole._24;
			throw new Exception("Unknown ObjectRole code '" + codeString + "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == ObjectRole._1)
				return "1";
			if (code == ObjectRole._2)
				return "2";
			if (code == ObjectRole._3)
				return "3";
			if (code == ObjectRole._4)
				return "4";
			if (code == ObjectRole._5)
				return "5";
			if (code == ObjectRole._6)
				return "6";
			if (code == ObjectRole._7)
				return "7";
			if (code == ObjectRole._8)
				return "8";
			if (code == ObjectRole._9)
				return "9";
			if (code == ObjectRole._10)
				return "10";
			if (code == ObjectRole._11)
				return "11";
			if (code == ObjectRole._12)
				return "12";
			if (code == ObjectRole._13)
				return "13";
			if (code == ObjectRole._14)
				return "14";
			if (code == ObjectRole._15)
				return "15";
			if (code == ObjectRole._16)
				return "16";
			if (code == ObjectRole._17)
				return "17";
			if (code == ObjectRole._18)
				return "18";
			if (code == ObjectRole._19)
				return "19";
			if (code == ObjectRole._20)
				return "20";
			if (code == ObjectRole._21)
				return "21";
			if (code == ObjectRole._22)
				return "22";
			if (code == ObjectRole._23)
				return "23";
			if (code == ObjectRole._24)
				return "24";
			return "?";
		}
	}

	public enum ObjectLifecycle {
		_1, // Origination / Creation
		_2, // Import / Copy from original
		_3, // Amendment
		_4, // Verification
		_5, // Translation
		_6, // Access / Use
		_7, // De-identification
		_8, // Aggregation, summarization, derivation
		_9, // Report
		_10, // Export / Copy to target
		_11, // Disclosure
		_12, // Receipt of disclosure
		_13, // Archiving
		_14, // Logical deletion
		_15, // Permanent erasure / Physical destruction
		Null; // added to help the parsers
		public static ObjectLifecycle fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("1".equals(codeString))
				return _1;
			if ("2".equals(codeString))
				return _2;
			if ("3".equals(codeString))
				return _3;
			if ("4".equals(codeString))
				return _4;
			if ("5".equals(codeString))
				return _5;
			if ("6".equals(codeString))
				return _6;
			if ("7".equals(codeString))
				return _7;
			if ("8".equals(codeString))
				return _8;
			if ("9".equals(codeString))
				return _9;
			if ("10".equals(codeString))
				return _10;
			if ("11".equals(codeString))
				return _11;
			if ("12".equals(codeString))
				return _12;
			if ("13".equals(codeString))
				return _13;
			if ("14".equals(codeString))
				return _14;
			if ("15".equals(codeString))
				return _15;
			throw new Exception("Unknown ObjectLifecycle code '" + codeString
					+ "'");
		}

		public String toCode() {
			switch (this) {
			case _1:
				return "1";
			case _2:
				return "2";
			case _3:
				return "3";
			case _4:
				return "4";
			case _5:
				return "5";
			case _6:
				return "6";
			case _7:
				return "7";
			case _8:
				return "8";
			case _9:
				return "9";
			case _10:
				return "10";
			case _11:
				return "11";
			case _12:
				return "12";
			case _13:
				return "13";
			case _14:
				return "14";
			case _15:
				return "15";
			default:
				return "?";
			}
		}
	}

	public class ObjectLifecycleEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("1".equals(codeString))
				return ObjectLifecycle._1;
			if ("2".equals(codeString))
				return ObjectLifecycle._2;
			if ("3".equals(codeString))
				return ObjectLifecycle._3;
			if ("4".equals(codeString))
				return ObjectLifecycle._4;
			if ("5".equals(codeString))
				return ObjectLifecycle._5;
			if ("6".equals(codeString))
				return ObjectLifecycle._6;
			if ("7".equals(codeString))
				return ObjectLifecycle._7;
			if ("8".equals(codeString))
				return ObjectLifecycle._8;
			if ("9".equals(codeString))
				return ObjectLifecycle._9;
			if ("10".equals(codeString))
				return ObjectLifecycle._10;
			if ("11".equals(codeString))
				return ObjectLifecycle._11;
			if ("12".equals(codeString))
				return ObjectLifecycle._12;
			if ("13".equals(codeString))
				return ObjectLifecycle._13;
			if ("14".equals(codeString))
				return ObjectLifecycle._14;
			if ("15".equals(codeString))
				return ObjectLifecycle._15;
			throw new Exception("Unknown ObjectLifecycle code '" + codeString
					+ "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == ObjectLifecycle._1)
				return "1";
			if (code == ObjectLifecycle._2)
				return "2";
			if (code == ObjectLifecycle._3)
				return "3";
			if (code == ObjectLifecycle._4)
				return "4";
			if (code == ObjectLifecycle._5)
				return "5";
			if (code == ObjectLifecycle._6)
				return "6";
			if (code == ObjectLifecycle._7)
				return "7";
			if (code == ObjectLifecycle._8)
				return "8";
			if (code == ObjectLifecycle._9)
				return "9";
			if (code == ObjectLifecycle._10)
				return "10";
			if (code == ObjectLifecycle._11)
				return "11";
			if (code == ObjectLifecycle._12)
				return "12";
			if (code == ObjectLifecycle._13)
				return "13";
			if (code == ObjectLifecycle._14)
				return "14";
			if (code == ObjectLifecycle._15)
				return "15";
			return "?";
		}
	}

	public class Event implements Element {
		/**
		 * Identifier for a specific audited event
		 */
		private Coding id;

		/**
		 * Indicator for type of action performed during the event that
		 * generated the audit
		 */
		private SecurityEventEventAction action;

		/**
		 * The time when the event occurred on the source
		 */
		private Instant dateTime;

		/**
		 * Indicates whether the event succeeded or failed
		 */
		private SecurityEventEventOutcome outcome;

		/**
		 * Identifier for the category of event
		 */
		private List<Coding> code = new ArrayList<Coding>();

		public Coding getId() {
			return this.id;
		}

		public void setId(Coding value) {
			this.id = value;
		}

		public SecurityEventEventAction getAction() {
			return this.action;
		}

		public void setAction(SecurityEventEventAction value) {
			this.action = value;
		}

		public Instant getDateTime() {
			return this.dateTime;
		}

		public void setDateTime(Instant value) {
			this.dateTime = value;
		}

		public Calendar getDateTimeSimple() {
			return this.dateTime == null ? null : this.dateTime.getValue();
		}

		public void setDateTimeSimple(Calendar value) {
			if (value == null)
				this.dateTime = null;
			else {
				if (this.dateTime == null)
					this.dateTime = new InstantImpl();
				this.dateTime.setValue(value);
			}
		}

		public SecurityEventEventOutcome getOutcome() {
			return this.outcome;
		}

		public void setOutcome(SecurityEventEventOutcome value) {
			this.outcome = value;
		}

		public List<Coding> getCode() {
			return this.code;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
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

	}

	public class Participant implements Element {
		/**
		 * Unique identifier for the user actively participating in the event
		 */
		private String userId;

		/**
		 * User identifier text string from authentication system. This
		 * identifier would be one known to a common authentication system
		 * (e.g., single sign-on), if available
		 */
		private String otherUserId;

		/**
		 * Human-meaningful name for the user
		 */
		private String name;

		/**
		 * Indicator that the user is or is not the requestor, or initiator, for
		 * the event being audited.
		 */
		private Boolean requestor;

		/**
		 * Specification of the role(s) the user plays when performing the
		 * event, as assigned in role-based access control security
		 */
		private List<Coding> role = new ArrayList<Coding>();

		/**
		 * Logical network location for application activity
		 */
		private Network network;

		public String getUserId() {
			return this.userId;
		}

		public void setUserId(String value) {
			this.userId = value;
		}

		public String getUserIdSimple() {
			return this.userId == null ? null : this.userId;
		}

		public void setUserIdSimple(String value) {
			if (value == null)
				this.userId = null;
			else {
				if (this.userId == null)
					this.userId = new String();
				this.userId = value;
			}
		}

		public String getOtherUserId() {
			return this.otherUserId;
		}

		public void setOtherUserId(String value) {
			this.otherUserId = value;
		}

		public String getOtherUserIdSimple() {
			return this.otherUserId == null ? null : this.otherUserId
					;
		}

		public void setOtherUserIdSimple(String value) {
			if (value == null)
				this.otherUserId = null;
			else {
				if (this.otherUserId == null)
					this.otherUserId = new String();
				this.otherUserId = value;
			}
		}

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

		public Boolean getRequestor() {
			return this.requestor;
		}

		public void setRequestor(Boolean value) {
			this.requestor = value;
		}

		public boolean getRequestorSimple() {
			return this.requestor == null ? null : this.requestor;
		}

		public void setRequestorSimple(boolean value) {
			if (value == false)
				this.requestor = null;
			else {
				if (this.requestor == null)
					this.requestor = new Boolean(false);
				this.requestor = value;
			}
		}

		public List<Coding> getRole() {
			return this.role;
		}

		public Network getNetwork() {
			return this.network;
		}

		public void setNetwork(Network value) {
			this.network = value;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
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

	}

	public class Network implements Element {
		/**
		 * An identifier for the type of network access point that originated
		 * the audit event
		 */
		private NetworkType type;

		/**
		 * An identifier for the network access point of the user device for the
		 * audit event
		 */
		private String id;

		public NetworkType getType() {
			return this.type;
		}

		public void setType(NetworkType value) {
			this.type = value;
		}

		public String getId() {
			return this.id;
		}

		public void setId(String value) {
			this.id = value;
		}

		public String getIdSimple() {
			return this.id == null ? null : this.id;
		}

		public void setIdSimple(String value) {
			if (value == null)
				this.id = null;
			else {
				if (this.id == null)
					this.id = new String();
				this.id = value;
			}
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
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

	}

	public class Source implements Element {
		/**
		 * Logical source location within the healthcare enterprise network
		 */
		private String site;

		/**
		 * Identifier of the source where the event originated
		 */
		private String id;

		/**
		 * Code specifying the type of source where event originated
		 */
		private List<Coding> type = new ArrayList<Coding>();

		public String getSite() {
			return this.site;
		}

		public void setSite(String value) {
			this.site = value;
		}

		public String getSiteSimple() {
			return this.site == null ? null : this.site;
		}

		public void setSiteSimple(String value) {
			if (value == null)
				this.site = null;
			else {
				if (this.site == null)
					this.site = new String();
				this.site = value;
			}
		}

		public String getId() {
			return this.id;
		}

		public void setId(String value) {
			this.id = value;
		}

		public String getIdSimple() {
			return this.id == null ? null : this.id;
		}

		public void setIdSimple(String value) {
			if (value == null)
				this.id = null;
			else {
				if (this.id == null)
					this.id = new String();
				this.id = value;
			}
		}

		public List<Coding> getType() {
			return this.type;
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
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

	}

	public class Object implements Element {
		/**
		 * Object type being audited
		 */
		private ObjectType type;

		/**
		 * Code representing the functional application role of Participant
		 * Object being audited
		 */
		private ObjectRole role;

		/**
		 * Identifier for the data life-cycle stage for the participant object
		 */
		private ObjectLifecycle lifecycle;

		/**
		 * Describes the identifier that is contained in Participant Object ID
		 */
		private Coding idType;

		/**
		 * Identifies a specific instance of the participant object
		 */
		private String id;

		/**
		 * Denotes policy-defined sensitivity for the Participant Object ID such
		 * as VIP, HIV status, mental health status or similar topics
		 */
		private String sensitivity;

		/**
		 * An instance-specific descriptor of the Participant Object ID audited,
		 * such as a person's name
		 */
		private String name;

		/**
		 * The actual query for a query-type participant object
		 */
		private Base64Binary query;

		public ObjectType getType() {
			return this.type;
		}

		public void setType(ObjectType value) {
			this.type = value;
		}

		public ObjectRole getRole() {
			return this.role;
		}

		public void setRole(ObjectRole value) {
			this.role = value;
		}

		public ObjectLifecycle getLifecycle() {
			return this.lifecycle;
		}

		public void setLifecycle(ObjectLifecycle value) {
			this.lifecycle = value;
		}

		public Coding getIdType() {
			return this.idType;
		}

		public void setIdType(Coding value) {
			this.idType = value;
		}

		public String getId() {
			return this.id;
		}

		public void setId(String value) {
			this.id = value;
		}

		public String getIdSimple() {
			return this.id == null ? null : this.id;
		}

		public void setIdSimple(String value) {
			if (value == null)
				this.id = null;
			else {
				if (this.id == null)
					this.id = new String();
				this.id = value;
			}
		}

		public String getSensitivity() {
			return this.sensitivity;
		}

		public void setSensitivity(String value) {
			this.sensitivity = value;
		}

		public String getSensitivitySimple() {
			return this.sensitivity == null ? null : this.sensitivity;
		}

		public void setSensitivitySimple(String value) {
			if (value == null)
				this.sensitivity = null;
			else {
				if (this.sensitivity == null)
					this.sensitivity = new String();
				this.sensitivity = value;
			}
		}

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

		public Base64Binary getQuery() {
			return this.query;
		}

		public void setQuery(Base64Binary value) {
			this.query = value;
		}

		public byte[] getQuerySimple() {
			return this.query == null ? null : this.query.getValue();
		}

		public void setQuerySimple(byte[] value) {
			if (value == null)
				this.query = null;
			else {
				if (this.query == null)
					this.query = new Base64BinaryImpl();
				this.query.setValue(value);
			}
		}

		@Override
		public Iterator iterator() {
			// TODO Auto-generated method stub
			return null;
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
	}

	public Event getEvent();

	public void setEvent(Event value);

	public List<Participant> getParticipant();

	public List<Source> getSource();

	public List<Object> getObject();
}