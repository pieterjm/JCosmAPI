package Cosm;

import org.json.JSONException;
import org.json.JSONObject;

public class Apikey {
	private String label;
	private Boolean privateAccess;
	private Permission[] permissions;
	private String expiresAt;
	private String apikey;
	
	public String getApikey() {
		return apikey;
	}

	public void setApikey(String apikey) {
		this.apikey = apikey;
	}

	public String getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Apikey() {
		permissions = new Permission[0];
	}
	
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public Boolean getPrivateAccess() {
		return privateAccess;
	}
	public void setPrivateAccess(Boolean privateAccess) {
		this.privateAccess = privateAccess;
	}
	public Permission[] getPermissions() {
		return permissions;
	}
	public void setPermissions(Permission[] permissions) {
		this.permissions = permissions;
	}
	
	JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		
		if ( label != null ) {
			jo.put("label",this.label);
		} else {
			throw new JSONException("required element label is null ");
		}
		
		if ( expiresAt != null ) {
			jo.put("expiresAt",expiresAt);
		}
		
		if ( privateAccess != null ) {
			jo.put("private_access", privateAccess.toString());
		}
		
		for(int i=0;(i<permissions.length);i++) {
			jo.append("permissions", permissions[i].toJSONObject());
		}
		
		
		return jo;
	}
	
	
}
