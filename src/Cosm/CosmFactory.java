package Cosm;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class CosmFactory {
	
	
	
	public static Unit toUnit(JSONObject jo) throws JSONException {
		Unit unit = new Unit();
		
		String type = jo.optString("type");
		if ( type != null ) {
			unit.setType(type);
		}
		
		String label = jo.optString("label");
		if ( label != null ) {
			unit.setLabel(label);
		}
		
		String symbol = jo.optString("symbol");
		if ( symbol != null ) {
			unit.setSymbol(symbol);
		}		
		
		return unit;
	
	}
	
	public static User toUser(JSONObject jo) throws JSONException {
		User user = new User();
		
		JSONObject ju = jo.optJSONObject("user");
		if ( ju == null ) {
			throw new JSONException("no user element found in json object");
		}
		
		String first_name = ju.optString("first_name");
		if ( first_name != null ) {
			user.setFirstName(first_name);
		}
		
		String login = ju.optString("login");
		if ( login != null ) {
			user.setLogin(login);
		} else {
			throw new JSONException("required attributed login not found in user");
		}
		
		String last_name = ju.optString("last_name");
		if ( last_name != null ) {
			user.setLastName(last_name);
		}
		
		String email = ju.optString("email");
		if ( email != null ) {
			user.setEmail(email);
		}
		
		JSONArray roles = ju.optJSONArray("roles");
		if ( roles != null ) {
			user.setRoles(CosmFactory.toStringArray(roles));
		} else {
			throw new JSONException("required parameter roles is missing");
		}
		
		String api_key = ju.optString("api_key");
		if ( api_key != null ) {
			user.setApiKey(api_key);
		}
		
		String full_name = ju.optString("full_name");
		if ( full_name != null ) {
			user.setFullName(full_name);
		}
		
		String about = ju.optString("about");
		if ( about != null ) {
			user.setAbout(about);
		}
		
		String deliver_email = ju.optString("deliver_email");
		if ( deliver_email != null ) {
			user.setDeliverEmail(Boolean.valueOf(deliver_email));
		}
		
		String display_activity = ju.optString("display_activity");
		if ( display_activity != null ) {
			user.setDisplayActivity(Boolean.valueOf(display_activity));
		}
		
		String display_information = ju.optString("display_information");
		if ( display_information != null ) {
			user.setDisplayInformation(Boolean.valueOf(display_information));
		}
		
		String display_stats = ju.optString("display_stats");
		if ( display_stats != null ) {
			user.setDisplayStats(Boolean.valueOf(display_stats));
		}
		
		String organisation = ju.optString("organisation");
		if ( organisation != null ) {
			user.setOrganisation(organisation);
		}
		
		String receive_forum_notifications = ju.optString("receive_forum_notifications");
		if ( receive_forum_notifications != null ) {
			user.setReceiveForumNotifications(Boolean.valueOf(receive_forum_notifications));
		}
		

		JSONArray createable_roles = ju.optJSONArray("createable_roles");
		if ( createable_roles != null ) {
			user.setCreateableRoles(CosmFactory.toStringArray(createable_roles));
		}
		
		String subscribed_to_mailings = ju.optString("subscribed_to_mailings");
		if ( subscribed_to_mailings != null ) {
			user.setSubscribedToMailings(Boolean.valueOf(subscribed_to_mailings));
		}
		
		String time_zone = ju.optString("time_zone");
		if ( time_zone != null ) {
			user.setTimeZone(time_zone);
		}
		
		String website = ju.optString("website");
		if ( website != null ) {
			user.setWebsite(website);
		}
				
		return user;
	}
	
	public static Integer[] toIntArray(JSONArray ja) throws JSONException {
		Integer[] list = new Integer[ja.length()];
		for(int i=0;(i<ja.length());i++) {
			list[i] = ja.getInt(i);
		}
		return list;
	}
	
	public static String[] toStringArray(JSONArray ja) throws JSONException {
		String[] list = new String[ja.length()];
		for(int i=0;(i<ja.length());i++) {
			list[i] = ja.getString(i);
		}
		return list;		
	}
	
	public static Datastream toDatastream(String s) throws CosmException {
		try {
			return toDatastream(new JSONObject(s));
		} catch ( Exception e ) {
			throw new CosmException(e.getMessage());
		}
	}
	
	public static Datastream toDatastream(JSONObject jo) throws JSONException {
		Datastream datastream = new Datastream();
		
		String current_value = jo.optString("current_value");
		if ( current_value != null ) {
			datastream.setCurrentValue(current_value);
		} else {
			throw new JSONException("required parameter current_value is missing from datastream");
		}
		
		String id = jo.optString("id");
		if ( id != null ) {
			datastream.setId(id);
		} else {
			throw new JSONException("Required parameter id is missing from datastream");
		}
		
		String maxvalue = jo.optString("max_value");
		if ( maxvalue != null ) {
			datastream.setMaxValue(maxvalue);	
		}
		
		String minvalue = jo.optString("min_value");
		if ( minvalue != null ) {
			datastream.setMinValue(minvalue);	
		}
		
		JSONArray ja = jo.optJSONArray("tags");
		if ( ja != null ) {
			datastream.setTags(CosmFactory.toStringArray(ja));
		}
		
		JSONObject ju = jo.optJSONObject("unit");
		if ( ju != null ) {
			datastream.setUnit(CosmFactory.toUnit(ju));
		}
		
		String at = jo.optString("at");
		if ( at != null ) {
			datastream.setAt(at);
		}
		
		return datastream;
	}
	
	
	public static Location toLocation(JSONObject jo) throws JSONException {
		Location location = new Location();
		
		String disposition = jo.optString("disposition");
		if (( disposition != null )&&(disposition.length()>0)) {
			location.setDisposition(Disposition.valueOf(disposition));
		}
	
		String ele = jo.optString("ele");
		if ( ele != null ) {
			location.setElevation(ele);
		}
		
		String name = jo.optString("name");
		if ( name != null ) {
			location.setName(name);
		}
		
		String lat = jo.optString("lat");
		if ( lat != null ) {
			location.setLat(lat);
		}
		
		String exposure = jo.optString("exposure");
		if (( exposure != null )&&(exposure.length()>0)) {
			location.setExposure(Exposure.valueOf(exposure));
		}
		
		String lon = jo.optString("lon");
		if ( lon != null ) {
			location.setLon(lon);
		}
		
		String domain = jo.optString("domain");
		if ( domain != null ) {
			location.setDomain(Domain.valueOf(domain));
		}

		return location;
	}
	
	public static Datastream[] toDatastreams(JSONArray ja) throws JSONException 
	{
		ArrayList<Datastream> dl = new ArrayList<Datastream>();
		for(int i=0;(i<ja.length());i++) {
			JSONObject jo = ja.getJSONObject(i);
			
			dl.add(CosmFactory.toDatastream(jo));
		}
		return dl.toArray(new Datastream[0]);
	}
	
	public static Feed toFeed(String s) throws CosmException {
		try {
			JSONObject jo = new JSONObject(s);
			return toFeed(jo);
		} catch ( Exception e ) {
			System.err.println("'" + s + "'");
			e.printStackTrace();
			throw new CosmException(e.getMessage());
		}
	}
	
	public static Feed toFeed(JSONObject jo) throws JSONException {
		Feed feed = new Feed();
		
		String title = jo.optString("title");
		if ( title != null ) {
			feed.setTitle(title);
		} else {
			throw new JSONException("title missing from Feed");
		}
		
		String errors = jo.optString("errors");
		if ( errors != null ) {
			feed.setErrors(errors);
		}
		
		Integer id = jo.optInt("id");
		if ( id != null ) {
			feed.setId(id);
		} else {
			throw new JSONException("id is missing in feed json object");
		}

		String updated = jo.optString("updated");
		if ( updated != null ) {
			feed.setUpdated(updated);
		}

		String creator = jo.optString("creator");
		if ( creator != null ) {
			feed.setCreator(creator);
		}		

		String feedUrl = jo.optString("feed");
		if ( feedUrl != null ) {
			feed.setFeed(feedUrl);
		}
		
		String status = jo.optString("status");
		if ( status != null ) {
			feed.setStatus(Status.valueOf(status));
		}
		
		String description = jo.optString("description");
		if ( description != null ) {
			feed.setDescription(description);
		}
		
		String website = jo.optString("website");
		if ( website != null ) {
			feed.setWebsite(website);
		}
		
		String icon = jo.optString("icon");
		if ( icon != null ) {
			feed.setIcon(icon);
		}
		
		JSONArray ja = jo.optJSONArray("tags");
		if ( ja != null ) {
			feed.setTags(CosmFactory.toStringArray(ja));
		}
		
		JSONObject location = jo.optJSONObject("location");
		if ( location != null ) {
			feed.setLocation(CosmFactory.toLocation(location));
		}

		JSONArray datastreams = jo.optJSONArray("datastreams");
		if ( datastreams != null ) {
			feed.setDatastreams(CosmFactory.toDatastreams(datastreams));
		}

		String privateFeed = jo.optString("private");
		if ( privateFeed != null ) {
			feed.setPrivate(Boolean.valueOf(privateFeed));
		}
		
		String created = jo.optString("created");
		if ( created != null ) {
			feed.setCreated(created);
		}
				
		String version = jo.optString("version");
		if ( version != null ) {
			feed.setVersion(version);
		}

		return feed;
		
	}
	
	public static Feed[] toFeeds(String s) throws CosmException {
		try {
			JSONObject jo = new JSONObject(s);
			return CosmFactory.toFeeds(jo.getJSONArray("results"));
		} catch ( Exception e ) {
			e.printStackTrace();
			throw new CosmException(e.getMessage());
			//"Exception in creating feeds from string");
		}
	}
	
	
	public static Feed[] toFeeds(JSONArray ja) throws JSONException {
		ArrayList<Feed> fl = new ArrayList<Feed>();
		for(int i=0;(i<ja.length());i++) {
			JSONObject jo = ja.getJSONObject(i);			
			fl.add(CosmFactory.toFeed(jo));
		}
		return fl.toArray(new Feed[0]);	
	}
	
	public static Group toGroup(JSONObject jo) throws JSONException {
		Group group = new Group();
		
		group.setFeeds(CosmFactory.toIntArray(jo.getJSONArray("feeds")));
		
		group.setMembers(CosmFactory.toStringArray(jo.getJSONArray("members")));
		
		String groupid = jo.optString("group_id");
		if ( groupid != null ) {
			group.setGroupid(groupid);
		} else {
			throw new JSONException("No groupid in JSONObject");
		}
		
		String label = jo.optString("label");
		if ( label != null ) {
			group.setLabel(label);
		} else {
			throw new JSONException("No label in JSONObject");
		}
		
		String owner = jo.optString("owner");
		if ( owner != null ) {
			group.setOwner(owner);
		} else {
			throw new JSONException("No owner in JSONObject");
		}
		
		
		return group;
	}
	
	public static Group[] toGroups(JSONArray ja) throws JSONException {
		ArrayList<Group> gl = new ArrayList<Group>();
		for(int i=0;(i<ja.length());i++) {
			JSONObject jo = ja.getJSONObject(i);
			gl.add(CosmFactory.toGroup(jo));
		}
		return gl.toArray(new Group[0]);
	}
	
	public static Group toGroup(String s) throws CosmException {
		try {
			return toGroup(new JSONObject(s));
		} catch ( Exception e ) {
			throw new CosmException(e.getMessage());
		}
	}
		
	public static Group[] toGroups(String s) throws CosmException {
		try {
			return CosmFactory.toGroups(new JSONArray(s));
		} catch ( Exception e ) {
			e.printStackTrace();
			System.err.println(s);
			throw new CosmException("not imnplemented");
		}
	}

	public static Trigger toTrigger(String s) throws CosmException {
		throw new CosmException("not imnplemented");
	}

	public static Trigger[] toTriggers(String s) throws CosmException {
		throw new CosmException("not imnplemented");
	}
	
}