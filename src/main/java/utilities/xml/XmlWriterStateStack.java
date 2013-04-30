package utilities.xml;

import java.io.IOException;
import java.util.Vector;

public class XmlWriterStateStack {

	private Vector<XmlWriterState> items = new Vector<XmlWriterState>();

	public int size(){
		return items.size();
	}
	
	public boolean empty(){
		return items.size() == 0;
	}
	
	public XmlWriterState current() throws IOException {
		if (empty())
			throw new IOException("stack is empty trying to get current");
		return items.get(items.size() - 1);
	}
	
	
	public void push(XmlWriterState element) {
		items.add(element);		
	}
		
	public void clear () {
		if (items != null)
		  items.clear();
	}

	public void pop() throws IOException {
		if (empty())
			throw new IOException("stack is empty trying to pop");
		if (current().isInComment())
			throw new IOException("Must close a comment sequence in the element in which it was started");
		
		items.remove(items.size() - 1);		
	}

	public XmlWriterState item(int index) {
		return items.get(index);
	}

	public boolean inComment() {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i).isInComment())
				return true;					
		}
		return false;
	}
}
