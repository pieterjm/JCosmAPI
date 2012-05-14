package Cosm;

import org.json.JSONException;
import org.json.JSONObject;

public class Datapoint {
	private String at;
	private String value;
	public String getAt() {
		return at;
	}
	public void setAt(String at) {
		this.at = at;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		jo.put("value",value);
		jo.put("at", at);
		return jo;
	}

	
	
}
