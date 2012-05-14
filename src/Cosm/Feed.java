package Cosm;

//import java.awt.Color;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Feed {

	//Title	A descriptive name for the environment	Yes	Yes
	private String title;

	private String errors;
	
	//	The ID of the environment	No	No
	private Integer id;

	//Updated	The time at which this environment was last updated	No	Not directly (will be updated automatically)
	private String updated;
	
	//Creator	A URL referencing the creator of the feed. For feeds produced by Cosm this will be a link to the user who created the feed.	No	Yes (?)
	private String creator;

	//Feed	The URL of the feed for the environment	No	No
	private String feed;
	
	//Status	Whether the feed is considered "live" or not. Value is "live" if the feed has been updated in the last 15 minutes otherwise it is considered "frozen"	No	No
	private Status status;
	
	//Description	A description about the environment	No	No
	private String description;

	//Website	The URL of a website which is relevant to this feed e.g. home page	No	Yes
	private String website;

	//Icon	The URL of an icon which is relevant to this feed	No	Yes
	private String icon;

	private String created;

	//Tags	Tagged metadata about the environment (characters ' " and commas will be stripped out)	No	Yes
	private String[] tags;

	private String version;

	//Location Name	The name of the location	No	Yes
	private Location location;
	
	//Private	Whether the environment is private or not. Can be either 'true' or 'false'	No	Yes
	private Boolean privateFeed;
	
	//User Login	The user who owns the feed	No	No
	//User User Level	Whether the user is a pro level user	No	No
	private User user;

	
	public Feed() {
		version = Cosm.VERSION;
		tags = new String[0];
		datastreams = new Datastream[0];
	}
	
	
	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	
	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	
	//Data	A datastream	No	Yes
	private Datastream[] datastreams;
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Datastream[] getDatastreams() {
		return datastreams;
	}

	public void setDatastreams(Datastream[] datastreams) {
		this.datastreams = datastreams;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFeed() {
		return feed;
	}

	public void setFeed(String feed) {
		this.feed = feed;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean isPrivateFeed() {
		return this.privateFeed;
	}

	public void setPrivate(Boolean privateFeed) {
		this.privateFeed = privateFeed;
	}

	public String getErrors() {
		return errors;
	}

	public void setErrors(String errors) {
		this.errors = errors;
	}

	public JSONObject toJSONObject() throws JSONException {
		JSONObject jo = new JSONObject();
		if ( this.title != null ) {
			jo.put("title",this.title);
		} else {
			throw new JSONException("required element title is missing from feed");
		}
		if ( this.version != null ) {
			jo.put("version", this.version);
		} else {
			throw new JSONException("required element version is missing in feed object");
		}
		jo.putOpt("creator", this.creator);
		jo.putOpt("website", this.website);
		jo.putOpt("icon", this.icon);
		JSONArray ja = new JSONArray();
		for(int i=0;(i<this.tags.length);i++) {
			ja.put(this.tags[i]);
		}
		jo.put("tags",ja);
		if ( this.location != null ) {
			jo.put("location",this.location.toJSONObject());
		}
		JSONArray jda = new JSONArray();
		for(int j=0;(j<datastreams.length);j++) {
			jda.put(datastreams[j].toJSONObject());
		}
		jo.put("datastreams", jda);
		jo.putOpt("private", this.privateFeed);
				
		return jo;
	}
	

}