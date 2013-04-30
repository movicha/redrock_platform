package formats;

import java.math.BigDecimal;
import java.util.Date;

import models.Address;
import models.Address.AddressUse;
import models.Address.AddressUseEnumFactory;
import models.AddressImpl;
import models.CarePlan;
import models.CarePlan.Activity;
import models.CarePlan.Participant;
import models.CarePlan.CarePlanActivityCategory;
import models.CarePlan.CarePlanActivityCategoryEnumFactory;
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
import models.Narrative.Image;
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
import models.Schedule.Repeat;
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
import resources.HumanName.NameUseEnumFactory;
import resources.HumanNameImpl;
import resources.Identifier;
import resources.Identifier.IdentifierUse;
import resources.Identifier.IdentifierUseEnumFactory;
import resources.IdentifierImpl;
import resources.Quantity;
import resources.Quantity.QuantityComparator;
import resources.Quantity.QuantityComparatorEnumFactory;
import resources.QuantityImpl;
import resources.ResourceReference;
import resources.ResourceReferenceImpl;
import resources.Type;
import utilities.Base64Binary;
import utilities.Base64BinaryImpl;
import utilities.Uri;
import utilities.UriImpl;

import org.xmlpull.v1.*;

public class XmlParser extends XmlParserBase {

  private boolean parseElementContent(int eventType, XmlPullParser xpp, Element res) throws Exception {
    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extension")) 
      res.getExtensions().add(parseExtension(xpp));
    else
      return false;
      
    return true;
  }

  @SuppressWarnings("unchecked")
  private <E extends Enum<E>> Enum<E> parseEnumeration(XmlPullParser xpp, E item, EnumFactory e) throws Exception {
    Enum<E> res = null; // new Enum<E>();
    // parseElementAttributes(xpp, res);
    res = (E) e.fromCode(xpp.getAttributeValue(null, "value"));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      // if (!parseElementContent(eventType, xpp, res))
      //   unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Integer parseInteger(XmlPullParser xpp) throws Exception {
    Integer res = new Integer(0);
    // parseElementAttributes(xpp, res);
    res = parseIntegerPrimitive(xpp.getAttributeValue(null, "value"));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      // if (!parseElementContent(eventType, xpp, res))
      //   unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private DateTime parseDateTime(XmlPullParser xpp) throws Exception {
    DateTime res = new DateTime();
    parseElementAttributes(xpp, res);
    res.setValue(parseDateTimePrimitive(xpp.getAttributeValue(null, "value")));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Code parseCode(XmlPullParser xpp) throws Exception {
    Code res = new CodeImpl();
    parseElementAttributes(xpp, res);
    res.setCodeText(parseCodePrimitive(xpp.getAttributeValue(null, "value")));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Date parseDate(XmlPullParser xpp) throws Exception {
    Date res = new Date();
    // parseElementAttributes(xpp, res);
    res = parseDatePrimitive(xpp.getAttributeValue(null, "value"));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      // if (!parseElementContent(eventType, xpp, res))
      //   unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private BigDecimal parseDecimal(XmlPullParser xpp) throws Exception {
    BigDecimal res = new BigDecimal(0);
    // parseElementAttributes(xpp, res);
    res = parseDecimalPrimitive(xpp.getAttributeValue(null, "value"));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      // if (!parseElementContent(eventType, xpp, res))
      //   unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Uri parseUri(XmlPullParser xpp) throws Exception {
    Uri res = new UriImpl();
    parseElementAttributes(xpp, res);
    res.setValue(parseUriPrimitive(xpp.getAttributeValue(null, "value")));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Id parseId(XmlPullParser xpp) throws Exception {
    Id res = new IdImpl();
    parseElementAttributes(xpp, res);
    res.setValue(parseIdPrimitive(xpp.getAttributeValue(null, "value")));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Base64Binary parseBase64Binary(XmlPullParser xpp) throws Exception {
    Base64Binary res = new Base64BinaryImpl();
    parseElementAttributes(xpp, res);
    res.setValue(parseBase64BinaryPrimitive(xpp.getAttributeValue(null, "value")));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private String parseString(XmlPullParser xpp) throws Exception {
    String res = new String();
    // parseElementAttributes(xpp, res);
    res = parseStringPrimitive(xpp.getAttributeValue(null, "value"));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      // if (!parseElementContent(eventType, xpp, res))
      //   unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Boolean parseBoolean(XmlPullParser xpp) throws Exception {
    Boolean res = new Boolean(false);
    // parseElementAttributes(xpp, res);
    res = parseBooleanPrimitive(xpp.getAttributeValue(null, "value"));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      // if (!parseElementContent(eventType, xpp, res))
      //   unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Instant parseInstant(XmlPullParser xpp) throws Exception {
    Instant res = new InstantImpl();
    parseElementAttributes(xpp, res);
    res.setValue(parseInstantPrimitive(xpp.getAttributeValue(null, "value")));
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Extension parseExtension(XmlPullParser xpp) throws Exception {
    Extension res = new ExtensionImpl();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mustUnderstand")) {
        res.setMustUnderstand(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && nameIsTypeName(xpp, "value")) {
        res.setValue(parseType("value", xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extension")) {
        eventType = nextNoWhitespace(xpp);
        while (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extension")) {
          res.getExtension().add(parseExtension(xpp));
          xpp.next();
          eventType = nextNoWhitespace(xpp);
        }
        if (eventType != XmlPullParser.END_TAG || !xpp.getName().equals("extension"))
          throw new Exception("XML Error in requestDetails");
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Narrative parseNarrative(XmlPullParser xpp) throws Exception {
    Narrative res = new NarrativeImpl();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, Narrative.NarrativeStatus.Null, new NarrativeStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("div")) {
        res.setDiv(parseXhtml(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("image")) {
        res.getImage().add(parseNarrativeImage(xpp, res));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Narrative.Image parseNarrativeImage(XmlPullParser xpp, Narrative owner) throws Exception {
    Narrative.Image res = new Image();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mimeType")) {
        res.setMimeType(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("content")) {
        res.setContent(parseBase64Binary(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Period parsePeriod(XmlPullParser xpp) throws Exception {
    Period res = new PeriodImpl();
    parseTypeAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("start")) {
        res.setStart(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEnd(parseDateTime(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Coding parseCoding(XmlPullParser xpp) throws Exception {
    Coding res = new CodingImpl();
    parseTypeAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplay(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Quantity parseQuantity(XmlPullParser xpp) throws Exception {
    Quantity res = new QuantityImpl();
    parseTypeAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("comparator")) {
        res.setComparator(parseEnumeration(xpp, Quantity.QuantityComparator.Null, new QuantityComparatorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private ResourceReference parseResourceReference(XmlPullParser xpp) throws Exception {
    ResourceReference res = new ResourceReferenceImpl();
    parseTypeAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("display")) {
        res.setDisplay(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private CodeableConcept parseCodeableConcept(XmlPullParser xpp) throws Exception {
    CodeableConcept res = new CodeableConceptImpl();
    parseTypeAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("coding")) {
        res.getCoding().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("primary")) {
        res.setPrimary(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Identifier parseIdentifier(XmlPullParser xpp) throws Exception {
    Identifier res = new IdentifierImpl();
    parseTypeAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseEnumeration(xpp, Identifier.IdentifierUse.Null, new IdentifierUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("label")) {
        res.setLabel(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("id")) {
        res.setId(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("assigner")) {
        res.setAssigner(parseResourceReference(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

 
  private Schedule parseSchedule(XmlPullParser xpp) throws Exception {
    Schedule res = new ScheduleImpl();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("repeat")) {
        res.setRepeat(parseScheduleRepeat(xpp, res));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Schedule.Repeat parseScheduleRepeat(XmlPullParser xpp, Schedule owner) throws Exception {
    Schedule.Repeat res = new Repeat();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("frequency")) {
        res.setFrequency(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("when")) {
        res.setWhen(parseEnumeration(xpp, Schedule.EventTiming.Null, new EventTimingEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("duration")) {
        res.setDuration(parseDecimal(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("units")) {
        res.setUnits(parseEnumeration(xpp, Schedule.UnitsOfTime.Null, new UnitsOfTimeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("count")) {
        res.setCount(parseInteger(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("end")) {
        res.setEnd(parseDateTime(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Contact parseContact(XmlPullParser xpp) throws Exception {
    Contact res = new ContactImpl();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("system")) {
        res.setSystem(parseEnumeration(xpp, Contact.ContactSystem.Null, new ContactSystemEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("value")) {
        res.setValue(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseEnumeration(xpp, Contact.ContactUse.Null, new ContactUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Address parseAddress(XmlPullParser xpp) throws Exception {
    Address res = new AddressImpl();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseEnumeration(xpp, Address.AddressUse.Null, new AddressUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("line")) {
        res.getLine().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("city")) {
        res.setCity(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("state")) {
        res.setState(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("zip")) {
        res.setZip(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("country")) {
        res.setCountry(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private HumanName parseHumanName(XmlPullParser xpp) throws Exception {
    HumanName res = new HumanNameImpl();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("use")) {
        res.setUse(parseEnumeration(xpp, HumanName.NameUse.Null, new NameUseEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
        res.setText(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("family")) {
        res.getFamily().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("given")) {
        res.getGiven().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prefix")) {
        res.getPrefix().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("suffix")) {
        res.getSuffix().add(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Demographics parseDemographics(XmlPullParser xpp) throws Exception {
    Demographics res = new DemographicsImpl();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.getName().add(parseHumanName(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("telecom")) {
        res.getTelecom().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gender")) {
        res.setGender(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("birthDate")) {
        res.setBirthDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("deceased")) {
        res.setDeceased(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.getAddress().add(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("maritalStatus")) {
        res.setMaritalStatus(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) {
        res.getLanguage().add(parseDemographicsLanguage(xpp, res));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Demographics.Language parseDemographicsLanguage(XmlPullParser xpp, Demographics owner) throws Exception {
    Demographics.Language res = new Language();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) {
        res.setLanguage(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setMode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("proficiencyLevel")) {
        res.setProficiencyLevel(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("preference")) {
        res.setPreference(parseBoolean(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private boolean parseResourceContent(int eventType, XmlPullParser xpp, Resource res) throws Exception {
    if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("extension")) { 
      res.getExtensions().add(parseExtension(xpp));
    } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("language")) { 
      res.setLanguage(parseCode(xpp));
    } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("text")) {
      res.setText(parseNarrative(xpp));
    } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contained")) {
      xpp.next();
      nextNoWhitespace(xpp);
      res.getContained().add(parse(xpp));
      xpp.next();
      nextNoWhitespace(xpp);
      xpp.next();
    } else
      return false;
      
    return true;
  }

  private CarePlan parseCarePlan(XmlPullParser xpp) throws Exception {
    CarePlan res = new CarePlanImpl();
    parseResourceAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.setIdentifier(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("status")) {
        res.setStatus(parseEnumeration(xpp, CarePlan.CarePlanStatus.Null, new CarePlanStatusEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("modified")) {
        res.setModified(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("concern")) {
        res.getConcern().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("participant")) {
        res.getParticipant().add(parseCarePlanParticipant(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("goal")) {
        res.setGoal(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("activity")) {
        res.getActivity().add(parseCarePlanActivity(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private CarePlan.Participant parseCarePlanParticipant(XmlPullParser xpp, CarePlan owner) throws Exception {
    CarePlan.Participant res = new Participant();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.setRole(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("member")) {
        res.setMember(parseResourceReference(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private CarePlan.Activity parseCarePlanActivity(XmlPullParser xpp, CarePlan owner) throws Exception {
    CarePlan.Activity res = new Activity();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("category")) {
        res.setCategory(parseEnumeration(xpp, CarePlan.CarePlanActivityCategory.Null, new CarePlanActivityCategoryEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("prohibited")) {
        res.setProhibited(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("schedule")) {
        res.setSchedule(parseSchedule(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("performer")) {
        res.getPerformer().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("product")) {
        res.setProduct(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("dailyAmount")) {
        res.setDailyAmount(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("quantity")) {
        res.setQuantity(parseQuantity(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("details")) {
        res.setDetails(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("action")) {
        res.getAction().add(parseResourceReference(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Device parseDevice(XmlPullParser xpp) throws Exception {
    Device res = new DeviceImpl();
    parseResourceAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("manufacturer")) {
        res.setManufacturer(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("model")) {
        res.setModel(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identity")) {
        res.setIdentity(parseDeviceIdentity(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("owner")) {
        res.setOwner(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("assignedId")) {
        res.getAssignedId().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("location")) {
        res.setLocation(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("patient")) {
        res.setPatient(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseContact(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Device.Identity parseDeviceIdentity(XmlPullParser xpp, Device owner) throws Exception {
    Device.Identity res = new Identity();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("gtin")) {
        res.setGtin(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("lot")) {
        res.setLot(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("serialNumber")) {
        res.setSerialNumber(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("expiry")) {
        res.setExpiry(parseDateTime(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance parseConformance(XmlPullParser xpp) throws Exception {
    Conformance res = new ConformanceImpl();
    parseResourceAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("date")) {
        res.setDate(parseDateTime(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("publisher")) {
        res.setPublisher(parseConformancePublisher(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("implementation")) {
        res.setImplementation(parseConformanceImplementation(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("software")) {
        res.setSoftware(parseConformanceSoftware(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("proposal")) {
        res.setProposal(parseConformanceProposal(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseId(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("acceptUnknown")) {
        res.setAcceptUnknown(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("rest")) {
        res.getRest().add(parseConformanceRest(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("messaging")) {
        res.getMessaging().add(parseConformanceMessaging(xpp, res));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("document")) {
        res.getDocument().add(parseConformanceDocument(xpp, res));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Publisher parseConformancePublisher(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Publisher res = new Conformance.Publisher();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.setAddress(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseContact(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Implementation parseConformanceImplementation(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Implementation res = new Conformance.Implementation();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("url")) {
        res.setUrl(parseUri(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Software parseConformanceSoftware(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Software res = new Conformance.Software();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("version")) {
        res.setVersion(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("releaseDate")) {
        res.setReleaseDate(parseDateTime(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Proposal parseConformanceProposal(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Proposal res = new Conformance.Proposal();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("description")) {
        res.setDescription(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Rest parseConformanceRest(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Rest res = new Conformance.Rest();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setMode(parseEnumeration(xpp, Conformance.RestfulConformanceMode.Null, new Conformance.RestfulConformanceModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("resource")) {
        res.getResource().add(parseConformanceResource(xpp, owner));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Resource parseConformanceResource(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Resource res = new Conformance.Resource();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("operation")) {
        res.getOperation().add(parseConformanceOperation(xpp, owner));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("history")) {
        res.setHistory(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("searchParam")) {
        res.getSearchParam().add(parseConformanceSearchParam(xpp, owner));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Operation parseConformanceOperation(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Operation res = new Conformance.Operation();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseEnumeration(xpp, Conformance.RestfulOperation.Null, new Conformance.RestfulOperationEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.SearchParam parseConformanceSearchParam(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.SearchParam res = new Conformance.SearchParam();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("name")) {
        res.setName(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("source")) {
        res.setSource(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("type")) {
        res.setType(parseEnumeration(xpp, Conformance.SearchParamType.Null, new Conformance.SearchParamTypeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("repeats")) {
        res.setRepeats(parseEnumeration(xpp, Conformance.SearchRepeatBehavior.Null, new Conformance.SearchRepeatBehaviorEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Messaging parseConformanceMessaging(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Messaging res = new Conformance.Messaging();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("endpoint")) {
        res.setEndpoint(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("event")) {
        res.getEvent().add(parseConformanceEvent(xpp, owner));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Event parseConformanceEvent(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Event res = new Conformance.Event();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("code")) {
        res.setCode(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setMode(parseEnumeration(xpp, Conformance.MessageConformanceEventMode.Null, new Conformance.MessageConformanceEventModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("protocol")) {
        res.getProtocol().add(parseCoding(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("focus")) {
        res.setFocus(parseCode(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("request")) {
        res.setRequest(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("response")) {
        res.setResponse(parseUri(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Conformance.Document parseConformanceDocument(XmlPullParser xpp, Conformance owner) throws Exception {
    Conformance.Document res = new Conformance.Document();
    parseElementAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("mode")) {
        res.setMode(parseEnumeration(xpp, Conformance.DocumentMode.Null, new Conformance.DocumentModeEnumFactory()));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("documentation")) {
        res.setDocumentation(parseString(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("profile")) {
        res.setProfile(parseUri(xpp));
      } else if (!parseElementContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Patient parsePatient(XmlPullParser xpp) throws Exception {
    Patient res = new PatientImpl();
    parseResourceAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("link")) {
        res.getLink().add(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("active")) {
        res.setActive(parseBoolean(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("details")) {
        res.setDetails(parseDemographics(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("provider")) {
        res.setProvider(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("diet")) {
        res.setDiet(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("confidentiality")) {
        res.setConfidentiality(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("recordLocation")) {
        res.setRecordLocation(parseCodeableConcept(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

  private Provider parseProvider(XmlPullParser xpp) throws Exception {
    Provider res = new ProviderImpl();
    parseResourceAttributes(xpp, res);
    xpp.next();
    int eventType = nextNoWhitespace(xpp);
    while (eventType != XmlPullParser.END_TAG) {
      if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("identifier")) {
        res.getIdentifier().add(parseIdentifier(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("details")) {
        res.setDetails(parseDemographics(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("organization")) {
        res.setOrganization(parseResourceReference(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("role")) {
        res.getRole().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("specialty")) {
        res.getSpecialty().add(parseCodeableConcept(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("period")) {
        res.setPeriod(parsePeriod(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("address")) {
        res.getAddress().add(parseAddress(xpp));
      } else if (eventType == XmlPullParser.START_TAG && xpp.getName().equals("contact")) {
        res.getContact().add(parseContact(xpp));
      } else if (!parseResourceContent(eventType, xpp, res))
        unknownContent(xpp);
      eventType = nextNoWhitespace(xpp);
    }
    xpp.next();
    return res;
  }

    @Override
  protected Resource parseResource(XmlPullParser xpp) throws Exception {
    if (xpp.getName().equals("CarePlan"))
      return parseCarePlan(xpp);
    else if (xpp.getName().equals("Device"))
      return parseDevice(xpp);
    else if (xpp.getName().equals("Conformance"))
      return parseConformance(xpp);
    else if (xpp.getName().equals("Patient"))
      return parsePatient(xpp);
    else if (xpp.getName().equals("Provider"))
      return parseProvider(xpp);
    throw new Exception("Unknown resource type "+xpp.getName()+"");
  }

  protected Type parseType(String prefix, XmlPullParser xpp) throws Exception {
    if (xpp.getName().equals(prefix+"Period"))
      return parsePeriod(xpp);
    else if (xpp.getName().equals(prefix+"Coding"))
      return parseCoding(xpp);
    else if (xpp.getName().equals(prefix+"Quantity"))
      return parseQuantity(xpp);
    else if (xpp.getName().equals(prefix+"ResourceReference"))
      return parseResourceReference(xpp);
    else if (xpp.getName().equals(prefix+"CodeableConcept"))
      return parseCodeableConcept(xpp);
    else if (xpp.getName().equals(prefix+"Identifier"))
      return parseIdentifier(xpp);
    else if (xpp.getName().equals(prefix+"Schedule"))
      return parseSchedule(xpp);
    else if (xpp.getName().equals(prefix+"Contact"))
      return parseContact(xpp);
    else if (xpp.getName().equals(prefix+"Address"))
      return parseAddress(xpp);
    else if (xpp.getName().equals(prefix+"HumanName"))
      return parseHumanName(xpp);
    else if (xpp.getName().equals(prefix+"Demographics"))
      return parseDemographics(xpp);
    else if (xpp.getName().equals(prefix+"DateTime"))
      return parseDateTime(xpp);
    else if (xpp.getName().equals(prefix+"Code"))
      return parseCode(xpp);
    else if (xpp.getName().equals(prefix+"Uri"))
      return parseUri(xpp);
    else if (xpp.getName().equals(prefix+"Id"))
      return parseId(xpp);
    else if (xpp.getName().equals(prefix+"Base64Binary"))
      return parseBase64Binary(xpp);
    else if (xpp.getName().equals(prefix+"Instant"))
      return parseInstant(xpp);
    throw new Exception("Unknown type "+xpp.getName());
  }

  public Element parseFragment(XmlPullParser xpp, String type) throws Exception {
    if (type.equals("Extension"))
      return parseExtension(xpp);
    else if (type.equals("Narrative"))
      return parseNarrative(xpp);
    else if (type.equals("Period"))
      return parsePeriod(xpp);
    else if (type.equals("Coding"))
      return parseCoding(xpp);
    else if (type.equals("Quantity"))
      return parseQuantity(xpp);
    else if (type.equals("ResourceReference"))
      return parseResourceReference(xpp);
    else if (type.equals("CodeableConcept"))
      return parseCodeableConcept(xpp);
    else if (type.equals("Identifier"))
      return parseIdentifier(xpp);
    else if (type.equals("Schedule"))
      return parseSchedule(xpp);
    else if (type.equals("Contact"))
      return parseContact(xpp);
    else if (type.equals("Address"))
      return parseAddress(xpp);
    else if (type.equals("HumanName"))
      return parseHumanName(xpp);
    else if (type.equals("Demographics"))
      return parseDemographics(xpp);
    else if (type.equals("CarePlan"))
      return parseCarePlan(xpp);
    else if (type.equals("Device"))
      return parseDevice(xpp);
    else if (type.equals("Conformance"))
      return parseConformance(xpp);
    else if (type.equals("Patient"))
      return parsePatient(xpp);
    else if (type.equals("Provider"))
      return parseProvider(xpp);
    else if (type.equals("dateTime"))
      return parseDateTime(xpp);
    else if (type.equals("code"))
      return parseCode(xpp);
    else if (type.equals("uri"))
      return parseUri(xpp);
    else if (type.equals("id"))
      return parseId(xpp);
    else if (type.equals("base64Binary"))
      return parseBase64Binary(xpp);
    else if (type.equals("instant"))
      return parseInstant(xpp);
    throw new Exception("Unknown type "+type);
  }

  private boolean nameIsTypeName(XmlPullParser xpp, String prefix) {
        if (xpp.getName().equals(prefix+"Period"))
      return true;
    if (xpp.getName().equals(prefix+"Coding"))
      return true;
    if (xpp.getName().equals(prefix+"Quantity"))
      return true;
    if (xpp.getName().equals(prefix+"ResourceReference"))
      return true;
    if (xpp.getName().equals(prefix+"CodeableConcept"))
      return true;
    if (xpp.getName().equals(prefix+"Identifier"))
      return true;
    if (xpp.getName().equals(prefix+"Schedule"))
      return true;
    if (xpp.getName().equals(prefix+"Contact"))
      return true;
    if (xpp.getName().equals(prefix+"Address"))
      return true;
    if (xpp.getName().equals(prefix+"HumanName"))
      return true;
    if (xpp.getName().equals(prefix+"Demographics"))
      return true;
    if (xpp.getName().equals(prefix+"CarePlan"))
      return true;
    if (xpp.getName().equals(prefix+"Device"))
      return true;
    if (xpp.getName().equals(prefix+"Conformance"))
      return true;
    if (xpp.getName().equals(prefix+"Patient"))
      return true;
    if (xpp.getName().equals(prefix+"Provider"))
      return true;
    if (xpp.getName().equals(prefix+"Integer"))
      return true;
    if (xpp.getName().equals(prefix+"DateTime"))
      return true;
    if (xpp.getName().equals(prefix+"Code"))
      return true;
    if (xpp.getName().equals(prefix+"Date"))
      return true;
    if (xpp.getName().equals(prefix+"Decimal"))
      return true;
    if (xpp.getName().equals(prefix+"Uri"))
      return true;
    if (xpp.getName().equals(prefix+"Id"))
      return true;
    if (xpp.getName().equals(prefix+"Base64Binary"))
      return true;
    if (xpp.getName().equals(prefix+"String"))
      return true;
    if (xpp.getName().equals(prefix+"Boolean"))
      return true;
    if (xpp.getName().equals(prefix+"Instant"))
      return true;
    return false;
  }
}
