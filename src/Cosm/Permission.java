package Cosm;

import org.json.JSONException;
import org.json.JSONObject;

public class Permission {
	private String sourceIp;
	private String referer;
	private String minimumInterval;
	private String label;
	private Resource[] resources;
	private AccessMethod[] accessMethods;

	public Permission() {
		resources = new Resource[0];
		accessMethods = new AccessMethod[0];
	}
	
	public String getSourceIp() {
		return sourceIp;
	}
	public void setSourceIp(String sourceIp) {
		this.sourceIp = sourceIp;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public String getMinimumInterval() {
		return minimumInterval;
	}
	public void setMinimumInterval(String minimumInterval) {
		this.minimumInterval = minimumInterval;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Resource[] getResources() {
		return resources;
	}
	public void setResources(Resource[] resources) {
		this.resources = resources;
	}
	public AccessMethod[] getAccessMethods() {
		return accessMethods;
	}
	public void setAccessMethods(AccessMethod[] accessMethods) {
		this.accessMethods = accessMethods;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		
		jo.putOpt("source_ip", this.sourceIp);
		jo.putOpt("referer", this.referer);
		jo.putOpt("minimum_interval", this.minimumInterval);
		jo.putOpt("label", this.label);
		
		for(int i=0;(i<resources.length);i++) {
			jo.append("resources", resources[i].toJSONObject());
		}
		for(int i=0;(i<accessMethods.length);i++) {
			jo.append("access_methods", accessMethods[i].toString());
		}
	
		return jo;
	}
	
}
