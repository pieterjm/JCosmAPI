package Cosm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class Datastream {
	private String current_value;
	private String id;
	private String max_value;
	private String min_value;
	private String[] tags;
	private Unit unit;
	private String at;
	
	public Datastream() {
		tags = new String[0];
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		
		jo.put("current_value", current_value);
		jo.put("id", id);
		jo.putOpt("max_value", max_value);
		jo.putOpt("min_value", min_value);
		JSONArray ja = new JSONArray();
		for(int i=0;(i<this.tags.length);i++) {
			ja.put(this.tags[i]);
		}
		jo.putOpt("tags",ja);
		if ( this.unit != null ) {
			jo.put("unit", this.unit.toJSONObject());
		}
		return jo;
	}
	
	
	public String getAt() {
		return this.at;
	}
	
	public void setAt(String at) {
		this.at = at;
	}
	
	public String getCurrentValue() {
		return current_value;
	}

	public void setCurrentValue(String current_value) {
		this.current_value = current_value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaxValue() {
		return max_value;
	}

	public void setMaxValue(String max_value) {
		this.max_value = max_value;
	}

	public String getMinValue() {
		return min_value;
	}

	public void setMinValue(String min_value) {
		this.min_value = min_value;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}	
}
