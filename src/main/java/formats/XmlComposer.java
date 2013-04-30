package formats;

import java.net.*;
import java.util.Date;
import java.util.Iterator;
import java.math.*;

import models.Address;
import models.AddressImpl;
import models.CarePlan;
import models.CarePlan.CarePlanStatus;
import models.CarePlan.CarePlanStatusEnumFactory;
import models.CarePlanImpl;
import models.Conformance;
import models.Conformance.Document;
import models.ConformanceImpl;
import models.Contact;
import models.Contact.ContactSystem;
import models.Contact.ContactSystemEnumFactory;
import models.Contact.ContactUse;
import models.Contact.ContactUseEnumFactory;
import models.ContactImpl;
import models.Demographics;
import models.DemographicsImpl;
import models.Device;
import models.DeviceImpl;
import models.Id;
import models.IdImpl;
import models.Instant;
import models.InstantImpl;
import models.Narrative;
import models.Narrative.NarrativeStatus;
import models.Narrative.NarrativeStatusEnumFactory;
import models.NarrativeImpl;
import models.Patient;
import models.PatientImpl;
import models.Period;
import models.PeriodImpl;
import models.Provider;
import models.ProviderImpl;
import models.Resource;
import models.ResourceImpl;
import models.Schedule;
import models.Schedule.EventTiming;
import models.Schedule.EventTimingEnumFactory;
import models.Schedule.UnitsOfTime;
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
import resources.HumanName.NameUse;
import resources.HumanNameImpl;
import resources.Identifier;
import resources.Identifier.IdentifierUseEnumFactory;
import resources.IdentifierImpl;
import resources.Identity;
import resources.IdentityImpl;
import resources.Quantity;
import resources.Quantity.QuantityComparator;
import resources.Quantity.QuantityComparatorEnumFactory;
import resources.QuantityImpl;
import resources.ResourceReference;
import resources.ResourceReferenceImpl;
import resources.Type;
import utilities.Base64Binary;
import utilities.Base64BinaryImpl;
import utilities.Binary;
import utilities.Uri;
import utilities.UriImpl;
import utilities.Utilities;

public class XmlComposer extends XmlComposerBase {

	private void composeElementElements(Element element) throws Exception {
		for (Extension e : element.getExtensions()) {
			composeExtension("extension", e);
		}
	}
	
//	private void composeElementElements(Enum<E> value) throws Exception {
//		for (Extension e : value.valueOf(Extension.class, value.name())) { //value.valueOf((Class<Extension>)value.getClass(), "extension")) {
//			composeExtension("extension", e);
//		}
//	}


	private <E extends Enum<E>> void composeEnumeration(String name,
			Enum<E> value, EnumFactory e) throws Exception {
		if (value != null) {
			composeElementAttributes(value);
			if (value != null)
				xml.attribute("value", e.toCode(value));

			xml.open(FHIR_NS, name);
			// composeElementElements(value);
			xml.close(FHIR_NS, name);
		}
	}

	protected void composeDateTime(String name, DateTime value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || !Utilities.noString(value
						.getValue()))) {
			composeElementAttributes(value);
			if (value.getValue() != null)
				xml.attribute("value", toString(value.getValue()));

			xml.open(FHIR_NS, name);
			composeElementElements(value);
			xml.close(FHIR_NS, name);
		}
	}

	protected void composeCode(String name, Code value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || !Utilities.noString(value
						.getCodeText()))) {
			composeElementAttributes(value);
			if (value.getCodeText() != null)
				xml.attribute("value", toString(value.getCodeText()));

			xml.open(FHIR_NS, name);
			composeElementElements(value);
			xml.close(FHIR_NS, name);
		}
	}

	protected void composeUri(String name, Uri value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || value.getValue() != null)) {
			composeElementAttributes(value);
			if (value.getValue() != null)
				xml.attribute("value", toString(value.getValue()));

			xml.open(FHIR_NS, name);
			composeElementElements(value);
			xml.close(FHIR_NS, name);
		}
	}

	protected void composeId(String name, Id value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || !Utilities.noString(value
						.getValue()))) {
			composeElementAttributes(value);
			if (value.getValue() != null)
				xml.attribute("value", toString(value.getValue()));

			xml.open(FHIR_NS, name);
			composeElementElements(value);
			xml.close(FHIR_NS, name);
		}
	}

	protected void composeBase64Binary(String name, Base64Binary value)
			throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || value.getValue() != null)) {
			composeElementAttributes(value);
			if (value.getValue() != null)
				xml.attribute("value", toString(value.getValue()));

			xml.open(FHIR_NS, name);
			composeElementElements(value);
			xml.close(FHIR_NS, name);
		}
	}

	protected void composeInstant(String name, Instant value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || value.getValue() != null)) {
			composeElementAttributes(value);
			if (value.getValue() != null)
				xml.attribute("value", toString(value.getValue()));

			xml.open(FHIR_NS, name);
			composeElementElements(value);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeExtension(String name, Extension element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeUri("url", element.getUrl());
			composeBoolean("mustUnderstand", element.getMustUnderstand());
			composeType("value", element.getValue());
			for (Extension e : element.getExtension())
				composeExtension("extension", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeNarrative(String name, Narrative element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			if (element.getStatus() != null)
				composeEnumeration("status", element.getStatus(),
						new Narrative.NarrativeStatusEnumFactory());
			composeXhtml("div", element.getDiv());
			for (Narrative.Image e : element.getImage())
				composeNarrativeImage("image", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeEnumeration(String name, NarrativeStatus status,
			NarrativeStatusEnumFactory e) {
		// TODO Auto-generated method stub
		
	}

	private void composeNarrativeImage(String name, Narrative.Image element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeCode("mimeType", element.getMimeType());
			composeBase64Binary("content", element.getContent());
			xml.close(FHIR_NS, name);
		}
	}

	private void composePeriod(String name, Period element) throws Exception {
		if (element != null) {
			composeTypeAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeDateTime("start", element.getStart());
			composeDateTime("end", element.getEnd());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeCoding(String name, Coding element) throws Exception {
		if (element != null) {
			composeTypeAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeUri("system", element.getSystem());
			composeCode("code", element.getCode());
			composeString("display", element.getDisplay());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeQuantity(String name, Quantity element)
			throws Exception {
		if (element != null) {
			composeTypeAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeDecimal("value", element.getValue());
			if (element.getComparator() != null)
				composeEnumeration("comparator", element.getComparator(),
						new QuantityComparatorEnumFactory());
			composeString("units", element.getUnits());
			composeUri("system", element.getSystem());
			composeCode("code", element.getCode());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeEnumeration(String name, QuantityComparator comparator,
			QuantityComparatorEnumFactory e) {
		// TODO Auto-generated method stub
		
	}

	private void composeResourceReference(String name, ResourceReference element)
			throws Exception {
		if (element != null) {
			composeTypeAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeCode("type", element.getType());
			composeUri("url", element.getUrl());
			composeString("display", element.getDisplay());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeCodeableConcept(String name, CodeableConcept element)
			throws Exception {
		if (element != null) {
			composeTypeAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			for (Coding e : element.getCoding())
				composeCoding("coding", e);
			composeString("text", element.getText());
			composeString("primary", element.getPrimary());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeIdentifier(String name, Identifier element)
			throws Exception {
		if (element != null) {
			composeTypeAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			if (element.getUse() != null)
				composeEnumeration("use", element.getUse(),
						new IdentifierUseEnumFactory());
			composeString("label", element.getLabel());
			composeUri("system", element.getSystem());
			composeString("id", element.getId());
			composePeriod("period", element.getPeriod());
			composeResourceReference("assigner", element.getAssigner());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeSchedule(String name, Schedule element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			for (Period e : element.getEvent())
				composePeriod("event", e);
			composeScheduleRepeat("repeat", element.getRepeat());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeScheduleRepeat(String name, Schedule.Repeat element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeInteger("frequency", element.getFrequency());
			if (element.getWhen() != null)
				composeEnumeration("when", element.getWhen(),
						new Schedule.EventTimingEnumFactory());
			composeDecimal("duration", element.getDuration());
			if (element.getUnits() != null)
				composeEnumeration("units", element.getUnits(),
						new Schedule.UnitsOfTimeEnumFactory());
			composeInteger("count", element.getCount());
			composeDateTime("end", element.getEnd());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeEnumeration(String name, UnitsOfTime units,
			UnitsOfTimeEnumFactory e) {
		// TODO Auto-generated method stub
		
	}

	private void composeEnumeration(String name, EventTiming when,
			EventTimingEnumFactory e) {
		// TODO Auto-generated method stub
		
	}

	private void composeContact(String name, Contact element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			if (element.getSystem() != null)
				composeEnumeration("system", element.getSystem(),
						new Contact.ContactSystemEnumFactory());
			composeString("value", element.getValue());
			if (element.getUse() != null)
				composeEnumeration("use", element.getUse(),
						new Contact.ContactUseEnumFactory());
			composePeriod("period", element.getPeriod());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeEnumeration(String name, ContactUse use,
			ContactUseEnumFactory e) {
		// TODO Auto-generated method stub
		
	}

	private void composeEnumeration(String name, ContactSystem system,
			ContactSystemEnumFactory e) {
		// TODO Auto-generated method stub
		
	}

	private void composeAddress(String name, Address element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			if (element.getUse() != null)
				composeEnumeration("use", element.getUse(),
						new Address.AddressUseEnumFactory());
			composeString("text", element.getText());
			for (String e : element.getLine())
				composeString("line", e);
			composeString("city", element.getCity());
			composeString("state", element.getState());
			composeString("zip", element.getZip());
			composeString("country", element.getCountry());
			composePeriod("period", element.getPeriod());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeHumanName(String name, HumanName element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			if (element.getUse() != null)
				composeEnumeration("use", element.getUse(),
						new HumanName.NameUseEnumFactory());
			composeString("text", element.getText());
			for (String e : element.getFamily())
				composeString("family", e);
			for (String e : element.getGiven())
				composeString("given", e);
			for (String e : element.getPrefix())
				composeString("prefix", e);
			for (String e : element.getSuffix())
				composeString("suffix", e);
			composePeriod("period", element.getPeriod());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeDemographics(String name, Demographics element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			for (HumanName e : element.getName())
				composeHumanName("name", e);
			for (Contact e : element.getTelecom())
				composeContact("telecom", e);
			composeCoding("gender", element.getGender());
			composeDateTime("birthDate", element.getBirthDate());
			for (Address e : element.getAddress())
				composeAddress("address", e);
			composeCodeableConcept("maritalStatus", element.getMaritalStatus());
			for (Demographics.Language e : element.getLanguage())
				composeDemographicsLanguage("language", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeDemographicsLanguage(String name,
			Demographics.Language element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeCodeableConcept("language", element.getLanguage());
			composeCodeableConcept("mode", element.getMode());
			composeCodeableConcept("proficiencyLevel",
					element.getProficiencyLevel());
			composeBoolean("preference", element.getPreference());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeResourceAttributes(Resource element) throws Exception {
		composeElementAttributes(element);
	}

	private void composeResourceElements(Resource element) throws Exception {
		composeElementElements(element);
		composeCode("language", element.getLanguage());
		composeNarrative("text", element.getText());
		for (Resource r : element.getContained()) {
			if (r.getXmlId() == null)
				throw new Exception(
						"Contained Resource has no id - one must be assigned"); // we
																				// can't
																				// assign
																				// one
																				// here
																				// -
																				// what
																				// points
																				// to
																				// it?
			xml.open(FHIR_NS, "contained");
			composeResource(r);
			xml.close(FHIR_NS, "contained");
		}
	}

	private void composeCarePlan(String name, CarePlan element)
			throws Exception {
		if (element != null) {
			composeResourceAttributes(element);
			xml.open(FHIR_NS, name);
			composeResourceElements(element);
			composeIdentifier("identifier", element.getIdentifier());
			composeResourceReference("patient", element.getPatient());
			if (element.getStatus() != null)
				composeEnumeration("status", element.getStatus(),
						new CarePlanStatusEnumFactory());
			composePeriod("period", element.getPeriod());
			composeDateTime("modified", element.getModified());
			for (ResourceReference e : element.getConcern())
				composeResourceReference("concern", e);
			for (CarePlan.Participant e : element.getParticipant())
				composeCarePlanParticipant("participant", e);
			composeString("goal", element.getGoal());
			for (CarePlan.Activity e : element.getActivity())
				composeCarePlanActivity("activity", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeEnumeration(String name, CarePlanStatus status,
			CarePlanStatusEnumFactory e) {
		// TODO Auto-generated method stub
		
	}

	private void composeCarePlanParticipant(String name,
			CarePlan.Participant element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeCodeableConcept("role", element.getRole());
			composeResourceReference("member", element.getMember());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeCarePlanActivity(String name, CarePlan.Activity element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			if (element.getCategory() != null)
				composeEnumeration(
						"category",
						element.getCategory(),
						new CarePlan.CarePlanActivityCategoryEnumFactory());
			composeCodeableConcept("code", element.getCode());
			composeBoolean("prohibited", element.getProhibited());
			composeSchedule("schedule", element.getSchedule());
			composeResourceReference("location", element.getLocation());
			for (ResourceReference e : element.getPerformer())
				composeResourceReference("performer", e);
			composeResourceReference("product", element.getProduct());
			composeQuantity("dailyAmount", element.getDailyAmount());
			composeQuantity("quantity", element.getQuantity());
			composeString("details", element.getDetails());
			for (ResourceReference e : element.getAction())
				composeResourceReference("action", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeDevice(String name, Device element) throws Exception {
		if (element != null) {
			composeResourceAttributes(element);
			xml.open(FHIR_NS, name);
			composeResourceElements(element);
			composeCodeableConcept("type", element.getType());
			composeString("manufacturer", element.getManufacturer());
			composeString("model", element.getModel());
			composeString("version", element.getVersion());
			composeDeviceIdentity("identity", element.getIdentity());
			composeResourceReference("owner", element.getOwner());
			for (Identifier e : element.getAssignedId())
				composeIdentifier("assignedId", e);
			composeResourceReference("location", element.getLocation());
			composeResourceReference("patient", element.getPatient());
			for (Contact e : element.getContact())
				composeContact("contact", e);
			composeUri("url", element.getUrl());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeDeviceIdentity(String name, Device.Identity element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeString("gtin", element.getGtin());
			composeString("lot", element.getLot());
			composeString("serialNumber", element.getSerialNumber());
			composeDateTime("expiry", element.getExpiry());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformance(String name, Conformance element)
			throws Exception {
		if (element != null) {
			composeResourceAttributes(element);
			xml.open(FHIR_NS, name);
			composeResourceElements(element);
			composeDateTime("date", element.getDate());
			composeConformancePublisher("publisher", element.getPublisher());
			composeConformanceImplementation("implementation",
					element.getImplementation());
			composeConformanceSoftware("software", element.getSoftware());
			composeConformanceProposal("proposal", element.getProposal());
			composeId("version", element.getVersion());
			composeBoolean("acceptUnknown", element.getAcceptUnknown());
			for (Conformance.Rest e : element.getRest())
				composeConformanceRest("rest", e);
			for (Conformance.Messaging e : element.getMessaging())
				composeConformanceMessaging("messaging", e);
			for (Conformance.Document e : element.getDocument())
				composeConformanceDocument("document", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformancePublisher(String name,
			Conformance.Publisher element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeString("name", element.getName());
			composeAddress("address", element.getAddress());
			for (Contact e : element.getContact())
				composeContact("contact", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceImplementation(String name,
			Conformance.Implementation element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeString("description", element.getDescription());
			composeUri("url", element.getUrl());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceSoftware(String name,
			Conformance.Software element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeString("name", element.getName());
			composeString("version", element.getVersion());
			composeDateTime("releaseDate", element.getReleaseDate());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceProposal(String name,
			Conformance.Proposal element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeString("description", element.getDescription());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceRest(String name, Conformance.Rest element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			if (element.getMode() != null)
				composeEnumeration(
						"mode",
						element.getMode(),
						new Conformance.RestfulConformanceModeEnumFactory());
			composeString("documentation", element.getDocumentation());
			for (Conformance.Resource e : element.getResource())
				composeConformanceResource("resource", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceResource(String name,
			Conformance.Resource element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeCode("type", element.getType());
			composeUri("profile", element.getProfile());
			for (Conformance.Operation e : element.getOperation())
				composeConformanceOperation("operation", e);
			composeBoolean("history", element.getHistory());
			for (Conformance.SearchParam e : element.getSearchParam())
				composeConformanceSearchParam("searchParam", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceOperation(String name,
			Conformance.Operation element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			if (element.getCode() != null)
				composeEnumeration("code", element.getCode(),
						new Conformance.RestfulOperationEnumFactory());
			composeString("documentation", element.getDocumentation());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceSearchParam(String name,
			Conformance.SearchParam element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeString("name", element.getName());
			composeUri("source", element.getSource());
			if (element.getType() != null)
				composeEnumeration("type", element.getType(),
						new Conformance.SearchParamTypeEnumFactory());
			if (element.getRepeats() != null)
				composeEnumeration("repeats", element.getRepeats(),
						new Conformance.SearchRepeatBehaviorEnumFactory());
			composeString("documentation", element.getDocumentation());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceMessaging(String name,
			Conformance.Messaging element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeUri("endpoint", element.getEndpoint());
			composeString("documentation", element.getDocumentation());
			for (Conformance.Event e : element.getEvent())
				composeConformanceEvent("event", e);
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceEvent(String name, Conformance.Event element)
			throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			composeCode("code", element.getCode());
			if (element.getMode() != null)
				composeEnumeration(
						"mode",
						element.getMode(),
						new Conformance.MessageConformanceEventModeEnumFactory());
			for (Coding e : element.getProtocol())
				composeCoding("protocol", e);
			composeCode("focus", element.getFocus());
			composeUri("request", element.getRequest());
			composeUri("response", element.getResponse());
			composeString("documentation", element.getDocumentation());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeConformanceDocument(String name,
			Conformance.Document element) throws Exception {
		if (element != null) {
			composeElementAttributes(element);
			xml.open(FHIR_NS, name);
			composeElementElements(element);
			if (element.getMode() != null)
				composeEnumeration("mode", element.getMode(),
						new Conformance.DocumentModeEnumFactory());
			composeString("documentation", element.getDocumentation());
			composeUri("profile", element.getProfile());
			xml.close(FHIR_NS, name);
		}
	}

	private void composePatient(String name, Patient element) throws Exception {
		if (element != null) {
			composeResourceAttributes(element);
			xml.open(FHIR_NS, name);
			composeResourceElements(element);
			for (ResourceReference e : element.getLink())
				composeResourceReference("link", e);
			composeBoolean("active", element.isActive());
			for (Identifier e : element.getIdentifier())
				composeIdentifier("identifier", e);
			composeDemographics("details", element.getDetails());
			composeResourceReference("provider", element.getProvider());
			composeCodeableConcept("diet", element.getDiet());
			composeCodeableConcept("confidentiality",
					element.getConfidentiality());
			composeCodeableConcept("recordLocation",
					element.getRecordLocation());
			xml.close(FHIR_NS, name);
		}
	}

	private void composeProvider(String name, Provider element)
			throws Exception {
		if (element != null) {
			composeResourceAttributes(element);
			xml.open(FHIR_NS, name);
			composeResourceElements(element);
			for (Identifier e : element.getIdentifier())
				composeIdentifier("identifier", e);
			composeDemographics("details", element.getDetails());
			composeResourceReference("organization", element.getOrganization());
			for (CodeableConcept e : element.getRole())
				composeCodeableConcept("role", e);
			for (CodeableConcept e : element.getSpecialty())
				composeCodeableConcept("specialty", e);
			composePeriod("period", element.getPeriod());
			for (Address e : element.getAddress())
				composeAddress("address", e);
			for (Contact e : element.getContact())
				composeContact("contact", e);
			xml.close(FHIR_NS, name);
		}
	}


	@Override
	protected void composeResource(Resource resource) throws Exception {
		if (resource instanceof CarePlan)
			composeCarePlan("CarePlan", (CarePlan) resource);
		else if (resource instanceof Device)
			composeDevice("Device", (Device) resource);
		else if (resource instanceof Conformance)
			composeConformance("Conformance", (Conformance) resource);
		else if (resource instanceof Patient)
			composePatient("Patient", (Patient) resource);
		else if (resource instanceof Provider)
			composeProvider("Provider", (Provider) resource);
		else if (resource instanceof Binary)
			composeBinary("Binary", (Binary) resource);
		else
			throw new Exception("Unhanded resource type "
					+ resource.getClass().getName());
	}

	@SuppressWarnings("unchecked")
	protected void composeType(String prefix, Type type) throws Exception {
		if (type == null)
			;
		else if (type instanceof Period)
			composePeriod(prefix + "Period", (Period) type);
		else if (type instanceof Quantity)
			composeQuantity(prefix + "Quantity", (Quantity) type);
		else if (type instanceof ResourceReference)
			composeResourceReference(prefix + "ResourceReference",
					(ResourceReference) type);
		else if (type instanceof CodeableConcept)
			composeCodeableConcept(prefix + "CodeableConcept",
					(CodeableConcept) type);
		else if (type instanceof Identifier)
			composeIdentifier(prefix + "Identifier", (Identifier) type);
		else if (type instanceof Schedule)
			composeSchedule(prefix + "Schedule", (Schedule) type);
		else if (type instanceof Contact)
			composeContact(prefix + "Contact", (Contact) type);
		else if (type instanceof Address)
			composeAddress(prefix + "Address", (Address) type);
		else if (type instanceof HumanName)
			composeHumanName(prefix + "HumanName", (HumanName) type);
		else if (type instanceof Demographics)
			composeDemographics(prefix + "Demographics", (Demographics) type);
		else if (type instanceof DateTime)
			composeDateTime(prefix + "DateTime", (DateTime) type);
		else if (type instanceof Code)
			composeCode(prefix + "Code", (Code) type);
		else if (type instanceof Uri)
			composeUri(prefix + "Uri", (Uri) type);
		else if (type instanceof Id)
			composeId(prefix + "Id", (Id) type);
		else if (type instanceof Base64Binary)
			composeBase64Binary(prefix + "Base64Binary", (Base64Binary) type);
		else
			throw new Exception("Unhanded type");
	}

}
