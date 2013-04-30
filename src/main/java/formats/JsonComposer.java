package formats;

import models.Address;
import models.Address.AddressUse;
import models.Address.AddressUseEnumFactory;
import models.Contact;
import models.Contact.ContactSystem;
import models.Contact.ContactSystemEnumFactory;
import models.Contact.ContactUse;
import models.Contact.ContactUseEnumFactory;
import models.Demographics;
import models.Demographics.Language;
import models.Device;
import models.Id;
import models.Instant;
import models.Narrative;
import models.Narrative.NarrativeStatus;
import models.Narrative.NarrativeStatusEnumFactory;
import models.Patient;
import models.Period;
import models.Provider;
import models.Resource;
import models.Schedule;
import models.Schedule.EventTiming;
import models.Schedule.EventTimingEnumFactory;
import models.Schedule.Repeat;
import models.Schedule.UnitsOfTime;
import models.Schedule.UnitsOfTimeEnumFactory;

import java.math.*;

import resources.Code;
import resources.CodeableConcept;
import resources.Coding;
import resources.DateTime;
import resources.Element;
import resources.EnumFactory;
import resources.Extension;
import resources.HumanName;
import resources.HumanName.NameUseEnumFactory;
import resources.Identifier;
import resources.Identifier.IdentifierUseEnumFactory;
import resources.ResourceReference;
import resources.Type;
import utilities.Base64Binary;
import utilities.Binary;
import utilities.Uri;
import utilities.Utilities;

public class JsonComposer extends JsonComposerBase {

	private void composeElement(Element element) throws Exception {
		if (element.getXmlId() != null)
			prop("_id", element.getXmlId());
		if (element.getExtensions().size() > 0) {
			openArray("extension");
			for (Extension ex : element.getExtensions())
				composeExtension(null, ex);
			closeArray();
		}
	}
	
	private void composeElement(Object object) throws Exception {
		prop("_id", toString(object.hashCode()));
	}

	private <E extends Enum<E>> void composeEnumeration(String name,
			Enum<E> value, EnumFactory e) throws Exception {
		if (value != null) {
			open(name);
			composeElement(value);
			if (value != null)
				prop("value", e.toCode(value));
			close();
		}
	}

	private void composeInteger(String name, Integer value) throws Exception {
		if (value != null) {
			open(name);
			composeElement(value);
			prop("value", toString(value));
			close();
		}
	}

	private void composeDateTime(String name, DateTime value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || !Utilities.noString(value
						.getValue()))) {
			open(name);
			composeElement(value);
			if (value.getValue() != null)
				prop("value", toString(value.getValue()));

			if (value.getExtensions().size() > 0) {
				openArray("extension");
				for (Extension ex : value.getExtensions())
					composeExtension(null, ex);
				closeArray();
			}
			close();
		}
	}

	private void composeCode(String name, Code value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || !Utilities.noString(value
						.getCodeText()))) {
			open(name);
			composeElement(value);
			if (value.getCodeText() != null)
				prop("value", toString(value.getCodeText()));

			if (value.getExtensions().size() > 0) {
				openArray("extension");
				for (Extension ex : value.getExtensions())
					composeExtension(null, ex);
				closeArray();
			}
			close();
		}
	}

	private void composeDate(String name, DateTime value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || !Utilities.noString(value
						.getValue()))) {
			open(name);
			composeElement(value);
			if (value.getValue() != null)
				prop("value", toString(value.getValue()));

			if (value.getExtensions().size() > 0) {
				openArray("extension");
				for (Extension ex : value.getExtensions())
					composeExtension(null, ex);
				closeArray();
			}
			close();
		}
	}

	private void composeDecimal(String name, BigDecimal value) throws Exception {
		if (value != null) {
			open(name);
			composeElement(value);
			if (value != null)
				prop("value", toString(value));
			close();
		}
	}

	private void composeUri(String name, Uri value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || value.getValue() != null)) {
			open(name);
			composeElement(value);
			if (value.getValue() != null)
				prop("value", toString(value.getValue()));

			if (value.getExtensions().size() > 0) {
				openArray("extension");
				for (Extension ex : value.getExtensions())
					composeExtension(null, ex);
				closeArray();
			}
			close();
		}
	}

	private void composeId(String name, Id value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || !Utilities.noString(value
						.getValue()))) {
			open(name);
			composeElement(value);
			if (value.getValue() != null)
				prop("value", toString(value.getValue()));

			if (value.getExtensions().size() > 0) {
				openArray("extension");
				for (Extension ex : value.getExtensions())
					composeExtension(null, ex);
				closeArray();
			}
			close();
		}
	}

	private void composeBase64Binary(String name, Base64Binary value)
			throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || value.getValue() != null)) {
			open(name);
			composeElement(value);
			if (value.getValue() != null)
				prop("value", toString(value.getValue()));

			if (value.getExtensions().size() > 0) {
				openArray("extension");
				for (Extension ex : value.getExtensions())
					composeExtension(null, ex);
				closeArray();
			}
			close();
		}
	}


	private void composeString(String name, String value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value))) {
			open(name);
			composeElement(value);
			if (value != null)
				prop("value", value);
			close();
		}
	}

	private void composeBoolean(String name, Boolean value) throws Exception {
		if (value != null) {
			open(name);
			composeElement(value);
			prop("value", toString(value));
			close();
		}
	}

	private void composeInstant(String name, Instant value) throws Exception {
		if (value != null
				&& (!Utilities.noString(value.getXmlId())
						|| value.hasExtensions() || value.getValue() != null)) {
			open(name);
			composeElement(value);
			if (value.getValue() != null)
				prop("value", toString(value.getValue()));

			if (value.getExtensions().size() > 0) {
				openArray("extension");
				for (Extension ex : value.getExtensions())
					composeExtension(null, ex);
				closeArray();
			}
			close();
		}
	}

	private void composeExtension(String name, Extension element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			composeUri("url", element.getUrl());
			composeBoolean("mustUnderstand", element.getMustUnderstand());
			composeType("value", element.getValue());
			if (element.getExtension().size() > 0) {
				openArray("extension");
				for (Extension e : element.getExtension())
					composeExtension(null, e);
				closeArray();
			}
			;
			close();
		}
	}

	private void composeNarrative(String name, Narrative element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			if (element.getStatus() != null)
				composeEnumeration("status", element.getStatus(),
						new NarrativeStatusEnumFactory());
			composeXhtml("div", element.getDiv());
			if (element.getImage().size() > 0) {
				openArray("image");
				for (Narrative.Image e : element.getImage())
					composeNarrativeImage(null, e);
				closeArray();
			}
			;
			close();
		}
	}

	private void composeNarrativeImage(String name, Narrative.Image element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			composeCode("mimeType", element.getMimeType());
			composeBase64Binary("content", element.getContent());
			close();
		}
	}

	private void composePeriod(String name, Period element) throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			composeDateTime("start", element.getStart());
			composeDateTime("end", element.getEnd());
			close();
		}
	}

	private void composeCoding(String name, Coding element) throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			composeUri("system", element.getSystem());
			composeCode("code", element.getCode());
			composeString("display", element.getDisplay());
			close();
		}
	}

	private void composeResourceReference(String name, ResourceReference element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			composeCode("type", element.getType());
			composeUri("url", element.getUrl());
			composeString("display", element.getDisplay());
			close();
		}
	}

	private void composeCodeableConcept(String name, CodeableConcept element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			if (element.getCoding().size() > 0) {
				openArray("coding");
				for (Coding e : element.getCoding())
					composeCoding(null, e);
				closeArray();
			}
			;
			composeString("text", element.getText());
			composeString("primary", element.getPrimary());
			close();
		}
	}

	private void composeIdentifier(String name, Identifier element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			if (element.getUse() != null)
				composeEnumeration("use", element.getUse(),
						new IdentifierUseEnumFactory());
			composeString("label", element.getLabel());
			composeUri("system", element.getSystem());
			composeString("id", element.getId());
			composePeriod("period", element.getPeriod());
			composeResourceReference("assigner", element.getAssigner());
			close();
		}
	}

	private void composeSchedule(String name, Schedule element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			if (element.getEvent().size() > 0) {
				openArray("event");
				for (Period e : element.getEvent())
					composePeriod(null, e);
				closeArray();
			}
			;
			composeScheduleRepeat("repeat", element.getRepeat());
			close();
		}
	}

	private void composeScheduleRepeat(String name, Schedule.Repeat element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			composeInteger("frequency", element.getFrequency());
			if (element.getWhen() != null)
				composeEnumeration("when", element.getWhen(),
						new EventTimingEnumFactory());
			composeDecimal("duration", element.getDuration());
			if (element.getUnits() != null)
				composeEnumeration("units", element.getUnits(),
						new UnitsOfTimeEnumFactory());
			composeInteger("count", element.getCount());
			composeDateTime("end", element.getEnd());
			close();
		}
	}

	private void composeContact(String name, Contact element) throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			if (element.getSystem() != null)
				composeEnumeration("system", element.getSystem(),
						new ContactSystemEnumFactory());
			composeString("value", element.getValue());
			if (element.getUse() != null)
				composeEnumeration("use", element.getUse(),
						new ContactUseEnumFactory());
			composePeriod("period", element.getPeriod());
			close();
		}
	}

	private void composeAddress(String name, Address element) throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			if (element.getUse() != null)
				composeEnumeration("use", element.getUse(),
						new AddressUseEnumFactory());
			composeString("text", element.getText());
			if (element.getLine().size() > 0) {
				openArray("line");
				for (String e : element.getLine())
					composeString(null, e);
				closeArray();
			}
			;
			composeString("city", element.getCity());
			composeString("state", element.getState());
			composeString("zip", element.getZip());
			composeString("country", element.getCountry());
			composePeriod("period", element.getPeriod());
			close();
		}
	}

	private void composeHumanName(String name, HumanName element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			if (element.getUse() != null)
				composeEnumeration("use", element.getUse(),
						new NameUseEnumFactory());
			composeString("text", element.getText());
			if (element.getFamily().size() > 0) {
				openArray("family");
				for (String e : element.getFamily())
					composeString(null, e);
				closeArray();
			}
			;
			if (element.getGiven().size() > 0) {
				openArray("given");
				for (String e : element.getGiven())
					composeString(null, e);
				closeArray();
			}
			;
			if (element.getPrefix().size() > 0) {
				openArray("prefix");
				for (String e : element.getPrefix())
					composeString(null, e);
				closeArray();
			}
			;
			if (element.getSuffix().size() > 0) {
				openArray("suffix");
				for (String e : element.getSuffix())
					composeString(null, e);
				closeArray();
			}
			;
			composePeriod("period", element.getPeriod());
			close();
		}
	}

	private void composeDemographics(String name, Demographics element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			if (element.getName().size() > 0) {
				openArray("name");
				for (HumanName e : element.getName())
					composeHumanName(null, e);
				closeArray();
			}
			;
			if (element.getTelecom().size() > 0) {
				openArray("telecom");
				for (Contact e : element.getTelecom())
					composeContact(null, e);
				closeArray();
			}
			;
			composeCoding("gender", element.getGender());
			composeDateTime("birthDate", element.getBirthDate());
			composeBoolean("deceased", element.getDeceased());
			if (element.getAddress().size() > 0) {
				openArray("address");
				for (Address e : element.getAddress())
					composeAddress(null, e);
				closeArray();
			}
			;
			composeCodeableConcept("maritalStatus", element.getMaritalStatus());
			if (element.getLanguage().size() > 0) {
				openArray("language");
				for (Demographics.Language e : element.getLanguage())
					composeDemographicsLanguage(null, e);
				closeArray();
			}
			;
			close();
		}
	}

	private void composeDemographicsLanguage(String name,
			Demographics.Language element) throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			composeCodeableConcept("language", element.getLanguage());
			composeCodeableConcept("mode", element.getMode());
			composeCodeableConcept("proficiencyLevel",
					element.getProficiencyLevel());
			composeBoolean("preference", element.getPreference());
			close();
		}
	}

	private void composeResourceElements(Resource element) throws Exception {
		composeElement(element);
		if (element.getText() != null)
			composeNarrative("text", element.getText());
		if (element.getContained().size() > 0) {
			openArray("contained");
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
				open(null);
				composeResource(r);
				close();
			}
			closeArray();
		}
	}

	private void composeDevice(String name, Device element) throws Exception {
		if (element != null) {
			open(name);
			composeResourceElements(element);
			composeCodeableConcept("type", element.getType());
			composeString("manufacturer", element.getManufacturer());
			composeString("model", element.getModel());
			composeString("version", element.getVersion());
			composeDeviceIdentity("identity", element.getIdentity());
			composeResourceReference("owner", element.getOwner());
			if (element.getAssignedId().size() > 0) {
				openArray("assignedId");
				for (Identifier e : element.getAssignedId())
					composeIdentifier(null, e);
				closeArray();
			}
			;
			composeResourceReference("location", element.getLocation());
			composeResourceReference("patient", element.getPatient());
			if (element.getContact().size() > 0) {
				openArray("contact");
				for (Contact e : element.getContact())
					composeContact(null, e);
				closeArray();
			}
			;
			composeUri("url", element.getUrl());
			close();
		}
	}

	private void composeDeviceIdentity(String name, Device.Identity element)
			throws Exception {
		if (element != null) {
			open(name);
			composeElement(element);
			composeString("gtin", element.getGtin());
			composeString("lot", element.getLot());
			composeString("serialNumber", element.getSerialNumber());
			composeDate("expiry", element.getExpiry());
			close();
		}
	}

	private void composePatient(String name, Patient element) throws Exception {
		if (element != null) {
			open(name);
			composeResourceElements(element);
			if (element.getLink().size() > 0) {
				openArray("link");
				for (ResourceReference e : element.getLink())
					composeResourceReference(null, e);
				closeArray();
			}
			;
			if (element.getIdentifier().size() > 0) {
				openArray("identifier");
				for (Identifier e : element.getIdentifier())
					composeIdentifier(null, e);
				closeArray();
			}
			;
			composeDemographics("details", element.getDetails());
			composeResourceReference("provider", element.getProvider());
			composeCodeableConcept("diet", element.getDiet());
			composeCodeableConcept("confidentiality",
					element.getConfidentiality());
			composeCodeableConcept("recordLocation",
					element.getRecordLocation());
			close();
		}
	}

	private void composeProvider(String name, Provider element)
			throws Exception {
		if (element != null) {
			open(name);
			composeResourceElements(element);
			if (element.getIdentifier().size() > 0) {
				openArray("identifier");
				for (Identifier e : element.getIdentifier())
					composeIdentifier(null, e);
				closeArray();
			}
			;
			composeDemographics("details", element.getDetails());
			composeResourceReference("organization", element.getOrganization());
			if (element.getRole().size() > 0) {
				openArray("role");
				for (CodeableConcept e : element.getRole())
					composeCodeableConcept(null, e);
				closeArray();
			}
			;
			if (element.getSpecialty().size() > 0) {
				openArray("specialty");
				for (CodeableConcept e : element.getSpecialty())
					composeCodeableConcept(null, e);
				closeArray();
			}
			;
			composePeriod("period", element.getPeriod());
			if (element.getAddress().size() > 0) {
				openArray("address");
				for (Address e : element.getAddress())
					composeAddress(null, e);
				closeArray();
			}
			;
			if (element.getContact().size() > 0) {
				openArray("contact");
				for (Contact e : element.getContact())
					composeContact(null, e);
				closeArray();
			}
			;
			close();
		}
	}

	@Override
	protected void composeResource(Resource resource) throws Exception {
		if (resource instanceof Device)
			composeDevice("Device", (Device) resource);
		else if (resource instanceof Patient)
			composePatient("Patient", (Patient) resource);
		else if (resource instanceof Binary)
			composeBinary("Binary", (Binary) resource);
		else
			throw new Exception("Unhanded resource type "
					+ resource.getClass().getName());
	}

	protected void composeNamedResource(String name, Resource resource)
			throws Exception {
		if (resource instanceof Device)
			composeDevice(name, (Device) resource);
		else if (resource instanceof Patient)
			composePatient(name, (Patient) resource);
		else if (resource instanceof Provider)
			composeProvider(name, (Provider) resource);
		else if (resource instanceof Binary)
			composeBinary(name, (Binary) resource);
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
		else if (type instanceof Coding)
			composeCoding(prefix + "Coding", (Coding) type);
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
		else if (type instanceof DateTime)
			composeDate(prefix + "DateTime", (DateTime) type);
		else if (type instanceof Uri)
			composeUri(prefix + "Uri", (Uri) type);
		else if (type instanceof Id)
			composeId(prefix + "Id", (Id) type);
		else if (type instanceof Base64Binary)
			composeBase64Binary(prefix + "Base64Binary", (Base64Binary) type);
		else if (type instanceof Instant)
			composeInstant(prefix + "Instant", (Instant) type);
		else
			throw new Exception("Unhanded type");
	}

}