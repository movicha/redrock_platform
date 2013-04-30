package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import resources.Extension;

import models.Narrative.Image;
import models.Narrative.NarrativeStatus;
import utilities.xhtml.XhtmlNode;

public class NarrativeImpl implements Narrative {
    /**
     * The status of the narrative - whether it's entirely generated (from just the defined data or the extensions too), or whether a human authored it and it may contain additional data
     */
    private Enum<NarrativeStatus> status;

    /**
     * The actual narrative content, a stripped down version of XHTML
     */
    private XhtmlNode div;

    /**
     * An image referred to directly in the xhtml
     */
    private List<Image> image = new ArrayList<Image>();

    public Enum<NarrativeStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enum<NarrativeStatus> value) { 
      this.status = value;
    }

    public NarrativeStatus getStatusSimple() { 
      return this.status == null ? null : this.status.valueOf(NarrativeStatus.class, this.status.name());
    }

    public void setStatusSimple(NarrativeStatus value) { 
      if (value == null)
        this.status = null;
      else
        this.status = value;
    }

    public XhtmlNode getDiv() { 
      return this.div;
    }

    public void setDiv(XhtmlNode value) { 
      this.div = value;
    }

    public List<Image> getImage() { 
      return this.image;
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
