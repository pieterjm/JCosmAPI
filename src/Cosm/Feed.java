package Cosm;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Feed {
	protected String description;
	protected String feed;
	protected Integer id;
	protected Location location;
	protected String title;
	private String errors;
	private String updated;
	private String creator; //
	private Status status;	
	private String website; //
	private String icon; //
	private String created;
	private String[] tags; //
	private String version; //
	private Boolean privateFeed; //
	private User user;
	private Datastream[] datastreams; //
	private String email;

	@Override
	public String toString() {
		return this.title;
	}
	
	
	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFeed() {
		return feed;
	}

	public void setFeed(String feed) {
		this.feed = feed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getPrivateFeed() {
		return privateFeed;
	}

	public void setPrivateFeed(Boolean privateFeed) {
		this.privateFeed = privateFeed;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


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

	
	
	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
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
	
		jo.putOpt("description", description);
		jo.putOpt("feed", feed);
		//jo.putOpt("id", id);
		if ( location != null ) {
			jo.put("location", location.toJSONObject());
		}
	
		if ( title != null ) {
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
		JSONArray jda = new JSONArray();
		for(int j=0;(j<datastreams.length);j++) {
			jda.put(datastreams[j].toJSONObject());
		}
		jo.put("datastreams", jda); //
		jo.putOpt("private", this.privateFeed); 
		jo.putOpt("email", this.email);
		return jo;
	}
	
}