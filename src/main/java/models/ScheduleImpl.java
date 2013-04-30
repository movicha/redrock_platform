package models;

import java.util.*;

import resources.Extension;

/**
 * A schedule that specifies an event that may occur multiple times. Schedules are not used for recording when things did happen, but when they are expected or requested to occur.
 */
public class ScheduleImpl implements Schedule {
    /**
     * Identifies specific time periods when the event should occur
     */
    private List<Period> event = new ArrayList<Period>();

    /**
     * Identifies a repeating pattern to the intended time periods. 
     */
    private Repeat repeat;

    public List<Period> getEvent() { 
      return this.event;
    }

    public Repeat getRepeat() { 
      return this.repeat;
    }

    public void setRepeat(Repeat value) { 
      this.repeat = value;
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
