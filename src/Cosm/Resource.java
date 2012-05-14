package Cosm;

import org.json.JSONException;
import org.json.JSONObject;

public class Resource {
	private String feedId;
	private String datastreamId;
	
	public String getFeedId() {
		return feedId;
	}
	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}
	public String getDatastreamId() {
		return datastreamId;
	}
	public void setDatastreamId(String datastreamId) {
		this.datastreamId = datastreamId;
	}	
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		
		if (( datastreamId != null ) && (feedId == null )) {
			throw new JSONException("feedid is not set while datastreamid is set");
		}
		
		jo.putOpt("feed_id",feedId);
		jo.putOpt("datastream_id",datastreamId);
		
		return jo;
	}
	
}
