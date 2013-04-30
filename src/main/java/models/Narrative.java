package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resources.Code;
import resources.CodeImpl;
import resources.Element;
import resources.EnumFactory;
import resources.Extension;
import utilities.Base64Binary;
import utilities.Base64BinaryImpl;
import utilities.xhtml.XhtmlNode;

// Uses MySQL and Cassandra as backing data store
// Needs two implememations - one to play.db.jpa.Model, one to play.db.nosql.Model
public interface Narrative extends Element {
	public enum NarrativeStatus {
		generated, // The contents of the narrative are entirely generated from
					// the structured data in the resource.
		extensions, // The contents of the narrative are entirely generated from
					// the structured data in the resource and some of the
					// content is generated from extensions
		additional, // The contents of the narrative contain additional
					// information not found in the structured data
		empty, // the contents of the narrative are some equivalent of
				// "No human readable text provided for this resource"
		Null; // added to help the parsers
		public static NarrativeStatus fromCode(String codeString)
				throws Exception {
			if (codeString == null || "".equals(codeString))
				return null;
			if ("generated".equals(codeString))
				return generated;
			if ("extensions".equals(codeString))
				return extensions;
			if ("additional".equals(codeString))
				return additional;
			if ("empty".equals(codeString))
				return empty;
			throw new Exception("Unknown NarrativeStatus code '" + codeString
					+ "'");
		}

		public String toCode() {
			switch (this) {
			case generated:
				return "generated";
			case extensions:
				return "extensions";
			case additional:
				return "additional";
			case empty:
				return "empty";
			default:
				return "?";
			}
		}
	}

	public class NarrativeStatusEnumFactory implements EnumFactory {
		public Enum<?> fromCode(String codeString) throws Exception {
			if (codeString == null || "".equals(codeString))
				if (codeString == null || "".equals(codeString))
					return null;
			if ("generated".equals(codeString))
				return NarrativeStatus.generated;
			if ("extensions".equals(codeString))
				return NarrativeStatus.extensions;
			if ("additional".equals(codeString))
				return NarrativeStatus.additional;
			if ("empty".equals(codeString))
				return NarrativeStatus.empty;
			throw new Exception("Unknown NarrativeStatus code '" + codeString
					+ "'");
		}

		public String toCode(Enum<?> code) throws Exception {
			if (code == NarrativeStatus.generated)
				return "generated";
			if (code == NarrativeStatus.extensions)
				return "extensions";
			if (code == NarrativeStatus.additional)
				return "additional";
			if (code == NarrativeStatus.empty)
				return "empty";
			return "?";
		}
	}

	public class Image implements Element {
		/**
		 * Mime type of image
		 */
		private Code mimeType;

		/**
		 * base64 image data
		 */
		private Base64Binary content;

		public Code getMimeType() {
			return this.mimeType;
		}

		public void setMimeType(Code value) {
			this.mimeType = value;
		}

		public String getMimeTypeSimple() {
			return this.mimeType == null ? null : this.mimeType.getCodeText();
		}

		public void setMimeTypeSimple(String value) {
			if (value == null)
				this.mimeType = null;
			else {
				if (this.mimeType == null)
					this.mimeType = new CodeImpl();
				this.mimeType.setCodeText(value);
			}
		}

		public Base64Binary getContent() {
			return this.content;
		}

		public void setContent(Base64Binary value) {
			this.content = value;
		}

		public byte[] getContentSimple() {
			return this.content == null ? null : this.content.getValue();
		}

		public void setContentSimple(byte[] value) {
			if (value == null)
				this.content = null;
			else {
				if (this.content == null)
					this.content = new Base64BinaryImpl();
				this.content.setValue(value);
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

	public Enum<NarrativeStatus> getStatus();
	public void setStatus(Enum<NarrativeStatus> value);
	public NarrativeStatus getStatusSimple();
	public void setStatusSimple(NarrativeStatus value);
	public XhtmlNode getDiv();
	public void setDiv(XhtmlNode value);
	public List<Image> getImage();
}