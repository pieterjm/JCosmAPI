package Cosm;

import org.json.JSONException;
import org.json.JSONObject;


public class Trigger {
	private String thresholdValue;
	private String user;
	private String notifiedAt;
	private String url;
	private TriggerType type;
	private String id;
	private Integer environment_id;
	private String stream_id;
		
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		
		
		jo.putOpt("threshold_value", this.thresholdValue);
		jo.putOpt("url", this.url);
		if ( type != null ) {
			jo.putOpt("trigger_type", type.toString());
		}
		jo.putOpt("environment_id", environment_id);
		jo.putOpt("stream_id", stream_id);
		jo.putOpt("user", user);
		jo.putOpt("notified_at",notifiedAt);
		jo.putOpt("id", id);
		
		return jo;
	}
	
	
	
	public String getUser() {
		return user;
	}



	public void setUser(String user) {
		this.user = user;
	}



	public String getNotifiedAt() {
		return notifiedAt;
	}



	public void setNotifiedAt(String notifiedAt) {
		this.notifiedAt = notifiedAt;
	}



	public Integer getEnvironmentId() {
		return environment_id;
	}

	public void setEnvironmentId(Integer environment_id) {
		this.environment_id = environment_id;
	}

	public String getStreamId() {
		return stream_id;
	}

	public void setStreamId(String stream_id) {
		this.stream_id = stream_id;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getThresholdValue() {
		return thresholdValue;
	}
	public void setThresholdValue(String thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	public TriggerType getType() {
		return type;
	}
	public void setType(TriggerType type) {
		this.type = type;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}	
}