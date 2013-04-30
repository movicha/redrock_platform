package resources;

// A concept that may be defined by a formal reference to a terminology or ontology or may be provided by text
public interface CodeableConcept extends Type {
	private ArrayList coding; //A reference to a code defined by a terminology system. Contains "coding" objects only.
	private String text; //A human language representation of the concept as seen/selected/uttered by the user who entered the data and/or which represents the intended meaning of the user or concept
	private String primary; //Indicates which of the codes in the codings was chosen by a user, if one was chosen directly
}