package Cosm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Group {
	private String groupid;
	private String label;
	private String[] members;
	private Integer[] feeds;
	private String owner;
	
	@Override
	public String toString() {
		return this.label;
	}
	
	public Group() {
		owner = "";
		groupid = "";
		label = "";
		members = new String[0];
		feeds = new Integer[0];
	}
	
	
	
	public String getOwner() {
		return owner;
	}



	public void setOwner(String owner) {
		this.owner = owner;
	}



	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String[] getMembers() {
		return members;
	}
	public void setMembers(String[] members) {
		this.members = members;
	}
	public Integer[] getFeeds() {
		return feeds;
	}
	public void setFeeds(Integer[] feeds) {
		this.feeds = feeds;
	}
	
	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		
		jo.put("label", this.label);
		JSONArray ja = new JSONArray();
		for(int i=0;(i<members.length);i++) {
			ja.put(members[i]);
		}
		jo.put("members",ja);
		
		JSONArray jf = new JSONArray();
		for(int j=0;(j<feeds.length);j++) {
			jf.put(feeds[j]);
		}
		jo.put("feeds", jf);
		
				
		return jo;
	}
	
}
