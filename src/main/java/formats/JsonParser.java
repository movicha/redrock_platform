package formats;

import java.math.BigDecimal;

import models.Address;
import models.Address.AddressUseEnumFactory;
import models.AddressImpl;
import models.CarePlan;
import models.CarePlan.Activity;
import models.CarePlan.Participant;
import models.CarePlan.CarePlanActivityCategoryEnumFactory;
import models.CarePlan.CarePlanStatusEnumFactory;
import models.CarePlanImpl;
import models.Conformance;
import models.ConformanceImpl;
import models.Contact;
import models.Contact.ContactSystemEnumFactory;
import models.Contact.ContactUseEnumFactory;
import models.ContactImpl;
import models.Demographics;
import models.Demographics.Language;
import models.DemographicsImpl;
import models.Device;
import models.Device.Identity;
import models.DeviceImpl;
import models.Id;
import models.IdImpl;
import models.Instant;
import models.InstantImpl;
import models.Narrative;
import models.Narrative.NarrativeStatusEnumFactory;
import models.NarrativeImpl;
import models.Patient;
import models.PatientImpl;
import models.Period;
import models.PeriodImpl;
import models.Provider;
import models.ProviderImpl;
import models.Resource;
import models.Schedule;
import models.Schedule.EventTimingEnumFactory;
import models.Schedule.Repeat;
import models.Schedule.UnitsOfTimeEnumFactory;
import models.ScheduleImpl;
import resources.Code;
import resources.CodeImpl;
import resources.CodeableConcept;
import resources.CodeableConceptImpl;
import resources.Coding;
import resources.CodingImpl;
import resources.DateTime;
import resources.Element;
import resources.EnumFactory;
import resources.Extension;
import resources.ExtensionImpl;
import resources.HumanName;
import resources.HumanName.NameUseEnumFactory;
import resources.HumanNameImpl;
import resources.Identifier;
import resources.Identifier.IdentifierUseEnumFactory;
import resources.IdentifierImpl;
import resources.Quantity;
import resources.Quantity.QuantityComparatorEnumFactory;
import resources.QuantityImpl;
import resources.ResourceReference;
import resources.ResourceReferenceImpl;
import resources.Type;
import utilities.Base64Binary;
import utilities.Base64BinaryImpl;
import utilities.Uri;
import utilities.UriImpl;
import utilities.json.JsonObject;
import utilities.json.JsonArray;

public class JsonParser extends JsonParserBase {

	protected void parseElementProperties(JsonObject json, Element element)
			throws Exception {
		super.parseElementProperties(json, element);
		if (json.has("extension")) {
			JsonArray array = json.getJsonArray("extension");
			for (int i = 0; i < array.length(); i++) {
				element.getExtensions().add(
						parseExtension(array.getJsonObject(i)));
			}
		}
		;
	}

	protected void parseTypeProperties(JsonObject json, Element element)
			throws Exception {
		parseElementProperties(json, element);
	}

	@SuppressWarnings("unchecked")
	private <E extends Enum<E>> Enum<E> parseEnumeration(
			JsonObject json, E item, EnumFactory e) throws Exception {
		Enum<E> res = null;
		// parseElementProperties(json, res);
		if (json.has("value"))
			res = (E) e.fromCode(json.getString("value"));
		return res;
	}

	private Integer parseInteger(JsonObject json) throws Exception {
		Integer res = new Integer(0);
		// parseElementProperties(json, res);
		if (json.has("value"))
			res = parseIntegerPrimitive(json.getString("value"));
		return res;
	}

	private DateTime parseDateTime(JsonObject json) throws Exception {
		DateTime res = new DateTime();
		parseElementProperties(json, res);
		if (json.has("value"))
			res.setValue(parseDateTimePrimitive(json.getString("value")));
		return res;
	}

	private Code parseCode(JsonObject json) throws Exception {
		Code res = new CodeImpl();
		parseElementProperties(json, res);
		if (json.has("value"))
			res.setCodeText(parseCodePrimitive(json.getString("value")));
		return res;
	}

	private DateTime parseDate(JsonObject json) throws Exception {
		DateTime res = new DateTime();
		parseElementProperties(json, res);
		if (json.has("value"))
			res.setValue(json.getString("value"));
		return res;
	}

	private BigDecimal parseDecimal(JsonObject json) throws Exception {
		BigDecimal res = new BigDecimal(0);
		// parseElementProperties(json, res);
		if (json.has("value"))
			res = parseDecimalPrimitive(json.getString("value"));
		return res;
	}

	private Uri parseUri(JsonObject json) throws Exception {
		Uri res = new UriImpl();
		parseElementProperties(json, res);
		if (json.has("value"))
			res.setValue(parseUriPrimitive(json.getString("value")));
		return res;
	}

	private Id parseId(JsonObject json) throws Exception {
		Id res = new IdImpl();
		parseElementProperties(json, res);
		if (json.has("value"))
			res.setValue(parseIdPrimitive(json.getString("value")));
		return res;
	}

	private Base64Binary parseBase64Binary(JsonObject json) throws Exception {
		Base64Binary res = new Base64BinaryImpl();
		parseElementProperties(json, res);
		if (json.has("value"))
			res.setValue(parseBase64BinaryPrimitive(json.getString("value")));
		return res;
	}
	
	private String parseString(JsonObject json) throws Exception {
		String res = new String();
		// parseElementProperties(json, res);
		if (json.has("value"))
			res = parseStringPrimitive(json.getString("value"));
		return res;
	}

	private Boolean parseBoolean(JsonObject json) throws Exception {
		Boolean res = new Boolean(false);
		// parseElementProperties(json, res);
		if (json.has("value"))
			res = parseBooleanPrimitive(json.getString("value"));
		return res;
	}

	private Instant parseInstant(JsonObject json) throws Exception {
		Instant res = new InstantImpl();
		parseElementProperties(json, res);
		if (json.has("value"))
			res.setValue(parseInstantPrimitive(json.getString("value")));
		return res;
	}

	private Extension parseExtension(JsonObject json) throws Exception {
		Extension res = new ExtensionImpl();
		parseElementProperties(json, res);
		if (json.has("url"))
			res.setUrl(parseUri(json.getJsonObject("url")));
		if (json.has("mustUnderstand"))
			res.setMustUnderstand(parseBoolean(json
					.getJsonObject("mustUnderstand")));
		Type value = parseType("value", json);
		if (value != null)
			res.setValue(value);
		if (json.has("extension")) {
			JsonArray array = json.getJsonArray("extension");
			for (int i = 0; i < array.length(); i++) {
				res.getExtension().add(parseExtension(array.getJsonObject(i)));
			}
		}
		;
		return res;
	}

	private Narrative parseNarrative(JsonObject json) throws Exception {
		Narrative res = new NarrativeImpl();
		parseElementProperties(json, res);
		if (json.has("status"))
			res.setStatus(parseEnumeration(json.getJsonObject("status"),
					Narrative.NarrativeStatus.Null,
					new NarrativeStatusEnumFactory()));
		if (json.has("div"))
			res.setDiv(parseXhtml(json.getString("div")));
		return res;
	}

	private Period parsePeriod(JsonObject json) throws Exception {
		Period res = new PeriodImpl();
		parseTypeProperties(json, res);
		if (json.has("start"))
			res.setStart(parseDateTime(json.getJsonObject("start")));
		if (json.has("end"))
			res.setEnd(parseDateTime(json.getJsonObject("end")));
		return res;
	}

	private Coding parseCoding(JsonObject json) throws Exception {
		Coding res = new CodingImpl();
		parseTypeProperties(json, res);
		if (json.has("system"))
			res.setSystem(parseUri(json.getJsonObject("system")));
		if (json.has("code"))
			res.setCode(parseCode(json.getJsonObject("code")));
		if (json.has("display"))
			res.setDisplay(parseString(json.getJsonObject("display")));
		return res;
	}

	private Quantity parseQuantity(JsonObject json) throws Exception {
		Quantity res = new QuantityImpl();
		parseTypeProperties(json, res);
		if (json.has("value"))
			res.setValue(parseDecimal(json.getJsonObject("value")));
		if (json.has("comparator"))
			res.setComparator(parseEnumeration(
					json.getJsonObject("comparator"),
					Quantity.QuantityComparator.Null,
					new QuantityComparatorEnumFactory()));
		if (json.has("units"))
			res.setUnits(parseString(json.getJsonObject("units")));
		if (json.has("system"))
			res.setSystem(parseUri(json.getJsonObject("system")));
		if (json.has("code"))
			res.setCode(parseCode(json.getJsonObject("code")));
		return res;
	}


	private ResourceReference parseResourceReference(JsonObject json)
			throws Exception {
		ResourceReference res = new ResourceReferenceImpl();
		parseTypeProperties(json, res);
		if (json.has("type"))
			res.setType(parseCode(json.getJsonObject("type")));
		if (json.has("url"))
			res.setUrl(parseUri(json.getJsonObject("url")));
		if (json.has("display"))
			res.setDisplay(parseString(json.getJsonObject("display")));
		return res;
	}

	private CodeableConcept parseCodeableConcept(JsonObject json)
			throws Exception {
		CodeableConcept res = new CodeableConceptImpl();
		parseTypeProperties(json, res);
		if (json.has("coding")) {
			JsonArray array = json.getJsonArray("coding");
			for (int i = 0; i < array.length(); i++) {
				res.getCoding().add(parseCoding(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("text"))
			res.setText(parseString(json.getJsonObject("text")));
		if (json.has("primary"))
			res.setPrimary(parseString(json.getJsonObject("primary")));
		return res;
	}

	private Identifier parseIdentifier(JsonObject json) throws Exception {
		Identifier res = new IdentifierImpl();
		parseTypeProperties(json, res);
		if (json.has("use"))
			res.setUse(parseEnumeration(json.getJsonObject("use"),
					Identifier.IdentifierUse.Null,
					new IdentifierUseEnumFactory()));
		if (json.has("label"))
			res.setLabel(parseString(json.getJsonObject("label")));
		if (json.has("system"))
			res.setSystem(parseUri(json.getJsonObject("system")));
		if (json.has("id"))
			res.setId(parseString(json.getJsonObject("id")));
		if (json.has("period"))
			res.setPeriod(parsePeriod(json.getJsonObject("period")));
		if (json.has("assigner"))
			res.setAssigner(parseResourceReference(json
					.getJsonObject("assigner")));
		return res;
	}

	private Schedule parseSchedule(JsonObject json) throws Exception {
		Schedule res = new ScheduleImpl();
		parseElementProperties(json, res);
		if (json.has("event")) {
			JsonArray array = json.getJsonArray("event");
			for (int i = 0; i < array.length(); i++) {
				res.getEvent().add(parsePeriod(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("repeat"))
			res.setRepeat(parseScheduleRepeat(json.getJsonObject("repeat"), res));
		return res;
	}

	private Schedule.Repeat parseScheduleRepeat(JsonObject json, Schedule owner)
			throws Exception {
		Schedule.Repeat res = new Repeat();
		parseElementProperties(json, res);
		if (json.has("frequency"))
			res.setFrequency(parseInteger(json.getJsonObject("frequency")));
		if (json.has("when"))
			res.setWhen(parseEnumeration(json.getJsonObject("when"),
					Schedule.EventTiming.Null,
					new EventTimingEnumFactory()));
		if (json.has("duration"))
			res.setDuration(parseDecimal(json.getJsonObject("duration")));
		if (json.has("units"))
			res.setUnits(parseEnumeration(json.getJsonObject("units"),
					Schedule.UnitsOfTime.Null,
					new UnitsOfTimeEnumFactory()));
		if (json.has("count"))
			res.setCount(parseInteger(json.getJsonObject("count")));
		if (json.has("end"))
			res.setEnd(parseDateTime(json.getJsonObject("end")));
		return res;
	}

	private Contact parseContact(JsonObject json) throws Exception {
		Contact res = new ContactImpl();
		parseElementProperties(json, res);
		if (json.has("system"))
			res.setSystem(parseEnumeration(json.getJsonObject("system"),
					Contact.ContactSystem.Null,
					new ContactSystemEnumFactory()));
		if (json.has("value"))
			res.setValue(parseString(json.getJsonObject("value")));
		if (json.has("use"))
			res.setUse(parseEnumeration(json.getJsonObject("use"),
					Contact.ContactUse.Null,
					new ContactUseEnumFactory()));
		if (json.has("period"))
			res.setPeriod(parsePeriod(json.getJsonObject("period")));
		return res;
	}

	private Address parseAddress(JsonObject json) throws Exception {
		Address res = new AddressImpl();
		parseElementProperties(json, res);
		if (json.has("use"))
			res.setUse(parseEnumeration(json.getJsonObject("use"),
					Address.AddressUse.Null,
					new AddressUseEnumFactory()));
		if (json.has("text"))
			res.setText(parseString(json.getJsonObject("text")));
		if (json.has("line")) {
			JsonArray array = json.getJsonArray("line");
			for (int i = 0; i < array.length(); i++) {
				res.getLine().add(parseString(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("city"))
			res.setCity(parseString(json.getJsonObject("city")));
		if (json.has("state"))
			res.setState(parseString(json.getJsonObject("state")));
		if (json.has("zip"))
			res.setZip(parseString(json.getJsonObject("zip")));
		if (json.has("country"))
			res.setCountry(parseString(json.getJsonObject("country")));
		if (json.has("period"))
			res.setPeriod(parsePeriod(json.getJsonObject("period")));
		return res;
	}

	private HumanName parseHumanName(JsonObject json) throws Exception {
		HumanName res = new HumanNameImpl();
		parseElementProperties(json, res);
		if (json.has("use"))
			res.setUse(parseEnumeration(json.getJsonObject("use"),
					HumanName.NameUse.Null,
					new NameUseEnumFactory()));
		if (json.has("text"))
			res.setText(parseString(json.getJsonObject("text")));
		if (json.has("family")) {
			JsonArray array = json.getJsonArray("family");
			for (int i = 0; i < array.length(); i++) {
				res.getFamily().add(parseString(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("given")) {
			JsonArray array = json.getJsonArray("given");
			for (int i = 0; i < array.length(); i++) {
				res.getGiven().add(parseString(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("prefix")) {
			JsonArray array = json.getJsonArray("prefix");
			for (int i = 0; i < array.length(); i++) {
				res.getPrefix().add(parseString(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("suffix")) {
			JsonArray array = json.getJsonArray("suffix");
			for (int i = 0; i < array.length(); i++) {
				res.getSuffix().add(parseString(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("period"))
			res.setPeriod(parsePeriod(json.getJsonObject("period")));
		return res;
	}



	private Demographics parseDemographics(JsonObject json) throws Exception {
		Demographics res = new DemographicsImpl();
		parseElementProperties(json, res);
		if (json.has("name")) {
			JsonArray array = json.getJsonArray("name");
			for (int i = 0; i < array.length(); i++) {
				res.getName().add(parseHumanName(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("telecom")) {
			JsonArray array = json.getJsonArray("telecom");
			for (int i = 0; i < array.length(); i++) {
				res.getTelecom().add(parseContact(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("gender"))
			res.setGender(parseCoding(json.getJsonObject("gender")));
		if (json.has("birthDate"))
			res.setBirthDate(parseDateTime(json.getJsonObject("birthDate")));
		if (json.has("deceased"))
			res.setDeceased(parseBoolean(json.getJsonObject("deceased")));
		if (json.has("address")) {
			JsonArray array = json.getJsonArray("address");
			for (int i = 0; i < array.length(); i++) {
				res.getAddress().add(parseAddress(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("maritalStatus"))
			res.setMaritalStatus(parseCodeableConcept(json
					.getJsonObject("maritalStatus")));
		if (json.has("language")) {
			JsonArray array = json.getJsonArray("language");
			for (int i = 0; i < array.length(); i++) {
				res.getLanguage().add(
						parseDemographicsLanguage(array.getJsonObject(i), res));
			}
		}
		;
		return res;
	}

	private Demographics.Language parseDemographicsLanguage(JsonObject json,
			Demographics owner) throws Exception {
		Demographics.Language res = new Language();
		parseElementProperties(json, res);
		if (json.has("language"))
			res.setLanguage(parseCodeableConcept(json.getJsonObject("language")));
		if (json.has("mode"))
			res.setMode(parseCodeableConcept(json.getJsonObject("mode")));
		if (json.has("proficiencyLevel"))
			res.setProficiencyLevel(parseCodeableConcept(json
					.getJsonObject("proficiencyLevel")));
		if (json.has("preference"))
			res.setPreference(parseBoolean(json.getJsonObject("preference")));
		return res;
	}

	protected void parseResourceProperties(JsonObject json, Resource res)
			throws Exception {
		parseElementProperties(json, res);
		if (json.has("language"))
			res.setLanguage(parseCode(json.getJsonObject("language")));
		if (json.has("text"))
			res.setText(parseNarrative(json.getJsonObject("text")));
		if (json.has("contained")) {
			JsonArray array = json.getJsonArray("contained");
			for (int i = 0; i < array.length(); i++) {
				res.getContained().add(parseResource(array.getJsonObject(i)));
			}
		}
		;
	}

	private CarePlan parseCarePlan(JsonObject json) throws Exception {
		CarePlan res = new CarePlanImpl();
		parseResourceProperties(json, res);
		if (json.has("identifier"))
			res.setIdentifier(parseIdentifier(json.getJsonObject("identifier")));
		if (json.has("patient"))
			res.setPatient(parseResourceReference(json.getJsonObject("patient")));
		if (json.has("status"))
			res.setStatus(parseEnumeration(json.getJsonObject("status"),
					CarePlan.CarePlanStatus.Null,
					new CarePlanStatusEnumFactory()));
		if (json.has("period"))
			res.setPeriod(parsePeriod(json.getJsonObject("period")));
		if (json.has("modified"))
			res.setModified(parseDateTime(json.getJsonObject("modified")));
		if (json.has("concern")) {
			JsonArray array = json.getJsonArray("concern");
			for (int i = 0; i < array.length(); i++) {
				res.getConcern().add(
						parseResourceReference(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("participant")) {
			JsonArray array = json.getJsonArray("participant");
			for (int i = 0; i < array.length(); i++) {
				res.getParticipant().add(
						parseCarePlanParticipant(array.getJsonObject(i), res));
			}
		}
		;
		if (json.has("activity")) {
			JsonArray array = json.getJsonArray("activity");
			for (int i = 0; i < array.length(); i++) {
				res.getActivity().add(
						parseCarePlanActivity(array.getJsonObject(i), res));
			}
		}
		return res;
	}

	private CarePlan.Participant parseCarePlanParticipant(JsonObject json,
			CarePlan owner) throws Exception {
		CarePlan.Participant res = new Participant();
		parseElementProperties(json, res);
		if (json.has("role"))
			res.setRole(parseCodeableConcept(json.getJsonObject("role")));
		if (json.has("member"))
			res.setMember(parseResourceReference(json.getJsonObject("member")));
		return res;
	}

	private CarePlan.Activity parseCarePlanActivity(JsonObject json,
			CarePlan owner) throws Exception {
		CarePlan.Activity res = new Activity();
		parseElementProperties(json, res);
		if (json.has("category"))
			res.setCategory(parseEnumeration(json.getJsonObject("category"),
					CarePlan.CarePlanActivityCategory.Null,
					new CarePlanActivityCategoryEnumFactory()));
		if (json.has("code"))
			res.setCode(parseCodeableConcept(json.getJsonObject("code")));
		if (json.has("prohibited"))
			res.setProhibited(parseBoolean(json.getJsonObject("prohibited")));
		// Type timing = parseType("timing", json);
		// if (timing != null)
		// 	res.setTiming(timing);
		if (json.has("location"))
			res.setLocation(parseResourceReference(json
					.getJsonObject("location")));
		if (json.has("performer")) {
			JsonArray array = json.getJsonArray("performer");
			for (int i = 0; i < array.length(); i++) {
				res.getPerformer().add(
						parseResourceReference(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("product"))
			res.setProduct(parseResourceReference(json.getJsonObject("product")));
		if (json.has("dailyAmount"))
			res.setDailyAmount(parseQuantity(json.getJsonObject("dailyAmount")));
		if (json.has("quantity"))
			res.setQuantity(parseQuantity(json.getJsonObject("quantity")));
		if (json.has("details"))
			res.setDetails(parseString(json.getJsonObject("details")));
		if (json.has("actionTaken")) {
			JsonArray array = json.getJsonArray("actionTaken");
			for (int i = 0; i < array.length(); i++) {
				res.getAction().add(
						parseResourceReference(array.getJsonObject(i)));
			}
		}
		;
		return res;
	}


	private Device parseDevice(JsonObject json) throws Exception {
		Device res = new DeviceImpl();
		parseResourceProperties(json, res);
		if (json.has("type"))
			res.setType(parseCodeableConcept(json.getJsonObject("type")));
		if (json.has("manufacturer"))
			res.setManufacturer(parseString(json.getJsonObject("manufacturer")));
		if (json.has("model"))
			res.setModel(parseString(json.getJsonObject("model")));
		if (json.has("version"))
			res.setVersion(parseString(json.getJsonObject("version")));
		if (json.has("identity"))
			res.setIdentity(parseDeviceIdentity(json.getJsonObject("identity"),
					res));
		if (json.has("owner"))
			res.setOwner(parseResourceReference(json.getJsonObject("owner")));
		if (json.has("assignedId")) {
			JsonArray array = json.getJsonArray("assignedId");
			for (int i = 0; i < array.length(); i++) {
				res.getAssignedId()
						.add(parseIdentifier(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("location"))
			res.setLocation(parseResourceReference(json
					.getJsonObject("location")));
		if (json.has("patient"))
			res.setPatient(parseResourceReference(json.getJsonObject("patient")));
		if (json.has("contact")) {
			JsonArray array = json.getJsonArray("contact");
			for (int i = 0; i < array.length(); i++) {
				res.getContact().add(parseContact(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("url"))
			res.setUrl(parseUri(json.getJsonObject("url")));
		return res;
	}

	private Device.Identity parseDeviceIdentity(JsonObject json, Device owner)
			throws Exception {
		Device.Identity res = new Identity();
		parseElementProperties(json, res);
		if (json.has("gtin"))
			res.setGtin(parseString(json.getJsonObject("gtin")));
		if (json.has("lot"))
			res.setLot(parseString(json.getJsonObject("lot")));
		if (json.has("serialNumber"))
			res.setSerialNumber(parseString(json.getJsonObject("serialNumber")));
		if (json.has("expiry"))
			res.setExpiry(parseDate(json.getJsonObject("expiry")));
		return res;
	}

	private Conformance parseConformance(JsonObject json) throws Exception {
		Conformance res = new ConformanceImpl();
		parseResourceProperties(json, res);
		if (json.has("date"))
			res.setDate(parseDateTime(json.getJsonObject("date")));
		if (json.has("publisher"))
			res.setPublisher(parseConformancePublisher(
					json.getJsonObject("publisher"), res));
		if (json.has("software"))
			res.setSoftware(parseConformanceSoftware(
					json.getJsonObject("software"), res));
		if (json.has("implementation"))
			res.setImplementation(parseConformanceImplementation(
					json.getJsonObject("implementation"), res));
		if (json.has("proposal"))
			res.setProposal(parseConformanceProposal(
					json.getJsonObject("proposal"), res));
		if (json.has("version"))
			res.setVersion(parseId(json.getJsonObject("version")));
		if (json.has("acceptUnknown"))
			res.setAcceptUnknown(parseBoolean(json
					.getJsonObject("acceptUnknown")));
		if (json.has("rest")) {
			JsonArray array = json.getJsonArray("rest");
			for (int i = 0; i < array.length(); i++) {
				res.getRest().add(
						parseConformanceRest(array.getJsonObject(i), res));
			}
		}
		;
		if (json.has("messaging")) {
			JsonArray array = json.getJsonArray("messaging");
			for (int i = 0; i < array.length(); i++) {
				res.getMessaging().add(
						parseConformanceMessaging(array.getJsonObject(i), res));
			}
		}
		;
		if (json.has("document")) {
			JsonArray array = json.getJsonArray("document");
			for (int i = 0; i < array.length(); i++) {
				res.getDocument().add(
						parseConformanceDocument(array.getJsonObject(i), res));
			}
		}
		;
		return res;
	}

	private Conformance.Publisher parseConformancePublisher(JsonObject json,
			Conformance owner) throws Exception {
		Conformance.Publisher res = new Conformance.Publisher();
		parseElementProperties(json, res);
		if (json.has("name"))
			res.setName(parseString(json.getJsonObject("name")));
		if (json.has("address"))
			res.setAddress(parseAddress(json.getJsonObject("address")));
		if (json.has("contact")) {
			JsonArray array = json.getJsonArray("contact");
			for (int i = 0; i < array.length(); i++) {
				res.getContact().add(parseContact(array.getJsonObject(i)));
			}
		}
		;
		return res;
	}

	private Conformance.Software parseConformanceSoftware(JsonObject json,
			Conformance owner) throws Exception {
		Conformance.Software res = new Conformance.Software();
		parseElementProperties(json, res);
		if (json.has("name"))
			res.setName(parseString(json.getJsonObject("name")));
		if (json.has("version"))
			res.setVersion(parseString(json.getJsonObject("version")));
		if (json.has("releaseDate"))
			res.setReleaseDate(parseDateTime(json.getJsonObject("releaseDate")));
		return res;
	}

	private Conformance.Implementation parseConformanceImplementation(
			JsonObject json, Conformance owner) throws Exception {
		Conformance.Implementation res = new Conformance.Implementation();
		parseElementProperties(json, res);
		if (json.has("description"))
			res.setDescription(parseString(json.getJsonObject("description")));
		if (json.has("url"))
			res.setUrl(parseUri(json.getJsonObject("url")));
		return res;
	}

	private Conformance.Proposal parseConformanceProposal(JsonObject json,
			Conformance owner) throws Exception {
		Conformance.Proposal res = new Conformance.Proposal();
		parseElementProperties(json, res);
		if (json.has("description"))
			res.setDescription(parseString(json.getJsonObject("description")));
		return res;
	}

	private Conformance.Rest parseConformanceRest(JsonObject json,
			Conformance owner) throws Exception {
		Conformance.Rest res = new Conformance.Rest();
		parseElementProperties(json, res);
		if (json.has("mode"))
			res.setMode(parseEnumeration(json.getJsonObject("mode"),
					Conformance.RestfulConformanceMode.Null,
					new Conformance.RestfulConformanceModeEnumFactory()));
		if (json.has("documentation"))
			res.setDocumentation(parseString(json
					.getJsonObject("documentation")));
		// if (json.has("security"))
		// 	res.setSecurity(parseConformanceSecurity(
		// 			json.getJsonObject("security"), owner));
		if (json.has("resource")) {
			JsonArray array = json.getJsonArray("resource");
			for (int i = 0; i < array.length(); i++) {
				res.getResource()
						.add(parseConformanceResource(array.getJsonObject(i),
								owner));
			}
		}
		;
		// if (json.has("batch"))
		// 	res.setBatch(parseBoolean(json.getJsonObject("batch")));
		// if (json.has("history"))
		// 	res.setHistory(parseBoolean(json.getJsonObject("history")));
		return res;
	}

	private Conformance.Resource parseConformanceResource(JsonObject json,
			Conformance owner) throws Exception {
		Conformance.Resource res = new Conformance.Resource();
		parseElementProperties(json, res);
		if (json.has("type"))
			res.setType(parseCode(json.getJsonObject("type")));
		if (json.has("profile"))
			res.setProfile(parseUri(json.getJsonObject("profile")));
		if (json.has("operation")) {
			JsonArray array = json.getJsonArray("operation");
			for (int i = 0; i < array.length(); i++) {
				res.getOperation()
						.add(parseConformanceOperation(array.getJsonObject(i),
								owner));
			}
		}
		;
		if (json.has("readHistory"))
		 	res.setHistory(parseBoolean(json.getJsonObject("readHistory")));
		if (json.has("searchParam")) {
			JsonArray array = json.getJsonArray("searchParam");
			for (int i = 0; i < array.length(); i++) {
				res.getSearchParam().add(
						parseConformanceSearchParam(array.getJsonObject(i),
								owner));
			}
		}
		;
		return res;
	}

	private Conformance.Operation parseConformanceOperation(JsonObject json,
			Conformance owner) throws Exception {
		Conformance.Operation res = new Conformance.Operation();
		parseElementProperties(json, res);
		if (json.has("code"))
			res.setCode(parseEnumeration(json.getJsonObject("code"),
					Conformance.RestfulOperation.Null,
					new Conformance.RestfulOperationEnumFactory()));
		if (json.has("documentation"))
			res.setDocumentation(parseString(json
					.getJsonObject("documentation")));
		return res;
	}

	private Conformance.SearchParam parseConformanceSearchParam(
			JsonObject json, Conformance owner) throws Exception {
		Conformance.SearchParam res = new Conformance.SearchParam();
		parseElementProperties(json, res);
		if (json.has("name"))
			res.setName(parseString(json.getJsonObject("name")));
		if (json.has("source"))
			res.setSource(parseUri(json.getJsonObject("source")));
		if (json.has("type"))
			res.setType(parseEnumeration(json.getJsonObject("type"),
					Conformance.SearchParamType.Null,
					new Conformance.SearchParamTypeEnumFactory()));
		if (json.has("repeats"))
			res.setRepeats(parseEnumeration(json.getJsonObject("repeats"),
					Conformance.SearchRepeatBehavior.Null,
					new Conformance.SearchRepeatBehaviorEnumFactory()));
		if (json.has("documentation"))
			res.setDocumentation(parseString(json
					.getJsonObject("documentation")));

		return res;
	}

	private Conformance.Messaging parseConformanceMessaging(JsonObject json,
			Conformance owner) throws Exception {
		Conformance.Messaging res = new Conformance.Messaging();
		parseElementProperties(json, res);
		if (json.has("endpoint"))
			res.setEndpoint(parseUri(json.getJsonObject("endpoint")));
		if (json.has("documentation"))
			res.setDocumentation(parseString(json
					.getJsonObject("documentation")));
		if (json.has("event")) {
			JsonArray array = json.getJsonArray("event");
			for (int i = 0; i < array.length(); i++) {
				res.getEvent().add(
						parseConformanceEvent(array.getJsonObject(i), owner));
			}
		}
		;
		return res;
	}

	private Conformance.Event parseConformanceEvent(JsonObject json,
			Conformance owner) throws Exception {
		Conformance.Event res = new Conformance.Event();
		parseElementProperties(json, res);
		if (json.has("code"))
			res.setCode(parseCode(json.getJsonObject("code")));
		if (json.has("mode"))
			res.setMode(parseEnumeration(
					json.getJsonObject("mode"),
					Conformance.MessageConformanceEventMode.Null,
					new Conformance.MessageConformanceEventModeEnumFactory()));
		if (json.has("protocol")) {
			JsonArray array = json.getJsonArray("protocol");
			for (int i = 0; i < array.length(); i++) {
				res.getProtocol().add(parseCoding(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("focus"))
			res.setFocus(parseCode(json.getJsonObject("focus")));
		if (json.has("request"))
			res.setRequest(parseUri(json.getJsonObject("request")));
		if (json.has("response"))
			res.setResponse(parseUri(json.getJsonObject("response")));
		if (json.has("documentation"))
			res.setDocumentation(parseString(json
					.getJsonObject("documentation")));
		return res;
	}

	private Conformance.Document parseConformanceDocument(JsonObject json,
			Conformance owner) throws Exception {
		Conformance.Document res = new Conformance.Document();
		parseElementProperties(json, res);
		if (json.has("mode"))
			res.setMode(parseEnumeration(json.getJsonObject("mode"),
					Conformance.DocumentMode.Null,
					new Conformance.DocumentModeEnumFactory()));
		if (json.has("documentation"))
			res.setDocumentation(parseString(json
					.getJsonObject("documentation")));
		if (json.has("profile"))
			res.setProfile(parseUri(json.getJsonObject("profile")));
		return res;
	}

	private Patient parsePatient(JsonObject json) throws Exception {
		Patient res = new PatientImpl();
		parseResourceProperties(json, res);
		if (json.has("link")) {
			JsonArray array = json.getJsonArray("link");
			for (int i = 0; i < array.length(); i++) {
				res.getLink().add(
						parseResourceReference(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("active"))
			res.setActive(parseBoolean(json.getJsonObject("active")));
		if (json.has("identifier")) {
			JsonArray array = json.getJsonArray("identifier");
			for (int i = 0; i < array.length(); i++) {
				res.getIdentifier()
						.add(parseIdentifier(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("details"))
			res.setDetails(parseDemographics(json.getJsonObject("details")));
		if (json.has("provider"))
			res.setProvider(parseResourceReference(json
					.getJsonObject("provider")));
		// Type multipleBirth = parseType("multipleBirth", json);
		// if (multipleBirth != null)
		// 	res.setMultipleBirth(multipleBirth);
		// if (json.has("deceasedDate"))
		// 	res.setDeceasedDate(parseDateTime(json
		// 			.getJsonObject("deceasedDate")));
		// if (json.has("diet"))
		// 	res.setDiet(parseCodeableConcept(json.getJsonObject("diet")));
		return res;
	}

	private Provider parseProvider(JsonObject json) throws Exception {
		Provider res = new ProviderImpl();
		parseResourceProperties(json, res);
		if (json.has("identifier")) {
			JsonArray array = json.getJsonArray("identifier");
			for (int i = 0; i < array.length(); i++) {
				res.getIdentifier()
						.add(parseIdentifier(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("details"))
			res.setDetails(parseDemographics(json.getJsonObject("details")));
		if (json.has("organization"))
			res.setOrganization(parseResourceReference(json
					.getJsonObject("organization")));
		if (json.has("role")) {
			JsonArray array = json.getJsonArray("role");
			for (int i = 0; i < array.length(); i++) {
				res.getRole().add(parseCodeableConcept(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("specialty")) {
			JsonArray array = json.getJsonArray("specialty");
			for (int i = 0; i < array.length(); i++) {
				res.getSpecialty().add(
						parseCodeableConcept(array.getJsonObject(i)));
			}
		}
		;
		if (json.has("period"))
			res.setPeriod(parsePeriod(json.getJsonObject("period")));
		return res;
	}


	@Override
	protected Resource parseResource(JsonObject json) throws Exception {
		if (json.has("CarePlan"))
			return parseCarePlan(json.getJsonObject("CarePlan"));
		else if (json.has("Device"))
			return parseDevice(json.getJsonObject("Device"));
		else if (json.has("Conformance"))
			return parseConformance(json.getJsonObject("Conformance"));
		else if (json.has("Patient"))
			return parsePatient(json.getJsonObject("Patient"));
		else if (json.has("Provider"))
			return parseProvider(json.getJsonObject("Provider"));
		else if (json.has("Binary"))
			return parseBinary(json.getJsonObject("Binary"));
		throw new Exception("Unknown.Unrecognised resource type");
	}

	protected Type parseType(String prefix, JsonObject json) throws Exception {
		if (json.has(prefix + "Period"))
			return parsePeriod(json.getJsonObject(prefix + "Period"));
		else if (json.has(prefix + "Coding"))
			return parseCoding(json.getJsonObject(prefix + "Coding"));
		else if (json.has(prefix + "Quantity"))
			return parseQuantity(json.getJsonObject(prefix + "Quantity"));
		else if (json.has(prefix + "ResourceReference"))
			return parseResourceReference(json.getJsonObject(prefix
					+ "ResourceReference"));
		else if (json.has(prefix + "CodeableConcept"))
			return parseCodeableConcept(json.getJsonObject(prefix
					+ "CodeableConcept"));
		else if (json.has(prefix + "Identifier"))
			return parseIdentifier(json.getJsonObject(prefix + "Identifier"));
		else if (json.has(prefix + "Schedule"))
			return parseSchedule(json.getJsonObject(prefix + "Schedule"));
		else if (json.has(prefix + "Contact"))
			return parseContact(json.getJsonObject(prefix + "Contact"));
		else if (json.has(prefix + "Address"))
			return parseAddress(json.getJsonObject(prefix + "Address"));
		else if (json.has(prefix + "HumanName"))
			return parseHumanName(json.getJsonObject(prefix + "HumanName"));
		else if (json.has(prefix + "Demographics"))
			return parseDemographics(json
					.getJsonObject(prefix + "Demographics"));
		else if (json.has(prefix + "DateTime"))
			return parseDateTime(json.getJsonObject(prefix + "DateTime"));
		else if (json.has(prefix + "Code"))
			return parseCode(json.getJsonObject(prefix + "Code"));
		else if (json.has(prefix + "Date"))
			return parseDate(json.getJsonObject(prefix + "Date"));
		else if (json.has(prefix + "Uri"))
			return parseUri(json.getJsonObject(prefix + "Uri"));
		else if (json.has(prefix + "Id"))
			return parseId(json.getJsonObject(prefix + "Id"));
		else if (json.has(prefix + "Base64Binary"))
			return parseBase64Binary(json
					.getJsonObject(prefix + "Base64Binary"));
		else if (json.has(prefix + "Instant"))
			return parseInstant(json.getJsonObject(prefix + "Instant"));
		return null;
	}

	private boolean hasTypeName(JsonObject json, String prefix) {
		if (json.has(prefix + "Period"))
			return true;
		if (json.has(prefix + "Coding"))
			return true;
		if (json.has(prefix + "Quantity"))
			return true;
		if (json.has(prefix + "ResourceReference"))
			return true;
		if (json.has(prefix + "CodeableConcept"))
			return true;
		if (json.has(prefix + "Identifier"))
			return true;
		if (json.has(prefix + "Schedule"))
			return true;
		if (json.has(prefix + "Contact"))
			return true;
		if (json.has(prefix + "Address"))
			return true;
		if (json.has(prefix + "HumanName"))
			return true;
		if (json.has(prefix + "Demographics"))
			return true;
		if (json.has(prefix + "CarePlan"))
			return true;
		if (json.has(prefix + "Device"))
			return true;
		if (json.has(prefix + "Conformance"))
			return true;
		if (json.has(prefix + "Patient"))
			return true;
		if (json.has(prefix + "Provider"))
			return true;
		if (json.has(prefix + "Integer"))
			return true;
		if (json.has(prefix + "DateTime"))
			return true;
		if (json.has(prefix + "Code"))
			return true;
		if (json.has(prefix + "Date"))
			return true;
		if (json.has(prefix + "Decimal"))
			return true;
		if (json.has(prefix + "Uri"))
			return true;
		if (json.has(prefix + "Id"))
			return true;
		if (json.has(prefix + "Base64Binary"))
			return true;
		if (json.has(prefix + "String"))
			return true;
		if (json.has(prefix + "Boolean"))
			return true;
		if (json.has(prefix + "Instant"))
			return true;
		return false;
	}
}