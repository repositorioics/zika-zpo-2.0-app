package ni.org.ics.zpo.v2.appmovil.parsers;

import org.simpleframework.xml.Element;

public class Meta {
	@Element
	private String instanceID;

	public String getInstanceID() {
		return instanceID;
	}

	public void setInstanceID(String instanceID) {
		this.instanceID = instanceID;
	}

}
